<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="roleEditFrom"  method="post">
<div class="easyui-panel" >
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">角色名称<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text" name="roleName" data-options="required:true" validType="length[0,20]"></input></td>
    		</tr>
    		<tr>
    			<th width="100">继承角色：</th>
    			<td><input  type="text" id="parentIds" name="parentIds" ></input></td>
			</tr>
    		<tr>
    			<th width="100">角色描述：</th>
    			<td><textarea class="easyui-validatebox" name="description" rows="4" cols="30" validType="length[0,110]"></textarea></td>
			</tr>
    	</table>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="roleEidt"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="roleCancel"class="easyui-linkbutton">取消</a>
   </div>
  <!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
  <input type="hidden" id="windowType" value="<%=request.getParameter("windowType")%>">
  <input type="hidden" id="roleId" name="id" value="<%=request.getParameter("id")%>">
  <input type="hidden" id="hparentIds" >
</form>
  
<script type="text/javascript" src="js/modules/system/role_edit.js"></script>
