<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
    <title>温馨提示</title>
    <meta charset="utf-8">
    <style>
        html{ overflow: hidden;}
        body{ margin: 0; padding: 0; font-family: "微软雅黑"; background: #c4deff url(js/easyui/themes/rzx-ui/images/body-bg.gif) repeat; overflow: hidden; }
        .waves { position: absolute; z-index: 50; bottom: 0; left: 0; width: 100%; height: 103px; list-style: none; margin: 0; padding: 0;}
        .waves .wave2 {position: absolute;height: 116px; width: 100%;background: url(js/easyui/themes/rzx-ui/images/wave2.png) center repeat; z-index: 20; }
        .waves .wave3 {position: absolute;height: 116px; width: 100%;background: url(js/easyui/themes/rzx-ui/images/wave3.png) center repeat; z-index: 10; }
        .container{width: 746px; height: 325px; background: url(js/easyui/themes/rzx-ui/images/ribbon.png) no-repeat; position: absolute; top: 50%; left: 50%;
            margin-left: -350px; margin-top: -230px;}
        .note{ font-size: 25px; color: #444; margin: 120px 0 0 140px; overflow: hidden;}
        .note .mess{ float: left; display: block; margin-top: 35px;}
        .error{ background:url("js/easyui/themes/rzx-ui/images/error.png") no-repeat; width: 75px; height: 70px;  float: left; margin-top: 20px; margin-right: 10px;}
        .right{ background:url("js/easyui/themes/rzx-ui/images/right.png") no-repeat; width: 75px; height: 70px;  float: left; margin-top: 20px; margin-right: 10px;}
        .return{ font-size: 18px; color: #0088cc; display: block; float: left; margin-top: 40px;  margin-left: 15px;}
    </style>
    <script type="text/javascript" src="js/easyui/jquery-1.8.0.min.js"></script>
    <script type="text/javascript">

	    $(function(){
			var errorType = $("#errorType").val();
			if( errorType == 0  ){
				$("#msg_css").attr("class","right");
			}else{
				$("#msg_css").attr("class","error");
			}
	    });
	</script>
</head>
<body>
    <div class="container">
        <div class="note">
            <i class="" id="msg_css"></i>
            <span class="mess">${message}</span>
<%--             <a href="<%=basePath%>" class="return">返回主页面</a> --%>
        </div>
    </div>
    <ul class="waves">
        <li class="wave2"></li>
        <li class="wave3"></li>
    </ul>
	<input type="hidden" id="errorType" name="errorType" value="${errorType}" />
</body>
</html>