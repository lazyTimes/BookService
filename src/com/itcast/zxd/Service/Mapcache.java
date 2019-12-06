package com.itcast.zxd.Service;
import java.util.*;
import java.util.Map.Entry;

import com.itcast.zxd.domain.CacheConModel;
import com.itcast.zxd.domain.Order;
/**
 * 	��������Ļ�����
 * 	���ڽ���߲����ĳ�������
 * 	ʹ�õ������ģʽ��ֻ������һ������
 * 	ͬʱʹ������ʽ��ֻ����Ҫ�õ���ʱ��Ŵ�������
 * 	����һ������
 * 		1.��Ϊ������Id����
 * 		2.ֵΪ��������
 * 		3.�����Ƿ������������
 * 		4.����о�ȡ����Ӧ�Ļ���
 * 		5.û�о���ӵ����沢��ʹ�ø�ID����
 * 
 * */
public class Mapcache {
	//���ڻ������ݵ�Map����
	private static Map<String,Object> cacheMap = new HashMap<String,Object>();
	//һ��������ʱ�������ʱ�������
	private static Map<String,Object> cacheConMap = new HashMap<String,Object>();
	
	private static Mapcache mc = null;
	
	private Mapcache(){}
	
	public static Mapcache getInstance(){
		if(mc == null){
			mc = new Mapcache();
			//ʹ��һ�����߳���ջ���
			Thread t = new ClearCache();
			t.start();
		}
		return mc;
	}
	
	/**
	 * 	��ӻ���
	 * 	@return �Ƿ���ӳɹ�
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
	 * 	��ȡ����ʵ��
	 * 	@return ��Ӧ����Id�Ķ�������
	 * 	
	 * */
	public synchronized Object getValue(String key){
		Object o = cacheMap.get(key);
		//���Ϊ������뵽���浱�в���ʹ�øû���
		if(null == o)
			return null;
		return o;
		
	}
	
	/**
	 * 	ɾ��һ������
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
	 * 	ʹ��һ�����߳̽����������
	 * */
	private static class ClearCache extends Thread{
		ClearCache(){
			setName("�����濪ʼ");
		}
		
		@Override
		public void run() {
			Set tempst = new HashSet();
			Set set = cacheConMap.keySet();
			Iterator it = tempst.iterator();
			while(it.hasNext()){
				Object obj = it.next();
				CacheConModel ocm = (CacheConModel) cacheConMap.get(obj);
				//�ж��Ƿ���Ҫ����û���
				if(!ocm.isForever()){
					//�����ǰ���е�ʱ����ڳ���ʱ��
					if((new Date().getTime() - ocm.getBeginTime())
							>= ocm.getDurableTime() * 60 * 1000){
						//��¼һ����Ҫ����ļ�
						tempst.add(obj);
					}
							
				}
			}
			
			//�������
			Iterator tempit = tempst.iterator();
			while(tempit.hasNext()){
				Object o = tempit.next();
				cacheMap.remove(o);
				//ֻ����һ���߳̽����������
				synchronized (cacheMap) {
					cacheConMap.remove(o);
				}
				
				
			}
			//��Ϣ
			try {
				Thread.sleep(60 * 1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
