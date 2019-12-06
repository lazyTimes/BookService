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
 * ��ҳ����Ʒչʾ��
 * 	1.����չʾ������Ʒ�б��������Ӧ��ť��ʱ��չʾ��Ӧ������
 */
@WebServlet("/ProductController/HomeConttroler")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductService proservice = new ProductService();

	/**
	 * ����չʾ��ҳ������
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess==null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		
		//Ĭ����ת����ҳ����չʾȫ������Ʒ
		Product newproducts = proservice.newProducts();
		request.setAttribute("newproducts", newproducts);
		//��ȡ����������
		List<Notice> note = proservice.CreateNotice();
		request.setAttribute("Noticelist", note);
		request.getRequestDispatcher("/WEB-INF/admin/product/Home.jsp")
			.forward(request, response);
		return;
	}

	/**
	 * ���ڽ��ܶ���ָ����Ʒ��ѯ��ajax����Ĵ���
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
