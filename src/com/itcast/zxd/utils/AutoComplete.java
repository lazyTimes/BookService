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
 * 	有关自动补全的 方法
 * 	自动补全的数据库查询
 * 	使用一个模糊匹配的方式去搜索数据库找到对应的数据进行匹配操作
 * 	使用了Map集合，因为这样查找速度最为快速
 * */
public class AutoComplete {
	
	/**
	 * 	查找数据库并且加入到map集合当中
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
