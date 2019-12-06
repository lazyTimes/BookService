package com.itcast.zxd.Service;

import com.itcast.zxd.Dao.UserDao;
import com.itcast.zxd.domain.User;


/**
 * 	�����û��Ƿ���ڵ�ҵ������
 * 
 * */
public class UserAlreadyExisit {
	private static String emailExist = "SELECT * FROM User WHERE Email=?";
	private static String UsernameExist = "SELECT * FROM User WHERE Username=?";
	private static String telephonelExist = "SELECT * FROM User WHERE Telephone=?";
	
	/**
	 * 	�����Ƿ�ע��
	 * 
	 * */
	public User emailExist(String email){
		User singleuser = UserDao.UserExist(emailExist, email);
		return singleuser;
	}
	
	/**
	 * �û����Ƿ��Ѿ���ע��
	 * 
	 * */
	public User UsernameExist(String username){
		User singleuser = UserDao.UserExist(UsernameExist, username);		
		return singleuser;
	}
	
	/**
	 * �û����Ƿ��Ѿ���ע��
	 * 
	 * */
	public User telephoneExist(String telep){
		User singleuser = UserDao.UserExist(telephonelExist, telep);
		return singleuser;
	}
}
