<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="public/header.jsp" %>
<title>登录页</title>
<base href="<%=basePath%>">
</head>

<style>
<!--
body{font-family: "微软雅黑"; background-color:#444;}
.logo{width: 500px;margin: 0 auto;
margin-top: 60px;margin-bottom: 10px;padding: 15px;text-align: center;color: #fff;font-size: 25px;}
#loginForm{width: 500px;margin:0px auto;background: #fff;padding: 20px 20px 20px 20px;}
.header{border-bottom: 1px solid #0088cc;padding-bottom: 10px;margin-bottom: 29px;color: #0088cc;font-weight: normal;}
.form-horizontal .control-label{width:100px;}
.form-horizontal .controls{ margin-left:120px;position:relative;}
.input-medium{ width:300px;}
.icon-user,.icon-lock{position: absolute;top: 7px;right: 75px;}
.help-block{margin-top:10px;}
-->
</style>
<body>
<div class="logo">自定义后台管理系统</div>
	<form id="loginForm" action="${ctx}user/logindy.do" method="post" class="form-horizontal">
	<%
	String error = (String) request.getAttribute("error");
	if(error != null){
	%>
		<div class="alert alert-error input-medium controls">
			<button class="close" data-dismiss="alert">×</button>登录失败,错误信息：<%=error %>请重试.
		</div>
	<%
	}
	%>
	<h4 class="header" >用户登录</h4>
	
		<div class="control-group">
			<label for="username" class="control-label">用户名:</label>
			<div class="controls">
				<input type="text" id="phone" name="phone"  value="${phone}" class="input-medium" />
				<i class="icon-user"></i>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="password" name="password" class="input-medium "/>
				<i class="icon-lock"></i>
			</div>
		</div>
				
		<div class="control-group">
			<div class="controls">
<!-- 				<label class="checkbox" for="rememberMe"><input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我</label> -->
				<input id="submit_btn" class="btn btn-primary" type="submit" value="登录"/>
<%-- 				 <a class="btn" href="${ctx}/register">注册</a> --%>
<!-- 			 	<span class="help-block">(管理员: <b>admin/admin</b>, 普通用户: <b>user/user</b>)</span> -->
			</div>
		</div>
	</form>
	
</body>
</html>
