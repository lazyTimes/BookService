<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>返回栏目</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<style type="text/css">
		.back{
			width:100%;
			height:20px;
			background-color: #2c7c7c;
			text-align: right;
			position: relative;
		
		}
		.back a{
			color:white;
			
			font-weight: bold;
			font-size:16px;
		}
	</style>
  </head>
  
  <body>
    <div class="back">
    	<a href="javascript:void(-1)" onclick="toback()">返回</a>
    </div>
  </body>
  <script type="text/javascript">
  	//返回上一页
  	function toback(){
  		window.history.back(-1);
  	}
  </script>
</html>
