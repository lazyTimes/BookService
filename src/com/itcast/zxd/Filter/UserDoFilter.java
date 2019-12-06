package com.itcast.zxd.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.itcast.zxd.domain.User;

/**
 * �Ƿ���¼��������
 * ��ֹ�û�û�е�¼ֱ��ͨ��url�ҵ���ҳ
 */
@WebFilter("/UserDoFilter")
public class UserDoFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		User usersess = (User) req.getSession().getAttribute("Usersession");
		if(null != usersess){
			chain.doFilter(request, response);
			return;
			
		}else{
			System.out.println("���û��Ƿ���¼");
			req.getRequestDispatcher("/index.jsp").forward(req, response);
			return;
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
