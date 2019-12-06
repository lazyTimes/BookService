package com.itcast.zxd.Dao;

import java.util.ArrayList;
import java.util.List;

import com.itcast.zxd.utils.DButil;

public class OrderDao {
	
	/**
	 * 	获取到订单详细表中的Orderid
	 * 	@return 订单Id号码
	 */
	private static String QueryOrderId = "SELECT Orderid FROM orders";
	public static List<String> getOrderidFromOrder(){
		List list = DButil.excuteQuery(QueryOrderId, null);
		
		List<String> strlist = new ArrayList<String>();
		//取出所有的Orderid加入到集合当中
		for(int x=0; x<list.size(); x++){
			Object[] obj = (Object[]) list.get(x);
			strlist.add(obj[0].toString());
		}
		return strlist;
	}
	
	
}
