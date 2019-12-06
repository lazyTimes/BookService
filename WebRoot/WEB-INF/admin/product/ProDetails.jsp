<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <title>商品详情页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/ProDetails.css">
  </head>
  
  <body>
    <!-- 电子商城的头部 -->
    <jsp:include page="../login/head.jsp"></jsp:include>
    <!-- 顶部导航栏 -->
	<jsp:include page="../login/navigation.jsp"></jsp:include>	
	<jsp:include page="Back.jsp"></jsp:include>
	<!-- 需要增加一个分类导航栏 -->
	<div class="clsnavi">
    	首页 &nbsp;>&nbsp; 全部 &nbsp;>&nbsp; ?
    </div>
    
    <div class="Outer-box">
    	<!-- 左半边商品展示 -->
		<jsp:include page="NaviLeft.jsp"></jsp:include>
    	
    	<!-- 右边栏目 -->
    	<div class="right">
    		<div class="right-top">
    			<!-- 图片样式 -->
    			<img src="resource/image/page_ad.jpg" width="100%"/>
    		</div>
    		<div class="right-bottom">
    			<!--  -->
    			<form method="POST" action="${pageContext.request.contextPath }/ProductController/CartController?page=AddProduct">
    			<div class="bottom-left">
    				<input type="hidden" name="productId" value="${Product.getProid() }" />
    				<img src="${Product.getImgurl() }" width="60%" height="100%" />
    				
    				
    				<div class="bottom-bottom">
    				<!-- 购物按钮 -->
    					
    				
    					<input type="image" src="resource/image/buy.gif" width="150px" height="50px" /></a>
    				</div>
    				
    			</div>
    			</form>
    			<div class="bottom-right">
    				<table>
    					<tr><td style="border-bottom: 5px solid #cccccc;" colspan="2"><img src="resource/image/icon1.png" />${Product.getProductName()}</td></tr>
    					<tr><td ><img width="140px" height="30px" src="resource/image/infobg.gif" /></td><td>作者</td></tr>
    					<tr><td ><img src="resource/image/priceicon.png" /></td><td>${Product.getPrice() }</td></tr>
    					<tr><td ><img src="resource/image/manager.png" /></td><td>${Product.getAuthor() }</td></tr>
    					<tr><td style="border-bottom: 5px solid #cccccc;"><lable style="font-size:18px; font-weight:bold;">内容简介</lable></td></tr>
    					<tr><td colspan="2">
    						${Product.getDescription() }
    					</td></tr>
    				</table>
    			</div>
    			
    			
    		</div>
    	</div>
    </div>
  </body>
</html>
