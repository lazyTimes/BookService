<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.itcast.zxd.domain.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品管理界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		.infobox {
			float:left;
		}
		.conditable{
			float:left;
			margin: 20px 0 0 200px;
			
		}
		
		.conditable td{
			text-align: center;
		}

		.condition a{
			margin: 10px;
			float: right;
		}

		.condition a:hover{
			color:red;
			background-color: #007fff;
		}
		
		.product-list{
			float:left;
		}
		
		.product-list table{
			width:100%;
			margin-left: 100px;
			height:35px;
		}
		
		.product-list .productlist{
			margin-left: 100px;
			text-align:center;
			background-color: #cccccc;
			width:100%;
			font-weight: bold;
		}
		
		.product-list table tr th{
			background-color:red;
			font-size:14px;
			width:100px;
			padding-left:30px;
			padding-right: 30px;
		}
	</style>

  </head>
  
  <body>
     <!-- 顶部导航栏目 -->
    <jsp:include page="main/Top.jsp"></jsp:include>
    <!-- 左边的菜单栏目 -->
     <jsp:include page="main/Left.jsp"></jsp:include>
     
     <!-- 整个右半边内容            -->
     <div class="infobox">
     	<!-- 查询条件的筛选 -->
     	<div class="condition">
<%--     		<table class="conditable">--%>
<%--     			<tr><td>商品编号：</td><td><input type="text" id="conPnum" /></td>--%>
<%--     			<td style="padding-left: 200px;">类别：</td><td><input type="text" id="conPnum" /></td></tr>--%>
<%--     			<tr><td>商品名称：</td><td><input type="text" id="conPnum" /></td>--%>
<%--     			<td style="padding-left: 200px;">价格单元区间：</td><td><input type="text" id="from" />-</td>--%>
<%--     			<td><input type="text" id="to" /></td></tr>--%>
<%--     			<tr ><td colspan="7" ><input style="float: right;" type="button" value="查询" id="search"/><input style="float: right;" type="reset" /></td></tr>--%>
<%--     		</table>--%>
     	<a style="padding:10px; font-size: 20px;" href="/BookService/MngTranstation?page=ProductManagerAdd">添加商品</a>
     	</div>

     	<!-- 商品列表 -->
     	<div class="product-list">
     	
     		<div class="productlist">商品列表</div>
     		
     		<table>	
     			<tr><th>商品编号</th><th>商品名称</th><th>商品价格</th><th>商品数量</th><th>商品类别</th><th>编辑</th><th>删除</th></tr>
				
				
				<c:forEach items="${list }" var="list">
					<tr>
					<td style="text-align: center;">${list.getProid() }</td>
					<td style="text-align: center;">${list.getProductName() }</td>
					<td style="text-align: center;">${list.getPrice() }</td>
					<td style="text-align: center;">${list.getPnum() }</td>
					<td style="text-align: center;">${list.getCategoryId() }</td>
					<td style="text-align: center;"><input type="button" value="删除" /></td>
					<td style="text-align: center;"><input type="button" value="修改" /></td>
					</tr>
				</c:forEach>
			
     		</table>
     	
     	</div>
     </div>
     
     <!-- 底部图片 -->
     <jsp:include page="main/Bottom.jsp"></jsp:include>
  </body>
</html>
