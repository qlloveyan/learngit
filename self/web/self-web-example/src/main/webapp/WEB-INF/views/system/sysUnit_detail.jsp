<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="sysUnitDetailFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">单位名称:</th>
    			<td><input class="easyui-validatebox" type="text"  name="unitName" data-options="required:true"></input></td>
    		</tr>
			<tr>
				<th width="100">上级单位:</th>
    			<td><input  type="text"  name="parentId" data-options="required:true"></input></td>
			</tr>
			<tr>
				<th width="100">单位标识号:</th>
    			<td><input class="easyui-validatebox" type="text"  name="nativeId" data-options="required:true"></input></td>
			</tr>

    	</table>
    </div>
</div>
<!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
<input type="hidden" id="sysUnitId" name="id" value="<%=request.getParameter("id")%>">
</form>
  
<script type="text/javascript" src="js/modules/system/sysUnit_detail.js"></script>
