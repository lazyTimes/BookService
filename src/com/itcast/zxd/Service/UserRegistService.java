package com.itcast.zxd.Service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.zxd.Dao.UserDao;
import com.itcast.zxd.domain.User;
import com.itcast.zxd.utils.DButil;

/**
 * 	处理用户注册业务的处理业务
 * */
public class UserRegistService {
	//'user123', '123456', 0, '1097483508@qq.com', '18229357812', '2018-5-4 20:54:10', 0);
	//更新数据库
	private static String addUserUpdate = 
			"INSERT INTO User(Username, Password, Gender, Email, Telephone, Regist_Time, Role, Activecode) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	
	//根据用户的激活码来获取用户名称
	private static String activeEquals = "SELECT Username FROM User WHERE Activecode=?";
	
	/**
	 * 	用户注册信息插入到数据当中
	 * 	实现插入数据的功能
	 * 	@param usr 用户对象
	 * */
	public boolean completeRegistOpration(User usr){
		int result = UserDao.addUser(addUserUpdate, usr);
		//如果注册成功，返回正确，否则错误
		if(1 == result)
			return true;
		else
			return false;
	}
	
	/**
	 * 	预留：处理用户验证激活码
	 * 	@param activeCode 用户激活码
	 * */
	public String completeRegistActiveCode(String activeCode){
		//修改用户是否激活按钮
		
		List list = DButil.excuteQuery(activeEquals, new String[]{activeCode});
		Object[] obj = (Object[]) list.get(0);
		//取出用户名信息
		String activeCodes = (String) obj[0];
		return activeCodes;
		
	}

}
