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
 * 	�����������ݿ�Ĺ���
 *	�������ڻ�ȡ����Դ�����ݿ������
 *	�����ڴ��������ʱ����Ҫ�ֶ���������
 *	�ṩ��һЩ��������ķ���
 * 
 * */
public class DButil {
	private static DataSource c3p0combo = new ComboPooledDataSource();
	private static ThreadLocal<Connection> thread = new ThreadLocal<Connection>();
	private static Connection conn;
	private static PreparedStatement prepared;
	private static ResultSet result;
	
	/**
	 * 	��ȡ���ӳ�
	 * 
	 * */
	public static DataSource getDataSource(){
		
		return c3p0combo;
	}
	
	/**
	 * 	�����Ҫ�ֶ����ƴ��������ʱ�򣬿��Ե��øķ����������
	 * 	@throws SQLException ��ȡ���ӳ�ʧ�� 
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
	 * 	���Ը�����Ҫ���ֶ�����ѯ����
	 * 	@param sql ���ݿ��ѯ���
	 * 	@param param ����
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
			//��ȡ�����н��
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
	 * 	��������
	 * @throws SQLException 
	 * */
	public static void startTransaction() throws SQLException {
		Connection conn = getConnection();
		//�������ӵ��Զ��ύģʽ����Ϊ����״̬��
		if(conn != null)
			conn.setAutoCommit(false);
	}
	
	/**
	 * 	�ӱ����̵߳����ͷ��̲߳��ҹر�Connection�����ӣ���������
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
	 * 	����ع�����ֹ�Զ��ύ�����֮�£�
	 * @throws SQLException 
	 * */
	public static void rollback() throws SQLException{
		Connection conn = getConnection();
		if(null != conn)
			conn.rollback();
	}
	
	/**
	 * �����¼�����
	 * 	����������ֶ��������ݲ���
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
			System.out.println("����ʧ��");
			flag = false;
			return flag;
		} finally{
			close();
		}
		return flag;
	}
	
	/**
	 * 	�ṩͳһ�Ĳ���/ɾ��/���·���[��Ҫ��������] 
	 * 	@param sql
	 * 	@param params 
	 * */
	
	public static void executeUpdate(String[] sql, String[][] params){
		
		try{
			conn = DButil.getConnection();
			
			//sql������ύ��Ӧ�ó����𣬳���������commit����rollback����
			//ʹ���ֶ���ʽʵ�ֲ�ѯ���ľ���ʵ��
			conn.setAutoCommit(false);
			
			for(int x=0; x<sql.length; x++){
				if(params[x]!=null){
					prepared = conn.prepareStatement(sql[x]);
				}
				prepared.executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
			//�ع�����
			
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
	 * 	�ر����ݿ�ı�Ҫ����
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
