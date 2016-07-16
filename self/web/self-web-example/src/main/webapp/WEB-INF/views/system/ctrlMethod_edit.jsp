<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="ctrlMethodEditFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table  width="100%" border="0" cellspacing="0" cellpadding="0">
    		<tr>
    			<th width="100">代码模块:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="module"  name="module" data-options="required:true" ></input></td>
    			<th width="100">功能模块:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="funcModule"  name="funcModule" data-options="required:true" ></input></td>
    		</tr>
    		<tr>
    			<th width="100">一级菜单:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="fiMenu"  name="fiMenu" data-options="required:true"></input></td>
    			<th width="100">二级菜单:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="seMenu"  name="seMenu" data-options="required:true" ></input></td>
    		</tr>
    		<tr>
    			<th width="100">方法类型:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="methodType" name="methodType" data-options="required:true"></input></td>
    			<th width="100">类名:</td>
    			<td><input class="easyui-validatebox input-big" type="text"  name="className" validType="english"></input></td>
    		</tr>
    		<tr>
    			<th width="100">类说明:</td>
    			<td><input class="easyui-validatebox input-big" type="text"  name="classExplain" ></input></td>
    			<th width="100">方法名:</td>
    			<td><input class="easyui-validatebox input-big" type="text"  name="methodName" validType="english"></input></td>
    		</tr>
    		<tr>
    			<th width="100">方法说明:</td>
    			<td><input class="easyui-validatebox input-big" type="text"  name="mothodExplain" ></input></td>
    		</tr>
    	</table>
    </div>
</div>
<div class="add-save">
   	<a href="javascript:void(0)" id="ctrlMethodEidt"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="ctrlMethodCancel"class="easyui-linkbutton">取消</a>
   </div>
  <!-- 隐藏域参数 接受通过url直接调转传过来的参数-->
  <input type="hidden" id="windowType" value="<%=request.getParameter("windowType")%>">
  <input type="hidden" id="ctrlMethodId" name="id" value="<%=request.getParameter("id")%>">
</form>
  
<script type="text/javascript" src="js/modules/system/ctrlMethod_edit.js"></script>
