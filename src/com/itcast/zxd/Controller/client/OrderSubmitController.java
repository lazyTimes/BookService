package com.itcast.zxd.Controller.client;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.zxd.Service.CartService;
import com.itcast.zxd.Service.OrderService;
import com.itcast.zxd.domain.Order;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;

/**
 * �����û��ύ����������
 * ����ʵ�ֶ����û��ύ�������ض�����
 */
@WebServlet("/ProductController/OrderSubmitController")
public class OrderSubmitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartservice = new CartService();   
    private OrderService oservice = new OrderService();   
    
    public OrderSubmitController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�û�ѡ����ȷ�ϲ����ύ�����İ�ť
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(null == usersess){
			System.out.println("���û��Ƿ���¼");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;

		}
		//���ݿ�д�붩������
		String receive_address = request.getParameter("receive_address");
		String receive_peaple = request.getParameter("receive_peaple");
		String receive_telephone = request.getParameter("receive_telephone");
		//�����������Щ��Ԫ�ز����ж���д��
		if(receive_address.isEmpty() || receive_peaple.isEmpty() || receive_telephone.isEmpty())
			return;
		//��ȡ���ﳵ�ڵ�������Ʒ
		List<Product> prolist = cartservice.showCart(usersess.getUsername());
		Order order = new Order();
		order.setReceiveAddress(receive_address);
		order.setReceivename(receive_peaple);
		order.setReceivePhone(receive_telephone);
		order.setPayState(1);
		order.setOrderid(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
		
		
		
		//����������˴�����ô����ת��һ������ҳ����ʾ���û������ύ����
		try{
			int result = oservice.OrderInput(usersess, prolist, order);
			//����ύ�ɹ�����ת��һ����ȷ��ҳ��
			if(result==1){
				//��չ��ﳵ
				System.out.println(usersess);
				cartservice.clearCart(usersess);
				
				response.sendRedirect(getServletContext().getContextPath()+"/ProductController/SuccessController?page=success");
				return;
			}
			
		}catch(Exception e){
			response.sendRedirect(getServletContext().getContextPath()+"/ProductController/SuccessController?page=error");
			return;	
		}
		
		
	}

}
