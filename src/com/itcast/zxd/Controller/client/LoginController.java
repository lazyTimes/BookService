package com.itcast.zxd.Controller.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.zxd.MyEception.LoginException;
import com.itcast.zxd.Service.CartService;
import com.itcast.zxd.Service.UserLoginService;
import com.itcast.zxd.domain.Product;
import com.itcast.zxd.domain.User;

/**
 * ���ƹ��ڵ�¼��һϵ������Ŀ�����
 * ������������Ҫ����һ����ת����̨����ϵͳ�����
 * 
 * 
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserLoginService loginservice = new UserLoginService();
	
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *	��ֹ�û��ظ���¼ʹ���ض������ת���ķ�ʽ�����ų�
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		
		if(page!=null){
			//��ת���û�ע��ҳ�������
			if(page.equals("goRegister")){
				request.getRequestDispatcher("WEB-INF/admin/login/register.jsp").forward(request, response);
				return;
			}else if(page.equals("exit")){
				//�û�ע��
				request.getSession().invalidate();
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
				
			}else if(page.equals("Manager")){
				request.getRequestDispatcher("WEB-INF/manager/main/Main.jsp").forward(request, response);
				return;
			}
		}
		
		
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess==null){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("ProductController/HomeConttroler").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username = request.getParameter("Username");
		String Password = request.getParameter("Password");
		
		//��ֹ�Ƿ�����
		if(Username.isEmpty() || Password.isEmpty()){
			try {
				throw new LoginException("��¼ʧ��");
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		User user = loginservice.findUserByUsernameAndPassword(Username, Password);
		//�û���֤�ɹ�
		if(null != user){
			String isRemem = request.getParameter("rememberUser");
			System.out.println("�û���¼�Ƿ��ס�û���" + isRemem==null?0:1);
			String autolog = request.getParameter("autologin");
			System.out.println("�û��Ƿ�ѡ�����Զ���¼" + autolog==null?0:1);
			
			if(null != isRemem && "0".equals(isRemem)){
				//�û���ס���û���ִ�в���
				Cookie cookie = loginservice.rememberUser(Username);
				response.addCookie(cookie);
			} else if(null != autolog && "0".equals(autolog)){
				//�û�ѡ�����Զ���¼
				Cookie[] cookie = loginservice.checkUserAutolog(Username, Password);
				//��Cookie��Ϣ����ӽ�ȥ
				for(Cookie ck : cookie)
					response.addCookie(ck);
			}
			
			//�����ס���û�����ִ�����в���
			//1.���û��ĸ�����Ϣ����Session���з�ֹ�Ƿ���¼
			request.getSession().setAttribute("Usersession", user);
			//2.���ڿ���Ҫ����һ�����ﳵ��Session��Ϣ
			CartService service = new CartService();
			service.createCart(Username);
			/*----------------�����û���״̬��Ϣ�ж��Ƿ�Ϊһ�������û�-----------------------*/
			//���Ϊ�����û�����ת����̨ϵͳ
			if(user.getRole()==1){
				response.sendRedirect(getServletContext().getContextPath()+"/LoginController?page=Manager");
				return;
			}
			response.sendRedirect(getServletContext().getContextPath()+"/LoginController");
			return;
		} else {
			request.setAttribute("errmess", "�û����������벻����");
			request.getRequestDispatcher("/index.jsp").forward(request, response);

			return;
		}
	}
	
	

}
