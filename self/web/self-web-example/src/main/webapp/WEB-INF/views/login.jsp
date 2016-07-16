<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" CONTENT="no-cache">
<title>欢迎使用<%=request.getSession().getServletContext().getInitParameter("smcsCnName")%></title>
<link rel="shortcut icon" type="image/x-icon"
	href="js/easyui/themes/rzx-ui/images/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="js/easyui/themes/rzx-ui/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<script type="text/javascript" src="js/easyui/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/common/utils.js"></script>
<script type="text/javascript">
	var logincount = 0;
	var Ry;
	var bConnect = 0;
	$(function() {
		
		$(".login-tis").hide();
		if ($("#msg").val() != "") {
			if ($("#errorType").val() == 1) {
				$("#minfo").html(
						'<b class="tisIco"></b>' + $("#msg").val() + '');
				$("#minfo").show();
			} else if ($("#errorType").val() == 2) {
				$("#pinfo").html(
						'<b class="tisIco"></b>' + $("#msg").val() + '');
				$("#pinfo").show();
			} else if ($("#errorType").val() == 3) {
				$("#cinfo").html(
						'<b class="tisIco"></b>' + $("#msg").val() + '');
				$("#cinfo").show();
			}
			var username = "";
			if ($('#errorUsername').val() != "null") {
				username = $('#errorUsername').val();
			}
			$('#userName').val(username);
		}
		if (top != self) {
			if (top.location != self.location) {
				top.location = self.location;
			}
		}
		$(document).keyup(function(event) {

			if (event.keyCode == 13) {
				submitForm();

			}

		});
		$("#change").click(
				function() {
					var random = Math.random();
					$("#vcode").attr(
							"src",
							"system/safety/verification.do?random=" + random
									+ "");

				});
	});

	function submitForm() {
		
		
		$(".login-tis").hide();
		var username = $("#userName").val();
		var pass = $("#rpwd").val();
		//var vcode = $("#captcha_value").val();
        // && vcode != ""
		if (username != "" && pass != "") {

			if (logincount == 0) {
				/* $.messager.progress({
					text:"系统登录中..."
				}); */
				$('#loginLoading').show();
				$('#loginLoaddingImg').show();
			}
			logincount += 1;
			setTimeout(submit, 1000);
		} else {
			if (username == "") {

				showmsg(1, "请输入用户名");
			}
			if (pass == "") {

				showmsg(2, "请输入密码");
			}
			//if (vcode == "") {

			//	showmsg(3, "请输入验证码");
			//} 
		}
	}
	function submit() {
		$('#loginForm').submit();
	}

	function showmsg(type, msg) {
		if (type == 1) {
			$("#minfo").html('<b class="tisIco"></b>' + msg + '');
			$("#minfo").show();
		} else if (type == 2) {
			$("#pinfo").html('<b class="tisIco"></b>' + msg + '');
			$("#pinfo").show();
		} else if (type == 3) {
			$("#cinfo").html('<b class="tisIco"></b>' + msg + '');
			$("#cinfo").show();
		}
	}

</script>
<style>
body {
	margin: 0;
	padding: 0;
	font-family: "微软雅黑";
}

ul,li,p,form,h1 {
	margin: 0;
	padding: 0;
	list-style: none
}

i {
	font-style: normal
}

#change {
	font-size: 12px;
}

#vcode {
	margin-left: 20px;
	vertical-align: middle;
	margin-right: 5px;
}

.login1440 {
	background: url(js/easyui/themes/smcs/login/bodybg.png) no-repeat center
		100px;
	background-color: #1d8bd8;
}

.login-form {
	font-size: 15px;
	color: #fff;
	width: 330px;
	height: 300px;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -165px;
	margin-top: -150px;
}

.login-form li.libg {
	padding: 3px 0 3px 15px;
	margin-bottom: 15px;
	width: 320px;
	height: 30px;
	color: #444;
	-webkit-border-radius: 20px;
	-moz-border-radius: 20px;
	border-radius: 20px;
	background-color: #fff;
	position: relative;
}

.login-form h1 {
	font-size: 30px;
	color: #fff;
	font-weight: 100;
	margin-bottom: 15px
}

.login-ipnut {
	border: 1px #FFFFFF solid;
	width: 230px;
	height: 28px;
	outline: 0;
	background-color: #fff;
	margin-left: 10px;
	padding-left: 5px;
	color: #444;
	font-size: 15px
}

.login li.login-num {
	padding-left: 46px;
	padding-top: 5px;
	padding-bottom: 0px;
	margin-bottom: 15px;
}

.login li.login-yzm {
	padding-left: 45px;
}

.login-tis {
	background-color: #0083BE;
	color: #fff;
	padding: 6px;
	margin-left: 30px;
	position: absolute;
	display: none;
	width: 150px;
}

.tisIco {
	width: 4px;
	height: 9px;
	background: url(js/easyui/themes/smcs/login/login-tisIco.png);
	position: absolute;
	left: -4px;
	top: 12px
}

.login-buttom a {
	display: block;
	width: 250px;
	height: 42px;
	-webkit-border-radius: 25px;
	-moz-border-radius: 25px;
	border-radius: 25px;
	line-height: 42px;
	text-align: center;
	background-color: #66d354;
	color: #fff;
	border: 0px;
	margin-left: 46px;
	z-index: 1000;
	position: absolute;
	text-decoration: none;
	font-size: 16px;
}

.login-buttom a:hover {
	background-color: #5dcd4a
}

.code {
	background-color: #0088da;
	border: 0px;
	color: #fff;
	width: 200px;
	font-size: 20px;
	margin: 0 0 0 140px
}

.login-loading {
	width: 100%;
	height: 1000px;
	display: none;
	z-index: 100000;
	top: 0px;
	position: fixed;
	background-color: #000;
	filter: alpha(opacity = 30); /*IE滤镜*/
	-webkit-opacity: 0.1;
	-moz-opacity: 0.1; /*Firefox私有*/
	opacity: 0.1; /*其他*/
}

.login-loadding-img {
	position: fixed;
	display: none;
	z-index: 100001;
	width: 200px;
	height: 45px;
	line-height: 45px;
	background-color: #1A1A1A;
	left: 50%;
	top: 50%;
	margin-left: -80px;
	-moz-box-shadow: 0px 0px 4px #005886;
	-webkit-box-shadow: 0px 0px 4px #005886;
	box-shadow: 0px 0px 4px #005886;
	filter: progid:DXImageTransform.Microsoft.Shadow(color='#005886',
		Direction=135, Strength=4); /*for ie6,7,8*/
	-moz-border-radius: 2px; /* Gecko browsers */
	-webkit-border-radius: 2px; /* Webkit browsers */
	border-radius: 2px; /* W3C syntax */
	filter: alpha(opacity = 60); /*IE滤镜*/
	-webkit-opacity: 0.6;
	-moz-opacity: 0.6; /*Firefox私有*/
	opacity: 0.6; /*其他*/
}

.login-loadding-img span {
	font-size: 14px;
	margin-left: 80px;
	color: #fff
}

.load-ico {
	background: url(js/easyui/themes/smcs/login/loadding.gif);
	margin-left: 30px;
	width: 40px;
	height: 41px;
	display: -moz-inline-box;
	display: inline-block;
	margin-right: 10px;
	position: absolute
}

.load_dun {
	position: absolute;
	top: 20px;
	right: 0;
	width: 270px;
	height: 48px;
	background-color: #0470b8;
	color: #fff;
	line-height: 48px;
}

.load_dun img {
	position: absolute;
	top: 1px;
	left: 1px;
}

.load_dun span {
	font-size: 14px;
	font-family: "微软雅黑";
	display: block;
	float: left;
	margin-left: 60px;
}

.load_dun span a {
	color: #fff;
	text-decoration: none;
}

.load_dun span a:hover {
	color: yellow;
	text-decoration: underline;
}

.foot {
	height: 40px;
	line-height: 40px;
	background-color: #0f64a5;
	text-align: center;
	color: #fff;
	font-size: 12px;
	position: absolute;
	bottom: 0;
	left: 0;
	right: 0;
}

.lg_logo {
	width: 605px;
	margin: 80px auto 0 auto;
}
</style>

</head>
<body class="login1440" >
<object type="application/x-dongle" id="don" width="0" height="0"></object>

	<div class="login-loading" id="loginLoading"></div>
	<div class="login-loadding-img" id="loginLoaddingImg">
		<i class="load-ico"></i><span>努力加载中······</span>
	</div>
	<div class="lg_logo">
		<img alt="" id="logo" src="js/sysui/images/loginimg/<%=request.getSession().getServletContext().getInitParameter("smcsArea")%>.png">
	</div>
	<div class="login" iconCls="icon-user" closable="false">
		<form id="loginForm" method="post" action="system/user/login.do">
			<div class="login-bg"></div>
			<div class="login-form">
				<ul>
					<li class="libg">用户名:<input type="text" autocomplete='off'
						class="login-ipnut" id="userName" name="username"
						data-options="required:true,validType:'length[3,10]'" /><i
						class="login-tis" id="minfo"></i></li>
					<li class="libg">密 码:<input type="password" autocomplete='off'
						class="login-ipnut" id="rpwd" name="pass" type="password" required
						validType="equals['#pwd']" /><i class="login-tis" id="pinfo"></i></li>
					<li class="libg">验证码:<input type="text" class="login-ipnut"
						id="captcha_value" id="captcha_value" name='captcha_value' /><i
						class="login-tis" id="cinfo"></i></li>
					<li class="login-num"><img id="vcode" width="150" height="40"
						alt="验证码" src="system/safety/verification.do"><a id="change"
						style="cursor: pointer;">看不清,换一张</a></li>
					<li class="login-buttom"><a id="ok" href="javascript:void(0)"
						onClick="submitForm();">登 录</a></li>
				</ul>
				<input type="hidden" id="errorType" value="${errorType}" /> 
				<input type="hidden" id="msg" value="${message}" /> 
				<input type="hidden" id="errorUsername" value="${username}" /> 
			</div>
		</form>
	</div>

	<div class="foot"></div>
</body>
</html>