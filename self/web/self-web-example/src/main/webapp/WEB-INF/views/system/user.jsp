<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-Control" CONTENT="no-cache">
	<title>应急部门管理</title>
    <jsp:include page="../../../public/common2.jsp" />
	<script type="text/javascript" src="js/modules/system/user.js"></script>
	<script type="text/javascript" src="js/plugs/easyui-expands/datagrid-detailview1.js"></script>
   	
</head>
<body  class="easyui-layout">
	<!-- 查询条件 -->
	<div region="north" class="breadCrumb-max">
		<!-- 查询条件 -->
		<div class="search-left">
			<span class="search-ico"></span>
			<h1 class="search-title">内容搜索</h1>
			<form id="queryForm">
			<div class="search-form">
			   	<span>用户名称  <input name="userName" id="userName" type="text" class="input-small"></span>
			   	<span>用户账号  <input name="userCode" id="userCode" type="text" class="input-small"></span>
			   	<span>角色  <input name="roleId" id="roleId" type="text" class="input-small"></span>
				<span class="search-button"><a href="javascript:void(0)" id="searchUser">搜索</a></span>
			</div>
			</form>
		</div>
		
		<!--  面包屑 -->
		<div class="search-right">
    		<span class="crumbs"><jsp:include page="../../../public/menuNav.jsp" /></span>
			<span class="full"><a href="javascript:void(0);"></a></span>
  		</div>
  		
  		
  		<!-- add -->
		<div class="list-add list-add-float">
			<a href="javascript:void(0)" id="addUser"><span class="add"></span>新增</a>
			<a href="javascript:void(0)" id="delUser"><span class="del"></span>删除</a>
	 	  </div>
	</div>
	<div data-options="region:'center'">
		<table id="userGrid" >
		</table>
	</div>
	
	
	<div id="assignRoles" >
    	<form id="assignRolesForm">
    		<input id="assignRolesForm-userId" type="hidden" name="userId" />
    		<div style="overflow-y: auto;height: 210px">
		    	<table id="assignRolesTable" style="width: 100%;" cellspacing="0">
				</table>
    		</div>
		</form>
    </div>
		
</body>
</html>
