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
 * ҳ����ת���Ĵ���վ��
 * 	1.�û����ȫ����ƷĿ¼��ת��ȫ����ƷĿ¼�Ľ���
 * 
 */
@WebServlet("/TransferStation")
public class TransferStation extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private ProductService service = new ProductService();

	/**
	 * ҳ����ת����
	 * ר�����ڴ�����ת�����һ����תվ
	 * 
	 * --------------------Ԥ����Ŀ--------------
	 * ������Ҫ����һ����ҳЧ��
	 * �����¼�������Ҫ���ڲ�ѯ����һ����ȡ
	 * ÿ��ֻչʾ������Ʒ��ֱ���´�����
	 * 
	 * page = goAll ��ѯ���е���Ʒ��������ȫ����չʾ
	 * page = goOne ��ת��ĳ����Ʒ������ҳ��
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
				//��ת��ȫ����Ʒ��ҳ��
				List<Product> prolist = null;
				String CategoryId = request.getParameter("categoryId");
				long proCount = 0;
				//Ĭ�ϻ���Ϊ1����������������Ʒ
				if(CategoryId == null || CategoryId.equals("1")){
					prolist = service.selectAll();
					proCount = service.SelectProductCount();
				}else{
					prolist = service.selectAll(CategoryId);
					proCount = service.SelectProductCount(CategoryId);
				}
//				System.out.println("��Ʒ�б�"+prolist);
				request.setAttribute("proCount", proCount);
				request.setAttribute("productlist", prolist);
				request.getRequestDispatcher("WEB-INF/admin/product/CLassify.jsp").forward(request, response);
				return;
			}
			
			//��ѯһ��ָ����Ʒ
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
	 * ��Ϊֻ����GET��������һ��POST������Ϊ��Ч
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return;
	}

}
