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
	<title>机房信息</title>
    <jsp:include page="../../../public/common2.jsp" />
	<script type="text/javascript" src="js/modules/system/sysLog.js"></script>
   	
</head>
<body  class="easyui-layout">
	<!-- 查询条件 -->
<!-- 	<div data-options="region:'north',collapsed:false,title:'查询',split:false" style="height:90px;padding:10px"> -->
<!-- 		<div> -->
<!-- 			<form id="queryForm"> -->
<!-- 				功能名称: <input class="easyui-validatebox" name="moduleName" id="moduleName" type="text">&nbsp;&nbsp; -->
<!-- 				操作员名称: <input class="easyui-validatebox" name="userName" id="userName"type="text" >&nbsp;&nbsp; -->
<!-- 				操作时间  从: -->
<!-- 	            <input class="easyui-datebox" id="opreateTimeBefore" name="opreateTimeBefore">&nbsp;至  -->
<!-- 	            <input class="easyui-datebox" id="opreateTimeAfter" name="opreateTimeAfter"> -->
<!-- 				<a href="javascript:void(0)" class="easyui-linkbutton" id="searchSysLog" iconCls="icon-search">查询</a> -->
<!-- 				<a href="javascript:void(0)" class="easyui-linkbutton" id="resetSysLog"iconCls="icon-database_delete">重置</a> -->
<!-- 			</form> -->
<!-- 		</div> -->
<!-- 	</div> -->
		<div region="north" class="breadCrumb-small">
			<!-- 查询条件 -->
			<div class="search-left">
				<span class="search-ico"></span>
				<h1 class="search-title">内容搜索</h1>
				<form id="queryForm">
				<div class="search-form">
				   	<span>功能名称: <input class="easyui-validatebox input-small" name="moduleName" id="moduleName" type="text"/></span>
					<span>用户账号: <input class="easyui-validatebox input-small" name="userName" id="userName" type="text"/></span>
					<span>
						操作时间  从: 
						<input class="easyui-datebox input-small" name="opreateTimeBefore" id="opreateTimeBefore" type="text"/> — 
						<input class="easyui-datebox input-small" name="opreateTimeAfter" id="opreateTimeAfter" type="text"/>
					</span>
					<span class="search-button"><a href="javascript:void(0)" class="easyui-linkbutton" id="searchSysLog" iconCls="icon-search">查询</a></span> 
					<span class="search-button"><a href="javascript:void(0)" class="easyui-linkbutton" id="resetSysLog"iconCls="icon-database_delete">重置</a></span> 
				</div>
				</form>
			</div>
			
			<!--  面包屑 -->
			<div class="search-right">
	    		<span class="crumbs"><jsp:include page="../../../public/menuNav.jsp" /></span>
				<span class="full"><a href="javascript:void(0);"></a></span>
	  		</div>
  		</div>
	<div data-options="region:'center'">
		<table id="sysLogGrid" >
		</table>
	</div>
</body>
</html>
