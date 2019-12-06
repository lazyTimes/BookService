package com.itcast.zxd.Controller.client;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.zxd.Service.ProductService;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;

/**
 * 页面跳转中心处理站点
 * 	1.用户点击全部商品目录跳转到全部商品目录的界面
 * 
 */
@WebServlet("/TransferStation")
public class TransferStation extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private ProductService service = new ProductService();

	/**
	 * 页面跳转中心
	 * 专门用于处理跳转请求的一个中转站
	 * 
	 * --------------------预留项目--------------
	 * 后期需要增加一个分页效果
	 * 如果记录过多就需要对于查询进行一个截取
	 * 每次只展示部分商品，直到下次请求
	 * 
	 * page = goAll 查询所有的商品，并进行全部的展示
	 * page = goOne 跳转到某个商品的详情页面
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("Usersession");
		if(user==null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		System.out.println(user);
		String pagetype = request.getParameter("page");
		System.out.println(pagetype);
		if(null != pagetype ){
			if(pagetype.equalsIgnoreCase("goAll")){
				//跳转到全部商品的页面
				List<Product> prolist = null;
				String CategoryId = request.getParameter("categoryId");
				long proCount = 0;
				//默认或者为1无条件查找所有商品
				if(CategoryId == null || CategoryId.equals("1")){
					prolist = service.selectAll();
					proCount = service.SelectProductCount();
				}else{
					prolist = service.selectAll(CategoryId);
					proCount = service.SelectProductCount(CategoryId);
				}
//				System.out.println("商品列表"+prolist);
				request.setAttribute("proCount", proCount);
				request.setAttribute("productlist", prolist);
				request.getRequestDispatcher("WEB-INF/admin/product/CLassify.jsp").forward(request, response);
				return;
			}
			
			//查询一个指定商品
			if(pagetype.equalsIgnoreCase("goOne")){
				String productId = request.getParameter("productId");
				Product pro = service.selectOneProduct(productId);
				request.setAttribute("Product", pro);
				System.out.println(pro);
				request.getRequestDispatcher("WEB-INF/admin/product/ProDetails.jsp").forward(request, response);
				return;
			}
		}
		
	}

	/**
	 * 因为只接受GET请求所以一切POST请求视为无效
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return;
	}

}
