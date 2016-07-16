<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="menuAddFrom"  method="post">
	<div class="easyui-panel" >
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
	    			<th width="100">菜单URL：</th>
	    			<td><input class="easyui-validatebox" type="text"  name="url"></input></td>
	    			<th width="100">序号<i class="must"></i>：</th>
	    			<td><input class="easyui-numberbox" type="text"  name="funcSort" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
		    		<th width="100">图标：</th>
					<td><input class="easyui-validatebox" type="text"  name="icon"></input></td>
				</tr>
	    	</table>
	    </div>
	</div>
	<div style="text-align:center;padding:5px">
	   	<a href="javascript:void(0)" id="menuAdd"class="easyui-linkbutton" >保存</a>
	   	<a href="javascript:void(0)" id="menuCancel"class="easyui-linkbutton">取消</a>
	</div>
	<input type="hidden"  name="funcType" value="0" />
	<input type="hidden" id="parentId" name="parentId" value="<%=request.getParameter("parentId")%>" />
</form>
  
<script type="text/javascript" src="js/modules/system/menu_add.js"></script>
