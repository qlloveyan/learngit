<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="menuDetailFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
		<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">菜单名称:</th>
    			<td><input class="easyui-validatebox" type="text"  name="funcName"></input></td>
    			<th width="100">菜单编码:</th>
    			<td><input class="easyui-validatebox" type="text"  name="funcCode"></input></td>
    		</tr>
    		<tr>
    			<th width="100">上级模块:</th>
    			<td><input class="easyui-validatebox" type="text"  name="parentId"></input></td>
    			<th width="100">菜单URL:</th>
    			<td><input class="easyui-validatebox" type="text"  name="url"></input></td>
    		</tr>
    		<tr>
    			<th width="100">图标:</th>
    			<td><input class="easyui-validatebox" type="text"  name="icon"></input></td>
    			<th width="100">序号:</th>
    			<td><input class="easyui-validatebox" type="text"  name="funcSort"></input></td>
    		</tr>

    	</table>
    </div>
</div>
<!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
<input type="hidden" id="menuId" name="id" value="<%=request.getParameter("id")%>">
</form>
  
<script type="text/javascript" src="js/modules/system/menu_detail.js"></script>
