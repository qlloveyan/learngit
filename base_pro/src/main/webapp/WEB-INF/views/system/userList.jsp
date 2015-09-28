<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../public/header.jsp" %>
<script
	src="${resourceRoot }/js/module/userList.js"></script>

<!-- 条件查询 -->
<div class="">
	<form UI-Module="listForm">
		<span class="list-search-ico"></span> 
		<span>
			<label class="help-inline" >用户名：</label>
			<input class="span2" type="text" name="name" style="margin-top: 10px;"></input>
			<label class="help-inline" >用户类型：</label>
<!-- 			<input class="span2" type="text" name="status" id="queryStatus" style="margin-top: 10px;"></input> -->
				<select name="status" class="span2" style="margin-top: 10px;">
				  <option value="">全部</option>
				  <option value="3">管理员</option>
				</select>
			<label class="help-inline" >用户状态：</label>
<!-- 			<input class="span2" type="text" name="active" id="queryActive" style="margin-top: 10px;"></input> -->
				<select name="active" class="span2" style="margin-top: 10px;">
				  <option value="">全部</option>
				  <option value="1">启用</option>
				  <option value="2">审核</option>
				  <option value="3">禁用</option>
				</select>
		</span> 
		<span>
			<button type="button" class="btn btn-success">查询</button>
			<button type="button" class="btn" >重置</button>
		</span>
	</form>
</div>
<!-- 列表区域 -->
<div name="gridParent" id="list_tab">
	<table UI-Module="listGrid">
	</table>
</div>

<!-- 弹窗区域 -->
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" style="z-index: -1;width: 400px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="addTitle">提示</h4>
			</div>
			<div class="modal-body" id="addContent">
				用户状态:<input type="text" id="active" name="active"  class="easyui-combobox"/>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="shenpi()">确认</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="javascript:$('#addModal').modal('hidden');">取消</button>
			</div>
		</div>
	</div>
</div>

<!-- 详情区域 -->
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: -1;width: 400px;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">用户详情信息</h4>
			</div>
			<div class="modal-body" style="padding: 5px 0 0 0;">
				<form id="detailForm">
					<input type="hidden" id="detId" />
<!-- 					&nbsp;&nbsp;用户名:&nbsp;<span id="detName" ></span></br> -->
					&nbsp;&nbsp;用户名:&nbsp;<input type="text" id="detName" class="easyui-validatebox" data-options="required:true"/></br>
					&nbsp;证件号码:&nbsp;<input type="text" id="detIdentityCard" class="easyui-validatebox" data-options="required:true,validType:'identityCard'"/></br>
					&nbsp;手机号码:&nbsp;<span id="detPhone" ></span></br>
					&nbsp;电子邮箱:&nbsp;<span id="detEmail" ></span></br>
					&nbsp;&nbsp;所在省:&nbsp;<span id="detProvince" ></span></br>
					&nbsp;&nbsp;所在市:&nbsp;<span id="detCity" ></span></br>
<!-- 					&nbsp;&nbsp;&nbsp;地址:&nbsp;<span id="detSrcAddr" ></span></br> -->
					&nbsp;&nbsp;&nbsp;地址:&nbsp;<input type="text" id="detSrcAddr" class="easyui-validatebox" data-options="required:true"/></br>
					&nbsp;注册时间:&nbsp;<span id="detRegisterTime" ></span></br>
					&nbsp;用户类型:&nbsp;<span id="detStatus" ></span></br>
					&nbsp;用户状态:&nbsp;<span id="detActive" ></span></br>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" onclick="changeSure()">修改</button>
				<button type="button" class="btn btn-default" onclick="javascript:$('#detailModal').modal('hidden');">取消</button>
			</div>
		</div>
	</div>
</div>
