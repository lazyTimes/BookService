<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>M错误页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<script type="text/javascript" src="resource/js/jquery-3.3.1.min.js" ></script>
	<style type="text/css">
		.errorbox{
			position: relative;
			left:0;
			top:50px;
			vertical-align: middle;
			text-align: center;
		}
		
		#text{
			color:red;
			font-size:20px;
			font-weight: bold;
		}
	</style>
  </head>
  
  <body>
    <!-- 电子商城的头部 -->
    <jsp:include page="../login/head.jsp"></jsp:include>
    <!-- 顶部导航栏 -->
	<jsp:include page="../login/navigation.jsp"></jsp:include>	
	<hr />
	<div class="clsnavi">
    	首页 &nbsp;>&nbsp;错误页面
			
    </div>
    
    <div class="errorbox">
    	<img src="resource/image/error.jpg"/>
    	<h1>对不起，当前系统过于繁忙，请稍后再次提交订单</h1>
    	<h3>页面将于<label id="text"> 3 </label> 秒之后跳转</h3>
    	
    </div>
  </body>
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
</html>
