/**
 * 机房信息业务
 */

$(function(){
	initElementPermissions();
	var dictionaryData = getDictionaryByType(DICTIONARY_IS_OPERATE);
	//加载数据
	loadData(dictionaryData);
	
	/********************************************************************/
	$("#addTypeDictionary").click(function(){
		
		var selectedRows = $('#dictionaryTypeGrid').treegrid('getSelected');
		$("#dictionary-type-parent-id").val((selectedRows==null)?1:selectedRows.id);
		$('#code-type-id').removeAttr("readonly");
		$("#typeAddWin").window('open');
	});
	
	$("#typeAddWinSave").click(function(){
		var id = $.trim($("#dictionary-type-id").val());
		
		var url = 'system/dictionary/addEntity.do';
		if(id != '' && id != null){
			url = 'system/dictionary/editEntity.do';
		}
		
		$('#typeAddForm').form('submit', {   
		    url: url,
		    onSubmit: function(){
		    	var flag = true;
				// 首先进行表单验证，通过后验证用户登录名称(userCode)是否重复
				if($(this).form('validate')){
					var codeType = $('#code-type-id').val();
					
					if(id == '' || id == null){
						// 新增的时候验证codeType是否存在
						if(isExistsDicType(codeType)){
							$.messager.alert('信息提示', '存在相同的字典类型');
							flag = false;
						}
					}
				}else{
					flag = false;
				}
				return flag;
		    },
		    success:function(data){
			 	$('#dictionaryTypeGrid').treegrid('reload');
				$('#typeAddForm').form('clear');
				$("#typeAddWin").window('close');
		    }   
		});
	});
	
	$("#typeAddWinClosed").click(function(){
		$('#typeAddForm').form('clear');
		$("#typeAddWin").window('close');
	});
	
	$("#delTypeDictionary").click(function(){
		var rows = $('#dictionaryTypeGrid').datagrid('getSelections');
		var ps = "";  
         $.each(rows,function(i,n){  
             if(i==0)   
                 ps += "?ids="+n.id;  
             else  
                 ps += "&ids="+n.id;  
         });  
         if(ps==""){
         	 $.messager.alert('提示',"请选择需要删除的数据！");  
         }else{
        	 $.messager.confirm('提示','将要删除该字典类型下的所有字典, 确定要删除吗?',function(result){  
 	            if (result){  
 	            	 $.post('system/dictionary/delBatchEntity.do'+ps,function(data){  
 	                    $('#dictionaryTypeGrid').treegrid('reload');
 	                });  
 	            }  
        	 });  
         }
	});
	
	$("#editTypeDictionary").click(function(){
		var selectedRows = $('#dictionaryTypeGrid').treegrid('getSelected');
		if(selectedRows == null){
			$.messager.alert('提示','请选择字典类型!');
			return;
		}
		$("#dictionary-type-id").val(selectedRows.id);
		$("#code-value-id").val(selectedRows.codeValue);
		$("#code-type-id").val(selectedRows.codeType);
		
		$('#code-type-id').attr("readonly","readonly");
		$("#typeAddWin").window('open');
	});
	/********************************************************************/
	
	
	/********************************************************************/
	//弹出新增页面
	$("#addDictionary").click(function(){
		var selectedRows = $('#dictionaryTypeGrid').treegrid('getSelected');
		if(selectedRows == null){
			$.messager.alert('提示','请选择字典类型!');  
			return;
		}
		$("#dictionary-parent-id").val(selectedRows.id);
		$("#dictionary-code-type").val(selectedRows.codeType);
		
		$("#addWin").window('open');
	});
	
	$("#dictionaryAdd").click(function(){
		var id = $.trim($("#dictionary-id").val());
		
		var url = 'system/dictionary/addEntity.do';
		if(id != '' && id != null){
			url = 'system/dictionary/editEntity.do';
		}
		$("#dictionaryAddFrom").form('submit',{
			url: url,
	 		onSubmit: function(){
    			var flag = false;
				// 首先进行表单验证，通过后验证用户登录名称(userCode)是否重复
				if($(this).form('validate')){
					var codeName = $('#code-value-id').val();
					if(isExists(codeName)){
						$.messager.alert('信息提示', '存在相同的登录名称');
					}else{
						flag = true;
					}
				}
				return flag;
    		},
    		success:function(data){
	 			$('#dictionaryGrid').datagrid('reload');
				$('#dictionaryAddFrom').form('clear');
				$("#addWin").window('close');
    		}  
		});
		
	});
	
	$("#dictionaryCancel").click(function(){
		$('#dictionaryAddFrom').form('clear');
		$("#addWin").window('close');
	});
	
	$("#editDictionary").click(function(){
		var selectedRows = $('#dictionaryGrid').datagrid('getSelected');
		if(selectedRows == null){
			$.messager.alert('提示','请选择要修改的字典!');
			return;
		}
		$('#dictionaryAddFrom').form('load', selectedRows);
		
		$("#addWin").window('open');
	});
	
	//批量删除
	$("#delDictionary").click(function(){
		 var rows = $('#dictionaryGrid').datagrid('getSelections'); 
         var ps = "";  
         $.each(rows,function(i,n){  
             if(i==0)   
                 ps += "?ids="+n.id;  
             else  
                 ps += "&ids="+n.id;  
         });  
         if(ps==""){
         	 $.messager.alert('提示',"请选择需要删除的数据！");  
         }else{
        	 $.messager.confirm('提示','确定要删除吗?',function(result){  
 	            if (result){  
 	            	 $.post('system/dictionary/delBatchEntity.do'+ps,function(data){  
 	                    $('#dictionaryGrid').datagrid('reload');   
 	                });  
 	            }
        	 });
         }
	});
	
	loadComboboxData('isOperate',DICTIONARY_IS_OPERATE);
	/********************************************************************/
})



/**
 * 初始化加载数据,分页
 */
function loadData(dictionaryData){
	
	$('#dictionaryTypeGrid').treegrid({
 		url: 'system/dictionary/getTreeGridAllDic.do?codeKey=type',
 		fit:true,
		fitColumns:true,
        method: 'post',
        idField: 'id',
        treeField:'codeValue',
        columns:[[
			{field:'codeValue',title:'字典名称',width:140,
				formatter:function(value,rec,index){
					return '<div class="text-ellipsis" title="'+value+'">'+value+'</div>';
				}
			},
	        {field:'codeType',title:'字典类型',width:100,
	        	formatter:function(value,rec,index){
					return '<div class="text-ellipsis" title="'+value+'">'+value+'</div>';
				}
			}
	    ]],
	   	toolbar: '#typeTb',
	   	onClickRow: function(rowData){
	   		if(rowData != null || rowData.codeType != null){
		   		$('#dictionaryGrid').treegrid('load', {
		   			'type': rowData.codeType
		   		});
	   		}
	   	},
		onLoadSuccess:function(data){
			
			var flag = true;
			flag = isHasElementPerssion('addTypeDictionary');
			$("a#addTypeDictionary").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('editTypeDictionary');
			$("a#editTypeDictionary").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('delTypeDictionary');
			$("a#delTypeDictionary").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			/**
			 * 数据加载成功后渲染linkbutton
			 */
			$(".addTypeDictionary").linkbutton({
				text:'新增', 
				title:'新增',
				plain:true
			});
			$(".editTypeDictionary").linkbutton({
				text:'编辑', 
				title:'编辑',
				plain:true
			});
			$(".delTypeDictionary").linkbutton({
				text:'删除', 
				title:'删除',
				plain:true
			});
		}
	});
	
	
	$('#dictionaryGrid').datagrid({
		url: 'system/dictionary/getDictionaryByType.do',
 		fit:true,
		fitColumns:true,
        method: 'post',
        singleSelect: true,
        columns:[[
			{field:'codeValue',title:'字典名称',width:80,
				formatter:function(value,rec,index){
					return '<div class="text-ellipsis" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'codeKey',title:'字典编码',width:80,align:'center'},
			{field:'codeSort',title:'字典排序',width:80,align:'center'},
			{field:'description',title:'描述',width:80,align:'center',
				formatter:function(value,rec,index){
					return '<div class="text-ellipsis" title="'+value+'">'+value+'</div>';
				}
			},
			{field:'isOperate',title:'是否可操作',width:80,align:'center',formatter:function(value,row,index){
				return getValueByKey(dictionaryData, value);
			}}
	    ]],
	    rownumbers:true,
	   	toolbar: '#tb',
		onLoadSuccess:function(){
			
			var flag = true;
			flag = isHasElementPerssion('addDictionary');
			$("a#addDictionary").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('editDictionary');
			$("a#editDictionary").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('delDictionary');
			$("a#delDictionary").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			/**
			 * 数据加载成功后渲染linkbutton
			 */
			$(".addDictionary").linkbutton({
				text:'新增', 
				title:'新增',
				plain:true
			});
			$(".editDictionary").linkbutton({
				text:'编辑', 
				title:'编辑',
				plain:true
			});
			$(".delDictionary").linkbutton({
				text:'删除', 
				title:'删除',
				plain:true
			});
		}
	});
	
	
}

/**
 * 查询机房信息
 */
function searchDictionary(){
	var params = $('#dictionaryGrid').datagrid('options').queryParams; //先取得 datagrid 的查询参数  
    var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });   
    $('#dictionaryGrid').treegrid('reload'); //设置好查询参数 reload
}

/**
 * 编辑机房
 * @param id 机房id
 */
function editDictionary(id,isOperate){
	if(isOperate==0){
		$.messager.alert('提示',"该记录不允许被修改！");  
		return ;
	}
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("editDictionaryWin","system/redirect/redirectHomePage.do?path=system/dictionary_edit","编辑数据字典",650,235,params);
}

/**
 * 查看字典详情
 * @param id 机房id
 */
function detailDictionary(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("detailDictionaryWin","system/redirect/redirectHomePage.do?path=system/dictionary_detail","数据字典详情",650,235,params);
}

/**
 * 单个删除
 * @param {} id
 */
function delDictionary(id){
	var ps = "?ids="+id;  
	 $.messager.confirm('提示','确定要删除吗?',function(result){  
	        if (result){  
	        	 $.post('system/dictionary/delBatchEntity.do'+ps,function(data){  
	                $('#dictionaryGrid').treegrid('reload');   
	            });  
	        }  
		 });  
}

/**
 * 
 */
function isExists(){
	return false;
}

/**
 * 验证是否存在相同的字典类型，
 * 存在就返回true，不存在返回false
 * @param {} codeValue
 * @param {} codeType
 */
function isExistsDicType(codeType){
	var flag = true;
	$.ajax({
		url: 'system/dictionary/getPageList.do',
		type: "POST",
	 	async: false,
		data: {
			codeType: codeType,
			codeKey: 'type' // 特指查询 codeKey为空
		},
		success: function(data){
			data = $.parseJSON(data);
			if(data == null || data.length <= 0){
				flag = false;
			}
		}
	});
	return flag;
}




