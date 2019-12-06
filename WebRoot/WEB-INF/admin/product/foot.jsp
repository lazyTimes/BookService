<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'foot.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/foot.css">
	

  </head>
  
  <body>
    	
    	<div class="foot"> 
    		<div class="foot-top">
    			<p>CONTACT US   
    				<select name="firendlink">
    					<option>友情链接</option>
    				</select>
    			</p>
    		</div>
    		<span><input id="bottomimg"  type="image" src="resource/image/logo_1.gif"></span>
    		<div class="foot-bottom">
    			COPYRIGHT 2006@ eSHOP ALL RIGHTS RESERVED
    		</div>
    	</div>
  </body>
</html>
