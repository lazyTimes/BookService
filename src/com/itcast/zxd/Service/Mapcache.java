package com.itcast.zxd.Service;
import java.util.*;
import java.util.Map.Entry;

import com.itcast.zxd.domain.CacheConModel;
import com.itcast.zxd.domain.Order;
/**
 * 	订单对象的缓存类
 * 	用于解决高并发的超发问题
 * 	使用单例设计模式，只允许有一个对象
 * 	同时使用懒汉式，只有需要用到的时候才创建对象
 * 	制作一个缓存
 * 		1.键为订单的Id号码
 * 		2.值为订单对象
 * 		3.查找是否添加了新数据
 * 		4.如果有就取出对应的缓存
 * 		5.没有就添加到缓存并且使用该ID号码
 * 
 * */
public class Mapcache {
	//用于缓存数据的Map集合
	private static Map<String,Object> cacheMap = new HashMap<String,Object>();
	//一个用来定时清理缓存的时间管理集合
	private static Map<String,Object> cacheConMap = new HashMap<String,Object>();
	
	private static Mapcache mc = null;
	
	private Mapcache(){}
	
	public static Mapcache getInstance(){
		if(mc == null){
			mc = new Mapcache();
			//使用一个多线程清空缓存
			Thread t = new ClearCache();
			t.start();
		}
		return mc;
	}
	
	/**
	 * 	添加缓存
	 * 	@return 是否添加成功
	 * */
	public synchronized boolean addCache(String orderid, Order order, CacheConModel com){
		boolean flag = false;
		try{
			cacheMap.put(orderid, order);
			cacheConMap.put(orderid, com);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
		
	}
	
	/**
	 * 	获取缓存实体
	 * 	@return 对应订单Id的订单对象
	 * 	
	 * */
	public synchronized Object getValue(String key){
		Object o = cacheMap.get(key);
		//如果为空则加入到缓存当中并且使用该缓存
		if(null == o)
			return null;
		return o;
		
	}
	
	/**
	 * 	删除一个缓存
	 * 	@return
	 * */
	public boolean removeCache(String key){
		boolean flag = false;
		try{
			cacheMap.remove(key);
			cacheConMap.remove(key);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
		
		
	}
	
	/**
	 * 	使用一个多线程进行清理操作
	 * */
	private static class ClearCache extends Thread{
		ClearCache(){
			setName("清理缓存开始");
		}
		
		@Override
		public void run() {
			Set tempst = new HashSet();
			Set set = cacheConMap.keySet();
			Iterator it = tempst.iterator();
			while(it.hasNext()){
				Object obj = it.next();
				CacheConModel ocm = (CacheConModel) cacheConMap.get(obj);
				//判断是否需要清除该缓存
				if(!ocm.isForever()){
					//如果当前运行的时间大于持续时间
					if((new Date().getTime() - ocm.getBeginTime())
							>= ocm.getDurableTime() * 60 * 1000){
						//记录一下需要清除的键
						tempst.add(obj);
					}
							
				}
			}
			
			//清除操作
			Iterator tempit = tempst.iterator();
			while(tempit.hasNext()){
				Object o = tempit.next();
				cacheMap.remove(o);
				//只允许一个线程进行清理操作
				synchronized (cacheMap) {
					cacheConMap.remove(o);
				}
				
				
			}
			//休息
			try {
				Thread.sleep(60 * 1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
