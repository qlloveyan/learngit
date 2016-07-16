/**
 * 机房编辑页面
 */
$(function(){
	
	init();
	/**
	 * 根据窗口类型初始化页面
	 */
	var sysUnitId = $.trim($("#sysUnitId").val());
	var parentUnitId = $.trim($("#parentUnitId").val());
	
	//单位信息
	if (sysUnitId != "null" && sysUnitId != "" && typeof(sysUnitId)!="undefined") {
		loadSysUnit();
	}
	//取消
	$("#sysUnitCancel").click(function() {
		closeWin('editSysUnitWin');
	});
	//编辑，新增机房信息，或修改机房信息
	$("#sysUnitEidt").click(function() {
		var unitLevel = $.trim($("#unitLevel").val());
		if($("input[name='parentId']").val()==sysUnitId){
			 $.messager.alert('提示',"上级不能选择自身");
			 return;
		}
		$("#sysUnitEditFrom").form('submit',{  
			url:'system/sysUnit/editEntity.do',
			onSubmit:function(){  
				return $(this).form('validate'); 
			},  
			success:function(data){ 
				getMainFrameWindow().reloadSysUnit(parentUnitId); 
				closeWin('editSysUnitWin');
			}  
		});
			
	});
	
	/**
	 * 
	 */
	function loadSysUnit(){
		
		$.getJSON("system/sysUnit/getEntity.do",{'id':sysUnitId}, function(data){
			$('#sysUnitEditFrom').form('load',data.result);
			var unitLevel = $("#unitLevel").val();
		});
	}
});


function init(){
	
	//初始化模块上级
	$("input[name='parentId']").combotree({
		 url:'system/sysUnit/listAllSysUnit.do',
		 onSelect:function(node){
			 $("#unitLevel").val(parseInt(node.attributes.unitLevel)+1);
		 }
	});
}