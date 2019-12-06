package com.itcast.zxd.Dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itcast.zxd.Service.ProductService;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;
import com.itcast.zxd.utils.DButil;

/**
 * 	���ﳵ�����ݿ��ѯ
 * 
 * */
public class CartDao {
	 private static Connection conn;
	 private static PreparedStatement prepared;
	 private static ProductService proservice = new ProductService();
	 
	 public static Product removeProductByProid(String proid, String username){
		 String removeString = "DELETE FROM "+username+"cart WHERE Proid=?";
		 boolean result = DButil.excuteUpdateString(removeString, new String[]{proid});
		 Product pro = null;
		 if(result)
			 pro = proservice.selectOneProduct(proid);
		 else
			 return null;
		 return pro;
	 }
	 
	/**
	 * 	�����ݿ��ȡ�û��Ĺ��ﳵ
	 * 	���û���򷵻�һ��NULLֵ���û��Լ��������ﳵ
	 * 	@param username �û�������ȡ��Ӧ�Ĺ��ﳵ
	 * */
	public static List<Product> findOldCart(String username){
		List<Product> cart = null;
		try{
			cart = new ArrayList<>();
			String findOldCartQueryString = "SELECT product.Proid,"
					+ "product.Author,"
					+ "product.Pnum,"
					+ "product.Price,"
					+ "product.Description,"
					+ "product.Imgurl, "
					+ "product.ProductName,  "
					+ username+"cart.Pcount, "
					+ "product.CategoryId "
					+ " FROM "+username+"cart,product WHERE "+username+"cart.Proid=product.Proid";
			
			List querycart = DButil.excuteQuery(findOldCartQueryString, null);

			for(int x=0; x<querycart.size(); x++){
				Object[] obj = (Object[]) querycart.get(x);
				Product pro = new Product();
				pro.setProid(obj[0].toString());
				pro.setAuthor(obj[1].toString());
				pro.setPnum(Integer.parseInt(obj[2].toString()));
				pro.setPrice(Double.parseDouble(obj[3].toString()));
				pro.setDescription(obj[4].toString());
				pro.setImgurl(obj[5].toString());
				pro.setProductName(obj[6].toString());
				pro.setPcount(Integer.parseInt(obj[7].toString()));
				pro.setCategoryId(Integer.parseInt(obj[8].toString()));
				cart.add(pro);

			}
		}catch(Exception e){
			return null;
		
		}
		//������ھɹ��ﳵ�ͷ���
		return cart;
		
	
	}
	
	/**
	 * 	��ȡ���ﳵ֮��������Ʒ���ܼ�Ǯ
	 * 	@return ���ﳵ������Ʒ���ܼ�
	 * */
	public static double getCartNumPrice(List<Product> cart){
		double numprice = 0;
		for(int x=0; x<cart.size(); x++){
			Product pro = cart.get(x);
			numprice = pro.getPcount()*pro.getPrice();
			
		}
		return numprice;	
	}
	
	/**
	 * 	��ȡ���ﳵ֮���ܵ���Ʒ��¼��Ŀ
	 * 
	 * */
	public static int selectCartCount(List<Product> cart){
		return cart.size();
	}
	
	
	/**
	 * 	�����û����ƴ���һ�����ﳵ���û�ʹ��
	 * 	
	 * */
	public static void createCart(String username){
		System.out.println("�û�������һ�����ﳵ");
		String createString = "CREATE TABLE "+username+"Cart("
				+ "CartId INT PRIMARY KEY AUTO_INCREMENT,"
				+ "Proid VARCHAR(100) NOT NULL UNIQUE KEY,"
				+ "Userid INT NOT NULL,"
				+ "pcount INT NOT NULL DEFAULT 1,"
				+ "UpdateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
				+ "FOREIGN KEY (Proid) REFERENCES Product(Proid),"
				+ "FOREIGN KEY (Userid) REFERENCES User(Userid)"
				+ ")";
		try {
			conn = DButil.getConnection();
			prepared = conn.prepareStatement(createString);
			prepared.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return;
		} finally {
			DButil.close();
			
		}
	}
	
	/**
	 * 	���ﳵ֮�������Ʒ
	 * 	@param proid ��ƷId
	 * 	@param userid �û�id
	 * 	@param count ��Ʒ������
	 * */
	public static boolean InsertProduct(String proid, User user){
		//���빺�ﳵ֮ǰ�ȸ����û����ҵ�����
		//String userid = UserDao.findUserIdByUsername(user.getUsername());
		
		String InsertToCartString = 
		 		"INSERT INTO "+user.getUsername()+"cart(Proid,Userid,UpdateTime) VALUES(?,?,?)";
		
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datestring = simple.format(new Date().getTime());
		System.out.println("���ﳵ����ʱ��"+datestring);
		
		//��Ϊ�漰��������������������Ҫ��д�߼�
		try {
			conn = DButil.getConnection();
			prepared = conn.prepareStatement(InsertToCartString);
			prepared.setObject(1, proid);
			prepared.setObject(2, user.getUserid());
			prepared.setObject(3, datestring);
			prepared.executeUpdate();
		} catch (SQLException e) {
			//�����ظ���Ʒ��ȡ����������+1
			AddPcount(proid, user);
		} finally{
			DButil.handlerClose(prepared, null);
			DButil.close();
		}
		
		
		return false;
	}

	/**
	 * 	���ڹ��ﳵ֮����Ʒ�����Ӳ���
	 * 	@return ����֮��Ĺ��ﳵ��Ʒ����
	 * 	@param proid 
	 * */
	public static int AddPcount(String proid, User user) {
		System.out.println("�����ظ���Ʒ��������+1");
		// ��������������ͬԪ�ص���Ʒ��Ѷ�Ӧ��Pcount+1
		String UpdatePcount = "UPDATE "+user.getUsername()+"Cart SET Pcount = ? WHERE Proid=?";
		String findPcountByProid = "SELECT Pcount FROM "+user.getUsername()+"Cart WHERE Proid=?";
		List list = DButil.excuteQuery(findPcountByProid, new String[]{proid});
		Object[] obj = (Object[]) list.get(0);
		String Pcount = obj[0].toString();
		int count = Integer.parseInt(Pcount);
		//�ҵ��˶�Ӧ��Ʒ��Pcount֮��������Ȼ����뵽��λ
		
		DButil.excuteUpdateString(UpdatePcount, 
				new String[]{String.valueOf(++count),proid});
		return count;
	}
	
	
	/**
	 * 	���ڹ��ﳵ֮����Ʒ�ļ��ٲ���
	 * 	@return ����֮��Ĺ��ﳵ��Ʒ����
	 * 	@param proid ��Ʒid
	 * */
	public static int ReducePcount(String proid, User user) {
		// ����Ӧ�Ĺ��ﳵ�ڵ���Ʒ-1
		String UpdatePcount = "UPDATE "+user.getUsername()+"Cart SET Pcount = ? WHERE Proid=?";
		String findPcountByProid = "SELECT Pcount FROM "+user.getUsername()+"Cart WHERE Proid=?";
		List list = DButil.excuteQuery(findPcountByProid, new String[]{proid});
		Object[] obj = (Object[]) list.get(0);
		String Pcount = obj[0].toString();
		int count = Integer.parseInt(Pcount);
		//�ҵ��˶�Ӧ��Ʒ��Pcount֮��������Ȼ����뵽��λ
		
		DButil.excuteUpdateString(UpdatePcount, 
				new String[]{String.valueOf(--count),proid});
		return count;
		
	}

	/**
	 * ��չ��ﳵ
	 */
	public static void clear(User user) {
		System.out.println("��ʼ�����ﳵ");
		String clearCartSql = "DELETE FROM "+user.getUsername()+"cart";
		try {
			
			Connection connection = DButil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(clearCartSql);
			int executeUpdate = prepareStatement.executeUpdate();
			System.out.println(executeUpdate);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				DButil.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally{
			
			DButil.close();
		}
		
	}
}
