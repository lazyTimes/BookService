package com.itcast.zxd.Service;

import java.util.ArrayList;
import java.util.List;

import com.itcast.zxd.Dao.CartDao;
import com.itcast.zxd.Dao.UserDao;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;

/**
 * 	���ڹ��ﳵ����ش���
 * 
 * */
public class CartService {
	
	/**
	 * 	�Ƴ����ﳵ���е�ĳ����Ʒ
	 * 	@param Proid ��Ʒid����
	 * 	@param username �����û����ҵ���Ӧ�Ĺ��ﳵ
	 * */
	public Product removeProduct(String Proid, String username){
		Product pro = CartDao.removeProductByProid(Proid, username);
		return pro;
		
	}
	
	/**
	 * 	�����û���ƴ�Ӵ�һ�����ﳵ
	 * */
	public void createCart(String username) {
		CartDao.createCart(username);
	}
	
	/**
	 * 	��Ϊ�ڳ�ʼ����ʱ��ͻὨһ�ű��Ҳ�������
	 * 	����Ҫ��ȡ���ﳵֱ�ӷ���
	 * 
	 * */
	public List<Product> showCart(String username){
		List<Product> cart = CartDao.findOldCart(username);
		return cart;
	}
	
	/**
	 * 	��һ����Ʒ��ӵ����ﳵ����
	 * 	�����ﳵ�Ѿ����������Ʒ��ʱ��
	 * 	�ͽ�����������Ʒ��������1
	 * 	
	 * */
	public boolean newCartAdd(User user, Product product){
		System.out.println("�����Ʒ");
		String proid = product.getProid();
		String pcount = String.valueOf(product.getPcount());
		CartDao.InsertProduct(proid, user);
		return true;
	}
	
	/**
	 * 	��չ��ﳵ
	 * */
	public void clearCart(User user){
		//��ʵ����ɾ�������ﳵ����ȫ���ļ�¼
		CartDao.clear(user);
	}
	
	/**
	 * 	������Ʒ��ID������ڹ�����֮�ڵ�ָ����Ʒ����Ajax�����Ӳ���
	 * 
	 * */
	public int addPcount(String proid, User user){
		int count = CartDao.AddPcount(proid, user);
		//�ɹ�֮�󷵻�����֮�������
		return count;
	}
	
	public int reductPcount(String proid, User user) {
		int count = CartDao.ReducePcount(proid, user);
		
		return count;
		
	}
}
