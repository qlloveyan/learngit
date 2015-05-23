<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎注册本站</title>
<jsp:include page="public/public.jsp"></jsp:include>
<script type="text/javascript">
	function register(){
		var params = "name="+$("#name").val()+"&password="+$("#password").val()+"&nickName="+$("#nickName").val()+"&email="+$("#email").val();
		$.ajax({
			url:'user/addUser.do?' + params,  
	        type:'post',  
	        dateType:'json',  
	        success:function(data){
	        	data = eval("("+data+")");
	        	if( !data.flag ){
	        		alert(data.msg);
	        	}
	        }  
		});
	}
</script>
</head>
<body>
	<form id="registerForm">
		姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" id="name" name="name"/><br/>
		密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="text" id="password" name="password"/><br/>
		昵&nbsp;&nbsp;&nbsp;&nbsp;称：<input type="text" id="nickName" name="nickName"/><br/>
		邮&nbsp;&nbsp;&nbsp;&nbsp;箱：<input type="text" id="email" name="email"/><br/><br/>
		
		<input type="button" value="添加" onclick="register()"/>
	</form>
</body>
</html>