<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="menuEditFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">菜单名称<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text"  name="funcName" data-options="required:true"></input></td>
    			<th width="100">菜单编码<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text"  name="funcCode" data-options="required:true"></input></td>
    		</tr>
    		<tr>
    			<th width="100">上级模块：</th>
    			<td><input  type="text"  name="parentId" data-options="required:true"></input></td>
    			<th width="100">菜单URL：</th>
    			<td><input class="easyui-validatebox" type="text"  name="url"></input></td>
    		</tr>
    		<tr>
    			<th width="100">图标：</th>
    			<td><input class="easyui-validatebox" type="text"  name="icon"></input></td>
    			<th width="100">序号：</th>
    			<td><input class="easyui-numberbox" type="text"  name="funcSort" data-options="required:true"></input></td>
    		</tr>

    	</table>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="menuEidt"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="menuCancel"class="easyui-linkbutton">取消</a>
   </div>
  <!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
  <input type="hidden" id="windowType" value="<%=request.getParameter("windowType")%>">
  <input type="hidden" id="menuId" name="id" value="<%=request.getParameter("id")%>">
  <input type="hidden" id="parentId" name="pId" value="<%=request.getParameter("parentId")%>">
  <input type="hidden" id="funcType"  name="funcType" value="0" />
</form>
  
<script type="text/javascript" src="js/modules/system/menu_edit.js"></script>
