$(function(){
	var userType = $("#userType").val();
	if ($("#syleTypeUpAndDownId").val() == "1") {
		//反显示菜单数据
		mainInitSyle("1");
		allLayout("1");
	} 
	else if ($("#syleTypeLeftAndRightId").val() == "2") {
		//反显示菜单数据
		mainInitSyle("2");
		allLayout("2");
	}
	//判断只有超级管理员可以设置logo
	if(userType=="0"){
		
		// 左右UI设置
		$(".set_logo").hover(function(){
			$(".logo_div").show();
		});
		$(".set_top_l").mouseleave(function(){
			$(".logo_div").hide();
		})
	}else{
		$(".logo_span").html("");
		
	}
    $(".nav_choose>div").hide();
    $(".set_nav_list li a").hover(function(){
        $(".nav_choose").show();
        var index1=$(this).parent().index(".set_nav_list li");
        $(this).parent().addClass("menulihover").siblings().removeClass("menulihover");
        $(this).parent().addClass("menuliselected").siblings().removeClass("menuliselected");
        $($(".nav_div")[index1]).show().siblings().hide();
    });
    $(".set_nav").mouseleave(function(){
        $(".nav_choose").hide();
        $(".set_nav_list li").removeClass("menulihover");
    })
    $(".nav_choose2>div").hide();
    $(".set_nav_list2 li a").hover(function(){
        $(".nav_choose2").show();
        var index2=$(this).parent().index(".set_nav_list2 li");
        $(this).parent().addClass("menulihover").siblings().removeClass("menulihover");
        $(this).parent().addClass("menuliselected").siblings().removeClass("menuliselected");
        $($(".nav_div2")[index2]).show().siblings().hide();
    });
    $(".set_nav2").mouseleave(function(){
        $(".nav_choose2").hide();
        $(".set_nav_list2 li").removeClass("menulihover menuliselected");
    })
    // 左右UI设置  end
})

/**
 * 布局动态加载信息
 */
function allLayout(bz) {
	if (bz == "1")
		var type = "upAnDown";
	else if ((bz == "2"))
		var type = "leftAndRight";
	
	 $.ajax({
	        type:"POST",
	        url:'system/sysSyle/initMainSlye.do',
	        data:{"syleType":bz},
	        dataType:"json",
	        async:false,
	        success:function(data){
	            for (a in data.result) {
	            	//行业管理
	            	if (typeof(data.result[a].hygl) != "undefined") {
	            		var hyHtml ="";
	            		var hygl = data.result[a].hygl.toString().replace(/\\/g,"/");
		            	var hyglArr = hygl.split(",");
	            		for(var i = 0; i < hyglArr.length; i++) {
	            			var temp = i+1;
	            			hyHtml = hyHtml+ '<li><a href="javascript:void(0);" onclick="editHy(\''+ type + '\',\''+ temp + '\',\''+ hyglArr[i] + '\')"><img src=\''+ hyglArr[i] + '\'></a></li>'
	            		}
	            		if (bz == "1")
	            		   document.getElementById('hy').innerHTML = hyHtml;
	            		else if (bz == "2")
	            		   document.getElementById('hyFr').innerHTML = hyHtml;	
	            	}
	            	//信息安全
	            	if (typeof(data.result[a].xxaq) != "undefined") {
	            		var xxHtml ="";
	            		var xxgl= data.result[a].xxaq.toString().replace(/\\/g,"/");;
		            	var xxaqArr = xxgl.split(",");
	            		for(var i = 0; i < xxaqArr.length; i++) {
	            			var temp = i+1;
	            			xxHtml = xxHtml+ '<li><a href="javascript:void(0);" onclick="editXx(\''+ type + '\',\''+ temp + '\',\''+ xxaqArr[i] + '\')"><img src=\''+ xxaqArr[i] + '\'></a></li>';
	            		}
	            		if (bz == "1")
		            	   document.getElementById('xx').innerHTML = xxHtml;
		            	else if (bz == "2")
		            	   document.getElementById('xxFr').innerHTML = xxHtml;	
	            	}
	            	//网络安全
	            	if (typeof(data.result[a].wlaq) != "undefined") {
	            		var wlHtml ="";
	            		var wlgl= data.result[a].wlaq.toString().replace(/\\/g,"/");;
		            	var wlaqArr = wlgl.split(",");
	            		for(var i = 0; i < wlaqArr.length; i++) {
	            			var temp = i+1;
	            			wlHtml = wlHtml+ '<li><a href="javascript:void(0);" onclick="editWl(\''+ type + '\',\''+ temp + '\',\''+ wlaqArr[i] + '\')"><img src=\''+ wlaqArr[i] + '\'></a></li>'; 
	            		}
	            		if (bz == "1")
			               document.getElementById('wl').innerHTML = wlHtml;
			            else if (bz == "2")
			               document.getElementById('wlFr').innerHTML = wlHtml;
	            	}
	            	//应急协查
	            	if (typeof(data.result[a].yjxc) != "undefined") {
	            		var yjHtml ="";
	            		var yjgl= data.result[a].yjxc.toString().replace(/\\/g,"/");;
		            	var yjxcArr = yjgl.split(",");
	            		for(var i = 0; i < yjxcArr.length; i++) {
	            			var temp = i+1;
	            			yjHtml = yjHtml+ '<li><a href="javascript:void(0);" onclick="editYj(\''+ type + '\',\''+ temp + '\',\''+ yjxcArr[i] + '\')"><img src=\''+ yjxcArr[i] + '\'></a></li>'; 
	            		}
	            		if (bz == "1")
				           document.getElementById('yj').innerHTML = yjHtml;
				        else if (bz == "2")
				           document.getElementById('yjFr').innerHTML = yjHtml;
	            	}
	            	//系统管理
	            	if (typeof(data.result[a].xtgl) != "undefined") {
	            		var xtHtml ="";
	            		var xtgl= data.result[a].xtgl.toString().replace(/\\/g,"/");;
		            	var xtglArr = xtgl.split(",");
	            		for(var i = 0; i < xtglArr.length; i++) {
	            			var temp = i+1;
	            			xtHtml = xtHtml+ '<li><a href="javascript:void(0);" onclick="editXt(\''+ type + '\',\''+ temp + '\',\''+ xtglArr[i] + '\')"><img src=\''+ xtglArr[i] + '\'></a></li>';
	            		}
	            		if (bz == "1")
					       document.getElementById('xt').innerHTML = xtHtml;
					    else if (bz == "2")
					       document.getElementById('xtFr').innerHTML = xtHtml;
	            	}
	            	//logo管理
	            	if (typeof(data.result[a].logo) != "undefined") {
	            		var logoHtml ="";
	            		var logogl= data.result[a].logo.toString().replace(/\\/g,"/");;
		            	var logoArr = logogl.split(",");
	            		for(var i = 0; i < logoArr.length; i++) {
	            			var temp = i+1;
	            			logoHtml = logoHtml+ '<li><a href="javascript:void(0);" onclick="editLogo(\''+ type + '\',\''+ temp + '\',\''+ logoArr[i] + '\')"><img src=\''+ logoArr[i] + '\'></a></li>';
	            		}
	            		if (bz == "1")
					        document.getElementById('logo').innerHTML = logoHtml;
					    else if (bz == "2")
					        document.getElementById('logoFr').innerHTML = logoHtml;
	            	}
	            	//皮肤管理上下
	            	if (typeof(data.result[a].skinUpAndDown) != "undefined" && bz == "1") {
	            		var skinUpAndDownHtml ="";
	            		var skinUpAndDowngl= data.result[a].skinUpAndDown.toString().replace(/\\/g,"/");
		            	var skinUpAndDownglArr = skinUpAndDowngl.split(",");
	            		for(var i = 0; i < skinUpAndDownglArr.length; i++) {
	            			var temp = i+1;
	            			if (skinDisplayInit("1") == skinUpAndDownglArr[i])
	            				skinUpAndDownHtml = skinUpAndDownHtml+ '<li ><a class="active" href="javascript:void(0);" onclick="editPf(\''+ type + '\',\''+ temp + '\',\''+ skinUpAndDownglArr[i] + '\')"><img src=\'js/sysui/css/ud/'+ skinUpAndDownglArr[i] + '/skin.png\'></a></li>';	
	            			else 
	            				skinUpAndDownHtml = skinUpAndDownHtml+ '<li><a  href="javascript:void(0);" onclick="editPf(\''+ type + '\',\''+ temp + '\',\''+ skinUpAndDownglArr[i] + '\')"><img src=\'js/sysui/css/ud/'+ skinUpAndDownglArr[i] + '/skin.png\'></a></li>';	
	            		}
					        document.getElementById('skin').innerHTML = skinUpAndDownHtml;
	            	}
	            	//皮肤管理左右
	            	if (typeof(data.result[a].skinLeftAndRight) != "undefined" && bz == "2") {
	            		var skinLeftAndRightArrHtml ="";
	            		var skinLeftAndRightgl= data.result[a].skinLeftAndRight.toString().replace(/\\/g,"/");
		            	var skinLeftAndRightArr = skinLeftAndRightgl.split(",");
	            		for(var i = 0; i < skinLeftAndRightArr.length; i++) {
	            			var temp = i+1;
	            			if (skinDisplayInit("2") == skinLeftAndRightArr[i]) 
	            			    skinLeftAndRightArrHtml = skinLeftAndRightArrHtml+ '<li><a class="active" href="javascript:void(0);" onclick="editPf(\''+ type + '\',\''+ temp + '\',\''+ skinLeftAndRightArr[i] + '\')"><img src=\'js/sysui/css/lr/'+ skinLeftAndRightArr[i] + '/skin.png\'></a></li>';
	            			else 
	            				skinLeftAndRightArrHtml = skinLeftAndRightArrHtml+ '<li><a href="javascript:void(0);" onclick="editPf(\''+ type + '\',\''+ temp + '\',\''+ skinLeftAndRightArr[i] + '\')"><img src=\'js/sysui/css/lr/'+ skinLeftAndRightArr[i] + '/skin.png\'></a></li>';
	            		}
					        document.getElementById('skinFr').innerHTML = skinLeftAndRightArrHtml;
	            	}
	            }
	            bindchangestyle();
	        }
	    });
}

/**
 * 皮肤显示红框
 */
var skinType;
function skinDisplayInit(type) {
	 $.ajax({
        type:"POST",
        async:false,
        url:'system/sysSyle/initMainScreen.do',
        data:{"elementType":3,"syleType":type},
        dataType:"json",
        success:function(data){
            skinType = data.result.value;
        }
    });	
	 return skinType;
}
/**
 * 初始化主菜单信息
 */
function mainInitSyle(type) {
	var allHtml ="";
    $.ajax({
       type:"POST",
       url:'system/sysSyle/getMainInitEntity.do',
       data:{"syleType":type},
       async:false,
       dataType:"json",
       success:function(data){
          if (!data.success){
                $.messager.alert('提示',result.msg);  
          } else {
                for (a in data.result)
                    allHtml += setMainPng(type,data.result[a].funcCode);
          }
          if (type == "1")
        	  document.getElementById('upId').innerHTML = allHtml;
          
          else if (type == "2")
        	  document.getElementById('leftId').innerHTML = allHtml;
          else {
        	  document.getElementById('upId').innerHTML = ""; 
        	  document.getElementById('leftId').innerHTML = "";
          }
           //点击反显示数据上下结构布局
          initSyle(type);
        }
    });
}

/**
 * 初始化主菜单信息
 */
function setMainPng(type,funcCode) {
	var allHtml = "";
	switch(type)
	{
	case "1":
		if (funcCode == "IndustryMgr") { //行业管理
			allHtml = '<li><a href="javascript:void(0);"><span class="nav_span">行业管理</span><img id="setHyUpId" src="js/sysui/images/set_nav.png"></a></li>';  
		} 
		  
		if (funcCode == "InfoSecurity") { //信息安全
			allHtml = '<li><a href="javascript:void(0);"><span class="nav_span">信息安全</span><img id="setXxUpId" src="js/sysui/images/set_nav.png"></a></li>';  
		}
		  
		if (funcCode == "NS_NetSecurity") { //网络安全
			allHtml = '<li><a href="javascript:void(0);"><span class="nav_span">网络安全</span><img id="setWlUpId" src="js/sysui/images/set_nav.png"></a></li>';  
		}
		  
		if (funcCode == "EmergencyCmd") { //应急协查
			allHtml = '<li><a href="javascript:void(0);"><span class="nav_span">应急协查</span><img id="setYjUpId" src="js/sysui/images/set_nav.png"></a></li>';  
		}
		  
		if (funcCode == "AuthorityMgr") { //系统管理
			allHtml = '<li><a href="javascript:void(0);"><span class="nav_span">系统管理</span><img id="setXtUpId" src="js/sysui/images/set_nav.png"></a></li>';  
		}
      break;
	case "2":
	    if (funcCode == "IndustryMgr") { //行业管理
	    	allHtml = '<li><a href="javascript:void(0);"><img id="setHyId" src="js/sysui/images/set_nav.png"><span class="nav_span">行业管理</span></a></li>';  
	    } 
	  
	    if (funcCode == "InfoSecurity") { //信息安全
	    	allHtml = '<li><a href="javascript:void(0);"><img id="setXxId" src="js/sysui/images/set_nav.png"><span class="nav_span">信息安全</span></a></li>';  
	    }
	  
	    if (funcCode == "NS_NetSecurity") { //网络安全
	    	allHtml = '<li><a href="javascript:void(0);"><img id="setWlId" src="js/sysui/images/set_nav.png"><span class="nav_span">网络安全</span></a></li>';  
	    }
	  
	    if (funcCode == "EmergencyCmd") { //应急协查
	    	allHtml = '<li><a href="javascript:void(0);"><img id="setYjId" src="js/sysui/images/set_nav.png"><span class="nav_span">应急协查</span></a></li>';  
	    }
	  
	    if (funcCode == "AuthorityMgr") { //系统管理
	    	allHtml = '<li> <a href="javascript:void(0);"><img id="setXtId" src="js/sysui/images/set_nav.png"><span class="nav_span">系统管理</span></a></li>';  
	    }
      break;
	default:
		//console.log('数据',allHtml);
	}
	return allHtml;
}

/**
 * 初始化数据信息填充图片 type:1上下 2:左右
 */
function initSyle(type) {
    $.ajax({
       type:"POST",
       url:'system/sysSyle/getInitEntity.do',
       data:{"syleType":type},
       async:false,
       dataType:"json",
       success:function(data){
          if (!data.success){
                $.messager.alert('提示',result.msg);  
          }else {
              for (a in data.result)
                  setPng(data.result[a].elementId, data.result[a].elementType, data.result[a].value, data.result[a].syleType)
          }
        }
    });
}

/**
 * 主页面点入加载图片信息
 */
function setPng(_elementId, _elementType, _value, _syleType) {
    if (_elementId == "IndustryMgr" && _elementType =="1" && _syleType == "1")  //行业管理初始化 IndustryMgr
        document.getElementById("setHyUpId").src= _value;
   
    if (_elementId == "NS_NetSecurity" && _elementType =="1" && _syleType == "1")  //网络安全初始化NS_NetSecurity 
        document.getElementById("setWlUpId").src= _value;
    
    if (_elementId == "InfoSecurity" && _elementType =="1" && _syleType == "1") //信息安全初始化InfoSecurity
        document.getElementById("setXxUpId").src= _value;
    
    if (_elementId == "EmergencyCmd" && _elementType =="1" && _syleType == "1") //应急协查初始化EmergencyCmd
        document.getElementById("setYjUpId").src= _value;
    
    if (_elementId == "AuthorityMgr" && _elementType =="1" && _syleType == "1") //系统管理初始化AuthorityMgr
        document.getElementById("setXtUpId").src= _value;
   
    if (_elementType =="2" && _syleType == "1")  //logo
        document.getElementById("setLogoUpId").src= _value;
    
    if (_elementId == "IndustryMgr" && _elementType =="1" && _syleType == "2")
    	document.getElementById("setHyId").src= _value;
	
    if (_elementId == "NS_NetSecurity" && _elementType =="1" && _syleType == "2")  //网络安全初始化NS_NetSecurity
    	document.getElementById("setWlId").src= _value;
	
    if (_elementId == "InfoSecurity" && _elementType =="1" && _syleType == "2") //信息安全初始化InfoSecurity
    	document.getElementById("setXxId").src= _value;
	
    if (_elementId == "EmergencyCmd" && _elementType =="1" && _syleType == "2") //应急协查初始化EmergencyCmd
    	document.getElementById("setYjId").src= _value;
	
    if (_elementId == "AuthorityMgr" && _elementType =="1" && _syleType == "2") //系统管理初始化AuthorityMgr
    	document.getElementById("setXtId").src= _value;
    
    if (_elementType =="2" && _syleType == "2")  //logo
    	document.getElementById("setLogoId").src= _value;
}

/**
 * logo图标选项
 */
function editLogo(pageType,type,url) {
    var elementType = "2";                 //logo标志位
    if (pageType == 'leftAndRight') 
        var syleType = $("#syleTypeLeftAndRightId").val(); //表中的syleType类型
    else if (pageType == 'upAnDown') 
        var syleType = $("#syleTypeUpAndDownId").val();    //表中的syleType类型
    addSyleEntity(url,syleType,elementType,null);
    
    if (pageType == 'leftAndRight') 
        document.getElementById("setLogoId").src= url;
    else if (pageType == 'upAnDown') 
        document.getElementById("setLogoUpId").src= url;
}

/**
 * 皮肤
 */
function editPf(pageType,type,url) {
    var elementType = "3";                  //logo标志位
    if (pageType == 'leftAndRight')
        var syleType = $("#syleTypeLeftAndRightId").val(); //表中的syleType类型
    else if (pageType == 'upAnDown')
        var syleType = $("#syleTypeUpAndDownId").val();    //表中的syleType类型
    addSyleEntity(url,syleType,elementType,null);
}

/**
 * 皮肤反显红框
 */
function bindchangestyle() {
	$("#skin li a").click(function() {
		$("#skin li a").removeClass("active");
		$(this).addClass("active");
	});
	$("#skinFr li a").click(function() {
		$("#skinFr li a").removeClass("active");
		$(this).addClass("active");
	});
	
}

/**
 * 行业管理
 */
function editHy(pageType,type,url) {
    var elementId = "IndustryMgr";           //行业菜单
    var elementType = "1";                   //菜单标志位
    if (pageType == 'leftAndRight')
        var syleType = $("#syleTypeLeftAndRightId").val(); //表中的syleType类型
    
    else if (pageType == 'upAnDown')
    var syleType = $("#syleTypeUpAndDownId").val();        //表中的syleType类型
    addSyleEntity(url,syleType,elementType,elementId);
    if (pageType == 'leftAndRight')
       document.getElementById("setHyId").src= url;
    else if (pageType == 'upAnDown') 
       document.getElementById("setHyUpId").src= url;
    
}

/**
 * 信息安全
 */
function editXx(pageType,type,url) {
    var elementId = "InfoSecurity";           //信息菜单
    var elementType = "1";                    //菜单标志位
    if (pageType == 'leftAndRight')
        var syleType = $("#syleTypeLeftAndRightId").val(); //表中的syleType类型
    
    else if (pageType == 'upAnDown')
        var syleType = $("#syleTypeUpAndDownId").val();    //表中的syleType类型
    addSyleEntity(url,syleType,elementType,elementId);
    
    if (pageType == 'leftAndRight')
        document.getElementById("setXxId").src= url;
     
    else if (pageType == 'upAnDown')
        document.getElementById("setXxUpId").src= url;
}

/**
 * 网络安全
 */
function editWl(pageType,type,url) {
    var elementId = "NS_NetSecurity";    //网络安全菜单
    var elementType = "1";               //菜单标志位
    if (pageType == 'leftAndRight')
        var syleType = $("#syleTypeLeftAndRightId").val(); //表中的syleType类型
    
    else if (pageType == 'upAnDown')
        var syleType = $("#syleTypeUpAndDownId").val();    //表中的syleType类型
    
    addSyleEntity(url,syleType,elementType,elementId);
    
    if (pageType == 'leftAndRight')
        document.getElementById("setWlId").src= url;
    
    else if (pageType == 'upAnDown')
        document.getElementById("setWlUpId").src= url;

}

/**
 *应急协查
 */
function editYj(pageType,type,url) {
    var elementId = "EmergencyCmd";           //应急协查菜单
    var elementType = "1";                    //菜单标志位
    if (pageType == 'leftAndRight')
        var syleType = $("#syleTypeLeftAndRightId").val(); //表中的syleType类型
    
    else if (pageType == 'upAnDown')
        var syleType = $("#syleTypeUpAndDownId").val(); //表中的syleType类型

    addSyleEntity(url,syleType,elementType,elementId);
    if (pageType == 'leftAndRight')
        document.getElementById("setYjId").src= url;
    
    else if (pageType == 'upAnDown')
        document.getElementById("setYjUpId").src= url;

}

/**
 *系统管理
 */
function editXt(pageType,type,url) {
    var elementId = "AuthorityMgr";           //系统管理菜单
    var elementType = "1";                    //菜单标志位
    if (pageType == 'leftAndRight')
        var syleType = $("#syleTypeLeftAndRightId").val(); //表中的syleType类型
    
    else if (pageType == 'upAnDown')
        var syleType = $("#syleTypeUpAndDownId").val(); //表中的syleType类型

    addSyleEntity(url,syleType,elementType,elementId);
    
    if (pageType == 'leftAndRight')
        document.getElementById("setXtId").src= url;
    
    else if (pageType == 'upAnDown') 
        document.getElementById("setXtUpId").src= url;
}

/**
 *设置添加元素
 */
function addSyleEntity(value,syleType,elementType,elementId) {
    //设置元素类型2:log，元素设置值
    $.ajax({
        type:"POST",
        async:false,
        url:'system/sysSyle/addEntity.do',
        data:{"elementType":elementType,"value":value,"syleType":syleType,"elementId":elementId},
        dataType:"json",
        success:function(data) {
            if(!data.success) {
                $.messager.alert('提示',result.msg);  
            }else {
                    $.messager.alert('提示','设置图标成功！');
             }
            }
        });
    //编辑，新增机房信息，或修改机房信息
    $("#sysSyleCancel").click(function() {
        closeWin("addSysSyleWin");
    });
}