package com.itcast.zxd.Controller.manager;

import com.itcast.zxd.Service.ProductService;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

/**
 * 后台的页面跳转控制器
 */
@WebServlet("/MngTranstation")
public class MngTranstation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductService productService = new ProductService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MngTranstation() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess==null){
			System.out.println("有用户非法登录");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
			
		}
		
		//跳转到商品管理的界面
		if(page.equals("ProductManager")){
			System.out.println("跳转到商品主页面");
			setPageInfoi(request);
			request.getRequestDispatcher("/WEB-INF/manager/ProductManage.jsp").forward(request, response);
			return;
		}else if (page.equals("ProductManagerAdd")) {
			//添加商品
			
			request.getRequestDispatcher("/WEB-INF/manager/ProductManagerAdd.jsp").forward(request, response);
			return;
		}
		return ;
	}


	public void setPageInfoi(HttpServletRequest request) {
		//查询所有的商品信息
		int start = 1;
		int total = 10;
		if(request.getParameter("start")!=null && !request.getParameter("start").equals(""))
			start = Integer.parseInt(request.getParameter("start"));
		if(request.getParameter("total")!=null && !request.getParameter("total").equals(""))
			total = Integer.parseInt(request.getParameter("total"));
		List<Product> list = productService.findAll(start, 200);
		request.setAttribute("list", list);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess==null){
			System.out.println("有用户非法登录");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;

		}

		if (page.equalsIgnoreCase("ProductManagerAddSubmit")) {
			String ProductName = request.getParameter("ProductName");
			ProductName = URLDecoder.decode(ProductName, "utf-8");
			String Pnum = request.getParameter("Pnum");
			String Author = request.getParameter("Author");
			String Price = request.getParameter("Price");
			String Description = request.getParameter("Description");
			String categoryId = request.getParameter("categoryId");
			Product product = new Product();
			product.setAuthor(Author);
			product.setProid(UUID.randomUUID().toString());
			product.setCategoryId(Integer.parseInt(categoryId));
			product.setDescription(Description);
			product.setPnum(Integer.parseInt(Pnum));
			product.setProductName(ProductName);
			product.setImgurl("resource/productImg/none.png");
			product.setPrice(Integer.parseInt(Price));

			productService.add(product);

			//添加商品
			setPageInfoi(request);
			request.getRequestDispatcher("/WEB-INF/manager/ProductManage.jsp").forward(request, response);
			return;
		}
	}

}
