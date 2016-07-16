<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="funcModuleAddFrom"  method="post">
<div class="easyui-panel" >
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">资源名称<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text"  name="funcName" data-options="required:true"></input></td>
    		</tr>
    		<tr>
    			<th width="100">资源编码<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text"  name="funcCode" data-options="required:true"></input></td>
    		</tr>
    		<tr>
    			<th width="100">资源URL前缀<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text"  name="url" data-options="required:true"></input></td>
    		</tr>
    		<tr>
    			<th width="100">授权参数<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text"  name="params" data-options="required:true"></input></td>
    		</tr>
    	</table>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="funcModuleAdd"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="funcModuleCancel"class="easyui-linkbutton">取消</a>
   </div>
   <input type="hidden" id="funcType"  name="funcType" value="1" >
   <input type="hidden" id="parentId" name="parentId" value="<%=request.getParameter("parentId")%>" />
</form>
  
<script type="text/javascript" src="js/modules/system/funcModule_add.js"></script>
