<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  	
    <title>商城首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/admin/publicCss.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/admin/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/admin/Home.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-ui.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/adminJS/AutoImgSblings.js"></script>
  </head>
  
  <body>
  	<!-- 电子商城的首页 -->
    <jsp:include page="../login/head.jsp"></jsp:include>
    <!-- 顶部导航栏 -->
	<jsp:include page="../login/navigation.jsp"></jsp:include>	
	<jsp:include page="Back.jsp"></jsp:include>

	<!-- 最外层的封装盒子 -->
	<div class="Outer-top">
		<!-- 左半边轮播图区域 -->
		<div class="top-left">
			<!-- 左半边剪头 -->
			<div id="toleft">&lt;</div>
			<div class="top-left-box">
				<div class="autoimgbox" id="autoimg">
					<a href="#"><img alt="" src="resource/autoimage/1.jpg" /></a>
					<a href="#"><img alt="" src="resource/autoimage/2.jpg" /></a>
					<a href="#"><img alt="" src="resource/autoimage/3.jpg" /></a>
					<a href="#"><img alt="" src="resource/autoimage/4.jpg" /></a>
					<a href="#"><img alt="" src="resource/autoimage/5.jpg" /></a>
				</div>
				<div class="lablepoint" id="lablepoint">
				<!-- 底部导航小方框 -->
					<ul>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
					</ul>
				</div>
			</div>
			<!-- 右边箭头的样式 -->
			<div id="toright">&gt;</div>
		</div>
		
		<!-- 右半边整体部分 -->
		<div class="top-right">
			<!-- 右半边上半部分新书上架内容 -->
			<div class="top-right-top">
				<!-- 新书上架左边样式 -->
				<div class="top-right-top-left">
					<!-- 具体的文字样式 -->
					<p style="font-weight: bold; font-size:20px; color:rgb(44, 124, 124);" >新书上架</p>
					<p ><label id="proAuthor">${newproducts.getAuthor() }</label>(<label id="proproname">${newproducts.getProductName() }</label>)</p>
					<p id="proDes" style=" text-indent:2em;line-height: 20px; letter-spacing: 3px;" class="newbookinfo">
						${newproducts.getDescription() }
					</p>
				</div>
				<!-- 右边图片的样式 -->
				<dir class="top-right-top-right">
					<img id="proimg" src="${newproducts.getImgurl()}" width="220px" height="280px" />
				</dir>
				<!-- 底部新书导航栏目 -->
				<div class="top-right-top-bottom">
					<label name="lablenumber">01</label>

					<div class="navibox" ><label name="naviboxnumber">1</label>第一件商品
					</div>
					<div class="navibox"><label name="naviboxnumber">2</label>第二件商品
					</div>
					<div class="navibox"><label name="naviboxnumber">3</label>第三件商品
					</div>
				</div>
			</div>
			
			<div class="top-right-bottom">
				<!-- 存放三种图片的盒子 -->
				<div class="image-box">
					<img  src="resource/image/pro.jpg"/>
					<img  src="resource/productImg/11/11/6d3c7635-545a-46b3-bd3e-d2d74f11ed69.jpg"/>
					<img  src="resource/productImg/2/0/2105fbe5-400f-4193-a7db-d7ebac389550.jpg"/>
					
				</div>
			</div>
		</div>
	</div>
	
	<!-- 外部下半边内容 -->
	<div class="Outer-bottom">
		<!-- 公告板样式-->
		<div class="bottom-left">
			<p ><img src="resource/image/billboard.gif"/></p>
			<!-- 公告板内容:使用一个美化功能实现 -->
			<div id="Notice">
			<c:forEach var="notice" items="${requestScope.Noticelist}">
				<h3>${notice.getTitle()}</h3>
				<p style="color:red;font-weight: bold;">
					${notice.getDetail()}
				</p>
			</c:forEach>
			</div>
			
		</div>
		<!-- 本周热卖商品-->
		<div class="bottom-right">
			<!-- 本周热卖文字说明部分 -->
			<div class="bottom-right-left">
				<p><img src="resource/image/hottitle.gif" /></p>

			</div>
			<!-- 本周热卖图片部分 -->
			<div class="bottom-right-right">
				<img width="180px" height="200px" src="resource/image/pro.jpg"/>
			</div>
		</div>
	</div>

    <jsp:include page="foot.jsp"></jsp:include>
  </body>
  
  <script type="text/javascript">
  	//公告栏的美化
  	$("#Notice").accordion();
  
  
  	//放大镜功能,鼠标移动到图片上就进行放大显示
  	$(".image-box img").hover(function(e){
  		//获取坐标位置
  		var x = 1000;
  		var y = 120;
  		
	  	//创建一个盒子
  		var img = document.createElement("img");
  		img.style.position = "absolute";
  		img.style.backgroundColor = "red";
  		img.style.width = "300";
  		img.style.height = "400";
  		img.style.left = x;
  		img.style.top = y;
  		img.src = $(this).attr("src");
		img.id = "bigimg";
  		$("body").append(img);

  	},function(){
  		//移开之后就要删除
  		$("#bigimg").remove();
  		
  	});
  	
  	/*
  		升级：对于三个导航栏进行ajax请求获取
  		分别取出三件最新的商品并且动态的展示出来
  		
  	*/
  	$(".navibox").click(function(){
  		//动态的获取value值
  		var value = $(this).find("label").html();
  		Select(value);
  	
  	});
	
	//查找对应的商品
	function Select(value){
		$.ajax({
  			url:"${pageContext.request.contextPath}/ProductController/HomeConttroler",
  			dataType: "JSON",
  			type:"POST",
  			data: {
  				"pagecontext" : value
  			},
  			/*
			author:"无"
			categoryId:0
			description:"我是介绍信息"
			imgurl:"resource/productImg/none.png"
			pcount:1
			pnum:0
			price:0
			productName:"测试商品1"
			proid:"1"
			*/
  			success: function(data, res, jqxml){
	  			if(res=="success"){
	  				//请求成功对于页面进行修改
	  				$("#proAuthor").html(data["author"]);
	  				$("#proproname").html(data["productName"]);
	  				$("#proimg").attr("src",data["imgurl"]);
	  				$("#proDes").html(data["description"]);
	  			}
	  			
  			}
  		});
	}
  </script>
</html>
