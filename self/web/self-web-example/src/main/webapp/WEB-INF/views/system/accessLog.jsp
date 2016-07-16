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
	<script type="text/javascript" src="js/modules/system/accessLog.js"></script>
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
				<span>操作用户 <input name="userName" id="userName" type="text" class="input-small"></span>
				<span>时间 <input name="timeBefore" id="timeBefore" type="text" class="easyui-datebox input-small" data-options="editable:false"></span>
					     至 <input  id="timeAfter" name="timeAfter" type="text" class="easyui-datebox input-small" data-options="editable:false" validType="after">
				<span class="search-button"><a href="javascript:void(0)" id="searchAccessLog">搜索</a></span> 
				<span class="search-button"><a href="javascript:void(0)" id="searchAccessLogAdvance">高级搜索</a></span> 
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
			<a href="javascript:void(0)"  id="exportAccessLog" ><span class="export"></span>导出</a>
<!-- 		<a href="javascript:void(0)" fileName="accessLog" exportExcel="accessLogGrid" type="export" formId="queryForm" id="exportAccessLog" plain="true"><span class="export"></span>导出</a> -->
	 	  </div>
	</div>
	<div data-options="region:'center'">
		<table id="accessLogGrid" >
		</table>
	</div>
	<div id="detailDiv" class="list-load" style="width: 100%;display: none;">
		<div class="list-detail">
			<form id="accessLogDetailForm">
				<table class="list-detail-table" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<th>日志信息:</th> <td colspan="5"><input class="nosign"  name="logMsg" readonly="readonly"></input></td>
					</tr>
					<tr>
						<th>系统模块:</th><td><input class="nosign" name="funcModule" readonly="readonly"></input></td>
						<th>一级菜单:</th><td><input class="nosign" name="fiMenu" readonly="readonly"></input></td>
						<th>二级菜单:</th><td><input class="nosign"  name="seMenu" readonly="readonly"></input></td>
					</tr>
					<tr>
						<th>方法类别:</th><td><input class="nosign" name="methodType" readonly="readonly"></input></td>
						<th>访问端IP:</th><td><input class="nosign" name="ip" readonly="readonly"></input></td>
						<th>操作用户:</th><td><input class="nosign" name="userName" readonly="readonly"></input></td>
					</tr>
					<tr>
						<th>时间:</th><td><input class="nosign"  name="time" readonly="readonly"></input></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div id="advanceSearch-dlg" style="width:600px;">
		<!-- 基本信息 -->
		<form id="advancedQueryForm"  method="post">
		
			<!-- 添加 -->
			<div class="add-table add-list2">
		    	<table  width="100%" border="0" cellspacing="0" cellpadding="0">
		    		<tr>
		    			<th width="100">所属平台：</th>
		    			<td><input id="plateform" type="text" class="input-text" readonly="readonly"></td>
		    			<th width="100">系统模块：</th>
		    			<td><input name="funcModule" id="funcModuleAdvance" type="text" class="input-text" readonly="readonly"></td>
		    		</tr>
		    		<tr>
		    			<th width="100">一级菜单：</th>
		    			<td><input name="fiMenu" id="fiMenuAdvance" type="text" class="input-text" readonly="readonly"></td>
		    			<th width="100">二级菜单：</th>
		    			<td><input name="seMenu" id="seMenuAdvance" type="text" class="input-text" readonly="readonly"></td>
		    		</tr>
		    		<tr>
		    			<th width="100">方法类别：</th>
		    			<td><input name="methodType" id="methodTypeAdvance" type="text" class="input-text" readonly="readonly"></td>
		    			<th width="100">操作用户：</th>
		    			<td><input name="userName" type="text" class="input-text"></td>
		    		</tr>
		    		<tr>
		    			<th width="100">访问端IP：</th>
		    			<td><input name="ip" type="text" class="easyui-validatebox input-text" validType="ip"></td>
		    			<th width="100">查询起始时间：</th>
		    			<td><input name="timeBefore" id="timeBeforeAdvance" type="text" class="easyui-datetimebox input-text" data-options="editable:false" ></td>
		    		</tr>
		    		<tr>
		    			<th width="100">查询截止时间：</th>
		    			<td><input  id="timeAfterAdvance" name="timeAfter" type="text" class="easyui-datetimebox input-text" data-options="editable:false" validType="afterAdvance"></td>
		    		</tr>
		    	</table>
		
		</div>
		
		<div class="add-save">
		   	<a href="javascript:void(0);" id="advanceSearchSubmit" class="easyui-linkbutton" >查询</a>
		   	<a href="javascript:void(0);" id="advanceSearchReset" class="easyui-linkbutton" >重置</a>
		   	<a href="javascript:void(0);" id="advanceSearchCancel" class="easyui-linkbutton" onclick="javascript:$('#advanceSearch-dlg').dialog('close')">关闭</a>
		</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="js/common/excel.js"></script>
</html>
