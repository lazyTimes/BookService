package com.itcast.zxd.Filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpRetryException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 处理中文乱码的过滤器
 * 每次进行页面取值或者发送数据的时候都能通过过滤器进行转码操作
 */
@WebFilter("/EncodingDoFilter")
public class EncodingDoFilter implements Filter {

    

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//处理请求乱码
		HttpServletRequest httprequest = (HttpServletRequest) request;
		//System.out.println("页面转码处理");
		HttpServletRequest myRequest = new MyRequest(httprequest);
		//处理响应乱码
		response.setContentType("text/html;charset=utf-8");
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	

}

/**
 * 	内部采用了自我实现的一个请求处理器
 * */
class MyRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	//是否进行了编码
	private boolean hasEncode;
	public MyRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		//获取请求方式
		String method = request.getMethod();
		if(method.equalsIgnoreCase("POST")){
			try {
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(method.equalsIgnoreCase("GET")){
			//如果为get请求
			Map<String, String[]> paramterMap = request.getParameterMap();
			//确保get转码只转码一次
			if(!hasEncode){
				for(String paramterName : paramterMap.keySet()){
					String values[] = paramterMap.get(paramterName);
					if(null != values){
						for(int x=0; x<values.length; x++){
							//使用String类进行包装并且转码
							try {
								values[x] = new String(values[x].getBytes("ISO-8859-1"),"utf-8");
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				hasEncode = true;
			}
			return paramterMap;
			
		}
		return super.getParameterMap();
	}
	
	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		Map<String, String[]> paramMap = getParameterMap();
		String[] values = paramMap.get(name);
		if(values == null)
			return null;
		//取回参数的第一个值
		return values[0];
	}
	
	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> paramMap = getParameterMap();
		String[] values = paramMap.get(name);
		return values;
	}
}
