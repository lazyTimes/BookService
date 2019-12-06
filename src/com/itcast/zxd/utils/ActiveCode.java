package com.itcast.zxd.utils;

import java.util.Random;

/**
 * 	�û�ע��������ɹ���
 * 	ʵ������������һ���ַ�������
 * 
 * */
public class ActiveCode {
	private static String[] context = 
			new String("A B C D E F G H I J K L M N O Q R S T U V W R S T").split(" ");
	private static Random rand = new Random();
	
	/**
	 * 	�������һ�������뱣�浽�û�����
	 * 
	 * */
	public static String createActiveCode(){
		StringBuilder strbuilder = new StringBuilder();
		for(int x=0; x<28; x++)
			strbuilder.append(context[rand.nextInt(context.length)]);
		return strbuilder.toString();
	}
}
