package com.itcast.zxd.Service;

import java.util.List;

import javax.servlet.http.Cookie;

import com.itcast.zxd.Dao.UserDao;
import com.itcast.zxd.domain.User;
import com.itcast.zxd.utils.DButil;

/**
 * 	处理用户登录请求的业务处理器
 * 	后台验证用户是否存在
 * */
public class UserLoginService {
	private static String  findUserQueryString = 
			"SELECT Username,Password,Status,Userid,Role FROM User WHERE Username=? AND Password=?";
	
	/**
	 * 	根据用户名和密码验证用户是否存在
	 * 	@param Username 用户名
	 * 	@param Password 密码
	 * */
	public User findUserByUsernameAndPassword(String Username, String Password){
		String[] param = new String[]{Username, Password};
		List paramlist = DButil.excuteQuery(findUserQueryString, param);
		if(null == paramlist || paramlist.size()==0)
			return null;
		Object[] obj = (Object[]) paramlist.get(0);
		User user = new User();
		user.setUsername(obj[0].toString());
		user.setPassword(obj[1].toString());
		user.setStatus(Integer.parseInt(obj[2].toString()));
		user.setUserid(Integer.parseInt(obj[3].toString()));
		user.setRole(Integer.parseInt(obj[4].toString()));
		return user;
		
	}
	
	/**
	 * 用户选择了记住密码
	 * 创建一个Cookie,默认生存时间为一周
	 * @param username 用户名信息
	 * @return 新的Cookie
	 * 
	 * 	
	 * */
	public Cookie rememberUser(String username){
		Cookie cookie = new Cookie("rememberUsername", username);
		cookie.setMaxAge(60*60*24*7);
		return cookie;
	}
	
	/**
	 * 	用于检查用户自动登录之后对于用户名称和密码的验证
	 * 	因为Cookie里面有多条信息则返回多个Cookie
	 * 	这里时间保存时间暂时设置为1天
	 * */
	public Cookie[] checkUserAutolog(String username, String password){
		User user = findUserByUsernameAndPassword(username, password);
		//设置Cookie之前先验证数据库是否存在该用户
		if(null == user){
			return null;
		}
		Cookie[] cookiearr = new Cookie[]{
				new Cookie("aUsername", username), 
				new Cookie("aPassword", password),
				new Cookie("aUserid", String.valueOf(user.getUserid()))
		};
		cookiearr[0].setMaxAge(60*60*24);
		cookiearr[1].setMaxAge(60*60*24);
		return cookiearr;
		
	}
}
