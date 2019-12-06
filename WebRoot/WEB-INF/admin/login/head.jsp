<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <title>顶部导航栏目</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="resource/css/admin/headtable.css">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">


  </head>
  
  <body>
  <center>
  <div class="head">
	  <table cellspacing="0" class="headtable" width="100%">
	  	<tr><td>
	  		<a style="background-color: inherit;" href="${pageContext.request.contextPath}/index.jsp">
	    	<img alt="" src="resource/image/logo.png" width="200px" height="70px" border="0" />
	    	</a>
	    	</td>
	    	<td style="text-align: right; float:right;padding-right: 100px;">
	    	
	    	<a href="${pageContext.request.contextPath}/ProductController/CartController?page=ShowCart">
	    	<img src="resource/image/cart.gif" height="25px" width="25px" />购物车 </a>
	    	|<a href="#">帮助中心 </a>
	    	|<a href="#">我的账户 </a>
	    	<c:if test="${sessionScope.Usersession!=null }">
	    		|<a href="${pageContext.request.contextPath}/LoginController?page=exit"><label style="color:red;cursor: pointer;">注销</label></a>
	    	</c:if>
	    	<c:if test="${sessionScope.Usersession==null }">
	    		|<a href="${pageContext.request.contextPath}/LoginController?page=goRegister">新用户注册 </a>
	    	</c:if>
	    	
	    	</td>
	    	
	    </tr>
	    
	    
	    </table>
    </div>
    </center>
  </body>
</html>
