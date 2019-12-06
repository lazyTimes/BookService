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
 * �û��Ĺ��ﳵ����������
 * 	1.���������Ʒ�����ﳵ������
 * 	2.����ɾ�����ﳵ��ĳ����Ʒ������
 * 	3.����
 */
@WebServlet("/ProductController/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CartService cartservice = new CartService();
   
    public CartController() {
        super();
       
    }

	/**
	 * 	���ڹ��ﳵ���������
	 * 	1.��ʾ���ﳵ
	 * 	2.���ڹ��ﳵ֮����Ʒ������
	 * 	3.���ڹ��ﳵ֮����Ʒ�ļ���
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ֻ�鿴���ﳵ�ڵ���Ʒ
		String page = request.getParameter("page");
		String proid = request.getParameter("productId");
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess==null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		//ѡ����ɾ����Ʒ
		if(page.equalsIgnoreCase("delete")){
			if(usersess!=null){
				Product pro = cartservice.removeProduct(proid, usersess.getUsername());
				PrintWriter out = response.getWriter();
				System.out.println("�û�ɾ����Ʒ"+pro);
				if(null != pro)
					out.println(true);
				else
					out.println(false);
			}
			
			
		}else if(page.equalsIgnoreCase("ShowCart")){
			//չʾ�û����ﳵ������
			if(usersess!=null){
				List<Product> cart = cartservice.showCart(usersess.getUsername());
				System.out.println("���ﳵ����Ʒ"+cart);
				request.setAttribute("Cart", cart);
				request.getRequestDispatcher("/WEB-INF/admin/product/Cart.jsp")
					.forward(request, response);
				return;
			}
			
			
		}else if(page.equalsIgnoreCase("AddPcount")){
			if(usersess!=null){
				int count = cartservice.addPcount(proid, usersess);
				PrintWriter out = response.getWriter();
				//�޸����֮���޸�֮���Count����ǰ̨����
				out.print(count);
				return;
			}
			
		}else if(page.equalsIgnoreCase("reducePcount")) {
			if(usersess!=null) {
				int count = cartservice.reductPcount(proid, usersess);
				PrintWriter out = response.getWriter();
				//�޸����֮���޸�֮���Count����ǰ̨����
				out.print(count);
				return;
			}
			
		}
		//Ԥ��������Ƿ������ҳ������ת��һ������ҳ��ȥ
		return;
	}

	/**	
	 * ֻҪ�����û�
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proid = request.getParameter("productId");
		String page = request.getParameter("page");
		System.out.println("���ﳵ��������"+page);
		if(null!=proid && page!=null){
			//չʾ���ﳵ��ҳ��
			if(page.equalsIgnoreCase("AddProduct")){
				User usersess = (User) request.getSession().getAttribute("Usersession");
				if(usersess!=null){
					//��ȡ��id֮���Ȳ��Ҷ�Ӧ����Ʒ
					Product pro = new ProductService().selectOneProduct(proid);
					cartservice.createCart(usersess.getUsername());
					cartservice.newCartAdd(usersess, pro);
					//������ܼ�Ȼ�󱣴浽Request����
					//-----------------------------------		
					//�����Ʒ֮����ת�����ﳵ����
					response.sendRedirect(getServletContext().getContextPath()+"/ProductController/CartController?page=ShowCart");
					return;				
				}
			}
			
		}
	}

}
