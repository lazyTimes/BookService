package com.itcast.zxd.Controller.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.zxd.domain.User;

/**
 * 处理所有关于成功信息的控制器
 */
@WebServlet("/ProductController/SuccessController")
public class SuccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessController() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess!=null)
			doPost(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess==null)
			return;
		//获取类型
		String page = request.getParameter("page");
		if(page!=null && page.equals("success")){
			request.getRequestDispatcher("/WEB-INF/admin/successPage/SubmitSucess.jsp").forward(request, response);
			return;
		}else if(page!=null && page.equals("error")){
			request.getRequestDispatcher("/WEB-INF/admin/error/error.jsp").forward(request, response);
			return;
		}
	}

}
