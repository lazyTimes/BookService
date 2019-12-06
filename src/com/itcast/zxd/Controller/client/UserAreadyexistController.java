package com.itcast.zxd.Controller.client;

import com.itcast.zxd.Service.UserAlreadyExisit;
import com.itcast.zxd.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 处理用户是否已经存在的验证器
 */
@WebServlet("/UserAreadyexistController")
public class UserAreadyexistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//用于处理用户名是否存在的业务处理
	UserAlreadyExisit all = new UserAlreadyExisit();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAreadyexistController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * 查找数据库并且查看是否有用户存在，如果存在则回应对应的信息
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("mail");
		String emails = request.getParameter("emailadreess");
		String username = request.getParameter("username");
		String telephone = request.getParameter("telephone");
		PrintWriter out = response.getWriter();
		
		//判断用户邮箱是否已经存在
		if(null != email){
			User user = all.emailExist(emails);
			
			if(user!=null)
				out.println(false);
			else
				out.println(true);
			out.flush();
		}
		
		//判断用户名是否已经存在
		if(null != username){
			User user = all.UsernameExist(username);
			System.out.println(user);
			if(user!=null)
				out.println(false);
			else
				out.println(true);
			out.flush();
		}
		
		//判断手机号是否已经存在
		if(null != telephone){
			User user = all.telephoneExist(telephone);
			
			if(user!=null)
				out.println(false);
			else
				out.println(true);
			out.flush();
		}
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
