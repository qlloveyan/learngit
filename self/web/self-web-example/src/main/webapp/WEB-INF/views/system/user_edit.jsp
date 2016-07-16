<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 基本信息  -->
<form  id="userEditFrom"  method="post">
<div class="easyui-panel" >
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">用户名称:<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox input-big" type="text"  name="userName" data-options="required:true" validType="length[0,20]"></input></td>
    			<th width="100">用户帐号:<i class="must"></i>：</th>
    			<td><input id="userEditFrom-userCode" class="easyui-validatebox input-big" type="text" name="userCode" data-options="required:true" validType="length[0,20]"></input></td>
    		</tr>
    		<tr>
    			<th width="100">所属单位/部门:</th>
    			<td><input type="text" name="unitId" class=" input-big" id="unitId"></input></td>
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
    		<tr>
    			<th width="100">联系地址:</th>
    			<td colspan="3"><input class="easyui-validatebox input-big" style="width: 423px"  name="addr"  validType="length[0,100]" ></input></td>
    		</tr>
    	</table>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="userEidt"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="userCancel"class="easyui-linkbutton">取消</a>
   </div>
  <!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
  <input type="hidden" id="windowType" value="<%=request.getParameter("windowType")%>">
  <input type="hidden" id="userId" name="id" value="<%=request.getParameter("id")%>">
</form>
 <!-- 隐藏用户原始的用户名和登录账号 -->
 <input type="hidden" id="userEditFrom-updateBeforeUserCode"/>
 <input type="hidden" id="userEditFrom-updateBeforeUserName"/>
 
<script type="text/javascript" src="js/modules/system/user_edit.js"></script>
<script type="text/javascript" src="js/common/validate.js"></script>
