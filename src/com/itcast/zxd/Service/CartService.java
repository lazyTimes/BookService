package com.itcast.zxd.Service;

import java.util.ArrayList;
import java.util.List;

import com.itcast.zxd.Dao.CartDao;
import com.itcast.zxd.Dao.UserDao;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;

/**
 * 	对于购物车的相关处理
 * 
 * */
public class CartService {
	
	/**
	 * 	移除购物车当中的某件商品
	 * 	@param Proid 商品id号码
	 * 	@param username 根据用户名找到对应的购物车
	 * */
	public Product removeProduct(String Proid, String username){
		Product pro = CartDao.removeProductByProid(Proid, username);
		return pro;
		
	}
	
	/**
	 * 	根据用户名拼接处一个购物车
	 * */
	public void createCart(String username) {
		CartDao.createCart(username);
	}
	
	/**
	 * 	因为在初始化的时候就会建一张表并且插入数据
	 * 	所以要获取购物车直接返回
	 * 
	 * */
	public List<Product> showCart(String username){
		List<Product> cart = CartDao.findOldCart(username);
		return cart;
	}
	
	/**
	 * 	将一件商品添加到购物车当中
	 * 	当购物车已经存在这件商品的时候
	 * 	就将购物篮的商品数量增加1
	 * 	
	 * */
	public boolean newCartAdd(User user, Product product){
		System.out.println("添加商品");
		String proid = product.getProid();
		String pcount = String.valueOf(product.getPcount());
		CartDao.InsertProduct(proid, user);
		return true;
	}
	
	/**
	 * 	清空购物车
	 * */
	public void clearCart(User user){
		//其实就是删除掉购物车里面全部的记录
		CartDao.clear(user);
	}
	
	/**
	 * 	根据商品的ID号码对于购物篮之内的指定商品进行Ajax的增加操作
	 * 
	 * */
	public int addPcount(String proid, User user){
		int count = CartDao.AddPcount(proid, user);
		//成功之后返回增加之后的数量
		return count;
	}
	
	public int reductPcount(String proid, User user) {
		int count = CartDao.ReducePcount(proid, user);
		
		return count;
		
	}
}
