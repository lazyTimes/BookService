<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
  
    <title>订单提交成功的页面</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="resource/js/jquery-3.3.1.min.js" ></script>
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<style type="text/css">
		.demos{
			margin-top: 200px;
			text-align: center;
		}
		#text{
			color:red;
			font-weight:bold;
			font-size: 22px;
		}
	</style>
	
	<script type="text/javascript">
		var index = 3;
  	$(document).ready(function(){
  		//定时器
  		var ticket = setInterval(function() {
	  	if(index>=0){
	  		$("#text").html(index);
	  		index--;
  		}else{
  			clearInterval(ticket);
  			window.location.href =
	  		"${pageContext.request.contextPath}/ProductController/CartController?page=ShowCart";
  		
  		}
  		
  		}, 1000);
  	
  	
  	});

	</script>
  </head>
  
  <body background="resource/image/demos.jpg" style=" background-repeat:no-repeat;background-size:100% 100%; background-attachment:fixed;">
	<h1></h1>
	
	<div class="demos">
		<img src="resource/image/true.png">
		<h1>恭喜您提交订单成功，请不要刷新此页面稍后会自动跳转
		
		</h1>
		<h3>页面会在&nbsp;<label id="text">3</label>&nbsp;秒之后跳转</h3>
	</div>
</body>

</html>
