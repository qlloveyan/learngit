<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html style="height:100%">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-Control" CONTENT="no-cache">
	<script type="text/javascript" src="js/easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript">
	//src="https://183.136.190.53:8443/EverOneForZj/main/index?sessionkey=sk_wangguohong"
	$(document).ready(function(){
		window.parent.sumitSn();
	});
	</script>
</head>
<body style="height:100%">
<form id="urlform"  method="post" target="showns" >

<input type="hidden" id="sessionkey" name="sessionkey" />
<input type="hidden" id="url" name="url" />
</form>
<!-- 
ns/securityAthor/getathordata.do
https://183.136.190.53:8443/EverOneForZj/main/index?sessionkey=sk_wangguohong
http://jingyan.baidu.com/article/e4d08ffdb61f040fd3f60d48.html
 -->
<iframe  id="shownsframe" name="showns" src="" style="background-color: white;border: 0" width="100%" height="100%" scrolling="auto"  ></iframe>
</body>
</html>