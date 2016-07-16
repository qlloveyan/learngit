function openDiv(winId,url,title,width,height){
//	var title="基础数据查询检测详情";
//	var winId="baseInfoDetail";
//	var url="baseinfo_detail.html";
//	var width=650;
//	var height=350;
	var addWindow = window.top.$(winId);
	if(addWindow.length <= 0) {
		addWindow = window.top.$("<div id='"+winId+"'/>").appendTo(window.top.document.body);
	}
	
	
	addWindow.window({
             title: title,
             href: url,
             width: width,
             height: height,
             modal:true,
             closed: false,
             closable:true,
             minimizable:false,
             maximizable:false,
             collapsible:false,
             resizable:false,
             zIndex:8000,
             onClose : function(){ 
            	 addWindow.window("destroy"); 
             }
     });
}