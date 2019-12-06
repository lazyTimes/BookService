package com.itcast.zxd.Dao;

import com.itcast.zxd.domain.User;
import com.itcast.zxd.utils.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
	private static Connection conn;
	private static PreparedStatement prepared;
	private static ResultSet result;
	
	private static String findUserIdByname = "SELECT Userid FROM User WHERE Username=?";
	
	/**
	 * 	�û������Ƿ����
	 * 	@param param ��Ҫ��֤�Ĳ���
	 * */
	public static User UserExist(String sql,String param){
		User user = null;
		List list = DButil.excuteQuery(sql, new String[]{param});
		if(list == null || list.size()==0){
			return user;
		}
		user = new User();
		return user;
	}
	
	public static String findUserIdByUsername(String username){
		List li = DButil.excuteQuery(findUserIdByname, new String[]{username});
		if(li==null)
			return null;
		Object[] obj = (Object[]) li.get(0);
		User user = new User();
		String id = obj[0].toString();
		return id;
		
	}
	
	/**
	 * 	ע���û�
	 * @throws SQLException ���ܵ����ݿ��������
	 * 
	 * */
	public static int addUser(String sql, User user){
		int result = 0;
		try {
			conn = DButil.getConnection();
			prepared = conn.prepareStatement(sql);
			prepared.setString(1, user.getUsername());
			prepared.setString(2, user.getPassword());
			prepared.setInt(3, user.getGender());
			prepared.setString(4, user.getEmail());
			prepared.setString(5, user.getTelephone());
			prepared.setString(6, user.getRegist_Time());
			prepared.setInt(7, user.getRole());
			//�ڲ������˼�����
			prepared.setString(8, user.getActivecode());
			result = prepared.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{	
			try {
				DButil.releaseAndColseConnetion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return result;
		
	}
	
	
}
