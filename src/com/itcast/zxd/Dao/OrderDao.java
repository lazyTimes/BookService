package com.itcast.zxd.Dao;

import java.util.ArrayList;
import java.util.List;

import com.itcast.zxd.utils.DButil;

public class OrderDao {
	
	/**
	 * 	��ȡ��������ϸ���е�Orderid
	 * 	@return ����Id����
	 */
	private static String QueryOrderId = "SELECT Orderid FROM orders";
	public static List<String> getOrderidFromOrder(){
		List list = DButil.excuteQuery(QueryOrderId, null);
		
		List<String> strlist = new ArrayList<String>();
		//ȡ�����е�Orderid���뵽���ϵ���
		for(int x=0; x<list.size(); x++){
			Object[] obj = (Object[]) list.get(x);
			strlist.add(obj[0].toString());
		}
		return strlist;
	}
	
	
}
