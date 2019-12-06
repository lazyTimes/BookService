/**
 * 自动轮播的实现代码
 */

//图片轮播效果展示
$(document).ready(function(){
	var ticket = setInterval(autoplay, 3000);
	//用于改变li的CSS样式
  	function changeLiCss(element){
  		$(element).css({
  			"background": "red",
        	"border": "1px solid #ffffff",
        	"opcity":"0.8"
  		}).siblings().css({
  			"background":"black"
  		});
  	}
  	//图片样式的改变
  	function imageChange(element){
  		var index = $(element).index();
  		$("#autoimg a").eq(index).fadeIn(1000).siblings().fadeOut(1000);
  	}
  	
  	//控制图片角标位置
  	var index = 0;
  	//自动轮播实现
  	function autoplay(){
  		index++;
  		if(index>=5)
  			index = 0;
  		var element = $("#lablepoint li").eq(index);
  		changeLiCss(element);
  		
  		imageChange(element);
  	}
  	
  	//实现底部导航框的自动样式
  	$("#lablepoint li").click(function(){
  		//console.log("----");
  		//$(this).addClass("lablepointClk").siblings().removeClass("lablepointClk");
  		changeLiCss($(this));
  		index = $(this).index();
  		imageChange(this);
  	});
  	
  	//实现鼠标停留在图片上就停止计时器,离开就开启计时器
  	$("#autoimg a img, #toleft, #toright").hover(
  	function(){
  		//显示两边的箭头
  		$("#toleft,#toright").show()
  		clearInterval(ticket);
  	},
  	//鼠标移开的时候的动作
  	function(){
  		$("#toleft,#toright").hide();
  		ticket = setInterval(autoplay, 3000);
  	});
  	
  	//点击了左箭头的时候
  	$("#toleft").click(function(){
  		//如果点击到了第五张则从最后一张开始
  		if(index <= 0)
  			index = 5;
  		index--;
  		var element = $("#lablepoint li").eq(index);
  		//console.log(element);
  		changeLiCss(element);
  		imageChange(element);
  	});
  	
  	//点击了右箭头的时候
  	$("#toright").click(function(){
  		//如果点击到了第五张则从最后一张开始
  		index++;
  		if(index >= 5)
  			index = 0;
  		
  		var element = $("#lablepoint li").eq(index);
  		//console.log(element);
	  		changeLiCss(element);
	  		imageChange(element);
	});
});
