/**
 * 机房编辑页面
 */
$(function(){
	init();
	/**
	 * 根据窗口类型初始化页面
	 */
	var userId = $.trim($("#userId").val());
	//加载用户信息
	if (userId != "null" && userId != "" && typeof(userId)!="undefined") {
		loadUser();
	}
	//取消
	$("#userCancel").click(function() {
		closeWin('editUserWin');
	});
	//编辑，新增用户信息，或修改用户信息
	$("#userEidt").click(function() {
		
		$("#userEditFrom").form('submit',{  
			url:'system/user/editEntity.do',
			onSubmit:function(){
				/*
				 * 1. 进行表单验证
				 * 2. 表单验证通过后，判断登录名称是否修改
				 * 3. 如果修改后，验证是否已存在登录名称
				 */
				var flag = false;
				if($(this).form('validate')){
					var userCode = $('#userEditFrom-userCode').val();
					var userName = $('#userEditFrom').find("input[name='userName']").val();
					var updateBeforeUserCode = $('#userEditFrom-updateBeforeUserCode').val();
					var updateBeforeUserName = $('#userEditFrom-updateBeforeUserName').val();
					if(userCode != updateBeforeUserCode || userName != updateBeforeUserName){
						if(isExistsCode(userCode,userName)){
							$.messager.alert('信息提示', '用户名或用户账号已经存在!');
						}else{
							flag = true;
						}
					}else{
						flag = true;
					}
				}
				if (flag) {
					$(this).serialize();
				}
				return flag;
			},  
			success:function(data){
				getMainFrameWindow().searchUser(); 
				closeWin('editUserWin');
			}  
		});  
			
	});
	
	/**
	 * 加载机房信息
	 */
	function loadUser(){
		$.getJSON("system/user/getEntity.do",{'id':userId}, function(data){
			$('#userEditFrom').form('myLoad', data.result);
			// 保存编辑前的登录名称(userCode)到隐藏域中，便于与修改之后的进行比对
			$('#userEditFrom-updateBeforeUserCode').val(data.result.userCode);
			$('#userEditFrom-updateBeforeUserName').val(data.result.userName);
		});
	}
	
});

/**
 * 判断是否存在登录名称(userCode)
 * @param userCode	用户登录名
 * @param userName	用户名称
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
			data =  $.parseJSON(data);
			flag = data.result;
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
			if (!$(this).tree('isLeaf', node.target)) {
				$('#unitId').combotree('showPanel');
			}
		}
	});
}