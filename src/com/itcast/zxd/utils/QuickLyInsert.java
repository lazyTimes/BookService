package com.itcast.zxd.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 	一个用于快速插入数据的io操作文件
 * 
 * */
//"D:\\myproject\\BookService\\WebRoot\\WEB-INF\\InsertFile"
public class QuickLyInsert {
	public void insertInfo(String path){
		
		File f = new File(path);
		try {
			BufferedReader bufread = new BufferedReader(new FileReader(f));
			String str = null;
			while((str=bufread.readLine())!=null){
				PreparedStatement pre = DButil.getConnection().prepareStatement(str);
				pre.executeUpdate();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
