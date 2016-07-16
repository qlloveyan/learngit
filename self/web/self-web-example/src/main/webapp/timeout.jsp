<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" /> 
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>哎呀...超时了！</title>
<link rel="stylesheet" type="text/css" />

<style>
*{margin:0;padding:0}
body{font-family:"微软雅黑";background:#DAD9D7}
img{border:none}
a *{cursor:pointer}
ul,li{list-style:none}
table{table-layout:fixed;}
table tr td{word-break:break-all; word-wrap:break-word;}


.cf:after{content: ".";display: block;height: 0;font-size: 0;clear:both;visibility: hidden;}
.cf{zoom: 1;clear:both}

.bg{width:100%;background:url("resources/images/timeout/01.jpg") no-repeat center top #DAD9D7;position:absolute;top:0;left:0;height:600px;overflow:hidden}
.cont{margin:0 auto;width:500px;line-height:20px;}
.c1{height:360px;text-align:center}
.c1 .img1{margin-top:180px}
.c1 .img2{margin-top:165px}
.cont h2{text-align:center;color:#555;font-size:18px;font-weight:normal;height:35px}
.c2{height:35px;text-align:center}
.c2 a{display:inline-block;margin:0 4px;font-size:14px;height:23px;color:#626262;padding-top:1px;text-decoration:none;text-align:left}
.c2 a:hover{color:#626262;text-decoration:none;}
.c2 a.home{width:66px;background:url("resources/images/timeout/02.png");padding-left:30px}
.c2 a.home:hover{background:url("resources/images/timeout/02.png") 0 -24px}
.c2 a.home:active{background:url("resources/images/timeout/02.png") 0 -48px}
.c2 a.re{width:66px;background:url("resources/images/timeout/03.png");padding-left:30px}
.c2 a.re:hover{background:url("resources/images/timeout/03.png") 0 -24px}
.c2 a.re:active{background:url("resources/images/timeout/03.png") 0 -48px}
.c2 a.sr{width:153px;background:url("resources/images/timeout/04.png");padding-left:28px}
.c2 a.sr:hover{background:url("resources/images/timeout/04.png") 0 -24px}
.c2 a.sr:active{background:url("resources/images/timeout/04.png") 0 -48px}
.c3{height:180px;text-align:center;color:#999;font-size:12px}
#bf{position:absolute;top:269px;left:0;width:100%}
.bf1{margin:0 auto;width:99px;padding-left:32px}
.bd{height:600px;overflow:hidden}
#box{position:absolute;top:165px;left:0;width:100%;text-align:center}
.bf1{margin:0 auto;width:99px;padding-left:32px}
</style>

</head>
<body>
<input type="hidden" id="basePath" value="<%=basePath%>"/>
<div class="bg">
	<div class="cont">
		<div class="c1"><img src="resources/images/timeout/01.png" class="img1" /></div>
		<h2>由于您长时间没有操作，需要重新登录<a href="<%=basePath%>" >返回登录</a></h2>
		<div class="c2"></div>
		<div class="c3"></div>
	</div>
</div>
</body>
</html>