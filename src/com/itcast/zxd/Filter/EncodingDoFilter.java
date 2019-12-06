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
 * ������������Ĺ�����
 * ÿ�ν���ҳ��ȡֵ���߷������ݵ�ʱ����ͨ������������ת�����
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
		//������������
		HttpServletRequest httprequest = (HttpServletRequest) request;
		//System.out.println("ҳ��ת�봦��");
		HttpServletRequest myRequest = new MyRequest(httprequest);
		//������Ӧ����
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
 * 	�ڲ�����������ʵ�ֵ�һ����������
 * */
class MyRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	//�Ƿ�����˱���
	private boolean hasEncode;
	public MyRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		//��ȡ����ʽ
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
			//���Ϊget����
			Map<String, String[]> paramterMap = request.getParameterMap();
			//ȷ��getת��ֻת��һ��
			if(!hasEncode){
				for(String paramterName : paramterMap.keySet()){
					String values[] = paramterMap.get(paramterName);
					if(null != values){
						for(int x=0; x<values.length; x++){
							//ʹ��String����а�װ����ת��
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
		//ȡ�ز����ĵ�һ��ֵ
		return values[0];
	}
	
	@Override
	public String[] getParameterValues(String name) {
		Map<String, String[]> paramMap = getParameterMap();
		String[] values = paramMap.get(name);
		return values;
	}
}
