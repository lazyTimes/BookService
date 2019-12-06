package com.itcast.zxd.Controller.client;

import java.io.IOException;
import java.util.List;

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
 * ������������
 * ������δ���
 * 	1.����û�û���ύ��������ô�ͽ�������ʱд�����ݿ⣬��������һ���̣߳�ÿ��һ��ʱ��ȥ�������ݿ�
 * 		ɾ������һ��û�и���Ķ���
 * 	2.����û������ˣ���ô�Ͳ����Ӧ����Ϣ
 * 	3.���������ݿ������Ҫ��Ϊ��ߵĸ������
 */
@WebServlet("/ProductController/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public OrderController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess==null){
			System.out.println("++�Ƿ���¼++");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
			
		}
	
		request.getRequestDispatcher("/WEB-INF/admin/product/Order.jsp").forward(request, response);
		return;
	}

	
	/**
	 * 	�����û��ύ����������
	 * 	��ζԶ�����Ϣ���й���
	 * 	���Ƚ����ﳵ֮�ڵĶ���ȫ��ȡ����Ȼ������һ�ݶ���չʾ���û�
	 * 	����û�û�и����ô�����ݴ浽��һ�ű�����
	 * 	����û��������ϸ��Ϣ�����Ҫ������
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartService cartservice = new CartService();
		User usersess = (User) request.getSession().getAttribute("Usersession");
		
		if(usersess!=null) {
			List<Product> cart = cartservice.showCart(usersess.getUsername());
			request.setAttribute("Cart", cart);
			request.getRequestDispatcher("/WEB-INF/admin/product/Order.jsp").forward(request, response);
			return;
		}else{
			System.out.println("���û��Ƿ���¼");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
			
		}
	}

}
