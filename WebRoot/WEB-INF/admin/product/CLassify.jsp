<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.itcast.zxd.domain.Product" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>全部商品分类目录</title>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
  	<link rel="stylesheet" type="text/css" href="resource/css/admin/CLassify.css">
  </head>
  
  <body>
  	<!-- 电子商城的头部 -->
    <jsp:include page="../login/head.jsp"></jsp:include>
    <!-- 顶部导航栏 -->
	<jsp:include page="../login/navigation.jsp"></jsp:include>	
	<jsp:include page="Back.jsp"></jsp:include>
	<!-- 需要增加一个分类导航栏 -->
	<div class="clsnavi">
    	首页 &nbsp;>&nbsp; 旅游 &nbsp;>&nbsp; 大勇和小花的日记
	</div>
	
	<!-- 分类最外层大盒子 -->
	<div class="Outer-box">
		<!-- 左半边商品展示 -->
		<jsp:include page="NaviLeft.jsp"></jsp:include>
		
		<!-- 右半边商品展示 -->
		<div class="right">
	 		<div class="right-top">商品目录</div>
	 		<div class="right-center"><lable class="lable1">计算机类</lable>		共 <label style="font-size:20px;color:red;"> ${requestScope.proCount} </label>种商品</div>
	 		<!-- 顶部中间主要分类的展示 -->
	 		<div class="right-center-public">
	 			<ol>
	 				<li><img src="resource/image/miniicon2.gif"/></li>
	 				<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=30">文学</a></li>
	 				<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=27">传记</a></li>
	 				<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=31">动漫</a></li>
	 				<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=11">艺术</a></li>
	 				<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=33">摄影</a></li>
	 				<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=32">设计</a></li>
	 			</ol>
	 		
	 		</div>
	 		<div class="right-center-image">
	 			<img src="resource/image/productlist.gif" width="100%" height="30px" />
	 		</div>
	 		
	 		<!-- 右边的商品详情盒子 -->
	 		<div class="right-probox">
	 			<c:forEach items="${productlist}" var="Product">
	 			<div class="probox"><a href="${pageContext.request.contextPath}/TransferStation?page=goOne&productId=${Product.getProid()}" }>
		 			<img src="${Product.getImgurl() }" />
		 			</a>
		 			<p><a href="#">${Product.getAuthor()}</a></p>
		 			<p><a href="#">作者：${Product.getAuthor()}</a></p>
	 			</div>
	 			</c:forEach>
	 			
	 		</div>
	 	</div>
	</div>
	
	<jsp:include page="foot.jsp"></jsp:include>
  </body>
</html>
