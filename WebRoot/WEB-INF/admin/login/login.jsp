<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>登录界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="resource/css/admin/publicCss.css">
<link rel="stylesheet" type="text/css"
	href="resource/css/admin/loginCss.css">
</head>

<body>
	<!-- 顶部首页 -->
	<jsp:include page="head.jsp"></jsp:include>
	<!-- 顶部导航栏 -->
	<jsp:include page="navigation.jsp"></jsp:include>
	<div class="loginbox">
		<p style="font-weight: bold;">首页 >> 个人中心</p>
		<!-- 左边登录框 -->
		<div class="loginboxleft">
			<img name="logo" src="resource/image/logintitle.gif" />
			<!-- 用户名和密码提交的一个表格 -->
			<form id="loginform" action="${pageContext.request.contextPath}/LoginController" method="POST">
				<table class="formtable">
					<tbody>
						<tr>
							<td>用户名:</td>
							<td><input id="Uname" type="text" name="Username"
								placeholder="请输入用户名" value="${cookie.rememberUsername.value}" /></td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input id="Upass" type="password" name="Password"
								placeholder="请输入密码" /></td>
						</tr>
						<!-- 查看用户是否选中了记住密码或者自动登录 -->
						<tr>
							<td colspan="2"><input name="rememberUser" value="0"
								type="checkbox" />记住用户名 <input name="autologin" value="0"
								type="checkbox" />自动登录</td>
						</tr>
						<tr style="text-align: center;">
							<td colspan="2"><p>
									<input type="image" src="resource/image/loginbutton.gif" />
								</p></td>
						</tr>
						<tr align="center">
							<td colspan="2" style="color:red;" id="errortext">
							<c:if test="${requestScope.errmess!=null}"></c:if>
							<%//用户名称或者密码不正确的时候的提示信息位置%>
								${requestScope.errmess}
							</td>
						</tr>
					</tbody>
				</table>
			</form>

		</div>
		<!-- 右边注册提示框 -->
		<div class="loginboxright">
			<h2>您还没有注册?</h2>
			<p style="font-weight: bold;">注册新会员，享受优惠价格!</p>
			<p>千种图书，供你挑选，注册享受丰富的折扣与优惠，便宜有好货！ 超过千万图书任你挑选</p>
			<p>超人气社团 | 精彩活动每一天，买家更安心，支付宝交易更安全</p>
			<p>
				<a href="${pageContext.request.contextPath}/LoginController?page=goRegister">
				<input id="goRegister" type="image"
					src="resource/image/signupbutton.gif" />
				</a>
			</p>
		</div>
	</div>

</body>
<script type="text/javascript" src="resource/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$("#loginform").submit(function() {
		//判断是否为空
		var username = $("#Uname").val();
		var password = $("#Upass").val();

		if (username == "" && password == "") {
			$("#errortext").html("用户名与密码不能为空");
			return false;
		} else if(username == ""){
			$("#errortext").html("用户不能为空");
			return false;
		} else if(password == ""){
			$("#errortext").html("密码不能为空");
			return false;
		}
	});
</script>
</html>
