package com.itcast.zxd.Controller.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itcast.zxd.Service.UserAlreadyExisit;
import com.itcast.zxd.Service.UserRegistService;
import com.itcast.zxd.domain.User;
import com.itcast.zxd.utils.ActiveCode;
import com.itcast.zxd.utils.BeanUtil;
import com.itcast.zxd.utils.Mailutils;

/**
 * 处理用户注册的控制器
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//调用Service层完成操作
	private UserRegistService registservi = new UserRegistService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 对于用户的激活码进行比对和处理
	 * 如果得出了结果则正确处理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//对于验证码的处理请求
		String activeCode = request.getParameter("activeCode");
		
		if(activeCode != null && !activeCode.equals("")){
			String result = registservi.completeRegistActiveCode(activeCode);
			//如果用户名存在
			if(result!=null){
				//将刚刚注册的用户名填入
				request.setAttribute("registerNow", result);
				//跳转到用户登录界面
				request.getRequestDispatcher("/WEB-INF/admin/login/login.jsp").forward(request, response);
				return;
			}else{
				request.setAttribute("errormessage", "您是非法注册用户！！");
				request.getRequestDispatcher("/WEB-INF/admin/login/register.jsp").forward(request, response);
				return;
			}
		} 
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		BeanUtil.polular(user, request.getParameterMap());
		//封装激活码
		user.setActivecode(ActiveCode.createActiveCode());
		
		
		boolean bool = registservi.completeRegistOpration(user);
		//成功页面的路径
		
		//判断是否成功
		if(bool){
			//成功之后发送一封邮件(由于验证出现了问题所以取消)
//			String mailmessage = "感谢您的注册，请单击"+
//					"<a href='http://localhost:8080/BookService/activeUser?activeCode="+
//					user.getActivecode()+"'> 激活 </a>后使用";
//			try {
//				Mailutils.sendMail(user.getEmail(), mailmessage);
//			} catch(Exception e){
//				e.printStackTrace();
//				System.out.println("注册失败");
//				return;
//			}
			String successpath = "/WEB-INF/admin/successPage/registerSuccessPage.jsp";
			request.setAttribute("ActiveCode", user.getActivecode());
			request.getRequestDispatcher(successpath).forward(request, response);
			return;
		}else{
			request.setAttribute("errormessage", "服务器正忙，请稍后重新注册");
			request.getRequestDispatcher("/WEB-INF/admin/login/register.jsp").forward(request, response);
			return;
		}
	}

}
