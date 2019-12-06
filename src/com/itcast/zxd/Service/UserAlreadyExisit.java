package com.itcast.zxd.Service;

import com.itcast.zxd.Dao.UserDao;
import com.itcast.zxd.domain.User;


/**
 * 	处理用户是否存在的业务处理器
 * 
 * */
public class UserAlreadyExisit {
	private static String emailExist = "SELECT * FROM User WHERE Email=?";
	private static String UsernameExist = "SELECT * FROM User WHERE Username=?";
	private static String telephonelExist = "SELECT * FROM User WHERE Telephone=?";
	
	/**
	 * 	邮箱是否被注册
	 * 
	 * */
	public User emailExist(String email){
		User singleuser = UserDao.UserExist(emailExist, email);
		return singleuser;
	}
	
	/**
	 * 用户名是否已经被注册
	 * 
	 * */
	public User UsernameExist(String username){
		User singleuser = UserDao.UserExist(UsernameExist, username);		
		return singleuser;
	}
	
	/**
	 * 用户名是否已经被注册
	 * 
	 * */
	public User telephoneExist(String telep){
		User singleuser = UserDao.UserExist(telephonelExist, telep);
		return singleuser;
	}
}
