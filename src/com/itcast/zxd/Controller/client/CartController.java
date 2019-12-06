package com.itcast.zxd.Controller.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.zxd.Service.CartService;
import com.itcast.zxd.Service.ProductService;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;

/**
 * 用户的购物车请求处理中心
 * 	1.处理添加商品到购物车的请求
 * 	2.处理删除购物车内某件商品的请求
 * 	3.处理
 */
@WebServlet("/ProductController/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CartService cartservice = new CartService();
   
    public CartController() {
        super();
       
    }

	/**
	 * 	对于购物车的相关请求
	 * 	1.显示购物车
	 * 	2.对于购物车之内商品的增加
	 * 	3.对于购物车之内商品的减少
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//只查看购物车内的商品
		String page = request.getParameter("page");
		String proid = request.getParameter("productId");
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess==null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		//选择了删除商品
		if(page.equalsIgnoreCase("delete")){
			if(usersess!=null){
				Product pro = cartservice.removeProduct(proid, usersess.getUsername());
				PrintWriter out = response.getWriter();
				System.out.println("用户删除商品"+pro);
				if(null != pro)
					out.println(true);
				else
					out.println(false);
			}
			
			
		}else if(page.equalsIgnoreCase("ShowCart")){
			//展示用户购物车的请求
			if(usersess!=null){
				List<Product> cart = cartservice.showCart(usersess.getUsername());
				System.out.println("购物车内商品"+cart);
				request.setAttribute("Cart", cart);
				request.getRequestDispatcher("/WEB-INF/admin/product/Cart.jsp")
					.forward(request, response);
				return;
			}
			
			
		}else if(page.equalsIgnoreCase("AddPcount")){
			if(usersess!=null){
				int count = cartservice.addPcount(proid, usersess);
				PrintWriter out = response.getWriter();
				//修改完成之后将修改之后的Count交给前台处理
				out.print(count);
				return;
			}
			
		}else if(page.equalsIgnoreCase("reducePcount")) {
			if(usersess!=null) {
				int count = cartservice.reductPcount(proid, usersess);
				PrintWriter out = response.getWriter();
				//修改完成之后将修改之后的Count交给前台处理
				out.print(count);
				return;
			}
			
		}
		//预留：如果非法进入此页面则跳转到一个错误页面去
		return;
	}

	/**	
	 * 只要处理用户
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proid = request.getParameter("productId");
		String page = request.getParameter("page");
		System.out.println("购物车请求类型"+page);
		if(null!=proid && page!=null){
			//展示购物车的页面
			if(page.equalsIgnoreCase("AddProduct")){
				User usersess = (User) request.getSession().getAttribute("Usersession");
				if(usersess!=null){
					//获取到id之后先查找对应的商品
					Product pro = new ProductService().selectOneProduct(proid);
					cartservice.createCart(usersess.getUsername());
					cartservice.newCartAdd(usersess, pro);
					//计算出总价然后保存到Request域当中
					//-----------------------------------		
					//添加商品之后跳转到购物车界面
					response.sendRedirect(getServletContext().getContextPath()+"/ProductController/CartController?page=ShowCart");
					return;				
				}
			}
			
		}
	}

}
