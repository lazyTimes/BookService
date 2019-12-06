<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/NaviLeft.css">
  </head>
  
  <body> 
  <!-- 左半边分类导航 -->
 	<div class="left">
 		<h2>分类</h2>
 		<ul>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=2">计算机</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=28">外语</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=11">艺术</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=3">经营</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=8">人文社科</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=10">少儿</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=29">生活</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=12">进口原版</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=13">科技</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=14">考试</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=7">励志</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=9">学术</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=16">小说</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=17">古籍</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=18">哲学</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=19">旅游</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=20">法律</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=21">宗教</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=22">历史</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=23">地理</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=24">政治</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=25">军医</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=26">心理学</a></li>
 			<li><a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=27">传记</a></li>
 		</ul>
 	</div>
  </body>
</html>
