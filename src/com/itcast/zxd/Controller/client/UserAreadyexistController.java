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
 * �����û��Ƿ��Ѿ����ڵ���֤��
 */
@WebServlet("/UserAreadyexistController")
public class UserAreadyexistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//���ڴ����û����Ƿ���ڵ�ҵ����
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
	 * �������ݿⲢ�Ҳ鿴�Ƿ����û����ڣ�����������Ӧ��Ӧ����Ϣ
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("mail");
		String emails = request.getParameter("emailadreess");
		String username = request.getParameter("username");
		String telephone = request.getParameter("telephone");
		PrintWriter out = response.getWriter();
		
		//�ж��û������Ƿ��Ѿ�����
		if(null != email){
			User user = all.emailExist(emails);
			
			if(user!=null)
				out.println(false);
			else
				out.println(true);
			out.flush();
		}
		
		//�ж��û����Ƿ��Ѿ�����
		if(null != username){
			User user = all.UsernameExist(username);
			System.out.println(user);
			if(user!=null)
				out.println(false);
			else
				out.println(true);
			out.flush();
		}
		
		//�ж��ֻ����Ƿ��Ѿ�����
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
