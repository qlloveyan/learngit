<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 基本信息 -->
<form id="elementAddFrom"  method="post">
<div class="easyui-panel" >
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">页面元素编码<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text"  name="elementCode" data-options="required:true"></input></td>
    		</tr>
			<tr>
				<th width="100">元素名称<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text"  name="elementName" data-options="required:true"></input></td>
    		</tr>
    	</table>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="elementAdd"class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="elementCancel"class="easyui-linkbutton">取消</a>
   </div>
   <input type="hidden" id="funcId" name="funcId" value="<%=request.getParameter("funcId")%>" />
</form>
  
<script type="text/javascript" src="js/modules/system/element_add.js"></script>
