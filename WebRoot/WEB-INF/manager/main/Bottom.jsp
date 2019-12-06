<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>底部提示图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		.bottom{
			position: fixed;
			bottom:0;
			left:100px;
			z-index:9999;
			width: 80%;
			height:70px;
			text-align: center;
		}
	
	</style>

  </head>
  
  <body>
  	<div class="bottom">
  	
  		<img src="resource/image/images/top_10.png" width="100%" height="auto" />
  	
  	</div>
    
  </body>
</html>
