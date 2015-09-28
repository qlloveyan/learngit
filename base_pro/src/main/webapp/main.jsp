<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="public/header.jsp" %>
<title>自定义后台管理系统</title>
    <style type="text/css">
      body {
      font-family:"微软雅黑";
        padding-top: 40px;
        overflow-y:hidden;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
      
      .nav-list li.unfold {
			color: #000;
			font-weight: bold;
			background-color: #70C5F4;
		}
    </style>
<script>

if(${empty user}){
    alert('您已经与服务器断开，请重新登录');
    window.location.href="<%=basePath%>login.jsp";
};

$(function(){
	Surfilter.main.init();
});

function changPass(){
	Surfilter.main.changPass();
}

function changeSure(){
	Surfilter.main.changeSure();
}
</script>
</head>
<body>
<!-- 头部 start -->
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="navbar-inner ">
  	<div class="container-fluid">
  	<b class="brand">翼族后台管理系统</b>

    <div class="pull-right top-right">
    <ul class="nav">
       <!-- 
			一级菜单
	    -->
	</ul>
	
	
	<div class="btn-group ">
				<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
					<i class="icon-user"></i> <shiro:principal property="name"/>
					<span class="caret"></span>
				</a>
			
				<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void()">${userName}</a></li>
						<li class="divider"></li>
					<li><a href="javascript:void()" onclick="changPass()">修改密码</a></li>
					<li><a href="<%=basePath%>user/logout.do">退出</a></li>
				</ul>
			</div>
	</div>
	</div>
	
			
  </div>
</div>
<!-- 头部 end -->

<div class="container-fluid">
<div class="row-fluid">
	<!-- 内容左 start -->
	<div class="span2">
	<div class="well sidebar-nav">
		<h1 class="nav-header"><!-- 任务管理 --></h1>
		<ul class="nav nav-list">
	  		<!-- 
	  			左侧二级、三级菜单
	   		-->
	 	</ul>
	 </div>
	 </div>
	<!-- 内容左 end -->
  
  	<!-- 密码修改 -->
  	<div class="modal fade" id="changePasDlg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;z-index: -1;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">密码修改</h4>
			</div>
			<div class="modal-body">
				<form id="changeForm">
					&nbsp;&nbsp;&nbsp;旧密码：<input type="password" name="oldPas" class="easyui-validatebox" data-options="required:true"/><br/>
					&nbsp;&nbsp;&nbsp;新密码：<input type="password" name="password" id="password" class="easyui-validatebox" data-options="required:true"/><br/>
					确认密码：<input type="password" name="password2" class="easyui-validatebox" required="required" validType="equals['#password']" /><br/>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" onclick="changeSure()">确认</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:$('#changePasDlg').modal('close');">取消</button>
			</div>
		</div>
	</div>
	</div>
  
	 <!-- 内容右 start -->
<div class="span10">
		<div id="myAlertSuccess" class="alert alert-success hide">
			<strong id="warningAlertMsg">成功!</strong>
				<button type="button" class="close" onclick='javascript: $("#myAlertSuccess").hide();'>
				  <span aria-hidden="true">&times;</span>
				</button>
		</div>
			
		<div id="myAlertInfo" class="alert alert-info hide">
			<strong id="infoAlertMsg">信息!</strong>
				<button type="button" class="close" onclick='javascript: $("#myAlertInfo").hide();'>
				  <span aria-hidden="true">&times;</span>
				</button>
		</div>
			
		<div id="myAlertWarning" class="alert alert-warning hide">
			<strong id="warningAlertMsg">警告!</strong>
				<button type="button" class="close" onclick='javascript: $("#myAlertWarning").hide();'>
				  <span aria-hidden="true">&times;</span>
				</button>
		</div>
			 	
		<div id="myAlertDanger" class="alert alert-danger hide">
			<strong id="dangerAlertMsg">危险!</strong>
				<button type="button" class="close" onclick='javascript: $("#myAlertDanger").hide();'>
				  <span aria-hidden="true">&times;</span>
				</button>
		</div>
	<iframe class="main-right" id="mainPage" frameborder=0 style="width:100%; overflow-y:hidden;">
	</iframe>
</div>
</div>
</div>
</body>
</html>