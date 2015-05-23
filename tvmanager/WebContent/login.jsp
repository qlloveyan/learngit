<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="public/public.jsp"></jsp:include>
<title>欢迎登录本系统</title>
</head>
<body>
	<form action="user/login.do" method="post">
		姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="name"/><br/>
		密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="text" name="password"/><br/><br/>
		
		<input type="submit" value="登录" />
	</form>
</body>
</html>