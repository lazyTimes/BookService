<%@page import="com.itcast.zxd.Service.UserLoginService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.itcast.zxd.domain.*" %>
<%@ page language="java" import="com.itcast.zxd.Service.UserLoginService" %>
<%@ page language="java" import="com.itcast.zxd.Service.CartService" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>中转站</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <!-- 内部实现了用户自动登录 -->
    <%
    	//首先如果用户已经登录默认跳转到首页
    	User usersess = (User) request.getSession().getAttribute("Usersession");
		if(usersess!=null){
			request.getRequestDispatcher("ProductController/HomeConttroler").forward(request, response);
			return;
		}
    
    	//验证用户自动登录信息的代码
    	Cookie[] cookies = request.getCookies();
    	String username = null;
    	String password = null;
    	if(cookies!=null){
	    	for(int x=0; x<cookies.length; x++){
	    		if(cookies[x].getName().equals("aUsername"))
	    			username = cookies[x].getValue();
	    		if(cookies[x].getName().equals("aPassword"))
	    			password = cookies[x].getValue();
	    	}
    	}
    	
    	//System.out.println("自动登录信息"+username);
    	//System.out.println("自动登录信息"+password);
    	//如果都不为空	
    	if(null!=username && null!=password){
    		User user = new UserLoginService().
    			findUserByUsernameAndPassword(username, password);
    		if(null != user){
    			CartService service = new CartService();
    			service.createCart(username);
    			request.getSession().setAttribute("Usersession", user);
    			//自动登录自动跳转到首页
    			request.getRequestDispatcher("/ProductController/HomeConttroler").forward(request, response);
    			return;
    		}
    	}
    	
    
     %>
     <!-- 自动跳转到用户登录的界面 -->
    
  	<jsp:forward page="/WEB-INF/admin/login/login.jsp"></jsp:forward>
  </body>
</html>
