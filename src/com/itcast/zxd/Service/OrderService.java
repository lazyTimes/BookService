package com.itcast.zxd.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.itcast.zxd.Dao.CartDao;
import com.itcast.zxd.Dao.OrderDao;
import com.itcast.zxd.Dao.UserDao;
import com.itcast.zxd.domain.CacheConModel;
import com.itcast.zxd.domain.Order;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;
import com.itcast.zxd.utils.DButil;

public class OrderService {
	//'000',2,'第一单',53.345,0,'湖南','怀化','2018-5-4'
	private Connection conn;
	private PreparedStatement preparedstatement;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 	构造器构造时候查找缓存数据，
	 * 	这是对订单超发情况的问题解决
	 * */
	public OrderService(){
		//处理事务之前都需要对缓存进行一次更新操作
		List<String> list = OrderDao.getOrderidFromOrder();
		System.out.println("用户id信息"+list);
		Mapcache map = Mapcache.getInstance();
		//将商品的Id信息加入
		if(list!=null){
			for(String s : list){
				Order od = new Order();
				od.setOrderid(s);
				CacheConModel com = new CacheConModel();
				com.setBeginTime(new Date().getTime());
				com.setDurableTime(120);
				map.addCache(s, od, com);
			}	
		}
		
			
	}
	
	
	/**
	 * 
	 * 	将订单信息写入到对应的订单表里面
	 * 	后期会加入另一个表来存放没有支付的订单！！！！
	 * 	@param user 用户对象
	 * 	@param prolist 购物车对象集合
	 * 	@param paystate 是否支付订单
	 * 	
	 * */
	private static String InserOrderInfo = "INSERT INTO Orders"
			+ "(Orderid, Userid, OrderName, Price, PayState, "
			+ "Receivename, ReceiveAddress, ReceivePhone, OrderCreatetime) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String InsertItemsInfo = "INSERT INTO orderItems(Orderid, Proid, Onumber) VALUES(?, ?, ?)";
	public int OrderInput(User user, List<Product> prolist, Order order){
		//先获取到对应的商品
		String userid = UserDao.findUserIdByUsername(user.getUsername());
		double numprice = CartDao.getCartNumPrice(prolist);
		String dateString = format.format(new Date().getTime());
		//测试是否所有的数据都已经写入到此地
//		System.out.println(user + "\n\n"+prolist+"\n\n" + order);
		
		//计算出购物车之内商品的总价钱
		try {
			System.out.println("用户开始写入订单.....");
			conn = DButil.getConnection();
			//因为这里并发访问量最大所以需要将隔离事务设为最高
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			preparedstatement = conn.prepareStatement(InserOrderInfo);
			//创建一个订单的ID信息
//			String orderid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
			preparedstatement.setObject(1, order.getOrderid());
			preparedstatement.setObject(2, userid);
			preparedstatement.setObject(3, user.getUsername()+"在"+dateString+"创建了一份订单");
			preparedstatement.setObject(4, numprice);
			preparedstatement.setObject(5, order.getPayState());
			preparedstatement.setObject(6, order.getReceivename());
			preparedstatement.setObject(7, order.getReceiveAddress());
			preparedstatement.setObject(8, order.getReceivePhone());
			preparedstatement.setObject(9, dateString);
			int result = preparedstatement.executeUpdate();
			System.out.println("是否正确"+result);
			System.out.println("订单写入完成---开始检查订单号.....");
			
			//查找缓存，如果缓存中存在数据则需要事务回滚
			Mapcache map = Mapcache.getInstance();
			//获取到了对应数据之后立即加入到缓存当中
			Object obj = map.getValue(order.getOrderid());
			CacheConModel com = new CacheConModel();
			com.setBeginTime(new Date().getTime());
			com.setDurableTime(60);
			com.setForever(true);
			map.addCache(order.getOrderid(), order, com);
			//如果缓存当中存在该数据就要事务回滚
			if(null != obj){
				//发生超发就给一个新的UUID进行订单提交
				order.setOrderid(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
				conn.rollback();
			}else{
				System.out.println("没有出现错误，开始写入订单详情");
				//获取到购物车之内的商品数量
				for(int x=0; x<prolist.size(); x++){
					Product p = prolist.get(x);
					preparedstatement = conn.prepareStatement(InsertItemsInfo);
					preparedstatement.setObject(1, order.getOrderid());
					preparedstatement.setObject(2, p.getProid());
					preparedstatement.setObject(3, p.getPcount());
					preparedstatement.executeUpdate();
				}
				
			}
			System.out.println("用户订单写入完成");
			//执行完业务逻辑之后就提交全部信息
			conn.commit();
			//提交成功之后需清空用户购物车内的所有物品
		} catch (SQLException e) {
			//一旦发生错误则回滚操作
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			DButil.handlerClose(preparedstatement, null);
			DButil.close();
			
		}
		return 1;
		 
	}
	
	
}
