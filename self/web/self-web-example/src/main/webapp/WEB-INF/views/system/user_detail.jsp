<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息  -->
<form id="userDetailFrom"  method="post">
	<!-- 添加 -->
	<div class="add-table add-list2 input-detail">
    	<table>
    		<tr>
    			<th width="100">用户名称:</th>
    			<td><input class="easyui-validatebox" type="text"  name="userName" data-options="required:true" validType="length[0,20]"></input></td>
    			<th width="100">用户帐号:</th>
    			<td><input id="userAddFrom-userCode" class="easyui-validatebox" type="text" name="userCode" data-options="required:true" validType="length[0,20]"></input></td>
    		</tr>
    		<tr>
    			<th width="100">所属单位/部门:</th>
    			<td><input type="text" name="sysunit.unitName" id="unitName"></input></td>
    			<th width="100">电话:</th>
    			<td><input class="easyui-validatebox" type="text" name="tel" validType="length[0,20]"></input></td>
    		</tr>
    		<tr>
    			<th width="100">手机:</th>
    			<td><input class="easyui-validatebox" type="text" name="mobile" validType="mobile"></input></td>
    			<th width="100">邮箱:</th>
    			<td><input class="easyui-validatebox" type="text" name="email" maxlength="100" validType="email"></input></td>
    		</tr>
    		<tr>
    			<th width="100">传真:</th>
    			<td><input class="easyui-validatebox" type="text" name="fax" validType="length[0,30]"></input></td>
    		</tr>
    		<tr>
    			<th width="100">联系地址:</th>
    			<td><input class="easyui-validatebox" name="addr" validType="length[0,100]"></input></td>
    			<th width="100">角色:</th>
    			<td colspan="3"><input class="easyui-validatebox" type="text" name="roleName"></input></td>
    		</tr>
    	</table>
    </div>
</div>
<!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
<input type="hidden" id="userId" name="id" value="<%=request.getParameter("id")%>">
</form>
  
<script type="text/javascript" src="js/modules/system/user_detail.js"></script>
