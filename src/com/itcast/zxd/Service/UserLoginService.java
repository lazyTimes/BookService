package com.itcast.zxd.Service;

import java.util.List;

import javax.servlet.http.Cookie;

import com.itcast.zxd.Dao.UserDao;
import com.itcast.zxd.domain.User;
import com.itcast.zxd.utils.DButil;

/**
 * 	�����û���¼�����ҵ������
 * 	��̨��֤�û��Ƿ����
 * */
public class UserLoginService {
	private static String  findUserQueryString = 
			"SELECT Username,Password,Status,Userid,Role FROM User WHERE Username=? AND Password=?";
	
	/**
	 * 	�����û�����������֤�û��Ƿ����
	 * 	@param Username �û���
	 * 	@param Password ����
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
	 * �û�ѡ���˼�ס����
	 * ����һ��Cookie,Ĭ������ʱ��Ϊһ��
	 * @param username �û�����Ϣ
	 * @return �µ�Cookie
	 * 
	 * 	
	 * */
	public Cookie rememberUser(String username){
		Cookie cookie = new Cookie("rememberUsername", username);
		cookie.setMaxAge(60*60*24*7);
		return cookie;
	}
	
	/**
	 * 	���ڼ���û��Զ���¼֮������û����ƺ��������֤
	 * 	��ΪCookie�����ж�����Ϣ�򷵻ض��Cookie
	 * 	����ʱ�䱣��ʱ����ʱ����Ϊ1��
	 * */
	public Cookie[] checkUserAutolog(String username, String password){
		User user = findUserByUsernameAndPassword(username, password);
		//����Cookie֮ǰ����֤���ݿ��Ƿ���ڸ��û�
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
