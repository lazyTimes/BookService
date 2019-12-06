package com.itcast.zxd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.itcast.zxd.domain.User;

/**
 * 	����װ��javaBean��һ������
 * 
 * */
public class BeanUtil {
	private static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 	����װ�����
	 * 	
	 * 
	 * */
	public static void polular(User obj, Map<String, String[]> property) {
		String mail = property.get("mail")[0];
		String username = property.get("username")[0];
		String userpassword = property.get("repassword")[0];
		String gender = property.get("gender")[0];
		String telephone = property.get("telephone")[0];
		String password = property.get("userpassword")[0];
		
		obj.setEmail(mail);
		obj.setUsername(username);
		obj.setPassword(userpassword);
		obj.setGender(Integer.parseInt(gender));
		obj.setTelephone(telephone);
		obj.setPassword(password);
		obj.setRole(0);
		
		String formattime = date.format(new Date());
		obj.setRegist_Time(formattime);
	}
}
