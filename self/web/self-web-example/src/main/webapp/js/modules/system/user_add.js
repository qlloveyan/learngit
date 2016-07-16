/**
 * 机房编辑页面
 */
$(function(){
	init();
	//编辑，新增机房信息，或修改机房信息
	$("#userAdd").click(function() {
		var url = "";
		var winId= "";
		$("#userAddFrom").form('submit',{  
			url:'system/user/addEntity.do',
			onSubmit:function(){
				var flag = false;
				// 首先进行表单验证，通过后验证用户登录名称(userCode)是否重复
				if($(this).form('validate')){
					var userCode = $('#userAddFrom-userCode').val();
					var userName = $("#userAddFrom").find("input[name='userName']").val();
					
					var isExists = isExistsCode(userCode,userName);
					if(isExists){
						$.messager.alert('信息提示', '用户名或用户账号已经存在!');
					}else{
						flag = true;
					}
				}
				return flag; 
			},  
			success:function(data){  
				getMainFrameWindow().searchUser(); 
				closeWin("addUserWin");
			}  
		});  
	});
	
	//编辑，新增机房信息，或修改机房信息
	$("#userCancel").click(function() {
		closeWin("addUserWin");
	});
})
/**
 * 判断是否存在登录名称(userCode)
 * @param userCode	用户登录名
 * @param userName	用户名称
 * 
 */
function isExistsCode(userCode,userName){
	var flag = true;
	$.ajax({
		url: 'system/user/isExistsCode.do',
		type: 'post',
		async: false, // 同步请求数据
		data: {
			'userCode': userCode,
			'userName': userName
		},
		success: function(data){
			var res = $.parseJSON(data);
			flag = res.result;
		}
	});
	return flag;
}

function init(){
	$("#unitId").combotree({
		url:'system/sysUnit/listAllSysUnitToCombobox.do',
		onBeforeSelect: function(node) {
			var unitLevel = node.attributes.unitLevel;
  
			//选中的节点是否为叶子节点,如果不是叶子节点,清除选中  
			var isLeaf = $(this).tree('isLeaf', node.target);
			if ( !isLeaf ) {  
				//清除选中  
				return false;
			}  
		},
		onClick: function(node) {
			var unitLevel = node.attributes.unitLevel;
			//如果不是叶子节点不让关闭下拉面板
			if (!$(this).tree('isLeaf', node.target) ) {
				$('#unitId').combotree('showPanel');
			}
		}	
	});
};