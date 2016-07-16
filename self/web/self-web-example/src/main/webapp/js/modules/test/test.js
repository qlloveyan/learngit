/**
 * 机房信息业务
 */

$(function(){
	//初始化页面元素
	initElementPermissions();
	//加载数据
	loadData();
	//弹出编辑页面
	$("#addTest").click(function(){
	
		openWin("addTestWin","system/redirect/redirectHomePage.do?path=test/test_add","添加机房",650,450);
	});
	
	
	//查询
	$("#searchTest").click(function(){
		searchTest();
	});
	
	
	
	//重置条件
	$("#resetTest").click(function(){
		 $('#queryForm').form('clear'); 
		 searchTest();
	});
	
	//弹出高级查询页面
	$('#advanceSearch-dlg').hide();
	$('#searchTestAdvance').click(function(){
		$('#advanceSearch-dlg').show();
		$('#advanceSearch-dlg').dialog({
		    title: '高级查询',
		    width: 550,
		    height: 300,
		    closed: false,
		    cache: false,
		    modal: true
		});
	});
	
	//高级查询
	$("#advanceSearchSubmit").click(function(){
		searchAdvance();
	});
	
	//高级查询重置
	$("#advanceSearchReset").click(function(){
		$('#advancedQueryForm').form('clear'); 
		searchAdvance();
	});
	
	//批量删除
	$("#delTest").click(function(){
		 var rows = $('#testGrid').datagrid('getChecked'); 
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
 	            	 $.post('test/test/delBatchEntity.do'+ps,function(data){  
 	                    $('#testGrid').datagrid('reload');   
 	                });  
 	            }  
        	 });  
            
         }
		
	});
	
	
})

/**
 * 初始化加载数据,分页
 */
function loadData(){
	//grid数据加载
	$('#testGrid').datagrid({
		view:detailview,
		fit:true,
		fitColumns:true,
		collapsible:false,
		nowrap: true,
		singleSelect:false,  
		autoRowHeight: false,
		striped: true,
		url:'test/test/getPageModel.do',
		sortName: 'id',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'id',
		checkOnSelect:false,
		frozenColumns:[[
            {field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'name',title:'名称',width:80,align:'center',sortable:true},

			{field:'opt',title:'操作',width:100,align:'center',
				formatter:function(value,rec,index){
						var edit = '<a id="editTestDg" href="javascript:void(0)"  onclick="editTestDg(\''+ rec.id + '\')"><span class="drop-edit"></span>编辑</a>';
						var del = '<a id="delTestDg" href="javascript:void(0)" onclick="delTestDg(\''+ rec.id + '\')"><span class="drop-del"></span>删除</a>';
						var btns = new Array();
						btns.push(edit);
						btns.push(del);
						return createBtn(rec.id, btns);
				}
			}
		]],
		pagination:true,
		rownumbers:true,
		toolbar:'#tb',
		detailFormatter: function(rowIndex, rowData){
			return '<div class="ddv" id="false"></div>';
		},
		onClickRow: function(rowIndex,rowData){
			detailGridClickFn($(this), rowIndex);
		},
		onExpandRow: function(index,rowData){
			/**
			 * 展示详情信息；
			 * 1. 先清空原有的层下的数据和样式
			 * 2. 获取数据，将模板表单克隆一份，修改克隆后的id
			 * 3. 将数据load到克隆后的模板，append到该显示的地方
			 * 4. 重新定位详情的高度
			 */
            var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
            ddv.empty();
			var cloneDetailForm = $('#testDetailForm').clone(); 
			cloneDetailForm.attr('id', 'testDetailForm'+rowData.id);
			ddv.append(cloneDetailForm);
			
			$.getJSON('is/test/getEntity.do',{'id':rowData.id},function(data){
				$(cloneDetailForm).form('load',data.result);
			});
            $('#testGrid').datagrid('fixDetailRowHeight',index);
		},
		onLoadSuccess:function(data){
		
			var flag = true;
			flag = isHasElementPerssion('editTest');
			$("a#editTest").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('detailTest');
			$("a#detailTest").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			onSelectBtnEvent(data.rows);
		}
	});
	//分页
	var p = $('#testGrid').datagrid('getPager');
	$(p).pagination({
		pageSize: 10,//每页显示的记录条数，默认为10 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        pageList: [5,10,15],//可以设置每页记录条数的列表  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		onBeforeRefresh:function(){
		}
	});
}


/**
 * 查询机房信息
 */
function searchTest(){
	var formId = $("a[type='export']").attr("formId","queryForm");
	var params = []; //先取得 datagrid 的查询参数  
    var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });   
    $('#testGrid').datagrid('load',params); //设置好查询参数 reload
}

/**
 * 高级查询信息
 */
function searchAdvance(){
	var formId = $("a[type='export']").attr("formId","advancedQueryForm");
	var params = []; //先取得 datagrid 的查询参数  
    var fields =$('#advancedQueryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });  
    $('#advanceSearch-dlg').dialog('close');
    $('#testGrid').datagrid('load',params); //设置好查询参数 reload
}

/**
 * 编辑机房
 * @param id 机房id
 */
function editTestDg(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("editTestWin","system/redirect/redirectHomePage.do?path=test/test_edit","编辑机房信息",650,450,params);
}

/**
 * 单条删除
 */
function delTestDg(id){
	 $.messager.confirm('提示','确定要删除吗?',function(result){  
        if (result){  
        	 $.post('is/test/delEntity.do',{id:id},function(data){  
                $('#testGrid').datagrid('reload');   
            });  
        }  
     });  
}


