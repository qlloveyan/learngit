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
	<script type="text/javascript" src="js/modules/system/ctrlMethod.js"></script>
	<script type="text/javascript" src="js/plugs/easyui-expands/datagrid-detailview1.js"></script>
	<script type="text/javascript" src="js/common/smcsUpload.js"></script>
   	
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
			   	<span>代码模块  <input name="module" id="module" type="text" class="input-small"></span>
				<span>类名 <input name="className" id="className" type="text" class="input-small"></span>
				<span>方法名 <input name="methodName" id="methodName" type="text" class="input-small"></span>
				<span class="search-button"><a href="javascript:void(0)" id="searchCtrlMethod">搜索</a></span> 
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
			<a href="javascript:void(0)" id="addCtrlMethod"><span class="add"></span>新增</a>
			<a href="javascript:void(0)" id="delCtrlMethod"><span class="del"></span>删除</a>
			<a href="javascript:void(0)" 
				maxCount="1" 
				maxSize="1mb" 
				resouseId="a0001" 
				chooseTitle="文档" 
				suffix="xls,xlsx"   
				resouseCode="ctrlMethod"
				isDataImport="true"
				fileupload="false"
				callback="callback"
				importurl="system/ctrlMethod/importData.do"
				id="import" plain="true"><span class="import"></span>导入</a>
	 	  </div>
	</div>
	<div data-options="region:'center'">
		<table id="ctrlMethodGrid" >
		</table>
	</div>
	<div id="detailDiv" class="list-load" style="width: 100%;display: none;">
		<div class="list-detail">
			<form id="ctrlMethodDetailForm">
				<table class="list-detail-table" border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<th>代码模块:</th> <td><input class="nosign"  name="module" readonly="readonly"></input></td>
						<th>类名:</th><td><input class="nosign" name="className" readonly="readonly"></input></td>
						<th>类说明:</th><td><input class="nosign" name="classExplain" readonly="readonly"></input></td>
					</tr>
					<tr>
						<th>方法名:</th><td><input class="nosign"  name="methodName" readonly="readonly"></input></td>
						<th>方法说明:</th><td><input class="nosign"  name="mothodExplain" readonly="readonly"></input></td>
						<th>功能模块:</th><td><input class="nosign" name="funcModule" readonly="readonly"></input></td>
					</tr>
					<tr>
						<th>一级菜单:</th><td><input class="nosign" name="fiMenu" readonly="readonly"></input></td>
						<th>二级菜单:</th><td><input class="nosign"  name="seMenu" readonly="readonly"></input></td>
						<th>方法类别:</th><td><input class="nosign" name="methodType" readonly="readonly"></input></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/common/excel.js"></script>
</html>
