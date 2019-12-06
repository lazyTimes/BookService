<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>用户登录界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/register.css">

	<script type="text/javascript" src="resource/js/jquery-3.3.1.min.js" ></script>
  </head>
  
  <body>
  <!-- 顶部首页 -->
  	<jsp:include page="head.jsp"></jsp:include>
  <!-- 顶部导航栏 -->
  	<jsp:include page="navigation.jsp"></jsp:include>
   	
   	<!-- 网上商城用户注册 -->
   	<div id="divcontent">
   		<!-- 如果不为空则输出错误的信息 -->
   		<c:if test="${requestScope.errormessage!=null}">
   		
   			${requestScope.errormessage}
   		</c:if>
   		<form id="valiUser" action="${pageContext.request.contextPath}/RegisterController" method="POST">
   			<h1>新用户注册</h1>
   			<table  id="registerInfo" style="margin-left: 150px;">
   				<tr><td>邮箱:</td><td colspan="3"><input id="email" type="text" name="mail"  /><span id="emailexist"></span></td></tr>
   				<tr><td>用户名:</td><td><input id="Usersssss"  type="text" name="username" /></td></tr>
   				<tr><td>密码:</td><td><input id="pass" name="userpassword" type="password"  /></td></tr>
   				<tr><td>确认密码:</td><td><input name="repassword" type="password"  /></td></tr>
   				<tr><td >性别:</td><td>男:<input checked="checked" type="radio" name="gender" value="0" />
   				女:<input type="radio" name="gender" value="1" /></td></tr>
   				<tr><td>联系电话:</td><td><input id="telephone" name="telephone" type="text" /></td></tr>
   				<tr><td>个人介绍:</td><td ><textarea name="introduction" style="resize: none;overflow-x: visible;" rows="5" cols="60"  ></textarea></td></tr>
   			
   			</table>
   			<hr />
   			<h1>注册验证</h1>
   			<div id="valiImg" class="valiImg">
   				<p>输入验证码: <input type="text" name="imageCodes" />
   				<a href="javascript:void(0);" id="changeImage" >看不清?换一张图片</a></p>
   				<p><img src="${pageContext.request.contextPath}/imageCode" /></p>
   			</div>
   			<hr />
   			<div class="determine">   			
   				<input type="image" src="resource/image/signup.gif" name="submit" />   				
   			</div>
   			
   		</form>
   	 </div>
  </body>
	<script type="text/javascript" src="resource/js/jquery.validate.js" ></script>
  	<script type="text/javascript" >
  	jQuery.validator.addMethod("phone", function(value, element, param){
    //方法中又有三个参数:value:被验证的值， element:当前验证的dom对象，param:参数(多个即是数组)
    //alert(value + "," + $(element).val() + "," + param[0] + "," + param[1]);
    return new RegExp(/^1[3458]\d{9}$/).test(value);

}, "手机号码不正确");
  	
  	
  		$(function(){
  		
  		$("#valiUser").validate({
  			onkeyup: false,
  			rules:{
		      mail:{
		        required:true,
		        email:true,
		        remote: {
		        	url:"${pageContext.request.contextPath}/UserAreadyexistController?emails="+$("#email").val(),
		        	type:"GET",
					data: {                     //要传递的数据
						emailadreess: function() {
							return $("#email").val();
						}
					}


		        }
		      },
		      username:{
		        required:true,
		        rangelength:[6,20],
		        remote: {
		        	url:"${pageContext.request.contextPath}/UserAreadyexistController",
		        	type:"GET",

		        }
		       	
		      },
		      userpassword:{
		        required:true,
		        rangelength:[6,18]
		      },
		      repassword:{
		        required:true,
		       	equalTo:'#pass'
		      },
		      gender:{
		      	 required:true,
		      },
		      telephone:{
		      	required:true,
		      	phone:true,
		      	remote: {
		        	url:"${pageContext.request.contextPath}/UserAreadyexistController",
		        	type:"GET",

		        }
		      }
		     
		    },
		    messages:{
		      mail:{
		        required:"邮箱必须存在",
		        email:"邮箱格式必须正确",
		        remote:"对不起该邮箱已被人使用"
		      },
		      username:{
		        required:"用户名称必须存在",
		        rangelength:$.validator.format("用户名称长度必须为{0}到{1}位"),
		        remote:"用户名已经存在"
		      },
		      userpassword:{
		        required:"密码必须存在",
		        rangelength:$.validator.format("密码称长度必须为{0}到{1}位")
		      },
		      repassword:{
		        required:"请再次确认密码",
		       	equalTo:"两次密码不一致"
		      },
		      gender:{
		      	 required:"请选择性别"
		      },
		      telephone:{
		      	required:"请输入手机号码",
		      	phone:"手机号码不正确",
		      	remote:"手机号码已经被注册"
		      }
			
		    },
		    errorClass: "errormessage"
  		
  		});
  		
  		
  		
  		});
  		
  		
  		
  	</script>
  	
  	
</html>
