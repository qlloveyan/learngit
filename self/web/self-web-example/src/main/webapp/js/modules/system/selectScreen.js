$(function() {
	init();
});

/**
 * 主页面反显示红色的框
 */
function init() {
	 $.ajax({
         type:"POST",
         async:false,
         url:'system/sysSyle/initMainScreen.do',
         data:{"elementType":4},
         dataType:"json",
         success:function(data){
        	if (data.result.value == "1") {
        		var xxHtml = '<li class="active"><a href="javascript:void(0);" onclick="upAndDown();"><i class="style1"></i><span>上下布局</span></a></li> <li><a href="javascript:void(0);" onclick="leftAndRight();"><i class="style2"></i><span>左右布局</span></a></li>';
        		document.getElementById('screenId').innerHTML = xxHtml;
        	}
        	else if (data.result.value == "2") {
        		var xdHtml = '<li><a href="javascript:void(0);" onclick="upAndDown();"><i class="style1"></i><span>上下布局</span></a></li> <li class="active"><a href="javascript:void(0);" onclick="leftAndRight();"><i class="style2"></i><span>左右布局</span></a></li>';
        		document.getElementById('screenId').innerHTML = xdHtml;
        	}
        	else {
        		var Html = '<li><a href="javascript:void(0);" onclick="upAndDown();"><i class="style1"></i><span>上下布局</span></a></li> <li><a href="javascript:void(0);" onclick="leftAndRight();"><i class="style2"></i><span>左右布局</span></a></li>';
        		document.getElementById('screenId').innerHTML = Html;
        	}
         }
     });	
}

/**
 * 上下布局链接
 */
function upAndDown(){
    closeWin('initSysSyleWin');
    addInitUserSyle("4","1");//有数据则更新
    openWin("upAndDownWin","system/redirect/redirectHomePage.do?path=system/upAndDown&flag=1","上下布局设置",1000,600);
   
}

/**
 * 左右布局链接
 */
function leftAndRight(){
    closeWin('initSysSyleWin');
    addInitUserSyle("4","2");//有数据则更新
    openWin("leftAndRightWin","system/redirect/redirectHomePage.do?path=system/leftAndRight&flag=2","左右布局设置",1000,600);
   
}
/**
 * 设置用户选择
 */
function addInitUserSyle(elementType,value) {
        $.ajax({
            type:"POST",
            async:false,
            url:'system/sysSyle/addEntity.do',
            data:{"elementType":elementType,"value":value},
            dataType:"json",
            success:function(data){
                  //var a = $.parseJSON(data);
                if(!data.success){
                    $.messager.alert('提示',result.msg);  
                }else{
                   //$.messager.alert('提示','设置成功！');
                   //alert(data.result.id);
                }
            }
        });
    //编辑，新增机房信息，或修改机房信息
    $("#sysSyleCancel").click(function() {
        closeWin("addSysSyleWin");
    });
}