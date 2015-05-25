<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<>">
<jsp:include page="public.jsp"></jsp:include>
<title></title>
<script type="text/javascript">
$(function(){
	//隐藏修改密码界面
	$('#changePas-dlg').hide();
	$("#btnChangePas").click(function(){
		
		$('#changePas-dlg').show();
		$('#changePas-dlg').dialog({
		    title: '修改密码',
		    width: 400,
		    height: 200,
		    closed: false,
		    cache: false,
		    modal: true
		});
	});
	
	//修改
	$("#changePasSubmit").click(function(){
		$('#changePasForm').form('submit', {   
		    url:'user/changePas.do',   
		    onSubmit: function(){
		    	return $(this).form('validate');
		    },   
		    success:function(data){
		    	data = eval("("+data+")");
		    	$.messager.show({
		    		title:'密码修改提示',
		    		msg:data.msg,
		    		timeout:3000,
		    		showType:'slide'
		    	});
		    	if( data.success ){
		    		$('#changePas-dlg').dialog('close');
		    	}
		    }   
		}); 
	});
});

</script>
</head>
<body>
	<!-- 欢迎信息层 -->
	<div>
		<div>
			欢迎您：
			<c:if test="${userName != null}">
				${userName}
			</c:if>
			<c:if test="${ userName == null}">
				游客
			</c:if>
			<c:if test="${identity == 1}">
				 | 普通会员
			</c:if>
			<c:if test="${identity == 2}">
				 | 管理员
			</c:if>
		</div>
		
		<div style="padding-right: 0px;">
			<c:if test="${userName != null }">
				<a href="javascript:void(0)" id="btnChangePas">修改密码</a> | 
				<a href="user/loginOut.do" id="btnChangePas">退出</a>
			</c:if>
			<c:if test="${userName == null}">
				<a href="login.jsp">登录</a> | 
				<a href="register.jsp">注册</a>
			</c:if>
		</div>
	</div>
	
	<!-- 菜单展示层 -->
	<div>
		
	</div>
	<hr/>
	<!-- 修改密码的链接 -->
	<div id="changePas-dlg">
		<!-- 基本信息 -->
		<form id="changePasForm"  method="post">
			<div>
				<table>
					<tr>
						<td style="text-align: right;">原始密码:</td>
						<td> <input type="password" id="oldPas" name="oldPas" class="easyui-validatebox" required="required"/></td>
					</tr>
					<tr>
						<td style="text-align: right;">新密码:</td>
						<td><input type="password" id="newPas" name="newPas" class="easyui-validatebox" required="required" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">确认密码:</td>
						<td><input type="password" id="newPas2" name="newPas2" class="easyui-validatebox" required="required" validType="equals['#newPas']"/></td>
					</tr>
				</table>
			</div>
			<br/>
			<div >
			   	<a href="javascript:void(0);" id="changePasSubmit" class="easyui-linkbutton" >确定</a>
			   	<a href="javascript:void(0);" id="changePasCancel" class="easyui-linkbutton" onclick="javascript:$('#changePas-dlg').dialog('close')">取消</a>
			</div>
			<input type="hidden" value="${userId}" name="id">
		</form>
	</div>
</body>
</html>