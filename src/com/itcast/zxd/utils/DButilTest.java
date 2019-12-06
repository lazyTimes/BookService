package com.itcast.zxd.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.itcast.zxd.Dao.CartDao;
import com.itcast.zxd.Dao.UserDao;
import com.itcast.zxd.Service.CartService;
import com.itcast.zxd.Service.ProductService;
import com.itcast.zxd.domain.Notice;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;



/**
 * 	测试数据库工具类是否可以运行
 * 
 * */
public class DButilTest {
	@Test
	public void test()  {
		ProductService s = new ProductService();
		Product p = s.RandomSelect("1");
		System.out.println(p);
	}

}
