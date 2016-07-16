<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息  -->
<form id="userAddFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">用户名称:<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox input-big" type="text"  name="userName" data-options="required:true" validType="length[0,20]"></input></td>
    			<th width="100">用户帐号:<i class="must"></i>：</th>
    			<td><input id="userAddFrom-userCode" class="easyui-validatebox input-big" type="text" name="userCode" data-options="required:true" validType="length[0,20]"></input></td>
    		</tr>
    		<tr>
    			<th width="100">所属单位/部门:</th>
    			<td><input type="text" name="unitId" id="unitId" class="input-big" data-options="required:true"></input></td>
    			<th width="100">电话:</th>
    			<td><input class="easyui-validatebox input-big" type="text" name="tel" validType="length[0,20]"></input></td>
    		</tr>
    		<tr>
    			<th width="100">手机(多个号码以;进行区分):</th>
    			<td><input class="easyui-validatebox input-big" type="text" name="mobile" validType="mobiles"></input></td>
    			<th width="100">邮箱:</th>
    			<td><input class="easyui-validatebox input-big" type="text" name="email" maxlength="100" validType="email"></input></td>
    		</tr>
    		<tr>
    			<th width="100">传真:</th>
    			<td><input class="easyui-validatebox input-big" type="text" name="fax" validType="length[0,30]"></input></td>
    		</tr>
    		<tr >
    			<th width="100">联系地址:</th>
    			<td colspan="3"><input class="easyui-validatebox input-big" style="width: 423px"  name="addr"  validType="length[0,100]" ></input></td>
    		</tr>
    	</table>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="userAdd" class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="userCancel" class="easyui-linkbutton">取消</a>
</div>
<input type="hidden" id="userType"  value="<%=request.getSession().getAttribute("userType")%>"/>
</form>

<script type="text/javascript" src="js/modules/system/user_add.js"></script>
<script type="text/javascript" src="js/common/validate.js"></script>
