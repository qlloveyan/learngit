<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="testAddFrom"  method="post">
<div class="easyui-panel" >
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
    		<tr>
    			<th width="100">名称:</td>
    			<td><input class="easyui-validatebox input-text" type="text"  name="name" data-options="required:true"></input></td>
    		</tr>

    	</table>
    </div>
</div>
<div class="add-save">
   	<a href="javascript:void(0)" id="testAdd"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="testCancel"class="easyui-linkbutton">取消</a>
   </div>
</form>
  
<script type="text/javascript" src="js/modules/test/test_add.js"></script>
