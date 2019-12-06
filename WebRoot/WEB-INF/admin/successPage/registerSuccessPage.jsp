<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册成功的页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<script type="text/javascript" src="resource/js/jquery-3.3.1.min.js" ></script>
  	<style type="text/css">
  		.box{
  			position:relative;
  			height:150px;
  			width:500px;
  			top:300px;
  			border:1px solid black;
  		}
  		
  		p lable{
  			color:red; 
  			font-size:18px; 
  			font-weight:bold;
  		}
  		
  		.message{
  			color:red;
  			font-size:18px;
  			font-weight:bold;
  			padding:3px;
  		}
  	</style>
  </head>
  		
  		
  <body>
  <center>
	  <div class="box">
		  <c:if test="${requestScope.ActiveCode!=null }">
		  	<p class="activeCode">${requestScope.ActiveCode}</p> 
		  	<p>	<lable >请不要刷新此页面!!!</lable>	</p>
		  	<p>请等待<span class="message">5</span>秒之后页面将自动跳转</p>
		  	如无法跳转，请点击请点击<a href="javascript:void(0);" name="doactive" style="font-size:18px;color:red;" >点我跳转</a>
		  </c:if>
	  </div>
  </center>
   
  </body>
  <script type="text/javascript">
  //定义一个定时器实现定时提交的功能
  	$(document).ready(function(){
  	  $("a[name=doactive]").click(function(){
  	  	window.location.href="/BookService/RegisterController?activeCode="+$(".activeCode").html();
  	  });
  	  
      var count = 5;
  		var ticket = setInterval(function(){
        count--;
        $(".message").html(count);
        if(count==0){
          clearInterval(ticket);
          window.location.href="/BookService/RegisterController?activeCode="+$(".activeCode").html();
        }
      }, 1000);
	 });
  </script>
</html>
