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
	<title>角色信息</title>
    <jsp:include page="../../../public/common2.jsp" />
     <style type="text/css">
    .tree-checkbox1 {
		    background: url('js/easyui/themes/black/images/tree_icons1.png') no-repeat -224px -18px;
		}
	.tree-checkbox0 {
	    background: url('js/easyui/themes/black/images/tree_icons1.png') no-repeat -208px -18px;
	}
    </style>
	<script type="text/javascript" src="js/modules/system/role.js"></script>
   	
</head>
<body  class="easyui-layout" fit="true">
	<!-- 左边 -->
	<div data-options="region:'center'" class="listCenter">
		<div  class="easyui-layout" fit="true">
			<!-- 查询条件 -->
			<div region="north" >
				<!-- 查询条件 -->
				<div class="search-left">
					<span class="search-ico"></span>
					<h1 class="search-title">内容搜索</h1>
					<form id="queryForm">
					<div class="search-form">
					   	<span>角色名称  <input id="roleName" class="input-small"/></span>
						<span class="search-button"><a href="javascript:void(0)" onclick="doSearch()" id="searchUser">搜索</a></span>
					</div>
					</form>
				</div>
		  		<!-- add -->
				<div class="list-add">
					 <a href="javascript:void(0)" class="btn" id="addRole"><i class="add"></i>新增</a>
			  		 <a href="javascript:void(0)" class="btn" id="delRole"><i class="del"></i>删除</a>
			 	 </div>
			</div>
			<div data-options="region:'center'">
				<table id="roleGrid" >
				</table>
			</div>
		</div>
	</div>
	<!-- 右边 -->
	<div data-options="region:'east'" class="east">
		<div class="easyui-layout" fit="true">
			<div data-options="region:'north'" style="height:85px">
				<h1 class="tree-h1">权限设置</h1>
				<h2 class="east-bt">
				   <span class="bt-1">
				     <div id="functionListPanel" class="easyui-panel" title="禁用功能模块列表" data-options="tools:[{iconCls:'icon-save1',handler:submitAssignModulesFn}]"  fit="true"></div>
				   </span>
				   <span class="bt-2">
			         <div id="btnListPanel" class="easyui-panel" title="禁用操作按钮列表"  data-options="tools:[{iconCls:'icon-save1',handler:submitAssignElementsFn}]"  fit="true" ></div>
			       </span>
				</h2>
				<p>
		   		  <a href="javascript:void(0)" class="btn" id="selectAllBtn">全选</a>
				  <a href="javascript:void(0)" class="btn" id="resetRole">重置</a>
		   		</p>
			</div>  
			<div data-options="region:'center'" style="overflow-y:hidden;">
			
				<div class="east-list-1" style="overflow-y:auto;">
				   <form id="assignModulesForm">
			    		<input type="hidden" name="roleId" id="roleId"/>
			   			<ul id="modulesTree" class="easyui-tree"></ul>
					</form>
				</div>
				<div class="east-list-2" style="overflow-y:auto;">
				   <form id="assignBtnsForm">
						<input type="hidden" name="funcId" id="funcId"/>
						<ul id="btnsTree" class="easyui-tree"></ul>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
<!-- <script type="text/javascript" src="js/common/excel.js"></script> -->
</html>
