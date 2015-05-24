<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="public/public.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		//登录
		$("#btnLogin").click(function(){
			$('#loginForm').form('submit', {   
			    url:'user/login.do',   
			    onSubmit: function(){
			    	return $(this).form('validate');
			    },   
			    success:function(data){
			    	data = eval("("+data+")");
			    	if( !data.success ){//登录不成功
			    		$.messager.alert('温馨提示',data.msg);
// 			    		$("#username").val(data.result.username);
			    	}
			    }   
			});  
		});
		
		//注册
		$("#btnRegister").click(function(){
			window.location.href="register.jsp";
		});
	});
</script>
<title>欢迎登录本系统</title>
</head>
<body>
	<form id="loginForm" method="post">
		姓&nbsp;&nbsp;&nbsp;&nbsp;名：<input type="text" name="username" id="username" class="easyui-validatebox" required="required"/><br/>
		密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" id="password" class="easyui-validatebox" required="required"/><br/>
		角&nbsp;&nbsp;&nbsp;&nbsp;色：
			<input type="radio" name="identity" value="1" checked="checked"/>普通用户
			<input type="radio" name="identity" value="2"/>管理员
		<br/><br/>
		
		<input type="button" value="登录" id="btnLogin" /> 
		<input type="button" value="注册" id="btnRegister" /> 
	</form>
</body>
</html>