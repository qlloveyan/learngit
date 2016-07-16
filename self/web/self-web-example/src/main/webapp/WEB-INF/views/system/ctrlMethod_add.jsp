<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="ctrlMethodAddFrom"  method="post">
<div class="easyui-panel" >
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
    		<tr>
    			<th width="100">代码模块:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="module"  name="module" data-options="required:true" ></input></td>
    			<th width="100">功能模块:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="funcModule"  name="funcModule" data-options="required:true" ></input></td>
    		</tr>
    		<tr>
    			<th width="100">一级菜单:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="fiMenu"  name="fiMenu"></input></td>
    			<th width="100">二级菜单:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="seMenu"  name="seMenu" ></input></td>
    		</tr>
    		<tr>
    			<th width="100">方法类型:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="methodType" name="methodType" data-options="required:true"></input></td>
    			<th width="100">类名:</td>
    			<td><input class="easyui-validatebox input-big" type="text" id="className"  name="className" validType="english"></input></td>
    		</tr>
    		<tr>
    			<th width="100">类说明:</td>
    			<td><input class="easyui-validatebox input-big" type="text"  name="classExplain" ></input></td>
    			<th width="100">方法名:</td>
<!--     			<td><input class="easyui-validatebox input-big" type="text" id="methodName"  name="methodName" data-options="required:true,validType:['english','equalMethod']"></input></td> -->
    			<td><input class="easyui-validatebox input-big" type="text" id="methodName"  name="methodName" validType="english"></input></td>
    		</tr>
    		<tr>
    			<th width="100">方法说明:</td>
    			<td><input class="easyui-validatebox input-big" type="text"  name="mothodExplain" ></input></td>
    		</tr>

    	</table>
    </div>
</div>
<div class="add-save">
   	<a href="javascript:void(0)" id="ctrlMethodAdd"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="ctrlMethodCancel"class="easyui-linkbutton">取消</a>
   </div>
</form>
  
<script type="text/javascript" src="js/modules/system/ctrlMethod_add.js"></script>
