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
	<script type="text/javascript" src="js/modules/test/test.js"></script>
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
			   	<span>网站名称  <input name="siteName" id="siteName" type="text" class="input-text"></span>
				<span>主办单位 <input name="sponsor" id="sponsor" type="text" class="input-text"></span>
				<span>信任级别 <input name="trustLevel" id="trustLevel" type="text" class="input-text"></span>
				<span>有效状态 
					<input  id="state" name="state" type="text" class="input-text">
				</span>
				<span class="search-button"><a href="javascript:void(0)" id="searchTest">搜索</a></span> 
				<span class="search-button search-gj"><a href="javascript:void(0)" id="searchTestAdvance">高级搜索</a></span> 
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
			<a href="javascript:void(0)" id="addTest"><span class="add"></span>新增</a>
			<a href="javascript:void(0)" id="delTest"><span class="del"></span>删除</a>
			<a href="javascript:void(0)" id="startTest"><span class="start"></span>启用</a>
			<a href="javascript:void(0)" id="stopTest"><span class="stop"></span>停用</a>
			<a href="javascript:void(0)" fileName="test" exportExcel="testGrid" type="export" formId="queryForm" id="exportTest" plain="true"><span class="export"></span>导出</a>
	 	  </div>
	</div>
	<div data-options="region:'center'">
		<table id="testGrid" >
		</table>
	</div>
	<div id="detailDiv" class="list-load" style="width: 100%;display: none;">
		<div class="list-detail">
			<form id="testDetailForm">
				<table class="list-detail-table" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<th>网站负责人:</th> <td><input class="nosign"  name="principal" readonly="readonly"></input></td>
						<th>网站首页:</th><td><input class="nosign" name="siteHomeUrl" readonly="readonly"></input></td>
						<th>有效证件类型:</th><td><input class="nosign" id="papersType" name="papersType" readonly="readonly"></input></td>
					</tr>
					<tr>
						<th>有效证件号码:</th><td><input class="nosign"  name="papersNum" readonly="readonly"></input></td>
						<th>手机号码:</th><td><input class="nosign"  name="telPhone" readonly="readonly"></input></td>
						<th>电子邮箱:</th><td><input class="nosign"  name="email" readonly="readonly"></input></td>
					</tr>
					<tr>
						<th>办公室号码:</th><td><input class="nosign"  name="officePhone" readonly="readonly"></input></td>
						<th>添加时间:</th><td><input class="nosign" name="createTime" readonly="readonly"></input></td>
						<th>备注:</th><td><input class="nosign" name="remark" readonly="readonly"></input></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div id="advanceSearch-dlg" style="width:600px;">
		<!-- 基本信息 -->
		<form id="advancedQueryForm"  method="post">
		<div class="easyui-panel" >
			<!-- 添加 -->
			<div class="add-table add-list2">
		    	<table  width="100%" border="0" cellspacing="0" cellpadding="0">
		    		<tr>
		    			<th width="100">网站名称：</th>
		    			<td><input name="siteName" type="text" class="input-text" /></td>
		    			<th width="100">主办单位：</th>
		    			<td><input name="sponsor" type="text" class="input-text" /></input></td>
		    		</tr>
		    		<tr>
		    			<th width="100">信任级别：</th>
		    			<td><input  id="trustLevelAdvance" name="trustLevel" type="text" class="input-text"></td>
		    			<th width="100">有效状态：</th>
		    			<td><input  id="stateAdvance" name="state" type="text" class="input-text"></td>
		    		</tr>
		    		<tr>
		    			<th width="100">域名：</th>
		    			<td><input name="domain" type="text" class="input-text"></td>
		    			<th width="100">网站类别：</th>
		    			<td><input name="siteType" type="text" class="input-text"></td>
		    		</tr>
		    		<tr>
		    			<th width="100">创建起始时间：</th>
		    			<td><input name="createTimeBefore" type="text" class="easyui-datebox" editable="false"></td>
		    			<th width="100">创建截止时间：</th>
		    			<td><input name="createTimeAfter" type="text" class="easyui-datebox" editable="false"></td>
		    		</tr>
		    	</table>
		    </div>
		</div>
		
		<div class="add-save">
		   	<a href="javascript:void(0);" id="advanceSearchSubmit" class="easyui-linkbutton" >搜索</a>
		   	<!--<a href="javascript:void(0);" id="advanceSearchReset" class="easyui-linkbutton" >重置</a>-->
		   	<a href="javascript:void(0);" id="advanceSearchCancel" class="easyui-linkbutton" onclick="javascript:$('#advanceSearch-dlg').dialog('close')">关闭</a>
		</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="js/common/excel.js"></script>
</html>
