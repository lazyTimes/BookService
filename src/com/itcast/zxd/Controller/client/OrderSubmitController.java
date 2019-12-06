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
 * 对于用户提交订单的请求
 * 可以实现对于用户提交订单的特定处理
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
		//用户选择了确认并且提交订单的按钮
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(null == usersess){
			System.out.println("有用户非法登录");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;

		}
		//数据库写入订单数据
		String receive_address = request.getParameter("receive_address");
		String receive_peaple = request.getParameter("receive_peaple");
		String receive_telephone = request.getParameter("receive_telephone");
		//如果不存在这些表单元素不进行订单写入
		if(receive_address.isEmpty() || receive_peaple.isEmpty() || receive_telephone.isEmpty())
			return;
		//获取购物车内的所有商品
		List<Product> prolist = cartservice.showCart(usersess.getUsername());
		Order order = new Order();
		order.setReceiveAddress(receive_address);
		order.setReceivename(receive_peaple);
		order.setReceivePhone(receive_telephone);
		order.setPayState(1);
		order.setOrderid(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase());
		
		
		
		//如果还发生了错误，那么就跳转到一个错误页面提示给用户重新提交订单
		try{
			int result = oservice.OrderInput(usersess, prolist, order);
			//如果提交成功则跳转到一个正确的页面
			if(result==1){
				//清空购物车
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
