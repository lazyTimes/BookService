package com.itcast.zxd.utils;

import java.util.Random;

/**
 * 	用户注册码的生成工具
 * 	实际上是生成了一个字符串序列
 * 
 * */
public class ActiveCode {
	private static String[] context = 
			new String("A B C D E F G H I J K L M N O Q R S T U V W R S T").split(" ");
	private static Random rand = new Random();
	
	/**
	 * 	随机生成一个激活码保存到用户当中
	 * 
	 * */
	public static String createActiveCode(){
		StringBuilder strbuilder = new StringBuilder();
		for(int x=0; x<28; x++)
			strbuilder.append(context[rand.nextInt(context.length)]);
		return strbuilder.toString();
	}
}
