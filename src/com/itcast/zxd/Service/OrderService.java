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
	//'000',2,'��һ��',53.345,0,'����','����','2018-5-4'
	private Connection conn;
	private PreparedStatement preparedstatement;
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 	����������ʱ����һ������ݣ�
	 * 	���ǶԶ������������������
	 * */
	public OrderService(){
		//��������֮ǰ����Ҫ�Ի������һ�θ��²���
		List<String> list = OrderDao.getOrderidFromOrder();
		System.out.println("�û�id��Ϣ"+list);
		Mapcache map = Mapcache.getInstance();
		//����Ʒ��Id��Ϣ����
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
	 * 	��������Ϣд�뵽��Ӧ�Ķ���������
	 * 	���ڻ������һ���������û��֧���Ķ�����������
	 * 	@param user �û�����
	 * 	@param prolist ���ﳵ���󼯺�
	 * 	@param paystate �Ƿ�֧������
	 * 	
	 * */
	private static String InserOrderInfo = "INSERT INTO Orders"
			+ "(Orderid, Userid, OrderName, Price, PayState, "
			+ "Receivename, ReceiveAddress, ReceivePhone, OrderCreatetime) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static String InsertItemsInfo = "INSERT INTO orderItems(Orderid, Proid, Onumber) VALUES(?, ?, ?)";
	public int OrderInput(User user, List<Product> prolist, Order order){
		//�Ȼ�ȡ����Ӧ����Ʒ
		String userid = UserDao.findUserIdByUsername(user.getUsername());
		double numprice = CartDao.getCartNumPrice(prolist);
		String dateString = format.format(new Date().getTime());
		//�����Ƿ����е����ݶ��Ѿ�д�뵽�˵�
//		System.out.println(user + "\n\n"+prolist+"\n\n" + order);
		
		//��������ﳵ֮����Ʒ���ܼ�Ǯ
		try {
			System.out.println("�û���ʼд�붩��.....");
			conn = DButil.getConnection();
			//��Ϊ���ﲢ�����������������Ҫ������������Ϊ���
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setAutoCommit(false);
			preparedstatement = conn.prepareStatement(InserOrderInfo);
			//����һ��������ID��Ϣ
//			String orderid = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
			preparedstatement.setObject(1, order.getOrderid());
			preparedstatement.setObject(2, userid);
			preparedstatement.setObject(3, user.getUsername()+"��"+dateString+"������һ�ݶ���");
			preparedstatement.setObject(4, numprice);
			preparedstatement.setObject(5, order.getPayState());
			preparedstatement.setObject(6, order.getReceivename());
			preparedstatement.setObject(7, order.getReceiveAddress());
			preparedstatement.setObject(8, order.getReceivePhone());
			preparedstatement.setObject(9, dateString);
			int result = preparedstatement.executeUpdate();
			System.out.println("�Ƿ���ȷ"+result);
			System.out.println("����д�����---��ʼ��鶩����.....");
			
			//���һ��棬��������д�����������Ҫ����ع�
			Mapcache map = Mapcache.getInstance();
			//��ȡ���˶�Ӧ����֮���������뵽���浱��
			Object obj = map.getValue(order.getOrderid());
			CacheConModel com = new CacheConModel();
			com.setBeginTime(new Date().getTime());
			com.setDurableTime(60);
			com.setForever(true);
			map.addCache(order.getOrderid(), order, com);
			//������浱�д��ڸ����ݾ�Ҫ����ع�
			if(null != obj){
				//���������͸�һ���µ�UUID���ж����ύ
				order.setOrderid(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
				conn.rollback();
			}else{
				System.out.println("û�г��ִ��󣬿�ʼд�붩������");
				//��ȡ�����ﳵ֮�ڵ���Ʒ����
				for(int x=0; x<prolist.size(); x++){
					Product p = prolist.get(x);
					preparedstatement = conn.prepareStatement(InsertItemsInfo);
					preparedstatement.setObject(1, order.getOrderid());
					preparedstatement.setObject(2, p.getProid());
					preparedstatement.setObject(3, p.getPcount());
					preparedstatement.executeUpdate();
				}
				
			}
			System.out.println("�û�����д�����");
			//ִ����ҵ���߼�֮����ύȫ����Ϣ
			conn.commit();
			//�ύ�ɹ�֮��������û����ﳵ�ڵ�������Ʒ
		} catch (SQLException e) {
			//һ������������ع�����
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
