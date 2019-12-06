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
 * �Զ���ȫ������
 * ��Ҫ����������ʱ���һЩ������
 * �Լ������Զ���ȫ��ajax����Ĵ���
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
	 * ��Ҫ������Ƕ����Զ���ȫ��ajax����
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * 	��Ҫ������ڸ��ֲ��Ҳ���������
	 * 	���ʹ��Cookie����������?
	 * 
	 * 	��ʵʹ��Cookie��������ʵ����ʷ������Ŀ�Ĺ���
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("Usersession");
		
		if(user!=null){
			//��ֹ������������ݣ���֤���������µ�
//			response.setHeader("Pragma","No-cache");      
//	        response.setHeader("Cache-Control","no-cache");  
//	        response.setDateHeader("Expires", -10);  
			String find = request.getParameter("find");
			//���������Cookie��ȥ���ݿ����
			Map<String,String> map = AutoComplete.getResource(find);
			PrintWriter out = response.getWriter();
			String mapJson = JSON.toJSONString(map);
			System.out.println("�Զ���ȫ"+mapJson);
			out.print(mapJson);
			out.flush();
		}else{
			return ;
			
		}
	}

}
