package com.itcast.zxd.Controller.client;

import com.alibaba.fastjson.JSON;
import com.itcast.zxd.domain.User;
import com.itcast.zxd.utils.AutoComplete;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 自动补全的请求
 * 主要接受搜索的时候的一些列请求
 * 以及对于自动补全的ajax请求的处理
 */
@WebServlet("/ProductController/AutoCompletecController")
public class AutoCompletecController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoCompletecController() {
        super();
        
    }

    /**
	 * 主要处理的是对于自动补全的ajax请求
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * 	主要处理关于各种查找操作的请求
	 * 	如何使用Cookie处理数据呢?
	 * 
	 * 	其实使用Cookie做的事是实现历史搜索栏目的功能
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("Usersession");
		
		if(user!=null){
			//禁止浏览器缓存数据，保证数据是最新的
//			response.setHeader("Pragma","No-cache");      
//	        response.setHeader("Cache-Control","no-cache");  
//	        response.setDateHeader("Expires", -10);  
			String find = request.getParameter("find");
			//如果不存在Cookie就去数据库查找
			Map<String,String> map = AutoComplete.getResource(find);
			PrintWriter out = response.getWriter();
			String mapJson = JSON.toJSONString(map);
			System.out.println("自动补全"+mapJson);
			out.print(mapJson);
			out.flush();
		}else{
			return ;
			
		}
	}

}
