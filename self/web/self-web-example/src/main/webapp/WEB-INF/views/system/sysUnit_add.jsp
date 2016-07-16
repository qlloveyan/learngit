<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 基本信息 -->
<form id="sysUnitAddFrom"  method="post">
<div class="easyui-panel">
	<!-- 添加 -->
	<div class="add-table add-list2">
    	<table>
    		<tr>
    			<th width="100">单位名称<i class="must"></i>：</th>
    			<td><input class="easyui-validatebox" type="text"  name="unitName" data-options="required:true"></input></td>
    		</tr>
			<tr>
				<th width="100">单位标识号<i class="must"></i>：</th>
    			<td><input type="text"  name="nativeId" ></input></td>
			</tr>
    	</table>
    </div>
</div>
<div style="text-align:center;padding:5px">
   	<a href="javascript:void(0)" id="sysUnitAdd" class="easyui-linkbutton" >保存</a>
   	<a href="javascript:void(0)" id="sysUnitCancel" class="easyui-linkbutton">取消</a>
</div>
<input type="hidden" id="parentId" name="parentId" value="<%=request.getParameter("parentId")%>" />
<input type="hidden" id="unitType" name="unitType" value="<%=request.getParameter("unitType")%>" />
<input type="hidden" id="unitLevel" name="unitLevel" value="<%=request.getParameter("unitLevel")%>" />
</form>
  
<script type="text/javascript" src="js/modules/system/sysUnit_add.js"></script>
