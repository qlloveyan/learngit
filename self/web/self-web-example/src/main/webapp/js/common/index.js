var bConnect = 0;
var sessionkey;
var url_prefix;
var nsmainurl;
var sessionkey;
var frameName;
var flagcount=0;
$(document).ajaxComplete(function(event, xhr, options){
	var text = xhr.responseText;
	if(text.indexOf("{")==0){
		var data = $.parseJSON(text);
		if(data.errorCode == '602'){
			$.messager.show({
                title:'系统访问权限提示',
                msg:data.message,
                timeout:6000,
                showType:'slide'
            });
		}
	}
});

if(top != self) {  
    if(top.location != self.location) {  
        top.location = self.location;  
    }  
}  

$(document).ajaxComplete(function(event, xhr, options){
	var text = xhr.responseText;
	if(text.indexOf("{")==0){
		var data = $.parseJSON(text);
		if(data.errorCode == '602'){
			$.messager.show({
                title:'系统访问权限提示',
                msg:data.message,
                timeout:6000,
                showType:'slide'
            });
		}
	}
});

if(top != self) {  
    if(top.location != self.location) {  
        top.location = self.location;  
    }  
} 
/**
 * 全局变量，用于存放用户的拥有权限的菜单
 * @type 
 */
var menuAllData = null;
$(function(){
	//alert(2);
	loadallmenu();
	initfirstMeun();
	$(".pullDownList li").hover(function(){
		 $(".yMenuListCon").show();
         var index=$(this).index(".pullDownList li");
         $(this).addClass("menulihover").siblings().removeClass("menulihover");
         $(this).addClass("menuliselected").siblings().removeClass("menuliselected");
         $($(".yMenuListConin")[index]).show().siblings().hide();
    });
    $(".pullDown").mouseleave(function(){
        $(".yMenuListCon").hide();
        $(".yMenuListConin").hide();
        $(".pullDownList li").removeClass("menulihover");

    });
});



/**
 * 显示一级菜单
 */
function initfirstMeun(){
	$('.yMenuListCon').empty();
	if(menuAllData != null && menuAllData.length>0){
		$.each(menuAllData, function(index, menuData){
			if(menuData.attributes.funcCode!="COMM_MENU"){
				className = menuData.attributes.funcCode;
				if(className!="COMM_MENU"){
					var li = "<li index="+menuData.id+">"+
									"<i style=\"background: url('"+menuData.attributes.pic+"'); display: block; width: 45px; height: 45px;\"></i>"+
									"<a href='javascript:void(0)'  id='"+menuData.id+"'>"+menuData.text+"</a>";
								"</li>";
					$('.pullDownList').append(li);
					
					//绑定一级惨淡点击事件
					bindClickFirstMenu(menuAllData);
					
					//加载二级 三级菜单
					initSecondMeun(menuData);
				}
				if(index==0 && className!="NS_NetSecurity" && className!="PS_sentiment"){
					var url=menuData.attributes.path;
					$("#f_House").attr("src",url);
					setBreadcrumb(menuData.id);
				}
				
			}else{
				//加载系统公共菜单部分
				initCommonBtn(menuData.children);
			}
		});
	}
}

//加载系统公共部分
function initCommonBtn(childMenu){
	console.info(childMenu);
}

function bindClickFirstMenu(menuAllData){
	$(".pullDownList li").click(function(){
   		var aId = $(this).find("a").attr('id');
   		$.each(menuAllData, function(index, menuData){
   			if(menuData.id == aId){
   				var url=menuData.attributes.path;
   				if(menuData.attributes.funcCode!="PS_sentiment"&&menuData.attributes.funcCode!="NS_JRZY"){
						
					$('iframe[win=show]').attr("src",url).attr("win","show");
					setBreadcrumb(menuData.id);
				}
   			}
   		});
 	});
}

function initSecondMeun(secondLevelMenu){
	var secondLevelMenus = secondLevelMenu.children;
	
	var secondmenudiv = '<div class="yMenuListConin" firstmenuid='+secondLevelMenu.id+'>';
	secondmenudiv+='<div class="yMenuLCinList">';
	
	if(secondLevelMenus != null && secondLevelMenus.length>0){
		
		$.each(secondLevelMenus, function(index, secondLevelMenu){
			var secondurl = secondLevelMenu.attributes.path;
			var funcCode=secondLevelMenu.attributes.funcCode;
			frameName = 'f_' + funcCode;
			if(secondLevelMenu.attributes.flagNetSecurity=="true"){
			}else{
				secondmenudiv +='<h3><a href="javascript:void(0)" id="'+secondLevelMenu.id+'" name="'+funcCode+'" class="yListName">'+secondLevelMenu.text+'</a></h3>';
				var sencodmenup = initThirdmenu(secondLevelMenu.children);
				secondmenudiv+=sencodmenup;
			}
		});
	}
	secondmenudiv += '</div>';
	secondmenudiv += '</div>';
	$('.yMenuListCon').append(secondmenudiv);

}

/**
 * 加载三级菜单
 */
function initThirdmenu(threeLevelMenus){
	 var secondnemup = '<p>';
	 if(threeLevelMenus!=null && threeLevelMenus.length>0){
			$.each(threeLevelMenus, function(index, threeLevelMenu){
				var threeurl = threeLevelMenu.attributes.path;
				var funcCode=threeLevelMenu.attributes.funcCode;
				frameName = 'f_' + funcCode;
				if(threeLevelMenu.attributes.flagNetSecurity=="true"){
				}else{
					secondnemup+='<a href="javascript:void(0)"  name="'+funcCode+'" id="'+threeLevelMenu.id+'" onclick="toPage(\''+threeurl+'\',\''+frameName+'\',\''+threeLevelMenu.id+'\');" >'+threeLevelMenu.text+'</a>';
				}
			});
		}
	 secondnemup+= '</p>';
	 return secondnemup;
}

/**
 * 加载所有菜单
 */
function loadallmenu(){
	$.ajax({
		url: 'system/funcModule/listAllModule.do', 
		type: 'post',
		async: false,
		dataType: 'json',
		success: function(data){
			menuAllData = data;
		}
	});
}

/**
 * 跳转页面
 * @param url
 * @param frameName
 */
function toPage(url, frameName,funcId){
    $('#mainPanel iframe').attr('id',frameName).attr('name', frameName).attr("win","show").attr('src', url);
    $('#globalsearch').attr("url","");
    setBreadcrumb(funcId);
}

/**
 * 设置面包屑导航
 */
function setBreadcrumb(funcId){
	$.ajax({
		url: 'system/funcModule/getBreadMenu.do', 
		type: 'post',
		data:{"id":funcId},
		dataType: 'json',
		success: function(data){
			var breadMenu = "";
			for(var i = data.length;i>0;i--){
				var url = data[i-1].url;
				var funcCode=data[i-1].funcCode;
				var frameName = 'f_' + funcCode;
				var id = data[i-1].id;
				if(data[i-1].id !=-1 && i!=1){
					if(url!="null" && url!=null && url!=""){
						breadMenu += '<a href="javascript:void(0)"  onclick="toPage(\''+url+'\',\''+frameName+'\',\''+id+'\');">'+data[i-1].funcName+'</a>'+' > ';
					}else{
						breadMenu += data[i-1].funcName+' > ';
						
					}
				}
				if(data[i-1].id !=-1 && i==1){
					if(url!="null" && url!=null && url!=""){
						breadMenu += '<a href="javascript:void(0)"  onclick="toPage(\''+url+'\',\''+frameName+'\',\''+id+'\');">'+data[i-1].funcName+'</a>';
					}else{
						breadMenu += +data[i-1].funcName+'</a>';
						
					}
				}
			}
			$("#breadMenu").html(breadMenu);
		}
	});
}





/**
 * 登出
 */
function logout(){
	$.messager.confirm("温馨提示","确定退出系统?",function(r){
		if(r){
			window.location.href = PROJECT_NAME + '/system/user/logout.do';
		}
	});
}


/**
 * 显示消息
 */
function showMsg(){
	openWin("msgWin","system/redirect/redirectHomePage.do?path=system/msg","消息",650,365);
}

/**
 * 密码修改
 */
function updatePwd(){
	openWin("editUserPwdWin","system/redirect/redirectHomePage.do?path=system/user_updatePwd","密码修改",350,220);
}

/**
 * 关于我们
 */
function aboutme(){
	openWin("aboutmeWin","system/redirect/redirectHomePage.do?path=system/aboutme","版本信息",350,220);
}

/**
 * 皮肤样式管理
 */
function initSysSyle(){
	openWin("initSysSyleWin","system/redirect/redirectHomePage.do?path=system/selectScreen","系统样式设置",820,580);
}

/**
 * 返回登录界面
 */
function returnNav(){
	$("#ngv_form").submit();
}


function getIframeDocument(element) {    
    return  element.contentDocument || element.contentWindow.document;    
};