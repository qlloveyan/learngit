<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="funcModuleEditFrom"  method="post">
<div class="easyui-panel">
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
    			<td><input class="easyui-validatebox" type="text"  name="params"></input></td>
			</tr>
			<tr>
				<th width="100">上级模块<i class="must"></i>：</th>
    			<td><input  type="text"  name="parentId" data-options="required:true"></input></td>
			</tr>
    	</table>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="funcModuleEidt"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="funcModuleCancel"class="easyui-linkbutton">取消</a>
   </div>
  <!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
  <input type="hidden" id="windowType" value="<%=request.getParameter("windowType")%>">
  <input type="hidden" id="funcModuleId" name="id" value="<%=request.getParameter("id")%>">
  <input type="hidden" id="funcType"  name="funcType" value="1" />
</form>
  
<script type="text/javascript" src="js/modules/system/funcModule_edit.js"></script>
