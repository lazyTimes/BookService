package com.itcast.zxd.domain;

import java.io.Serializable;

/**
 * 	����������
 * 	@author zxd
 * 
 * */
public class CacheConModel implements Serializable{
	
	private long beginTime;		//���濪ʼ��ʱ��
	private boolean isForever = false;	//�Ƿ�־�
	private int durableTime;	//����ʱ��
	
	
	public long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}
	public boolean isForever() {
		return isForever;
	}
	public void setForever(boolean isForever) {
		this.isForever = isForever;
	}
	public int getDurableTime() {
		return durableTime;
	}
	public void setDurableTime(int durableTime) {
		this.durableTime = durableTime;
	}

}
