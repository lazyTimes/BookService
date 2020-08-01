package com.itcast.zxd.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


/**
 * 	帮助链接数据库的工具
 *	可以用于获取数据源和数据库的链接
 *	由于在处理事务的时候需要手动创建链接
 *	提供了一些处理事务的方法
 * 
 * */
public class DButil {
	private static DataSource c3p0combo = new ComboPooledDataSource();
	private static ThreadLocal<Connection> thread = new ThreadLocal<Connection>();
	private static Connection conn;
	private static PreparedStatement prepared;
	private static ResultSet result;
	
	/**
	 * 	获取连接池
	 * 
	 * */
	public static DataSource getDataSource(){
		
		return c3p0combo;
	}
	
	/**
	 * 	如果需要手动控制处理事务的时候，可以调用改方法获得连接
	 * 	@throws SQLException 获取连接池失败 
	 * 	@return
	 * */
	public static Connection getConnection() throws SQLException{
		Connection conn = thread.get();
		if(null == conn){
			conn = c3p0combo.getConnection();
			thread.set(conn);
		}
		return conn;
	}
	
	/**
	 * 	可以根据需要的字段数查询数据
	 * 	@param sql 数据库查询语句
	 * 	@param param 参数
	 * */
	public static List excuteQuery(String sql, String[] param){
		List arraylist = new ArrayList();
		try {
			conn = DButil.getConnection();
			prepared = conn.prepareStatement(sql);
			if(param!=null){
				for(int x=0; x<param.length; x++){
					prepared.setObject(x+1, param[x]);
				}
			}
			result = prepared.executeQuery();
			
			ResultSetMetaData metadata = prepared.getMetaData();
			//获取多少列结果
			int columns = metadata.getColumnCount();
			
			while(result.next()){
				Object[] obj = new Object[columns];
				for(int x=0; x<obj.length; x++){
					obj[x] = result.getObject(x+1);
				}
				arraylist.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
			
		}
		
		
		return arraylist;
		
	}
	
	/**
	 * 	开启事务
	 * @throws SQLException 
	 * */
	public static void startTransaction() throws SQLException {
		Connection conn = getConnection();
		//将此连接的自动提交模式设置为给定状态。
		if(conn != null)
			conn.setAutoCommit(false);
	}
	
	/**
	 * 	从本地线程当中释放线程并且关闭Connection的链接，结束事务
	 * @throws SQLException 
	 * */
	public static void releaseAndColseConnetion() throws SQLException{
		Connection conn = getConnection();
		if( null != conn){
			//conn.commit();
			thread.remove();
			conn.close();
		}
	}
	
	/**
	 * 	事务回滚（禁止自动提交的情况之下）
	 * @throws SQLException 
	 * */
	public static void rollback() throws SQLException{
		Connection conn = getConnection();
		if(null != conn)
			conn.rollback();
	}
	
	/**
	 * 插入记录的语句
	 * 	根据填入的字段数将数据插入
	 * 
	 * */
	public static boolean excuteUpdateString(String sql, String[] param){
		boolean flag = true;
		try {
			conn = DButil.getConnection();
			prepared = conn.prepareStatement(sql);
			if(param != null && param.length>0){
				for(int x=0; x<param.length; x++)
					prepared.setObject(x+1, param[x]);
				int result = prepared.executeUpdate();
				if(result != 1)
					flag = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			System.out.println("更新失败");
			flag = false;
			return flag;
		} finally{
			close();
		}
		return flag;
	}
	
	/**
	 * 	提供统一的插入/删除/更新方法[需要考虑事物] 
	 * 	@param sql
	 * 	@param params 
	 * */
	
	public static void executeUpdate(String[] sql, String[][] params){
		
		try{
			conn = DButil.getConnection();
			
			//sql命令的提交由应用程序负责，程序必须调用commit或者rollback方法
			//使用手动方式实现查询语句的具体实现
			conn.setAutoCommit(false);
			
			for(int x=0; x<sql.length; x++){
				if(params[x]!=null){
					prepared = conn.prepareStatement(sql[x]);
				}
				prepared.executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
			//回滚操作
			
			try {
				rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException();
			}
			
		}finally{
			DButil.close();
		}
	
	}
	
	
	/**
	 * 	关闭数据库的必要操作
	 * 
	 * */
	public static void close(){
		if(null != result)
			try {
				result.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		if(null != prepared)
			try {
				prepared.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		try {
			DButil.releaseAndColseConnetion();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void handlerClose(PreparedStatement prepared, ResultSet result){
		if(null != result)
			try {
				result.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		if(null != prepared)
			try {
				prepared.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}
}
