<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="now" class="java.util.Date" />  
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>顶部导航栏目</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<link rel="stylesheet" type="text/css" href="resource/css/jquery-ui.css">
	<script type="text/javascript" src="resource/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="resource/js/jquery-ui.js"></script>
	<style type="text/css">
		.navibox{
			background-color: white;
			width:100%;
			height:40px;
		}
		.navidate{
			float:left;
			padding: 10px;
			font-size:14px;
			font-weight: bold;
		}
		
		.navibtn{
			padding:7px;
			float:right;
			padding-right: 150px;
			
		}
	
	</style>
	<script type="text/javascript">
		$(function(){
			/*使用框架的按钮样式*/
			$("#navibtn").button();
		});
		
	</script>
  </head>
  
  <body>
    	<div class="top">
    		<img src="resource/image/images/top_11.png" width="100%" height="auto" />
    		<div class="navibox">
    			<div class="navidate">
    				<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" />
    				星期${now.getDay	()}
    			</div>
    			<div class="navibtn"><input id="navibtn" type="button" value="退出系统" /></div>
    		</div>
    	</div>
    	
    	
    	
    
  </body>
</html>
