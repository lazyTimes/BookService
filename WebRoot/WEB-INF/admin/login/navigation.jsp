<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页导航栏</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="resource/css/admin/navigationtop.css">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/publicCss.css">
	<link rel="stylesheet" type="text/css" href="resource/css/admin/jquery-ui.css">
	<script type="text/javascript" src="resource/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="resource/js/jquery-ui.js"></script>
  </head>
  
  <body>
    <div class="top">
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=30">文学</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=29">生活</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=2">计算机</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=28">外语</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=3">经营</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=7">励志</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=8">社科</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=9">学术</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=10">少儿</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=11">艺术</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=12">原版</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=13">科技</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=14">考试</a>
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=15">生活百科</a>
    	
    	<a href="${pageContext.request.contextPath}/TransferStation?page=goAll&categoryId=1" style="color:yellow;">全部商品目录</a>
    </div>
   	<div class="bottom">
   		<table>
   		<tr><td>
   		<p>Search: <input type="text" id="serchText" name="search" height="50px" />
   		<input id="serchbtn" height="25px" type="image" src="resource/image/serchbutton.gif"  /></p>
   		<div id="keyname"></div>
   		</td></tr>
   		</table>
   	</div>
   	
   	
  </body>
  <script type="text/javascript">
  	var searchArr;
  	if(localStorage.search){
  		//如果存在数据
  		searchArr = localStorage.search.split(',');
  	}else{
		//如果没有，则定义searchArr为一个空的数组
		searchArr = [];
	}
	
	//绑定搜索按钮的点击事件，每次搜索都去查找对应数据
	$("#serchbtn").click(function(){
		var val = $("#serchText").val();
		//点击搜索按钮时，去重
		KillRepeat(val);
		//去重后把数组存储到浏览器localStorage
		localStorage.search = searchArr;
	});
	
// 	function MapSearchArr(){
// 		var tmpHtml = "";
// 		for (var i=0;i<searchArr.length;i++){
// 			tmpHtml += "<span>" + searchArr[i] + "</span>&nbsp;"
// 		}
// 		$("#keyname").html(tmpHtml);
// 	}
	
	
	//对于数据进行去重操作
	function KillRepeat(val){
		var kill = 0;
		for(var x=0; x<searchArr.length; x++){
			if(searchArr[x] === val)
				kill++;
		}
		if(kill < 1)
			searchArr.push(val);
	}
	
  	console.log(searchArr);
  	$("#serchText").autocomplete({
  		autoFocus: false,
  		minLength: 1,
  		selectFirst :false,
  		matchSubset :true,
  		max:12,
  		focus:function(event, ui){
  			$(this).val(ui.item.label);
  			return false;
  		},
  		source: function(request, resposne){
  			//使用一个ajax请求去请求Cookie数据
  			var text = request.term;
  			
  			
  			if(text!=null){
  				//改进：先将历史搜索作为数据源，
  			
	  			$.ajax({
	  				url:"${pageContext.request.contextPath}/ProductController/AutoCompletecController",
	  				type:"POST",
	  				dataType: "JSON",
	  				data:{
	  					"find":text
	  				},
	  				success: function(data, res, jqxml){
	  					resposne(data);
	  					
	  				}
	  			});
  			}
  		}
  	});
  
  </script>
</html>
