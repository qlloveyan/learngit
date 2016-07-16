/**
 * 机房信息业务
 */

$(function(){
	
	//初始化页面元素
	initElementPermissions();
	//加载数据
	loadData();
	//弹出编辑页面
	$("#addCtrlMethod").click(function(){
	
		openWin("addCtrlMethodWin","system/redirect/redirectHomePage.do?path=system/ctrlMethod_add","添加控制类方法字典",600,270);
	});
	
	
	//查询
	$("#searchCtrlMethod").click(function(){
		searchCtrlMethod();
	});
	
	
	
	//重置条件
	$("#resetCtrlMethod").click(function(){
		 $('#queryForm').form('clear'); 
		 searchCtrlMethod();
	});
	
	//弹出高级查询页面
	$('#advanceSearch-dlg').hide();
	$('#searchCtrlMethodAdvance').click(function(){
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
	$("#delCtrlMethod").click(function(){
		 var rows = $('#ctrlMethodGrid').datagrid('getChecked'); 
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
 	            	 $.post('system/ctrlMethod/delBatchEntity.do'+ps,function(data){  
 	                    $('#ctrlMethodGrid').datagrid('reload');   
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
	$('#ctrlMethodGrid').datagrid({
		view:detailview,
		fit:true,
		fitColumns:true,
		collapsible:false,
		nowrap: true,
		singleSelect:false,  
		autoRowHeight: false,
		striped: true,
		url:'system/ctrlMethod/getPageModel.do',
		sortName: 'id',
		sortOrder: 'desc',
		remoteSort: false,
		idField:'id',
		checkOnSelect:false,
		frozenColumns:[[
            {field:'ck',checkbox:true}
		]],
		columns:[[
			{field:'module',title:'代码模块',width:30,align:'center',sortable:true},
			{field:'className',title:'类名',width:100,align:'center',sortable:true},
//			{field:'classExplain',title:'类说明',width:80,align:'center',sortable:true},
			{field:'methodName',title:'方法名',width:100,align:'center',sortable:true},
//			{field:'mothodExplain',title:'方法说明',width:80,align:'center',sortable:true},
			{field:'funcModule',title:'功能模块',width:80,align:'center',sortable:true},
			{field:'fiMenu',title:'一级菜单',width:80,align:'center',sortable:true},
			{field:'seMenu',title:'二级菜单',width:80,align:'center',sortable:true},
			{field:'methodType',title:'方法类别',width:80,align:'center',sortable:true},
			{field:'opt',title:'操作',width:50,align:'center',
				formatter:function(value,rec,index){
						var edit = '<a id="editCtrlMethodDg" href="javascript:void(0)"  onclick="editCtrlMethodDg(\''+ rec.id + '\')"><span class="drop-edit"></span>编辑</a>';
						var del = '<a id="delCtrlMethodDg" href="javascript:void(0)" onclick="delCtrlMethodDg(\''+ rec.id + '\')"><span class="drop-del"></span>删除</a>';
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
			var cloneDetailForm = $('#ctrlMethodDetailForm').clone(); 
			cloneDetailForm.attr('id', 'ctrlMethodDetailForm'+rowData.id);
			ddv.append(cloneDetailForm);
			
			$.getJSON('system/ctrlMethod/getEntity.do',{'id':rowData.id},function(data){
				$(cloneDetailForm).form('load',data.result);
			});
            $('#ctrlMethodGrid').datagrid('fixDetailRowHeight',index);
		},
		onLoadSuccess:function(data){
		
			var flag = true;
			flag = isHasElementPerssion('editCtrlMethod');
			$("a#editCtrlMethod").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			flag = isHasElementPerssion('detailCtrlMethod');
			$("a#detailCtrlMethod").each(function(){
				if(!flag){
					$(this).css("display","none");
				}
			});
			
			onSelectBtnEvent(data.rows);
		}
	});
	//分页
	var p = $('#ctrlMethodGrid').datagrid('getPager');
	$(p).pagination({
		pageSize: 30,//每页显示的记录条数，默认为10 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        pageList: [30,50,100],//可以设置每页记录条数的列表  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		onBeforeRefresh:function(){
		}
	});
}


/**
 * 查询机房信息
 */
function searchCtrlMethod(){
	var formId = $("a[type='export']").attr("formId","queryForm");
	var params = []; //先取得 datagrid 的查询参数  
    var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象 
    $.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
    });   
    $('#ctrlMethodGrid').datagrid('load',params); //设置好查询参数 reload
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
    $('#ctrlMethodGrid').datagrid('load',params); //设置好查询参数 reload
}

/**
 * 编辑机房
 * @param id 机房id
 */
function editCtrlMethodDg(id){
	var params = [{param:[{paramName:"id",value:id}]}];
	openWin("editCtrlMethodWin","system/redirect/redirectHomePage.do?path=system/ctrlMethod_edit","编辑控制类方法字典",600,270,params);
}

/**
 * 单条删除
 */
function delCtrlMethodDg(id){
	 $.messager.confirm('提示','确定要删除吗?',function(result){  
        if (result){  
        	 $.post('system/ctrlMethod/delEntity.do',{id:id},function(data){  
                $('#ctrlMethodGrid').datagrid('reload');   
            });  
        }  
     });  
}


