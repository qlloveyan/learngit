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
		$('#registerForm').form('submit', {   
		    url:'user/addEntity.do',   
		    onSubmit: function(){
		    	return $(this).form('validate');
		    },   
		    success:function(data){
		    	data = eval("("+data+")");
		    	if( !data.success ){//登录不成功
		    		$.messager.alert('温馨提示',data.msg);
		    	}else{
		    		window.location.href="login.jsp";
		    	}
		    }   
		});  
	}
</script>
</head>
<body>
	<form id="registerForm" method="post">
		姓&nbsp;&nbsp;名：<input type="text" id="username" name="username" class="easyui-validatebox" required="required"/><br/>
		密&nbsp;&nbsp;码：<input type="password" id="password" name="password" class="easyui-validatebox" required="required"/><br/>
		确认密码：<input type="password" id="re_password" name="re_password" class="easyui-validatebox" required="required" validType="equals['#password']" /><br/>
		昵&nbsp;&nbsp;称：<input type="text" id="realname" name="realname" class="easyui-validatebox" required="required" /><br/>
		性&nbsp;&nbsp;别：
			<input type="radio" id="male" name="sex" value="男"/>男  &nbsp;&nbsp;
			<input type="radio" id="female" name="sex" value="女"/>女
		<br/> 
		年&nbsp;&nbsp;龄：<input type="text" id="age" name="age" class="easyui-numberspinner" data-options="min:1,max:150,editable:false"/><br/>
		电&nbsp;&nbsp;话：<input type="text" id="tel" name="tel" class="easyui-validatebox" data-options="required:true,validType:['tel','phone']"/><br/><br/>
		
		<input type="button" value="确定" onclick="register()"/>
	</form>
</body>
</html>