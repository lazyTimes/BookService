package com.itcast.zxd.Controller.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.itcast.zxd.Service.ProductService;
import com.itcast.zxd.domain.Notice;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;

/**
 * 首页的商品展示：
 * 	1.可以展示三个商品列表，当点击对应按钮的时候展示对应的内容
 */
@WebServlet("/ProductController/HomeConttroler")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService proservice = new ProductService();

	/**
	 * 处理展示首页的请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess==null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		//默认跳转到首页并且展示全部的商品
		Product newproducts = proservice.newProducts();
		request.setAttribute("newproducts", newproducts);
		//获取公告板的数据
		List<Notice> note = proservice.CreateNotice();
		request.setAttribute("Noticelist", note);
		request.getRequestDispatcher("/WEB-INF/admin/product/Home.jsp")
			.forward(request, response);
		return;
	}

	/**
	 * 用于接受对于指定物品查询的ajax请求的处理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pagecontext = request.getParameter("pagecontext");
		if(pagecontext!=null){
			
			Product pro = proservice.RandomSelect(pagecontext);
			PrintWriter out = response.getWriter();
			
			if(pro!=null){
				String proJson = JSON.toJSONString(pro);
				out.print(proJson);
				out.flush();
			}
			
		}
		return;
		
	}

}
