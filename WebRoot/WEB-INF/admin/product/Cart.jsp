<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<title>我的购物车界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="resource/css/admin/publicCss.css">
<link rel="stylesheet" type="text/css"
	href="resource/css/admin/Cart.css">
<link rel="stylesheet" type="text/css" href="resource/css/jquery-ui.css">
</head>

<body>
	<!-- 电子商城的头部 -->
	<jsp:include page="../login/head.jsp"></jsp:include>
	<!-- 顶部导航栏 -->
	<jsp:include page="../login/navigation.jsp"></jsp:include>
	<jsp:include page="Back.jsp"></jsp:include>
	<div class="clsnavi">首页 &nbsp;>&nbsp;购物车</div>

	<div class="Outer-box">
		<div class="inner-image">
			<img src="resource/image/page_ad2.jpg" />
		</div>
		<div class="inner-image2">
			<img src="resource/image/buy1.gif" />
		</div>
		<div class="inner-table">
			<%
				int x = 1; //序号自增
			%>
			<!-- 动态展示所有的页面 -->
			<!-- 绘制一个7*n的表格 -->
			<form
				action="${pageContext.request.contextPath}/ProductController/OrderController"
				method="post">

				<table>
					<tbody>
						<tr>
							<th>序号</th>
							<th>商品名称</th>
							<th>价格</th>
							<th>数量</th>
							<th>库存</th>
							<th>小计</th>
							<th>取消</th>
						</tr>
						<c:forEach items="${Cart}" var="product">
							<tr>
								<td><%=x++%><input id="productId" type="hidden"
									name="productId" value="${product.getProid()}" /></td>
								<td>${product.getProductName()}</td>
								<td>${product.getPrice()}</td>
								<!-- 购物中商品的购买数量 -->
								<td width="100px"><input type="button" value="-"
									name="reducePcount" /> <label id="pCount">${product.getPcount()}</label>
									<input type="button" value="+" name="addPcount" /></td>
								<td><label id="pnum">${product.getPnum()}</label></td>
								<td><fmt:formatNumber type="number"
										value="${product.getPcount()*product.getPrice()}"
										pattern="#.00" /></td>
								<td><img name="cancel" style="cursor: pointer;"
									src="resource/image/error.jpg" width="25px" height="25px" /></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="3"><a
								href="${pageContext.request.contextPath}/TransferStation?page=goAll">
									<img id="returnBuy" src="resource/image/gwc_jx.gif" />
							</a></td>
							<td colspan="4"><input type="image"
								src="resource/image/gwc_buy.gif"></td>
						</tr>
						<tr>
							<th colspan="7" style="text-align: right;"><label
								name="numPrice">总价</label></th>
						</tr>
					</tbody>
				</table>
				<!-- 错误信息的提示框 -->
				<div style="display: none;" id="mhconfirm">
					<label style="font-size:14px;font-weight: bold;">确认删除改商品吗？</label>
				</div>
			</form>


		</div>

	</div>
	<jsp:include page="foot2.jsp"></jsp:include>
</body>
<script type="text/javascript" src="resource/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="resource/js/jquery-ui.js"></script>
<script type="text/javascript">
	//取消指定商品
	$(".inner-table img[name=cancel]").click(cancelProduct);
	//增加商品在购物车的数量
	$(".inner-table input[name=addPcount]").click(AddPcount);
	//减少购物车之内商品的数量
	$(".inner-table input[name=reducePcount]").click(ReducePcount);

	//让购物车之内某件商品的数量-1
	function ReducePcount() {
		//如果添加的量超过99则不允许添加
		var pnum = $(this).parent().parent().find("#pnum").html();
		var pcount = $(this).parent().find("#pCount").html();
		if (parseInt(pcount) <= 1) {
			console.log(pcount + "----" + pnum);
			$(this).addClass("mydisabledNo");
			return;
		}
		//首先需要动态的获取ProductId
		var productId = $(this).parent().parent().find("#productId").val();
		jQuery.ajax({
			type : "GET",
			data : {
				page : "reducePcount",
				productId : productId
			},
			url : "${pageContext.request.contextPath}/ProductController/CartController",
			//删除成功之后要移除掉此td
			success : function(data, textStatus, jqXHR) {
				//如果状态正确并且请求成功
				if (textStatus == "success") {
					window.location.href = "${pageContext.request.contextPath}/ProductController/CartController?page=ShowCart";

				}
			}
		});


	}
	//增加商品的购买数量
	function AddPcount() {
		//如果添加的量超过99则不允许添加
		var pnum = $(this).parent().parent().find("#pnum").html();
		var pcount = $(this).parent().find("#pCount").html();
		if (parseInt(pcount) >= parseInt(pnum)) {
			console.log(pcount + "----" + pnum);
			$(this).addClass("mydisabledNo");
			return;
		}
		//首先需要动态的获取ProductId
		var productId = $(this).parent().parent().find("#productId").val();
		jQuery.ajax({
			type : "GET",
			data : {
				page : "AddPcount",
				productId : productId
			},
			url : "${pageContext.request.contextPath}/ProductController/CartController",
			//删除成功之后要移除掉此td
			success : function(data, textStatus, jqXHR) {
				//如果状态正确并且请求成功
				if (textStatus == "success") {
					window.location.href = "${pageContext.request.contextPath}/ProductController/CartController?page=ShowCart";

				}
			}
		});
	}

	//从购物篮当中移除某件商品,同时使用了自定的对话框进行样式装饰
	function cancelProduct() {
		$("#mhconfirm").dialog({
			modal : true,
			resizable : false,
			draggable : false,
			title : "确定要删除该商品吗？",
			show : {
				effect : "blind",
				duration : 100
			},
			width : 300,
			height : 200,
			buttons : [ {
				text : "确认",
				click : function() {
					//获取到对应的productId
					var productId = $(this).parent().parent().find("#productId").val();
					//删除商品的Ajax请求
					jQuery.ajax({
						type : "GET",
						data : {
							page : "delete",
							productId : productId
						},
						url : "${pageContext.request.contextPath}/ProductController/CartController",
						//删除成功之后要移除掉此td
						success : function(data, textStatus, jqXHR) {
							//如果状态正确并且请求成功
							if (textStatus == "success") {
								if (data) {
									//请求成功之后需要将地址重定向一次
									window.location.href = "${pageContext.request.contextPath}/ProductController/CartController?page=ShowCart";

								}

							}
						}
					});

				}
			}, {
				text : "取消",
				click : function() {
					$(this).dialog("close");
					return;
				}
			} ]
		});
	}
</script>
</html>
