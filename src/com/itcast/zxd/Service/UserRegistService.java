package com.itcast.zxd.Service;

import java.sql.SQLException;
import java.util.List;

import com.itcast.zxd.Dao.UserDao;
import com.itcast.zxd.domain.User;
import com.itcast.zxd.utils.DButil;

/**
 * 	�����û�ע��ҵ��Ĵ���ҵ��
 * */
public class UserRegistService {
	//'user123', '123456', 0, '1097483508@qq.com', '18229357812', '2018-5-4 20:54:10', 0);
	//�������ݿ�
	private static String addUserUpdate = 
			"INSERT INTO User(Username, Password, Gender, Email, Telephone, Regist_Time, Role, Activecode) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	
	//�����û��ļ���������ȡ�û�����
	private static String activeEquals = "SELECT Username FROM User WHERE Activecode=?";
	
	/**
	 * 	�û�ע����Ϣ���뵽���ݵ���
	 * 	ʵ�ֲ������ݵĹ���
	 * 	@param usr �û�����
	 * */
	public boolean completeRegistOpration(User usr){
		int result = UserDao.addUser(addUserUpdate, usr);
		//���ע��ɹ���������ȷ���������
		if(1 == result)
			return true;
		else
			return false;
	}
	
	/**
	 * 	Ԥ���������û���֤������
	 * 	@param activeCode �û�������
	 * */
	public String completeRegistActiveCode(String activeCode){
		//�޸��û��Ƿ񼤻ť
		
		List list = DButil.excuteQuery(activeEquals, new String[]{activeCode});
		Object[] obj = (Object[]) list.get(0);
		//ȡ���û�����Ϣ
		String activeCodes = (String) obj[0];
		return activeCodes;
		
	}

}
