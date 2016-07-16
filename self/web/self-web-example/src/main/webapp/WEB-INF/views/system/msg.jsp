<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- 查询条件 -->
<!-- 	<div data-options="region:'north',collapsed:false,title:'查询',split:false" style="height:90px;padding:10px">
		<div>
			<form id="queryForm">
				机房名称: <input class="easyui-validatebox" name="msgName" id="msgName" type="text">
				机房编号: <input class="easyui-validatebox" name="msgCode" id="msgCode"type="text">
				<a href="javascript:void(0)" class="easyui-linkbutton" id="searchMsg" iconCls="icon-search">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" id="resetMsg"iconCls="icon-database_delete">重置</a>
			</form>
		</div>
	</div> -->
	<div data-options="region:'center'">
		<!-- <div id="tb" style="padding:0px;height:auto;">
			<div style="margin-bottom:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"  id="addMsg" plain="true">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" id="delMsg" plain="true">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-import" id="importMsg" plain="true">导入</a>
			</div>
		</div> 
		-->
		<div style="height: 314px; margin:0 0 0 0px">
				<table id="msgGrid" >
				</table>
		</div>
		
	</div>
<script type="text/javascript" src="js/modules/system/msg.js"></script>
