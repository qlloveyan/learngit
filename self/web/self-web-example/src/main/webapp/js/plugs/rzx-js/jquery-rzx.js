$(function(){
});

//add-tab-下一步上一步
 var xaform = $(".xa-form");
 var xaform1 = $(".xa-form1");
 var xaform2 = $(".xa-form2");
 var xaform3 = $(".xa-form3");
 var xaform4 = $(".xa-form4");
 

function toTab1(){
	(xaform).hide();
	 xaform1.show();
	 $(".xa-add li").removeClass("xa-add-hover");
	 $(".xa-add li").eq(0).addClass("xa-add-hover");	
} 
 
function toTab2(){
	//setHouseByIspIdc();
	//setHouseByIspIdc2();
	 (xaform).hide();
	 xaform2.show();
	 $(".xa-add li").removeClass("xa-add-hover");
	 $(".xa-add li").eq(1).addClass("xa-add-hover"); 
}
 
function  toTab3(){
	 (xaform).hide();
	 xaform3.show();
	 $(".xa-add li").removeClass("xa-add-hover");
	 $(".xa-add li").eq(2).addClass("xa-add-hover");
}
function  toTab4(){
	(xaform).hide();
	xaform4.show();
	$(".xa-add li").removeClass("xa-add-hover");
	$(".xa-add li").eq(3).addClass("xa-add-hover");
}



$(document).ready(function () {
	//高级查询内容隐藏
		$(".search-gj a").click(function(){
		   $("#advancedQueryForm,#eventTraceAddFrom,#superSearchForm,#superSearchForm-noRecordWebsite,#superSearchForm-blackListWebsite,#superSearchForm-illegalBusinessWebsite,#superSearchForm-accessUnReal,#superSearchForm-dieClosedWebsite").show();
	    })
	
	//查询条件大于3个时隐藏面包屑
	  var searchlength = $(".search-form > span:not('span.search-button')").length;
		if((searchlength > 3) && (screen.width == 1024) && (screen.height == 768)){
			$(".crumbs").hide();
		}
		
	
	//按钮下拉
	  $(".btn-tab button").click(function(){
		 $(".btn-tab button").removeClass("btn-hover");
		 $(this).addClass("btn-hover");
	  })
	  
	  
	
	
	$(".more-tab span").bind("click",function(){
		$(".more-tab span").removeClass("hover");
		$(this).addClass("hover");
	})
	
	$(".tip-yellowsimple a").click(function(){
		/*alert("1");*/
	})
	
	//头部搜索适应分辨率及改变窗口变化
	if ((screen.width == 1024) && (screen.height == 768)) {
        $(".top-input-search").addClass("top-search-min");//此分辨率下你需要的操作
        $(".jfxx-tpt").addClass("jfxx-tpt-min");
        $(".search-drop").addClass("search-drop-min");

    }  else {
        $(".top-input-search").removeClass("top-search-min");//默认操作
        $(".jfxx-tpt").removeClass("jfxx-tpt-min");
        $(".search-drop").removeClass("search-drop-min");
    }
	
	//resize当改变文档窗口时，作出的变化
	$(window).resize(function(){ 
	    if($(window).width() <= 1167){
	  $(".top-input-search").addClass("top-search-min");
	  $(".jfxx-tpt").addClass("jfxx-tpt-min");
	  $(".search-drop").addClass("search-drop-min");
	} else {
	  $(".top-input-search").removeClass("top-search-min");  
	  $(".jfxx-tpt").removeClass("jfxx-tpt-min");
	  $(".search-drop").removeClass("search-drop-min");
	}
	});
	
	//获取对象位置并设置浮动层位置
	//小于1024所作出的控制
	//$(window).resize(function(){ 
	//	if($(window).width() < 1003){
	//	   $(".panel-fit").addClass("width1003");   //宽度小于1003时，出现横向滚动条
	//	}else{
	//		$(".panel-fit").removeClass("width1003"); //宽度小于1003时，移除横向滚动条
	//	}
	//})
	
	 $(".subnav li:last").css({"padding":"0","background-image":"none"});
	 
	 
	//帮助说明提示
	 $(".tip").hover(function(){
		   $(this).children().show();
	   },function(){
		   $(this).children().hide();
	   });
	 
    
	 
	//换肤展开特效
	$(".float-skin,.float-resource").hide();
	var sti;
	$(".tool-resource,.float-skin,.float-resource").hover(function(){
	    sti && clearTimeout(sti);
	    sti = null;
	    $(".float-skin,.float-resource").show().animate({"right":"40px"},500);
	},function(){
	    sti = setTimeout(function(){
	        $(".float-skin,.float-resource").animate({"right":"-150px"},500);
	    }, 500);//300是延迟隐藏的毫秒数
	});
	 
	 
	//消息提醒动画
	 setInterval(function (){ msg();}, 200)
	 function msg(){
		    var ys = $(".tool-bounce")
			if(!($(ys).is(":animated"))){
				$(ys).animate({"top":"-6px"},200).animate({"top":"-0px"},200)
				.animate({"top":"-3px"},200).animate({"top":"-0px"},200)
				.animate({"top":"-1px"},200).animate({"top":"-1px"},200)
				//.animate({"top":"-1px"},200).animate({"top":"-0px"},200);
			}
	 }
	 
	 //信息闪烁动画
	 setInterval(function(){
		 $(".tool-flicker").fadeOut(200).fadeIn(200);
	 },400);
	 
	 
	    

//列表左右高度自适应
//var _leftheight = jQuery(".jczy-left").height();
//var _rightheight = jQuery(".jczy-right,.xa-right").height();
//if (_leftheight > _rightheight) {
//　 jQuery(".jczy-right,.xa-right").height(_leftheight);
//　} else {
//　　jQuery(".jczy-left").height(_rightheight);
//} 
  
  
//搜索样式给予
 $("#queryForm>span").addClass("search-amend");
 
   
  
//tab
		//$(".xa-form2-tab li").bind("click",function(){
		   // $(".xa-form2-tab li").removeClass("lihover");
		   // $(this).addClass("lihover").siblings().removeClass();
		   // $(this).parent().addClass("xa-form2-tabHover");
			//$(".xa-form2-tab ul").removeClass("xa-form2-tabHover");
			//$(this).parent().addClass("xa-form2-tabHover");
		//})



//搜索关键字获取焦点 
$('.top-input-search').bind({ 
	focus:function(){ 
	  if (this.value == this.defaultValue){ 
	  this.value=""; 
	  }}, 
	blur:function(){ 
	  if (this.value == ""){ 
	  this.value = this.defaultValue; 
	}} 
}); 
 
 //一级模块点击后，当前状态效果实现
// $(".nav li").bind("click",function(){
//   var hover =$(".nav-hover");
//   $(hover).insertAfter(this);
// })
 
 //窗口最小时，出现横向滚动条
// $(window).resize(function(){ //resize当改变文档窗口时，作出的变化
//	 if($(window).width() < 1120){
//	  $("body").css({"overflow-x":"scroll","width":"1003px"});
 //	} else {
//	  $("body").css({"overflow-x":"","width":""});
 //	} 
 //  });
 
 //数据过多，鼠标经过展开
// $(".jczy-data ul").hover(function(){
 //  $(".jczy-data ul.jczy-data-more").show();	 
 //},function(){
//	 $(".jczy-data ul.jczy-data-more").hide(); 
	 
 //})
 
 
 //三级菜单下拉选择插件
// $('.subnav ul li a').hover(function(){  
//	var subnav = $(this).parent().parent().parent().parent();  
//	var subnav2 = subnav.find('.subnav2');  
//	subnav2.slideDown('fast').show();  
//	subnav.hover(function(){},function(){  
//		subnav2.slideUp('fast');  
//	});  
//},function(){  
//}); 

 //列表下拉选择插件
// $('.list-drop').hover(function(){ 
//    var float = $(".float-drop"); 
//	var divfloat = $(float).insertAfter(this);
//	var float2 = $(divfloat).parent(); 
//	var divfloat2 = float2.find(float);  
//	divfloat2.slideDown(0).show();
//	float2.hover(function(){},function(){  
//		float.slideUp(0);  
//	}); 
	  
//  },function(){  
// });
 
 //pop-float
 $(".xa-pop-float").hide();
 $(".xa-pop").bind("click",function(){
   var popfloat = $(".xa-pop-float").show();
   var ff=$(popfloat).insertAfter(this).parent();
   var rew = $(ff).parent();
   $(".xa-pop").parent().find(".xa-pop-float").removeClass("xa-pop-float2");
   $(".xa-pop2").parent().find(".xa-pop-float").addClass("xa-pop-float2");
 });
 
 $(".xa-add-close a,.xa-pop-float h3 a").click(function(){
   $(".xa-pop-float").hide();
 })
 

 
 //add-tab
 var tabs = $(".tab-button>span");
 tabs.bind("click",function(){
     $(this).siblings().removeClass('tab-button-hover');
     $(this).addClass("tab-button-hover");
    // var hdw_index = tabs.index(this);
    // $('.ct').eq(hdw.index(this)).show().siblings().hide();
 });
 
 
 
//add-tab
// $(".xa-add li").bind("click",function(){
//	 $(".xa-add li").removeClass("xa-add-hover");
//	 $(this).addClass("xa-add-hover");
//	 $(".xa-form").hide().eq($(".xa-add li").index(this)).show();
// });
 
 
 $(".up1").click(function(){
	 (xaform).hide();
	 xaform1.show();
	 $(".xa-add li").removeClass("xa-add-hover");
	 $(".xa-add li").eq(0).addClass("xa-add-hover");
 });
 $(".up2").click(function(){
	 (xaform).hide();
	 xaform2.show();
	 $(".xa-add li").removeClass("xa-add-hover");
	 $(".xa-add li").eq(1).addClass("xa-add-hover");
 });
 $(".up3").click(function(){
	 (xaform).hide();
	 xaform3.show();
	 $(".xa-add li").removeClass("xa-add-hover");
	 $(".xa-add li").eq(2).addClass("xa-add-hover");
 });
 
	
// $(".xa-add li").bind("click",function(){
//       var dx =$(".xa-add-tabbg");
//		$(dx).insertAfter($(this).children());
//	});
 
 

 
 //插入详情
//  var dd=$(".list-detail").html();
//  var zk = $("<tr class='detail-tr'><td colspan='10'>"+dd+"</td></tr>");
  
//  $(".list-table tr:not(:first),.jf-table tr:not(:first)").bind("click",function(){
//     $(zk).insertAfter(this)
	
		//列表左右高度自适应
	//	var _leftheight = jQuery(".jczy-left,.xa-left,.xa-mid").height();
	//	var _rightheight = jQuery(".jczy-right,.xa-mid,.xa-mid2").height();
	//	if (_leftheight > _rightheight) {
	//	　 jQuery(".jczy-right,.xa-mid,.xa-mid2").height(_leftheight);
	//	　} else {
	//	　　jQuery(".jczy-left,.jczy2-left,.xa-left").height(_rightheight);
	//	} 
//  })
  
//  $(".list-table tr:not(:first),.jf-table tr:not(:first)").bind("click",function(){
//	 $(this).addClass("list-trHover");	
//	 $(this).siblings().removeClass("list-trHover");
//  })
  
  
  //机房-拓扑图-当前状态图片
  $(".jfxx-tpt a").click(function(){
   var tpth =$(".tpt-hover");
   $(this).append(tpth);
  })

  
  $(".datagrid-btable tr").bind("click",function(){
	$(this).siblings().removeClass(".datagrid-row-selected");							   
  })
 
})