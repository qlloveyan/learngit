<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="funcModuleDetailFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">资源名称:</th>
    			<td><input class="easyui-validatebox" type="text"  name="funcName" data-options="required:true"></input></td>
    		</tr>
    		<tr>
    			<th width="100">资源编码:</th>
    			<td><input class="easyui-validatebox" type="text"  name="funcCode" data-options="required:true"></input></td>
    		</tr>
    		<tr>
    			<th width="100">资源URL前缀:</th>
    			<td><input class="easyui-validatebox" type="text"  name="url" data-options="required:true"></input></td>
    		</tr>
			<tr>
				<th width="100">授权参数:</th>
    			<td><input class="easyui-validatebox" type="text"  name="params"></input></td>
			</tr>
			<tr>
				<th width="100">上级模块:</th>
    			<td><input  type="text"  name="parentId" data-options="required:true"></input></td>
			</tr>
    	</table>
    </div>
</div>
<!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
<input type="hidden" id="funcModuleId" name="id" value="<%=request.getParameter("id")%>">
</form>
  
<script type="text/javascript" src="js/modules/system/funcModule_detail.js"></script>
