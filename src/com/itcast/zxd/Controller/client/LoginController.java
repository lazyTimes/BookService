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
 * 控制关于登录的一系列请求的控制器
 * ！！！后期需要加入一个跳转到后台管理系统的语句
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
	 *	防止用户重复登录使用重定向加上转发的方式进行排除
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		
		if(page!=null){
			//跳转到用户注册页面的请求
			if(page.equals("goRegister")){
				request.getRequestDispatcher("WEB-INF/admin/login/register.jsp").forward(request, response);
				return;
			}else if(page.equals("exit")){
				//用户注销
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
		
		//防止非法介入
		if(Username.isEmpty() || Password.isEmpty()){
			try {
				throw new LoginException("登录失败");
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		User user = loginservice.findUserByUsernameAndPassword(Username, Password);
		//用户验证成功
		if(null != user){
			String isRemem = request.getParameter("rememberUser");
			System.out.println("用户登录是否记住用户名" + isRemem==null?0:1);
			String autolog = request.getParameter("autologin");
			System.out.println("用户是否选择了自动登录" + autolog==null?0:1);
			
			if(null != isRemem && "0".equals(isRemem)){
				//用户记住了用户名执行操作
				Cookie cookie = loginservice.rememberUser(Username);
				response.addCookie(cookie);
			} else if(null != autolog && "0".equals(autolog)){
				//用户选择了自动登录
				Cookie[] cookie = loginservice.checkUserAutolog(Username, Password);
				//将Cookie信息都添加进去
				for(Cookie ck : cookie)
					response.addCookie(ck);
			}
			
			//如果记住了用户名则执行下列操作
			//1.将用户的个人信息放入Session当中防止非法登录
			request.getSession().setAttribute("Usersession", user);
			//2.后期可能要创建一个购物车的Session信息
			CartService service = new CartService();
			service.createCart(Username);
			/*----------------根据用户的状态信息判断是否为一个超级用户-----------------------*/
			//如果为超级用户则跳转到后台系统
			if(user.getRole()==1){
				response.sendRedirect(getServletContext().getContextPath()+"/LoginController?page=Manager");
				return;
			}
			response.sendRedirect(getServletContext().getContextPath()+"/LoginController");
			return;
		} else {
			request.setAttribute("errmess", "用户名或者密码不存在");
			request.getRequestDispatcher("/index.jsp").forward(request, response);

			return;
		}
	}
	
	

}
