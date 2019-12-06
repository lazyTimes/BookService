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
    <title>订单提交的页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="订单提交内容">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<link rel="stylesheet" type="text/css" href="resource/css/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/Order.css">
	<script type="text/javascript" src="resource/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="resource/js/jquery-ui.js"></script>
	
	
	
</head>
<body>
	<!-- 电子商城的头部 -->
    <jsp:include page="../login/head.jsp"></jsp:include>
    <!-- 顶部导航栏 -->
	<jsp:include page="../login/navigation.jsp"></jsp:include>	
	<jsp:include page="Back.jsp"></jsp:include>
	<hr />
	<div class="clsnavi">
    	首页 &nbsp;>&nbsp;确认订单的界面

    </div>
    <!-- 订单页面 -->
	<div class="orderBox">
		<!-- 导航图片 -->
		<div class="naviimage">
			<img src="resource/image/buy2.gif"/>
		</div>
		<div class="Userinfo">你好,?欢迎来到网上书城结算中心</div>
		
		<!-- 购物车详细信息的展示 -->
		<form action="${pageContext.request.contextPath}/ProductController/OrderSubmitController" id="valiOrder" method="POST">
		<div class="Order-item">
			<%int x=0; %>
			<table>
				<tbody>
					<!-- 商品展示 -->
					<tr><th>序号</th><th width="300px">商品名称</th><th>价格</th><th>类别</th><th>数量</th><th>小计</th></tr>
					<c:forEach items="${Cart}" var="product">
					<tr><td><%=++x %><img src="${prodcut.getImgUrl()}"/></td><td>${product.getProductName()}</td><td>${product.getPrice()}</td><td>${product.getCategoryId()}</td><td>${product.getPcount()}</td><td>${product.getPcount()*product.getPrice()}</td></tr>
					</c:forEach>	
					<tr><th style="border: none;padding:5px;border-top: 5px solid blue;opacity: 0.5;" colspan="6"></th></tr>
				
				</tbody>
			
			</table>
			
			<!-- 收货地址等信息 -->
			<div class="order-address">
				<div class="receiveAddress"><label class="label1" for="receive_address">收获地址:</label><input style="width: 400px;" name="receive_address"  type="text" id="receive_address" /><span style="color:red;padding:3px;">*</span></div>
				<div class="receiveAddress"><label class="label1" for="receive_peaple">收货人:&nbsp;&nbsp;&nbsp;</label><input type="text" name="receive_peaple"  id="receive_peaple" /><span style="color:red;padding:3px;">*</span></div>
				<div class="receiveAddress"><label class="label1" for="telephone">联系方式:</label><input type="text" name="receive_telephone" id="telephone"/><span style="color:red;padding:3px;" id="star">*</span></div>
			</div>
			<!-- 选择支付方式 -->
			<div class="paySelcter">
				<h3>请选择支付方式</h3>
				<div id="bank">
    			<input checked="checked" type="radio" id="pay" name="paytype"><label for="pay"><img src="resource/image/bank_img/abc.bmp"/></label>
    			<input type="radio" id="pay1" name="paytype"><label for="pay1"><img src="resource/image/bank_img/bc.bmp"/></label>
    			<input type="radio" id="pay2" name="paytype"><label for="pay2"><img src="resource/image/bank_img/bcc.bmp"/></label>
    			<input type="radio" id="pay3" name="paytype"><label for="pay3"><img src="resource/image/bank_img/beijingnongshang.bmp"/></label>
    			<input type="radio" id="pay4" name="paytype"><label for="pay4"><img src="resource/image/bank_img/cib.bmp"/></label>
    			<input type="radio" id="pay5" name="paytype"><label for="pay5"><img src="resource/image/bank_img/ccb.bmp"/></label>
    			<input type="radio" id="pay6" name="paytype"><label for="pay6"><img src="resource/image/bank_img/cmbc.bmp"/></label>
    			<input type="radio" id="pay7" name="paytype"><label for="pay7"><img src="resource/image/bank_img/dy.bmp"/></label>
    			<input type="radio" id="pay8" name="paytype"><label for="pay8"><img src="resource/image/bank_img/guangda.bmp"/></label>
    			<input type="radio" id="pay9" name="paytype"><label for="pay9"><img src="resource/image/bank_img/ningbo.bmp"/></label>
 				</div>
				
			</div>
			<div style="display: none;" id="mhconfirm">
				<label style="font-size:14px;font-weight: bold;">确认提交订单信息吗？</label>
				<p><label style="color:red;font-size:15px">注意填写必要信息，否则无法提交订单！！！</label></p>
			</div>
			
			<!-- 订单的提交按钮 -->
			<div class="subOrder">
				<img id="sub" style="cursor: pointer;" src="resource/image/gif53_029.gif"/>
			</div>
		</div>
		</form>
	</div>

</body>
<script type="text/javascript" src="resource/js/jquery.validate.js"></script>
<script type="text/javascript" src="resource/js/adminJS/publiJSc.js"></script>
<script type="text/javascript">
		
	$( "#bank" ).buttonset();
	//对于表单字段的必填设定
	$("#valiOrder").validate({
		onkeyup: false,
		rules:{
			"receive_address":{
				required:true
			},
			"receive_peaple":{
				required:true
			},
			"receive_telephone":{
				required:true,
				
			},
			
		},
		messages:{
			"receive_address":{
				required:"请输入收货地址"
			},
			"receive_peaple":{
				required:"请输入收货人信息"
			},
			"receive_telephone":{
				required:"请输入手机号码",
				phone:"手机号码格式不正确"
			}	
		},
		errorClass:"errormessage"
	
	});
	
	$("#sub").click(showDialog);
	
	//显示对话框
	function showDialog(){
		$("#mhconfirm").dialog({
			modal: true,
			resizable: false,
			draggable: false,
			title: "确认提交表单",
			show:{
				effect:"blind",
				duration:100
			},
			width: 300,
			height: 200,
			buttons: [{
				text:"确认",
				click: function(){
					console.log('---');
					var form = $("#valiOrder");
					//使用自定义提交
					return form.submit();
					
				}
			},{
				text:"取消",
				click: function(){
					$(this).dialog("close");
					return;
				}
			}]
			
		});
		
	}
	
</script>
</html>