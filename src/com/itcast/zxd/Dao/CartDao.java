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
 * 	购物车的数据库查询
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
	 * 	从数据库获取用户的购物车
	 * 	如果没有则返回一个NULL值给用户自己构建购物车
	 * 	@param username 用户名来获取对应的购物车
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
		//如果存在旧购物车就返回
		return cart;
		
	
	}
	
	/**
	 * 	获取购物车之内所有商品的总价钱
	 * 	@return 购物车所用商品的总价
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
	 * 	获取购物车之内总的商品记录数目
	 * 
	 * */
	public static int selectCartCount(List<Product> cart){
		return cart.size();
	}
	
	
	/**
	 * 	根据用户名称创建一辆购物车供用户使用
	 * 	
	 * */
	public static void createCart(String username){
		System.out.println("用户创建了一辆购物车");
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
	 * 	向购物车之内添加物品
	 * 	@param proid 商品Id
	 * 	@param userid 用户id
	 * 	@param count 商品的数量
	 * */
	public static boolean InsertProduct(String proid, User user){
		//加入购物车之前先根据用户名找到主键
		//String userid = UserDao.findUserIdByUsername(user.getUsername());
		
		String InsertToCartString = 
		 		"INSERT INTO "+user.getUsername()+"cart(Proid,Userid,UpdateTime) VALUES(?,?,?)";
		
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datestring = simple.format(new Date().getTime());
		System.out.println("购物车更新时间"+datestring);
		
		//因为涉及到更新数量操作所以需要自写逻辑
		try {
			conn = DButil.getConnection();
			prepared = conn.prepareStatement(InsertToCartString);
			prepared.setObject(1, proid);
			prepared.setObject(2, user.getUserid());
			prepared.setObject(3, datestring);
			prepared.executeUpdate();
		} catch (SQLException e) {
			//遇到重复商品则取出数量并且+1
			AddPcount(proid, user);
		} finally{
			DButil.handlerClose(prepared, null);
			DButil.close();
		}
		
		
		return false;
	}

	/**
	 * 	对于购物车之内商品的增加操作
	 * 	@return 增加之后的购物车商品数量
	 * 	@param proid 
	 * */
	public static int AddPcount(String proid, User user) {
		System.out.println("遇到重复商品购买数量+1");
		// 如果遇到添加了相同元素的商品则把对应的Pcount+1
		String UpdatePcount = "UPDATE "+user.getUsername()+"Cart SET Pcount = ? WHERE Proid=?";
		String findPcountByProid = "SELECT Pcount FROM "+user.getUsername()+"Cart WHERE Proid=?";
		List list = DButil.excuteQuery(findPcountByProid, new String[]{proid});
		Object[] obj = (Object[]) list.get(0);
		String Pcount = obj[0].toString();
		int count = Integer.parseInt(Pcount);
		//找到了对应商品的Pcount之后，在自增然后存入到栏位
		
		DButil.excuteUpdateString(UpdatePcount, 
				new String[]{String.valueOf(++count),proid});
		return count;
	}
	
	
	/**
	 * 	对于购物车之内商品的减少操作
	 * 	@return 减少之后的购物车商品数量
	 * 	@param proid 商品id
	 * */
	public static int ReducePcount(String proid, User user) {
		// 将对应的购物车内的商品-1
		String UpdatePcount = "UPDATE "+user.getUsername()+"Cart SET Pcount = ? WHERE Proid=?";
		String findPcountByProid = "SELECT Pcount FROM "+user.getUsername()+"Cart WHERE Proid=?";
		List list = DButil.excuteQuery(findPcountByProid, new String[]{proid});
		Object[] obj = (Object[]) list.get(0);
		String Pcount = obj[0].toString();
		int count = Integer.parseInt(Pcount);
		//找到了对应商品的Pcount之后，在自增然后存入到栏位
		
		DButil.excuteUpdateString(UpdatePcount, 
				new String[]{String.valueOf(--count),proid});
		return count;
		
	}

	/**
	 * 清空购物车
	 */
	public static void clear(User user) {
		System.out.println("开始清理购物车");
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
