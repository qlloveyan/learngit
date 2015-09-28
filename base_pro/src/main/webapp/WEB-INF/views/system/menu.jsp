<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../public/header.jsp"%>
<script src="${resourceRoot }js/module/menu.js"></script>

<!-- 左侧树形菜单区域  -->
<div class="content">
	<!-- 按钮层 -->
	<div class="codeTree" style="display:inline-block;padding-top: 10px;  vertical-align: top;border:1px solid #ccc;height: 100%;">
	    <input type="hidden" id="pId" /> 
		<input class="btn btn-primary" type="button" onclick="add()" value="新增" />
		<input class="btn btn-primary" type="button" onclick="edit()" value="编辑" />
		<input class="btn btn-primary" type="button" onclick="del()" value="删除" />
		<ul>
			<li><span><i class="icon-folder-open"></i>菜单</span>
				<ul id="ulCode">
<!-- 					<li> -->
<!-- 	                	<span onclick="childClick('4')"><i class="icon-minus-sign"></i> 性别(USER_SEX)</span>  -->
<!-- 	                </li> -->
<!-- 	                <li> -->
<!-- 	                	<span onclick="childClick('7')"><i class="icon-minus-sign"></i> 用户状态(USER_STATE)</span> -->
<!-- 	                </li> -->
				</ul>
			</li>
		</ul>
	</div>
	<div style="width:77%;display: inline-block;">
		<!-- 父节点信息展示层 -->
		<div>
			<div class="dTitle">
				<span>菜单基本信息</span>
				<hr>
			</div>
			<div class="dFromCss">
				<form id="detailPFrom">
					<span>菜单名称：<input type="text" name="name" readonly="readonly"/></span>
					<span>菜单链接：<input type="text" name="pageCmpUrl" readonly="readonly"/></span>
					<span>菜单序号：<input type="text" name="showIndex" readonly="readonly"/></span>
				</form>
			</div >
		</div>
		<!-- 操作栏 -->
		<div>
			<div class="dTitle">
				<span>子菜单信息</span>
				<hr>
			</div>
			<input class="btn btn-primary" type="button" onclick="addChild()" value="新增" />
		</div >
		<div class="datagrid" style="display:inline-block;height:500px;width:100%;background-color:#fff;border:1px solid #ccc;">
			<table id="codeGrid" >
			</table>
		</div>
	</div>
</div>
<!-- 父类新增区域 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">新增菜单</h4>
			</div>
			<div class="modal-body">
				<form id="addPForm">
					菜单名称:<input type="text" name="name" class="easyui-validatebox" data-options="required:true"/><br/>
					菜单链接:<input type="text" name="pageCmpUrl" class="easyui-validatebox" data-options="required:true"/><br/>
					菜单序号:<input type="text" name="showIndex" class="easyui-validatebox" data-options="required:true,validType:'number'"/><br/>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" onclick="addPSure()">确认</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:$('#addModal').modal('close');">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- 编辑区域 -->
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">编辑菜单</h4>
			</div>
			<div class="modal-body">
				<form id="editPForm">
					<input type="hidden" name="id" />
					菜单名称:<input type="text" name="name" class="easyui-validatebox" data-options="required:true"/><br/>
					菜单链接:<input type="text" name="pageCmpUrl" class="easyui-validatebox" data-options="required:true"/><br/>
					菜单序号:<input type="text" name="showIndex" class="easyui-validatebox" data-options="required:true,validType:'number'"/><br/>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" onclick="editPSure()">确认</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:$('#editModal').modal('close');">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- 子类新增区域 -->
<div class="modal fade" id="addCModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">新增菜单</h4>
			</div>
			<div class="modal-body">
				<form id="addCForm">
					菜单名称:<input type="text" name="name" class="easyui-validatebox" data-options="required:true"/><br/>
					菜单链接:<input type="text" name="pageCmpUrl" class="easyui-validatebox" data-options="required:true"/><br/>
					菜单序号:<input type="text" name="showIndex" class="easyui-validatebox" data-options="required:true,validType:'number'"/><br/>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" onclick="addCSure()">确认</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:$('#addCModal').modal('close');">取消</button>
			</div>
		</div>
	</div>
</div>
<!-- 子类编辑区域 -->
<div class="modal fade" id="editCModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">编辑菜单</h4>
			</div>
			<div class="modal-body">
				<form id="editCForm">
					<input type="hidden" name="id" />
					菜单名称:<input type="text" name="name" class="easyui-validatebox" data-options="required:true"/><br/>
					菜单链接:<input type="text" name="pageCmpUrl" class="easyui-validatebox" data-options="required:true"/><br/>
					&nbsp;父菜单:<input type="text" name="parentMenu" id="editCPid" style=" height: 30px;"/><br/>
					菜单序号:<input type="text" name="showIndex" class="easyui-validatebox" data-options="required:true,validType:'number'"/><br/>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" onclick="editCSure()">确认</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:$('#editCModal').modal('close');">取消</button>
			</div>
		</div>
	</div>
</div>