<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="dictionaryAddFrom"  method="post">
<div class="easyui-panel" title="基本信息" >
	<!-- 添加 -->
	<div style="padding:5px 0 5px 60px">
    	<table>
    		<tr>
    			<td align="right">字典类型:</td>
    			<td><input class="easyui-validatebox" type="text"  name="codeType" data-options="required:true"></input></td>
    			<td align="right">KEY:</td>
    			<td><input class="easyui-validatebox" type="text"  name="codeKey" data-options="required:true"></input></td>
    		</tr>
    		<tr>
    			<td align="right">VALUE:</td>
    			<td><input class="easyui-validatebox" type="text"  name="codeValue" data-options="required:true"></input></td>
    			<td align="right">字典排序:</td>
    			<td><input class="easyui-numberbox" type="text"  name="codeSort" data-options="required:true"></input></td>
    		</tr>
    		<tr>
    			<td align="right">描述:</td>
    			<td><input class="easyui-validatebox" type="text"  name="description" data-options="required:true"></input></td>
    			<td align="right">是否可操作:</td>
    			<td><input  type="text" id="isOperate"  name="isOperate" data-options="required:true"></input></td>
    		</tr>

    	</table>
		<input type="hidden" name="parentId" value="<%=request.getParameter("parentId")%>"/>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="dictionaryAdd"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="dictionaryCancel"class="easyui-linkbutton">取消</a>
   </div>
</form>
  
<script type="text/javascript" src="js/modules/system/dictionary_add.js"></script>
