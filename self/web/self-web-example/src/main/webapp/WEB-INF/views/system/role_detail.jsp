<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="roleDetailFrom"  method="post">
<div class="easyui-panel" title="基本信息" >
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">角色名称</th>
    			<td><input class="easyui-validatebox" type="text"  name="roleName" data-options="required:true"></input></td>
    			<th width="100">角色描述</th>
    			<td><textarea class="easyui-validatebox" name="description" rows="4" cols="30" validType="length[0,110]"></textarea></td>
    		</tr>

    	</table>
    </div>
</div>
<!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
<input type="hidden" id="roleId" name="id" value="<%=request.getParameter("id")%>">
</form>
  
<script type="text/javascript" src="js/modules/system/role_detail.js"></script>
