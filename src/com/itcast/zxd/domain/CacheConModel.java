package com.itcast.zxd.domain;

import java.io.Serializable;

/**
 * 	缓存属性类
 * 	@author zxd
 * 
 * */
public class CacheConModel implements Serializable{
	
	private long beginTime;		//缓存开始的时间
	private boolean isForever = false;	//是否持久
	private int durableTime;	//持续时间
	
	
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
