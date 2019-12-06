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
 * 处理订单的请求
 * 订单如何处理？
 * 	1.如果用户没有提交订单，那么就将订单暂时写入数据库，但是设立一个线程，每隔一定时间去请求数据库
 * 		删除超过一天没有付款的订单
 * 	2.如果用户付款了，那么就插入对应的信息
 * 	3.订单的数据库操作需要设为最高的隔离机制
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
			System.out.println("++非法登录++");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
			
		}
	
		request.getRequestDispatcher("/WEB-INF/admin/product/Order.jsp").forward(request, response);
		return;
	}

	
	/**
	 * 	处理用户提交订单的请求
	 * 	如何对订单信息进行管理
	 * 	首先将购物车之内的东西全部取出来然后做成一份订单展示给用户
	 * 	如果用户没有付款，那么将数据存到另一张表里面
	 * 	如果用户付款，则将详细信息存进主要表里面
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
			System.out.println("有用户非法登录");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
			
		}
	}

}
