package com.itcast.zxd.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 	�й��Զ���ȫ�� ����
 * 	�Զ���ȫ�����ݿ��ѯ
 * 	ʹ��һ��ģ��ƥ��ķ�ʽȥ�������ݿ��ҵ���Ӧ�����ݽ���ƥ�����
 * 	ʹ����Map���ϣ���Ϊ���������ٶ���Ϊ����
 * */
public class AutoComplete {
	
	/**
	 * 	�������ݿⲢ�Ҽ��뵽map���ϵ���
	 * 
	 * */
	public static Map<String,String> getResource(String find){
		String queryString = "SELECT ProductName FROM product WHERE ProductName LIKE '"+find+"%'";
		Map<String,String> map = new HashMap<String,String>();
		List list = DButil.excuteQuery(queryString, null);
		for(int x=0; x<list.size(); x++){
			Object[] obj = (Object[]) list.get(x);
			String proname = obj[0].toString();
			map.put(proname, proname);
		}
		return map;	
	}
}
