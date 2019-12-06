<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;Charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加商品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.min.js" ></script>
	<style type="text/css">
		table td{
			padding: 20px;
		}
		select{
			padding: 20px;
		}
		input[type=text]{
			padding: 10px;
		}
	</style>

  </head>
  
  <body>
    
    <center>
    	<form id="myform" action="${pageContext.request.contextPath}/MngTranstation?page=ProductManagerAddSubmit"  method="POST">
    		<table border="1" style="text-align: center;">
    			<tr style="text-align: center;">
    				<td >
    				<label for="pro"> 商品名称</label>
    				</td>
    				<td>
    					<input id="pro" type="text" name="ProductName">	
    				</td>
    				
    			</tr>
    			<tr style="text-align: center;">
    				<td >
    				<label for="pm"> 商品数量</label>
    				</td>
    				<td>
    					<input id="pm" type="text" name="Pnum">
    				</td>
    				
    			</tr>
    			<tr style="text-align: center;">
    				<td >
    				<label for="au">商品作者</label>
    				</td>
    				<td>
    					<input id="au" type="text" name="Author">
    				</td>
    				
    			</tr>
    			<tr style="text-align: center;">
    			<td >
    				<label for="prd"> 商品描述</label>
    				</td>
    				<td>
    					<input id="prd" type="text" name="Description">
    				</td>
    				
    			</tr>
    			<tr style="text-align: center;">
    				<td >
    				<label for="pri"> 商品价格</label>
    				</td>
    				<td>
    					<input id="pri" type="text" name="Price">
    				</td>
    				
    			</tr>
    			<tr style="text-align: center;">
    				<td >
    				<label for="cat"> 商品类别</label>
    				</td>
    				<td>
    					<select name="categoryId" id="cat">
    					<c:forEach begin="1" end="33" var="li">
    						<option>
    							${li}
    						</option>
    						</c:forEach>
    					</select>
    					
    				</td>
    				
    			</tr>
    			
				<tr><td colspan="2">
				<input style="padding: 10px;" type="submit" value="添加"> 
				<input style="padding: 10px;" type="reset" value="重置"> 
				</td></tr>
				
				
				
    		</table>
    		
    	</form>
    	
    </center>
    
  </body>
  <script type="text/javascript">
	  $("#myform").submit(function () {
		  var pm = $("#pm").val();
		  var prd = $("#prd").val();
		  var pro = $("#pro").val();
		  var pri = $("#pri").val();
		  if(pro == ''){
			  alert("商品名称不能为空")
			  return false;
		  }
		  if(pm == ''){
		  	alert("商品数量不能为空")
		  	return false;
		  }
		  if(prd == ''){
			  alert("商品描述不能为空")
			  return false;
		  }

		  if(pri == ''){
			  alert("商品价格不能为空")
			  return false;
		  }

		  $.ajax({
			  url:"${pageContext.request.contextPath}/MngTranstation",
			  type:"POST",
			  // dataType: "JSON",
			  data:{
			  	"page":"ProductManagerAddSubmit",
			  	"ProductName":pro,
			  	"Pnum":pm,
			  	"Description":prd,
			  	"Author":$("#au").val(),
			  	"Price":pri,
			  	"categoryId":$("#cat").find("option:selected").val(),
			  },
			  success: function(data, res, jqxml){
				alert("添加成功");
			  }
		  });
		  return false;
	  });
  
  </script>
</html>
