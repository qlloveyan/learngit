//信息展示方法
function Msgshow(msg) {
    $.messager.show({
        title: '提示',
        msg: msg,
        showType: 'show'
    });
}
function Msgslide(msg) {
    $.messager.show({
        title: '提示',
        msg: msg,
        timeout: 3000,
        showType: 'slide'
    });
}
function Msgfade(msg) {
    $.messager.show({
        title: '提示',
        msg: msg,
        timeout: 3000,
        showType: 'fade'
    });
}
//消息提示方法
function MsgAlert(title,msg,model){
	$.messager.alert(title,msg,model);
}

//整形转换为日期
function int2Date(longNumber){
	var date = new Date(longNumber);
	return date.Format("yyyy-MM-dd HH:mm:ss");
}

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
 var o = {
     "M+": this.getMonth() + 1, //月份 
     "d+": this.getDate(), //日 
     "H+": this.getHours(), //小时 
     "m+": this.getMinutes(), //分 
     "s+": this.getSeconds(), //秒 
     "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
     "S": this.getMilliseconds() //毫秒 
 };
 if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
 for (var k in o)
 if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
 return fmt;
}

//字典表获取
function getValueByType(type){
	var value;
	$.ajax({
	  url: "code/getDictionaryByType.do?type="+type,
	  datatype:'json',
	  type: "POST",
	  async:false,
	  success: function(data){
		  value = eval("("+data+")");
	  }
	});
	return value;
}

/**
 * 遍历字典，获取key所对应的value
 * @param {} dictionaryData 字典数据集合
 * @param {} key  对应数据字典中的codeKey
 * @return {}
 */
function getValueByKey(dictionaryData, key){
	var value = null;
	if(dictionaryData != null && dictionaryData.length>0){
		for(var i=0;i<dictionaryData.length;i++){
			if(dictionaryData[i].codeKey == key){
				value = dictionaryData[i].codeName;
				break;
			}
		}
	}
	return value;
}

/**
 * 获取用户状态数据
 * @param flag  是否启用2审核
 * @returns 
 */
function getUserActives(flag){
	var activeArray = new Array();
	var map = null;
	var stateValue = getValueByType(DICTIONARY_USER_STATE);
	if(stateValue != null && stateValue.length>0){
		for(var i=0;i<stateValue.length;i++){
			if( !flag && stateValue[i].codeKey == '2'){
				continue;
			}else{
				map = new Map();
				map["codeKey"]=stateValue[i].codeKey;
				map["codeName"]=stateValue[i].codeName;
				activeArray.unshift( map );
			}
		}
	}
	return activeArray;
}

/**
 * 表格内操作按钮，鼠标移上去之后，操作按钮下拉
 * @param {} id  为了构建层的id做唯一标示
 * @param {} btns 按钮数据，需要显示的下拉按钮
 * @return {}
 */
function createBtn(id, btns){
	btnsCount = btns.length;
	var html = '';
	if(btns != null && btns.length>0){
		html = '<span id="selectBtn'+id+'" class="list-drop"><a href="javascript:void(0);">选择</a><b class="list-drop-ico"></b></span>';
		html += '<div id="detailBtn'+id+'" class="float2-style float-drop"><ul>';
						
		$.each(btns, function(index, btn){
			html += '<li>'+btn+'</li>';
		});
		html +=	'</ul></div>';
	}
	return html;
}

/**
 * 给表格后面的选择按钮鼠标移上去的后显示操作按钮的事件
 * @param {} rows 表格当前页的数据集
*/
function onSelectBtnEvent(rows){
	if(rows!=null && rows.length>0){
		$.each(rows, function(index, rowData){
			//列表下拉选择插件
			var selectBtn = null;
			var detailBtn = null;
			
			if(rowData.id == 'undefined' || rowData.id == undefined){
				selectBtn = $('#selectBtn'+rowData.serialId);
				detailBtn = $('#detailBtn'+rowData.serialId);
			}else{
				selectBtn = $('#selectBtn'+rowData.id);
				detailBtn = $('#detailBtn'+rowData.id);
			}
			
			if(!selectBtn || !detailBtn){
				alert('请确保传入的是rec.id或者rec.serialId');
			}
			var classname= "";
			if(btnsCount>5){
				classname = "tip-yellowsimplemore";
			}else{
				classname = "tip-yellowsimple";
			}
				
			selectBtn.poshytip({
			 		className: classname,
				    content: detailBtn.html(),
				    alignTo: 'target',
				    alignX: 'center',
				    offsetY: 5,
					alignY: 'bottom',
					showTimeout:10
					//hideTimeout:1000000
				});
		});
	}
}

/**
 * 表格内操作按钮，鼠标移上去之后，操作按钮下拉
 * @param {} id  为了构建层的id做唯一标示
 * @param {} btns 按钮数据，需要显示的下拉按钮
 * @return {}
 */
function createImg(id, imgSrc){
	var html = '';
	if(imgSrc != null && imgSrc != '' ){
		html = '<span id="selectImgBtn'+id+'" class="list-drop"><a href="javascript:void(0);">小票预览</a><b class="list-drop-ico"></b></span>';
		html += '<div id="detailImgBtn'+id+'" class="float2-style float-drop"><ul>';
		
		html += '<li>'+imgSrc+'</li>';
		html +=	'</ul></div>';
	}
	return html;
}

function onSelectImgBtnEvent(rows){
	if(rows!=null && rows.length>0){
		$.each(rows, function(index, rowData){
			//列表下拉选择插件
			var selectBtn = null;
			var detailBtn = null;
			
			if(rowData.id == 'undefined' || rowData.id == undefined){
				selectBtn = $('#selectImgBtn'+rowData.serialId);
				detailBtn = $('#detailImgBtn'+rowData.serialId);
			}else{
				selectBtn = $('#selectImgBtn'+rowData.id);
				detailBtn = $('#detailImgBtn'+rowData.id);
			}
			
			if(!selectBtn || !detailBtn){
				alert('请确保传入的是rec.id或者rec.serialId');
			}
			var classname= "";
			if(btnsCount>5){
				classname = "tip-yellowsimplemore";
			}else{
				classname = "tip-yellowsimple";
			}
				
			selectBtn.poshytip({
			 		className: classname,
				    content: detailBtn.html(),
				    alignTo: 'target',
				    alignX: 'center',
				    offsetY: 5,
					alignY: 'bottom',
					showTimeout:10
					//hideTimeout:1000000
				});
		});
	}
}

/**
 * 获取文件模板
 * module 模板类型
 */
function getTemplate(module){
	var html='';
	$.ajax({
	  url: "file/getTemplate.do?module="+module,
	  datatype:'json',
	  type: "POST",
	  async:false,
	  success: function(data){
		  data = eval("("+data+")");
		  for( var i = 0 ; i < data.length ; i++ ){
			 var temp = data[i];
			 html += '<li><a href="file/downTemplate.do?path='+temp.resourcePath+'" target="_blank">' + temp.fileName + '</li>';
		  }
	  }
	});
	return html;
}
