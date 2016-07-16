<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<title>欢迎使用<%=request.getSession().getServletContext().getInitParameter("smcsCnName") %></title>
<link rel="shortcut icon" type="image/x-icon" href="js/easyui/themes/rzx-ui/images/favicon.ico" />
<jsp:include page="public/common2.jsp" />
<link rel="stylesheet" type="text/css" href="resources/css/index.css">
<link rel="stylesheet" type="text/css" href="js/plugs/autoComplete/jquery.autocomplete.css"></link>
<link rel="stylesheet" type="text/css" href="js/sysui/css/clock/clock.css"></link>
<script type="text/javascript" src="js/common/index.js"></script>
<script type="text/javascript" src="js/common/message.js"></script>
<!---->
<script type="text/javascript" src="js/plugs/autoComplete/jquery.autocomplete.js"></script>
<script type="text/javascript" src="js/common/globalsearch.js"></script>
<script type="text/javascript" src="js/plugs/easyui-expands/datagrid-detailview1.js"></script>

<script type="text/javascript">
$(function(){

});

$(document).ajaxComplete(function(event, xhr, options){
	var text = xhr.responseText;
	if(text.indexOf("{")==0){
		var data = $.parseJSON(text);
		
//		console.log("data:" + data);
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

$(document).ready(function(){
	
	
	
	$(document).keyup(function(event){
		//alert(event.keyCode);
		 /*  if(event.keyCode ==13){
			  $("#ok").click();
		  } */
		  
	});
	
	
	$("#globalsearch").keyup(function(){
		var text=$(this).val(); 
		if(!text){
			$(this).removeClass("input-warning");
		}
	});
	
    $(".top-close").hide();
	
	$("#globalsearch").keyup(function(){
		var zz = $("#globalsearch");
		if(zz.length>0){
			 $(".top-close").show();
		} 
	})
	
	 
	$(".top-close").bind("click",function(){
		$("#globalsearch").val("");
		$(this).hide();
		return false;
	})
		 
});
</script>
<body class="easyui-layout" border="false">
<div class="setting" title="设置皮肤" onclick="initSysSyle()"></div>
<div class="side_right">
	<ul class="skin_head_ico">
		<li id="li_version_h"><a href="javascript:void(0);"  onclick="aboutme()" class="skin-tool skin-tool-resource"  title="版本信息"></a></li>                                                                         
		<li id="li_msg_h"><a href="javascript:void(0);"  onclick="showMsg()" class="skin-tool skin-tool-msg" title="短信息"><span class="msg-num"><b type="msgnum" >0</b></span></a></li>
		<li><a href="javascript:void(0);"  onclick="updatePwd()" class="skin-tool skin-tool-set"  title="密码修改"></a></li>
		<li id="li_szpf_h"><a href="javascript:void(0);"  onclick="initSysSyle()" class="skin-tool skin-tool-szpf"  title="设置皮肤"></a></li>
		<li><a href="javascript:void(0);"  onclick="logout()" class="skin-tool skin-tool-exit"  title="退出"></a></li>
	</ul>
</div>
<div region="north" border="false" class="skin_head" split="false">
     <div class="skin_head_top">
         <span class="skin_head_wel">欢迎您：<span><%=request.getSession().getAttribute("userName") %>!</span></span>
         <ul class="skin_head_ico">
         	 <li id="li_version_r"><a href="javascript:void(0);"  onclick="aboutme()" class="skin-tool skin-tool-resource"  title="版本信息"></a></li>                                                                        
             <li id="li_msg_r"><a href="javascript:void(0);"  onclick="showMsg()" class="skin-tool skin-tool-msg" title="短信息"><span class="msg-num"><b type="msgnum" >0</b></span></a></li>
             <li><a href="javascript:void(0);"  onclick="updatePwd()" class="skin-tool skin-tool-set"  title="密码修改"></a></li>
             <li id="li_szpf_r"><a href="javascript:void(0);"  onclick="initSysSyle()" class="skin-tool skin-tool-szpf"  title="设置皮肤"></a></li>
             <li><a href="javascript:void(0);"  onclick="logout()" class="skin-tool skin-tool-exit"  title="退出"></a></li>
         </ul>
     </div>
     <div class="skin_head_logo">
         <div class="logo"><img  src="<%=request.getSession().getAttribute("logo") %>"></div>
 		<div class="pullDown">
       		<ul class="pullDownList">
       		</ul>
       		<!-- 下拉详细列表具体分类 -->
       		<div class="yMenuListCon">
       		</div>
        </div>
    </div>
</div>
<div region="west" border="false"  split="false" class="skins_left">
<div class="skin_left">
    <div class="pullDown">
        <ul class="pullDownList">
        </ul>
        <!-- 下拉详细列表具体分类 -->
        <div class="yMenuListCon">
        </div>
    </div>
  </div>  
</div>
<div region="center" border="false" class="skin_center" split="false">
<div  class="easyui-layout" fit="true">
	<div region="north" border="false" >
		<div class="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home"></i>
                <i id="breadMenu"><!-- <a href="javascript:void(0);">信息安全</a> > <a href="javascript:void(0);">日常监测</a> --></i>
                
            </li>
            <!-- <li class="active">>监测任务管理</li> -->
        </ul>
        <span class="skin_head_wel">欢迎您：<span><%=request.getSession().getAttribute("userName") %>!</span></span>
    </div>
	</div>
	<div region="center" border="false" id="mainPanel">
    	<iframe win="show" style="background-color: white;" height="100%" width="100%" scrolling="auto"  id="f_House" name="f_House"  frameborder="0"></iframe>
	</div>
</div>
	<div class="searchTop-drop">
		<ul>
			<li>业务</li>
			<li>菜单</li>
			<li>网页</li>
		</ul>
	</div>
	<input type="hidden" id="ukeynum" value="<%=request.getSession().getAttribute("ukeyNum") %>"/>
	<input type="hidden" id="usertype" value="<%=request.getSession().getAttribute("userType") %>"/>
	<input type="hidden" id="crrentdiverpath" value="<%=request.getSession().getAttribute("crrentdiverpath") %>"/>
	<object type="application/x-dongle" id="don" width="0" height="0"></object>
</body>
</html>