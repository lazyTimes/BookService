<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>第二个foot界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<style type="text/css">
		.foot{
			position: fixed;
			left:0;
			bottom:10px;
			width:100%;
			height:75px;
			z-index:9999;
		}
		.foot img{
			text-align: center;
			float: center;
			padding-left: 500px;
		}
	</style>
  </head>
  
  <body>
     <div class="foot"> 
    		<img src="resource/image/page_ad.jpg"/>
     </div>
  </body>
</html>
