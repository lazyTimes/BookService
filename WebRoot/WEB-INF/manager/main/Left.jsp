<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>左边导航栏</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		.leftnavigationbox{
			width:15%;
			float:left;
			height:auto;
		}
		.leftnavigationbox a{
			color:black;
		}
		.navi{
			padding:20px;
			text-align: center;
			border-bottom: 1px solid #cccccc;
			font-size:14px;
			font-weight: bold;
		}
		.navi:HOVER {
			background-color:red;
			font-weight: bold;
			color:white;
		}
	</style>

  </head>
  
  <body>
<%-- MngTranstation --%>
    <div class="leftnavigationbox">
    	<a href="${pageContext.request.contextPath}/MngTranstation?page=ProductManager"><div class="navi">商品管理</div></a>
    	<a href="javascript:void(0);"><div class="navi">销售榜单</div></a>
    	<a href="javascript:void(0);"><div class="navi">订单管理</div></a>
    	<a href="javascript:void(0);"><div class="navi">公告管理</div></a>
    </div>
  </body>
</html>
