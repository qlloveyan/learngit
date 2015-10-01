<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../public/header.jsp"%>
<script src="${resourceRoot }js/module/code.js"></script>

<!-- 左侧树形菜单区域 -->
<div class="content">
	<!-- 按钮层 -->
	<div class="codeTree" style="display:inline-block;padding-top: 10px; vertical-align: top;border:1px solid #ccc;width: 20%">
	    <input type="hidden" id="pId" />
	    <input type="hidden" id="codeType" />
		<input class="btn btn-primary" type="button" onclick="add()" value="新增" />
		<input class="btn btn-primary" type="button" onclick="edit()" value="编辑" />
		<input class="btn btn-primary" type="button" onclick="del()" value="删除" />
		<ul>
			<li><span><i class="icon-folder-open"></i>数据字典</span>
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
	
	<div style="width:78%;display: inline-block;">
		<!-- 父节点信息展示层 -->
		<div>
			<div class="dTitle">
					<span>字典基本信息</span>
					<hr>
			</div>
			<div class="dFromCss">
				<form id="detailPFrom">
					<span>字典类型：<input type="text" name="codeType" readonly="readonly"/></span>
					<span>字典名称：<input type="text" name="codeName" readonly="readonly"/></span>
<!-- 					<span>字典编码：<input type="text" name="codeKey" readonly="readonly"/></span></br> -->
<!-- 					<span>字典序号：<input type="text" name="codeSort" readonly="readonly"/></span> -->
				</form>
			</div >
		</div>
		<div>
			<div class="dTitle">
				<span>子节点信息</span>
				<hr>
			</div>
			<input class="btn btn-primary" type="button" onclick="addChild()" value="新增" />
		</div>
		<div class="datagrid" style="width:100%;display:inline-block;height:500px;background-color:#fff;border:1px solid #ccc;">
			<table id="codeGrid" >
			</table>
		</div>
	</div>
</div>
<!-- 父类新增区域 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;z-index: -1;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">新增字典</h4>
			</div>
			<div class="modal-body">
				<form id="addPForm">
					字典名称:<input type="text" name="codeName" class="easyui-validatebox" data-options="required:true"/><br/>
					字典类型:<input type="text" name="codeType" class="easyui-validatebox" data-options="required:true"/><br/>
<!-- 					菜单序号:<input type="text" name="codeSort" class="easyui-validatebox" data-options="required:true,validType:'number'"/><br/> -->
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
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;z-index: -1;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">编辑字典</h4>
			</div>
			<div class="modal-body">
				<form id="editPForm">
					<input type="hidden" name="id" />
					字典名称:<input type="text" name="codeName" class="easyui-validatebox" data-options="required:true"/><br/>
					字典类型:<input type="text" name="codeType" readonly="readonly"/><br/>
<!-- 					菜单序号:<input type="text" name="codeSort" class="easyui-validatebox" data-options="required:true,validType:'number'"/><br/> -->
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
<div class="modal fade" id="addCModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;z-index: -1;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">新增字典</h4>
			</div>
			<div class="modal-body">
				<form id="addCForm">
					字典名称:<input type="text" name="codeName" class="easyui-validatebox" data-options="required:true"/><br/>
					字典类型:<input type="text" name="codeType" readonly="readonly"/><br/>
					&nbsp;字典值:<input type="text" name="codeKey" class="easyui-validatebox" data-options="required:true"/><br/>
					字典序号:<input type="text" name="codeSort" class="easyui-validatebox" data-options="required:true,validType:'number'"/><br/>
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
<div class="modal fade" id="editCModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 400px;z-index: -1;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">编辑字典</h4>
			</div>
			<div class="modal-body">
				<form id="editCForm">
					<input type="hidden" name="id" />
					字典名称:<input type="text" name="codeName" class="easyui-validatebox" data-options="required:true"/><br/>
					字典类型:<input type="text" name="codeType" readonly="readonly"/><br/>
					&nbsp;字典值:<input type="text" name="codeKey" class="easyui-validatebox" data-options="required:true"/><br/>
					&nbsp;父节点:<input type="text" name="pid" id="editCPid" style="height: 30px;"/><br/>
					字典序号:<input type="text" name="codeSort" class="easyui-validatebox" data-options="required:true,validType:'number'"/><br/>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" onclick="editCSure()">确认</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:$('#editCModal').modal('close');">取消</button>
			</div>
		</div>
	</div>
</div>