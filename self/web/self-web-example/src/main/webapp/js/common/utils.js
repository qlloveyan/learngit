//提交数据是清楚缓存 针对IE提交缓存问题
$.ajaxSetup({cache:false});

/**
 * 加载combobox的数据
 * @param elementName 元素name
 * @param type 枚举类型
 * @param width 下拉框宽度 
 * @param flag  下拉框选项是否加全部
 * @author hongcheng 
 */
function loadComboboxData(elementId,type,flag){
	$("#"+elementId).combobox({
		valueField:'codeKey',
		textField:'codeValue',
		panelHeight:'auto',
		editable : false
	});
	$.ajax({
		type:"POST",
		url:"system/dictionary/getDictionaryByType.do",
		data:{"type":type},
		async:false,
		dataType:'json',
		success:function(result){
			if(result!=null){
				if(flag!=null&&flag){
					result.unshift(ALLVALUE);
				}
				$("#"+elementId).combobox('loadData',result);
			}
		}
	});
}

/**
 * 加载combobox的数据
 * @param elementName 元素name
 * @param type 枚举类型
 * @param flag  下拉框选项是否加全部
 * @param defaultValue  默认值
 * @author hongcheng 
 */
function loadComboboxData(elementId,type,flag,defaultValue){
	$("#"+elementId).combobox({
		valueField:'codeKey',
		textField:'codeValue',
		panelHeight:'auto',
		editable : false,
		value:defaultValue,
		onLoadSuccess:function(){
		}
	});
	$.ajax({
		type:"POST",
		url:"system/dictionary/getDictionaryByType.do",
		data:{"type":type},
		async:false,
		dataType:'json',
		success:function(result){
			if(result!=null){
				if(flag!=null&&flag){
					result.unshift(ALLVALUE);
				}
				$("#"+elementId).combobox('loadData',result);
			}
		}
	});
}
/**
 * 遍历代码表，获取code所对应的codeName
 * @param {} isCodeData 代码表集合
 * @param {} code  对应数据字典中的codeName
 * @return {}
 */
function getCodeNameBycode(isCodeData, code){
	var value = null;
	if(isCodeData != null && isCodeData.length>0){
		for(var i=0;i<isCodeData.length;i++){
			if(isCodeData[i].code == code){
				value = isCodeData[i].codeName;
				break;
			}
		}
	}
	return value;
}

/**
 * 通过类型查询所有对应的字典数据
 * @param {} type
 * @return {}
 */
function getDictionaryByType(type){
	var dictionarys;
	var e = $.ajax({
		type:"POST",
		url:"system/dictionary/getDictionaryByType.do",
		data:{"type":type},
		async:false,
		dataType:'json',
		success:function(result){
			dictionarys = result;
		}
	});
	return dictionarys;
}



function getIllegalValueByKey(illegalTypes,key){
	var value = null;
	if(illegalTypes != null && illegalTypes.length>0){
		for(var i=0;i<illegalTypes.length;i++){
			if(illegalTypes[i].id == key){
				value = illegalTypes[i].className;
				break;
			}
		}
	}
	return value;
}
// 通过areaCode 查询区域信息
function getAreaInfoByCode(areaCode){
	var AreaCode;
	var e = $.ajax({
		type:"POST",
		url:"system/areaCode/getEntityByCode.do",
		data:{"areaCode":areaCode},
		async:false,
		dataType:'json',
		success:function(result){
			AreaCode = result;
		}
	});
	return AreaCode;
}
// 通过areaCode 查询区域areaName
function getAreaNameByareaCode(areaCode){
	var AreaName;
	var e = $.ajax({
		type:"POST",
		url:"system/areaCode/getEntityByCode.do",
		data:{"areaCode":areaCode},
		async:false,
		dataType:'json',
		success:function(result){
			if(result!=null){
				AreaName = result.areaName;
			}else{
				AreaName="";
			}
		}
	});
	return AreaName;
}

function getAreaNameBycode(areaData, code){
	var value = null;
	if(areaData != null && areaData.length>0){
		for(var i=0;i<areaData.length;i++){
			if(areaData[i].areaCode == code){
				value = areaData[i].areaName;
				break;
			}
		}
	}
	return value;
}
/**
 * 通过类型查询所有对应的字典数据
 * @param areaCode  可以做模糊查询某个省所有县市
 * @param areaType  区域类型
 * @return {}
 */
function getAreaCodeList(areaCode,areaType){
	var AreaCodeList;
	var e = $.ajax({
		type:"POST",
		url:"system/areaCode/getPageList.do",
		data:{'areaType':areaType,"areaCode":areaCode},
		async:false,
		dataType:'json',
		success:function(result){
			result.unshift(AREAALLVALUE);
			AreaCodeList = result;
		}
	});
	return AreaCodeList;
}

/**
 * 通过类型查询所有对应的字典数据 不含全部
 * @param areaCode  可以做模糊查询某个省所有县市
 * @param areaType  区域类型
 * 
 * @return {}
 */
function getAreaCodeListNoAll(areaCode,areaType){
	var AreaCodeList;
	var e = $.ajax({
		type:"POST",
		url:"system/areaCode/getPageList.do",
		data:{'areaType':areaType,"areaCode":areaCode},
		async:false,
		dataType:'json',
		success:function(result){
			AreaCodeList = result;
		}
	});
	return AreaCodeList;
}


function loadAreaAllComboboxData(elementId,areaType,areaCode,flag){
	$("#"+elementId).combobox({
		valueField:'areaCode',
		textField:'areaName',
		panelHeight:'auto',
		editable : false
	});
	$.ajax({
		type:"POST",
		url:"system/areaCode/getPageList.do",
		data:{"areaType":areaType,"areaCode":areaCode},
		async:false,
		dataType:'json',
		success:function(result){
			if(result!=null){
				if(flag!=null&&flag){
					result.unshift(AREAALLVALUE);
				}
				$("#"+elementId).combobox('loadData',result);
			}
		}
	});
}

function loadShiAllComboboxData(elementId,areaType,areaCode,flag){
	$("#"+elementId).combobox({
		valueField:'areaCode',
		textField:'areaName',
		panelHeight:'auto',
		editable : false
	});
	$.ajax({
		type:"POST",
		url:"system/areaCode/getShiList.do",
		data:{"areaType":areaType,"areaCode":areaCode},
		async:false,
		dataType:'json',
		success:function(result){
			if(result!=null){
				if(flag!=null&&flag){
					result.unshift(AREAALLVALUE);
				}
				$("#"+elementId).combobox('loadData',result);
			}
		}
	});
}

/**
 * 通过类型查询所有对应的字典数据
 * @param areaCode  可以做模糊查询某个省所有县市
 * @param areaType  区域类型
 * @return {}
 */
function getAreaCodeListProvince(areaCode,areaType,flag){
	var AreaCodeList;
	var e = $.ajax({
		type:"POST",
		url:"system/areaCode/getPageList.do",
		data:{'areaType':areaType,"areaCode":areaCode},
		async:false,
		dataType:'json',
		success:function(result){
			if(flag){
				result.unshift(AREAALLVALUE);
			}
			AreaCodeList = result;
		}
	});
	return AreaCodeList;
}

/**
 * 通过类型查询所有对应的字典数据
 * @param areaCode  可以做模糊查询某个省所有县市
 * @param areaType  区域类型
 * @return {}
 */
function getAreaNameList(areaType){
	var AreaNameList;
	var e = $.ajax({
		type:"POST",
		url:"system/areaCode/getPageList.do",
		data:{'areaType':areaType},
		async:false,
		dataType:'json',
		success:function(result){
			AreaNameList = result;
		}
	});
	return AreaNameList;
}
/**
 * 加载区域省市县的数据
 * @param areaType类型
 */
function loadAreaCodeComboboxData(id,areaType){
	var $id0 = '#'+id[0];
	$($id0).combobox({
		url:"system/areaCode/getPageList.do",
		data:{'areaType':PROVINCE},
		valueField:'areaCode',
		textField:'areaName',
		panelHeight:'auto',
		editable : false,
		onSelect : function(record) {
			var $id1 = '#'+id[1];
			$('#codeType').val(record.codeType);
			$($id1).combobox({
				url:"system/areaCode/getPageList.do",
				data:{'areaType':CITY},
				valueField : 'areaCode',
				textField : 'areaName',
				panelHeight : 'auto',
				editable : false,
				onSelect : function(record) {
						var $id2 = '#'+id[2];
						$('#codeType').val(record.codeType);
						$($id2).combobox({
						url:"system/areaCode/getPageList.do",
						data:{'areaType':COUNTY},
						valueField : 'areaCode',
						textField : 'areaName',
						panelHeight : 'auto',
						editable : false
						})
				}
			})
		}
	});
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
				value = dictionaryData[i].codeValue;
				break;
			}
		}
	}
	return value;
}
function getValueById(dictionaryData, key){
	var value = null;
	if(dictionaryData != null && dictionaryData.length>0){
		for(var i=0;i<dictionaryData.length;i++){
			if(dictionaryData[i].id == key){
				value = dictionaryData[i].name;
				break;
			}
		}
	}
	return value;
}

/**
 * 通过数据字典类型、值得到数据字典描述
 * @param value 枚举类型
 * @param value 枚举值
 * @return 枚举值对应的中文描述
 * @author hongcheng 
 */
function getDictionaryByValue(type,key){
	
	var value;
	var e = $.ajax({
		type:"POST",
		url:"system/dictionary/getDictionaryByKey.do",
		data:{"type":type,"key":key},
		async:false,
		dataType:'json',
		success:function(result){
			if(result ==null){
				value = '';
			}else{
				value = result.codeValue;
			}
		}
	});
	return value;
}
/**
 * 对Date的扩展，将 Date 转化为指定格式的String   
 * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，  
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
 * 例子：   
 * (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
 * (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
 * @param  fmt 被转换的日期
 * @return 转换完成的日期类型
 * @author hongcheng 
 */
Date.prototype.format = function(fmt) {  
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
} 

/**
 * 格式化日期
 * @param  value 被格式化数值
 * @param  row 行
 * @param  index 序号
 * @return 格式化后的数值
 * @author hongcheng
 */
function formatDateTime(value){
	if(value==null){
		return '';
	}else{
		if(typeof value == 'string'){
			value = value.replace(/-/g,"/");
		}
		var date = new Date(value).format("yyyy-MM-dd hh:mm:ss");
		return date;
	}
	
}

/**
 * 格式化日期
 * @param  value 被格式化数值
 * @param  row 行
 * @param  index 序号
 * @return 格式化后的数值
 * @author hongcheng
 */
function formatDate(value,fmt){
	
	//console.error("value:" + value);
	if(value==null){
		return '';
	}else{
		if(typeof value == 'string'){
			value = value.replace(/-/g,"/");
		}
		//console.log("value----------------:" + value);
		var date = new Date(value).format(fmt);
		return date;
	}
	
}

function getDate(value){
	if(value == null){
		return null;
	}
	if(typeof value == 'string'){
		value = value.replace(/-/g,"/");
	}
	var showValue=new Date(value).format('yyyy-MM-dd');
	return showValue;
}

/**
 * 计算两个日期相隔的天数
 * @param  value 被格式化数值
 * @param  firstDate 第一个时间
 * @param  secondDate 第二个时间
 * @return 相隔的天数
 * @author ShanPeng
 */
function daysBetween(firstDate,secondDate){
	var days;
	if(firstDate == null || secondDate == null){
		return 0;
	}else{
		var fdate = new Date(getStringFromDate(firstDate));
		var sdate = new Date(getStringFromDate(secondDate));
		var result = sdate-fdate;
		if(result >= 0){
			days = parseInt(result/1000/60/60/24);
			if(days < 3){
				return true;
			}
			return false;
		}
		return false;
	}
}

/**
 * 真.计算两个日期相隔的天数(后面日期减去前面日期)
 * @param  firstDateStr 第一个时间
 * @param  secondDateStr 第二个时间
 * @return 相隔的天数
 * @author zhangjw
 */
function getBetweenDays(firstDateStr,secondDateStr){
	var days;
	if(firstDateStr == null || secondDateStr == null){
		return 0;
	}else{
		var fdate = new Date(firstDateStr);
		var sdate = new Date(secondDateStr);
		var result = sdate.getTime() -fdate.getTime();
		days = parseInt(result/1000/60/60/24);
		return days;
	}
}


/**
 * 比较两个日期的先后
 * @param  value 被格式化数值
 * @param  firstDate 第一个时间
 * @param  secondDate 第二个时间
 * @return 返回比较结果
 * @author ShanPeng
 */
function compareDate(firstDate,secondDate){
	if(firstDate == null || secondDate == null){
		return 0;
	}else{
		var fdate = new Date(getStringFromDate(firstDate));
		var sdate = new Date(getStringFromDate(secondDate));
		var result = parseInt(sdate-fdate);
		if(result < 0){
			return true;
		}
		return false;
	}
}
function getStringFromDate(value){
	var dd = new Date(value);
	var y = dd.getFullYear();
    var m = dd.getMonth()+1 ;
    var d = dd.getDate();
    return m+"-"+d+"-"+y;
}


//获取生效时间
function getEffectTime(f){
	if(f){
		try{
			var effectTime=new Date(f.replace("-", "/").replace("-", "/"));  
			
			var now = new Date().format("yyyy-MM-dd")+"";
			
			if(f == now ){
				return formatDateTime(d+"");
			}else{
				return formatDateTime(effectTime + "");
			}
		}catch(e){
			$.messager.alert("信息提示","生效时间或失效时间格式不正确");
		}
	}
	$.messager.alert("信息提示","生效时间不能为空");
	return f;
}

function stringToTime(string){ 
    var f = string.split(' ', 2); 
    var d = (f[0] ? f[0] : '').split('-', 3); 
    var t = (f[1] ? f[1] : '').split(':', 3); 
    return (new Date(parseInt(d[0], 10) || null, 
    		(parseInt(d[1], 10) || 1)-1,
    		parseInt(d[2], 10) || null,
    		parseInt(t[0], 10) || null, 
    		parseInt(t[1], 10) || null, 
    		parseInt(t[2], 10) || null));

}

function getDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = (dd.getMonth()+1 >= 10) ? (dd.getMonth()+1) : ("0"+(dd.getMonth()+1));
    var d = (dd.getDate() >=10) ? dd.getDate() : ("0"+dd.getDate());
    return y+"-"+m+"-"+d;
}

function getDateStr2(date,AddDayCount) {
    var dd = new Date(date);
    dd.setTime(dd.getTime() + (AddDayCount) * 24 * 60 * 60 * 1000);
    var y = dd.getFullYear();
    var m = (dd.getMonth()+1) >= 10 ? (dd.getMonth()+1) : ("0"+(dd.getMonth()+1));//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;
}

function validTime(effectTime,failTime){
	var result=false;
	if(effectTime && failTime){
		var date1=stringToTime(effectTime);
		var date2=stringToTime(failTime);
		var dif=(date2.getTime()-date1.getTime())/3600000;
		if(date1<date2 && dif<2){
			result=true;
		}else{
			$.messager.alert("提示","填写错误，应该填写生效日期小于失效日期,并且相差不能大于两个小时");
		}
	}else{
		$.messager.alert("提示","生效日期和失效日期填写错误");
	}
	return result;
}

function validIp(id,require) { 
	var re = new RegExp(/^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i);
	var ip = $.trim($('#'+id).val());
	if(require){
		if(ip){
			if(re.test(ip)){
				return true;
			}else{
				$.messager.alert("提示","IP格式填写错误");
				return false;
			}
		}else{
			$.messager.alert("提示","IP不能为空");
			return false;
		}
	}else{
		if(ip){
			if(re.test(ip)){
				return true;
			}else{
				$.messager.alert("提示","IP格式填写错误");
				return false;
			}
		}else{
			return true;
		}
	}
}


function ipToNumber(ip) {  
	var numbers = ip.split(".");  
	return parseInt(numbers[0])*256*256*256 +   
	parseInt(numbers[1])*256*256 +   
	parseInt(numbers[2])*256 +   
	parseInt(numbers[3]);  
}  

function numberToIp(number) {  
	return (Math.floor(number/(256*256*256))) + "." +   
	(Math.floor(number%(256*256*256)/(256*256))) + "." +   
	(Math.floor(number%(256*256)/256)) + "." +   
	(Math.floor(number%256));  
}
//比较两个ip的大小,如果大于，返回1，等于返回0，小于返回-1  
function compareIP(ipBegin, ipEnd)
{
	var temp1;
	var temp2;	
	temp1 = ipBegin.split(".");
	temp2 = ipEnd.split(".");	
	for (var i = 0; i < 4; i++)
	{
	    if (temp1[i]>temp2[i])
		{
			return 1;
		}
		else if (temp1[i]<temp2[i])
		{
			return -1;
		}
	}
	return 0;	
}
function validMulIp(id,require) { 
	var re = new RegExp(/^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i);
	var ips = $.trim($('#'+id).val());
	if(require){
		if(ips){
			var ipArr = ips.split('\n');
			var flag = true;
			for(var i=0;i<ipArr.length;i++){
				if(ipArr[i] != ""){
					if(!re.test(ipArr[i])){
						$.messager.alert("提示","第"+(i+1)+"个IP格式填写错误");
						flag = false;
						break;
					}
				}
			}
			return flag;
		}else{
			$.messager.alert("提示","IP不能为空");
			return false;
		}
	}else{
		if(ips){
			var ipArr = ips.split('/n');
			var flag = true;
			for(var i=0;i<ipArr.length;i++){
				if(ipArr[i] != ""){
					if(!re.test(ipArr[i])){
						$.messager.alert("提示","第"+(i+1)+"个IP格式填写错误");
						flag = false;
						break;
					}
				}
			}
			return flag;
		}else{
			return true;
		}
	}
}

function validMulDomainIp(id,require) { 
	// var re = new RegExp(/^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i);
	var reDomain = /^(www\.)?([0-9A-Za-z\-_\u4E00-\u9FA5]{1,62}\.){1,10}[a-z]{1,4}$/;
	var reIp = /^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i;
	var domainIps = $.trim($('#'+id).val());
	if(require){
		if(domainIps){
			var domainIpArr = domainIps.split('\n');
			var flag = true;
			for(var i=0;i<domainIpArr.length;i++){
				if(domainIpArr[i] != ""){
					if(!(reDomain.test(domainIpArr[i]) || reIp.test(domainIpArr[i]))){
						$.messager.alert("提示","第"+(i+1)+"个域名/IP格式填写错误");
						flag = false;
						break;
					}
				}
			}
			return flag;
		}else{
			$.messager.alert("提示","域名/IP不能为空");
			return false;
		}
	}else{
		if(domainIps){
			var domainIpArr = domainIps.split('/n');
			var flag = true;
			for(var i=0;i<domainIpArr.length;i++){
				if(domainIpArr[i] != ""){
					if(!(reDomain.test(domainIpArr[i]) || reIp.test(domainIpArr[i]))){
						$.messager.alert("提示","第"+(i+1)+"个域名/IP格式填写错误");
						flag = false;
						break;
					}
				}
			}
			return flag;
		}else{
			return true;
		}
	}
}
function getUnitNameById(id){
	var unitName;
	var e = $.ajax({
		type:"POST",
		url:'system/sysUnit/getEntity.do',
		data:{"id":id},
		async:false,
		dataType:'json',
		success:function(data){
			unitName = data.result.unitName;
		}
	});
	return unitName;
}
function commonAppendsHoses(node){
	var root = $('#areaHouseTreeNew').tree('find','root');
	if(node){
		if(node.attributes.level=='1'){
			var npNode=$('#areaHouseTreeNew').tree('find',node.id);
			if(!npNode){
				var datas = $('#areaHouseTree').tree('getData',node.target);
				$("#areaHouseTreeNew").tree('insert',{
					after: root.target,
					data: datas
				});
			}else{
				//获取子节点
				var childNodes = $('#areaHouseTree').tree('getChildren',node.target);
				$.each(childNodes,function(index,childNode){
					var exNode = $('#areaHouseTreeNew').tree('find',childNode.id);
					var dataArray=[];
					if(!exNode){
						//如果不存在
						var data1 = $('#areaHouseTree').tree('getData',childNode.target);
						dataArray.push(data1);
					}
					if(dataArray.length>0){
						$("#areaHouseTreeNew").tree('append',{
							parent: npNode.target,
							data: dataArray
						});
					}
				});
			}
		}else{
			var exNode = $('#areaHouseTreeNew').tree('find',node.id);
			if(!exNode){
				//源树节点
				var pNode=$('#areaHouseTree').tree('getParent',node.target);
				//目的树节点
				var npNode=$('#areaHouseTreeNew').tree('find',pNode.id);
				
				if(!npNode){
					//不存在父节点,插入父节点
					var datas = $('#areaHouseTree').tree('getData',pNode.target);
					datas.children=[];
					var selChild = $('#areaHouseTree').tree('getData',node.target);
					datas.children.push(selChild);
					$("#areaHouseTreeNew").tree('insert',{
						after: root.target,
						data: datas
					});
				}else{
					exNode = $('#areaHouseTreeNew').tree('find',node.id);
					npNode=$('#areaHouseTreeNew').tree('find',pNode.id);
					if(!exNode){
						//如果不存在
						var datas = $('#areaHouseTreeNew').tree('getData',npNode.target);
						var data1 = $('#areaHouseTree').tree('getData',node.target);
						var dataArray=[];
						datas.children.push(data1);
						dataArray.push(data1);
						$("#areaHouseTreeNew").tree('append',{
							parent: npNode.target,
							data: dataArray
						});
					}
				}
			}
		
		}
	}
}

function commonRemovesHoses(){
	var node = $('#areaHouseTreeNew').tree('getSelected');
	if(node && node.text!='机房名称'){
		if(node.attributes.level==2){
			var pNode=$('#areaHouseTreeNew').tree('getParent',node.target);
			var children=$("#areaHouseTreeNew").tree('getChildren',pNode.target);
			if(children.length<=1){
				var dataNodes = $('#areaHouseTreeNew').tree('remove',pNode.target);
			}else{
				var dataNodes = $('#areaHouseTreeNew').tree('remove',node.target);
			}
		}else{
			var dataNodes = $('#areaHouseTreeNew').tree('remove',node.target);
		}
		
		
	}
}
/**
 * 匹配域名和IP域名
 * @param value
 * @returns {Boolean}
 */
function matchDomain(value) {
	var reDomain = /^(www\.)?([0-9A-Za-z\-_\u4E00-\u9FA5]{1,62}\.){1,10}[a-z]{1,4}$/;
	var reIP = /^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i;
	if(value && (reDomain.test(value) || reIP.test(value))){
		return true;
	}else{
		return false;
	}
}

/**
 * 匹配URL
 * @param value
 * @returns {Boolean}
 */
function matchURL(value) {
    var strRegex = "^(http\\:\/\/)?(([1-9][0-9]{0,2}\\.)([0-9]{0,3}\\.){2}[1-9][0-9]{0,2})(\\:([1-9][0-9]{1,4}))?\/|(([0-9A-Za-z]{1,62}\\.){1,10}[a-z]{1,4})(\/([0-9A-Za-z\\?=\\.@#&\\$!~*%_\\-|;])*)+$";
    var re = new RegExp(strRegex);
	if(value && re.test(value)){
		return true;
	}else{
		return false;
	}
}

/**
 * 判断是否为url 此处做了系统特殊处理，可兼容不以http协议等开头的url
 * @param id
 * @param request
 * @returns {Boolean}
 */
function domainOrURL(id,request) {
	var strRegex = "^(http\\:\/\/)?(([1-9][0-9]{0,2}\\.)([0-9]{0,3}\\.){2}[1-9][0-9]{0,2})(\\:([1-9][0-9]{1,4}))?|(([0-9A-Za-z]{1,62}\\.){1,10}[a-z]{1,4})(\:)?(([1-9]|[1-9]\\d{1,3}|[1-6][0-5][0-5][0-3][0-5]))?(\/([0-9A-Za-z\\?=\\.@#&\\$!~*%_\\-|;])*)+$";
	var re=new RegExp(strRegex); 
    var val=$("#"+id).val();
    if(request){
    	if(val){
    		if (!re.test(val)) {
    			$.messager.alert("提示","URL格式填写错误");
    			return false;
    		}
    		return true;
    	}else{
    		$.messager.alert("提示","URL为必填项");
    		return false;
    	}
    }else{
    	if(val){
    		if (!re.test(val)) {
    			$.messager.alert("提示","URL格式填写错误");
    			return false;
    		}
    	}
    	return true;
    }
}

/**
 * 验证域名
 * @param id
 * @param request {Boolean} 
 * @returns {Boolean}
 */
function isDomain(id,request) {
	var strRegex = "^(www\\.)?([0-9A-Za-z\\-_\u4E00-\u9FA5]{1,62}\\.){1,10}[a-z]{1,4}$";
	var ipre = /^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i;
	var re = new RegExp(strRegex);
    var val=$("#"+id).val();
    if(request){
    	if(val){
    		if (re.test(val)||ipre.test(val)) {
    			return true;
    		} else {
    			$.messager.alert("提示","域名格式填写错误");
    			return false;
    		}
    	}else{
    		$.messager.alert("提示","域名为必填项");
    		return false;
    	}
    }else{
    	if(val){
    		if (re.test(val)||ipre.test(val)) {
    	        return true;
    	    } else {
    	    	$.messager.alert("提示","域名格式填写错误");
    	        return false;
    	    }
    	}else{
    		return true;
    	}
    }
}

/**
 * 验证多级域名模糊匹配格式
 * @param id
 * @param request {Boolean} 
 * @returns {Boolean}
 */
function isFuzzyDomain(id,request) {
	var strRegex = "^(\\*\\.)?([0-9A-Za-z\\-_\u4E00-\u9FA5]{1,62}\\.){1,10}[a-z]{1,4}$";
	var re = new RegExp(strRegex);
    var val=$("#"+id).val();
    if(request){
    	if(val){
    		if (re.test(val)) {
    			return true;
    		} else {
    			$.messager.alert("提示","域名格式填写错误");
    			return false;
    		}
    	}else{
    		$.messager.alert("提示","域名为必填项");
    		return false;
    	}
    }else{
    	if(val){
    		if (re.test(val)) {
    	        return true;
    	    } else {
    	    	$.messager.alert("提示","域名格式填写错误");
    	        return false;
    	    }
    	}else{
    		return true;
    	}
    }
}

/**
 * 验证域名
 * @param id
 * @param request {Boolean} 
 * @returns {Boolean}
 */
function isDomainOrIp(id,request) {
	var strRegex = "^(www\\.)?([0-9A-Za-z\\-_\u4E00-\u9FA5]{1,62}\\.){1,10}[a-z]{1,4}$";
	var ipre = /^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i;
	var re = new RegExp(strRegex);
    var val=$("#"+id).val();
    if(request){
    	if(val){
    		if (re.test(val)||ipre.test(val)) {
    			return true;
    		} else {
    			$.messager.alert("提示","域名/IP格式填写错误");
    			return false;
    		}
    	}else{
    		$.messager.alert("提示","域名/IP 为必填项");
    		return false;
    	}
    }else{
    	if(val){
    		if (re.test(val)||ipre.test(val)) {
    	        return true;
    	    } else {
    	    	$.messager.alert("提示","域名/IP格式填写错误");
    	        return false;
    	    }
    	}else{
    		return true;
    	}
    }
}

function isIpNoMsg(ip,isMust){
	var ipre = /^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i;
	if (isMust) {
		if (ip) {
			return ipre.test(ip);
		}else{
			return false;
		}
	}else{
		if (ip) {
			return ipre.test(ip);
		}else{
			return true;
		}
	}
}

/**
 * 验证URL
 * @param id
 * @param request {Boolean} 
 * @returns {Boolean}
 */
function isURL(id,request) {
    var strRegex = "^(http\\:\/\/)?(([1-9][0-9]{0,2}\\.)([0-9]{0,3}\\.){2}[1-9][0-9]{0,2})(\\:([1-9][0-9]{1,4}))?|(([0-9A-Za-z]{1,62}\\.){1,10}[a-z]{1,4})(\/([0-9A-Za-z\\?=\\.@#&\\$!~*%_\\-|;])*)+$";
    var re = new RegExp(strRegex);
    var val=$("#"+id).val();
    if(request){
    	if(val){
    		if (re.test(val)) {
    			return true;
    		} else {
    			$.messager.alert("提示","URL格式填写错误");
    			return false;
    		}
    	}else{
    		$.messager.alert("提示","URL为必填项");
    		return false;
    	}
    }else{
    	if(val){
    		if (re.test(val)) {
    	        return true;
    	    } else {
    	    	$.messager.alert("提示","URL格式填写错误");
    	        return false;
    	    }
    	}else{
    		return true;
    	}
    }
}

/**
 * 验证域名和URL
 * @param id
 * @param request {Boolean} 
 * @returns {Boolean}
 */
function isDomainURL(id,request) {
	var domainStrRegex = "^(www\\.)?([0-9A-Za-z\\-]{1,62}\\.){1,10}[a-z]{1,4}$";
    var urlStrRegex = "^(http\\:\/\/)?(([1-9][0-9]{0,2}\\.)([0-9]{0,3}\\.){2}[1-9][0-9]{0,2})(\\:([1-9][0-9]{1,4}))?|(([0-9A-Za-z]{1,62}\\.){1,10}[a-z]{1,4})(\/([0-9A-Za-z\\?=\\.@#&\\$!~*%_\\-|;])*)*$";
    var domainRe = new RegExp(domainStrRegex);
    var urlRe = new RegExp(urlStrRegex);
    var val=$("#"+id).val();
    if(request){
    	if(val){
    		if (!domainRe.test(val) && !urlRe.test(val)) {
    			$.messager.alert("提示","URL格式填写错误");
    			return false;
    		}
    		return true;
    	}else{
    		$.messager.alert("提示","URL为必填项");
    		return false;
    	}
    }else{
    	if(val){
    		if (!domainRe.test(val) && !urlRe.test(val)) {
    			$.messager.alert("提示","URL格式填写错误");
    			return false;
    		}
    	}
    	return true;
    }
}

/**
 * 验证多个域名和URL
 * @param id
 * @param request {Boolean} 
 * @returns {Boolean}
 */
function isMulURL(id,request) {
	var domainStrRegex = "^(www\\.)?([0-9A-Za-z-_]{1,62}\\.){1,10}[a-z]{1,4}$";
	    
    var urlStrRegex = "^(http(s)?://)?((([1-9][0-9]{0,2}\\.)([0-9]{0,3}\\.){0,2}[1-9][0-9]{0,2})|(([0-9A-Za-z-_]{1,62}\\.){1,10}[a-z]{1,4}))(\\:([1-9][0-9]{1,4}))?(\/([0-9A-Za-z\\?=\\.@#&\\$!~*%_\\-|;])*)*$";
		
    var domainRe = new RegExp(domainStrRegex);
    var urlRe = new RegExp(urlStrRegex);
    var val=$("#"+id).val();
	if(val){
		var temp=val.split("\n");
		var flag=false;
		for(var i=0;i<temp.length;i++){
			if ((domainRe.test(temp[i]) && temp[i].match(domainRe)[0]===temp[i]) ||
			 (urlRe.test(temp[i]) && temp[i].match(urlRe)[0]===temp[i]) ) {
				flag=true;
			}else{
				$.messager.alert("提示","第"+(i+1)+"个，URL格式填写错误");
				flag=false;
				break;
			}
		}
		return flag;
	}else if(request){
		$.messager.alert("提示","URL为必填项");
		return false;
	}else{
		return true;
	}
    
}

function beforeTime(day){
	var time=" 00:00:00";
	if(day){
		day+=time;
	}
	return day;
}
function afterTime(day){
	var time=" 23:59:59";
	if(day){
		day+=time;
	}
	return day;
}

function queryParam(params,fields){
	$.each( fields, function(i, field){  
        params[field.name] = field.value; //设置查询参数  
        if (field.name == 'effectTimeBefore') {
			params[field.name] = beforeTime(field.value);
		}
		if (field.name == 'effectTimeAfter') {
			params[field.name] = afterTime(field.value);
		}
		if (field.name == 'pastTimeBefore') {
			params[field.name] = beforeTime(field.value);
		}
		if (field.name == 'pastTimeAfter') {
			params[field.name] = afterTime(field.value);
		}
    });  
	return params;
}

function validIpSeg(ipName,startIp,endIp){
	if((isEmpty(startIp) && isEmpty(endIp))){
		return true;
	}
	if((!isEmpty(startIp) && isEmpty(endIp)) || (isEmpty(startIp) && !isEmpty(endIp))){
		$.messager.alert("信息提示",ipName+"填写不完整!");
		return false;
	}
	var startIpArr=startIp.split(".");
	var endIpArr=endIp.split(".");
	if(parseInt(startIpArr[0],10)>0 && parseInt(startIpArr[0],10)<=255 &&
			parseInt(startIpArr[1],10)>=0 && parseInt(startIpArr[1],10)<=255 &&
			parseInt(startIpArr[2],10)>=0 && parseInt(startIpArr[2],10)<=255 &&
			parseInt(startIpArr[3],10)>0 && parseInt(startIpArr[3],10)<=255 &&
			parseInt(endIpArr[0],10)>0 && parseInt(endIpArr[0],10)<=255 &&
			parseInt(endIpArr[1],10)>=0 && parseInt(endIpArr[1],10)<=255 &&
			parseInt(endIpArr[2],10)>=0 && parseInt(endIpArr[2],10)<=255 &&
			parseInt(endIpArr[3],10)>0 && parseInt(endIpArr[3],10)<=255){
		if(parseInt(startIpArr[0],10) ==parseInt(endIpArr[0],10) &&
				parseInt(startIpArr[1],10) ==parseInt(endIpArr[1],10) &&
				parseInt(startIpArr[2],10) ==parseInt(endIpArr[2],10)){
			if(parseInt(startIpArr[3],10) <=  parseInt(endIpArr[3],10)){
				return true;
			}else{
				$.messager.alert("提示",ipName+"的结束Ip不能小于开始Ip");
				return false;
			}
		}else{
			$.messager.alert("提示",ipName+"不能跨段");
			return false;
		}
	}else{
		$.messager.alert("提示",ipName+"不合法");
		return false;
	}
}



function isIpSeg(startIp,endIp){
	if(!startIp){
		$.messager.alert("提示","起始Ip为空");
		return false;
	}
	if(!endIp){
		$.messager.alert("提示","终止Ip为空");
		return false;
	}
	var startIpArr=startIp.split(".");
	var endIpArr=endIp.split(".");
	var ipReg = /^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i;
	if(startIpArr.length!=4||!ipReg.test(startIp)){
		$.messager.alert("提示","起始Ip不合法");
		return false;
	}
	if(endIpArr.length!=4||!ipReg.test(endIp)){
		$.messager.alert("提示","终止Ip不合法");
		return false;
	}
	if(parseInt(startIpArr[0],10)>0 && parseInt(startIpArr[0],10)<=255 &&
			parseInt(startIpArr[1],10)>=0 && parseInt(startIpArr[1],10)<=255 &&
			parseInt(startIpArr[2],10)>=0 && parseInt(startIpArr[2],10)<=255 &&
			parseInt(startIpArr[3],10)>0 && parseInt(startIpArr[3],10)<=255 &&
			parseInt(endIpArr[0],10)>0 && parseInt(endIpArr[0],10)<=255 &&
			parseInt(endIpArr[1],10)>=0 && parseInt(endIpArr[1],10)<=255 &&
			parseInt(endIpArr[2],10)>=0 && parseInt(endIpArr[2],10)<=255 &&
			parseInt(endIpArr[3],10)>0 && parseInt(endIpArr[3],10)<=255){
		if(parseInt(startIpArr[0],10) ==parseInt(endIpArr[0],10) &&
				parseInt(startIpArr[1],10) ==parseInt(endIpArr[1],10) &&
				parseInt(startIpArr[2],10) ==parseInt(endIpArr[2],10)){
			if(parseInt(startIpArr[3],10) <=  parseInt(endIpArr[3],10)){
				return true;
			}else{
				$.messager.alert("提示","终止Ip不能小于起始Ip");
				return false;
			}
		}else{
			$.messager.alert("提示","IP不能跨段");
			return false;
		}
	}else{
		$.messager.alert("提示","Ip不合法");
		return false;
	}
	
}
function openWindow(name){  
	var arr=name.split("\n");
	name=arr[0];
	if(name){
		if(name.indexOf('http://') > -1){
			window.open(name);
		}else{
			window.open('http://'+name);	
		}
	}
} 

function loadMask(){
	$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body"); 
	$("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候...").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true)-190)/2,top:($(window).height()-45)/2}); 

}

function removeMask(){
	var wrap = $(document.body);
	wrap.find("div.datagrid-mask-msg").remove(); 
	wrap.find("div.datagrid-mask").remove(); 
} 

function loadTopDomain(url){
	var temp="http://";
	var prefix=url.substring(0,temp.length);
	if(prefix==temp){
		url=url.substring(0,temp.length);
	}
	
	temp="https://";
	prefix=url.substring(0,temp.length);
	if(prefix==temp){
		url=url.substring(0,temp.length);
	}
	
	var pos=url.indexOf("/");
	url=url.subString(0,pos);
	
	var array=url.split(".");
	var topdomain='';
	if(array[length-1].length==2){
		topdomain=array[length-3]+"."+array[length-2]+"."+array[length-1];
	}else{
		topdomain=array[length-2]+"."+array[length-1];
	}
	return topdomain;
}

/**
 * 将long类型的数值转换成date类型
 * @param  value long类型数值
 * @return转换后的时间
 * @author pengshan
 */
function getDateByLong(value){
	var dateTime=new Date(value).format('yyyy-MM-dd hh:mm:ss');
	return dateTime;
}

/**
 * 将long类型的数值转换成date类型
 * @param  value long类型数值
 * @return转换后的时间
 * @author pengshan
 */
function getDateYByLong(value){
	var dateTime=new Date(value).format('yyyy-MM-dd');
	return dateTime;
}
/**
 * 讲时间转换成long类型
 * 
 * @param value 格式为"2014/03/08 17:20"
 */
function getLongByDate(value){
	if(null == value){
		return null;
	}
	value = value.replace(/-/g,"/");
	var date = new Date(value);
	var time = date.getTime();
	return time;
}
/**
 * 值是否为空
 * @param value
 * @returns {Boolean}
 */
function isBlank(value){
	if (value != "null" && value != "" && typeof(value)!="undefined") {
		return false;
	}
	return true;
}


/**
 * 下载文件
 * @param filePath 文件路径
 * @param fileName 文件名称
 * @param isConfirm 是否需要提示
 */
function downFile(filePath, fileName,isConfirm){
	if(isConfirm==true){
		var url = 'framework/filehandle/fileHandle/downOpenFile.do?filePath='+filePath+'&fileName='+fileName;
		url = encodeURI(encodeURI(url));
		window.open(url);
	}else{
		
		$.messager.confirm('提示','确定要下载文件吗?',function(result){  
			if (result){
				/*var url = 'framework/filehandle/fileHandle/downOpenFile.do?filePath='+filePath+'&fileName='+fileName;
				url = encodeURI(encodeURI(url));
				window.open(url);*/
	        	 var url = 'framework/filehandle/fileHandle/downOpenFile.do?filePath='+filePath+'&fileName=';
	        	 url = encodeURI(encodeURI(url)+encodeURI(fileName).replace(/\(/g,"%28").replace(/\)/g,"%29").replace(/\+/g,"%2B").replace(/\#/g,"%23").replace(/\&/g,"%26"));
	        	 window.open(url);
	         
			}  
		});  
	}
}

/**
 * 去除html标记
 * @param str
 * @returns
 */
function delHtmlTag(str){
	return str.replace(/<[^>\u4e00-\u9fa5]*>/g, '');
}

function isPort(id){
	var port=$("#"+id).val();
	if(!isNaN(port)){
		if(port>=0 && port<=65535){
			return true;
		}else{
			$.messager.alert("提示","端口为0-65535");
		}
	}else{
		 $.messager.alert("提示","端口为数字");
	}
	return false;
}

/**
 * 添加端口验证
 * @param Post 端口值
 * @returns {Boolean}
 */
function validPostSeg(name,Post){
	if(isEmpty(Post)){
		return true;
	}
	if((isNumber(Post) && (Post>=0 && Post<65536))){
		return true;
	}else{
		$.messager.alert("提示",name+"端口应为0-65535的整数。");
		return false;
	}
}

function isNumber(post){
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	if (post.search(re) != -1) {
		return true;
	} else {
		return false;
	}
}


/**
 * 
 * 判断新添加的IP是否在已有的数组中存在交集IP    
 * @param destStartIpArray  开始IP数组
 * @param destEndIpArray    结束IP数组
 * @param destStartIp      需要添加的开始IP
 * @param destEndIp        需要添加的结束IP
 * @returns {Boolean}      是否添加成功 true 成功  false 存在交集
 */
function ipVerify(destStartIpArray,destEndIpArray,destStartIp,destEndIp){
	var Regex = new RegExp(/^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i);
	var flag=true;
	if (!(destStartIp && destEndIp)) {
		$.messager.alert("信息提示", "IP段 不能为空!");
		return false;
	}
	if (!Regex.test(destStartIp)){
		$.messager.alert('提示','开始IP格式错误');
		return false;
	}
	if (!Regex.test(destEndIp)){
		$.messager.alert('提示','结束IP格式错误');
		return false;
	}
	//比较开始和结束IP的大小
	if (compareIpSize(destStartIp,destEndIp)){
		if(destStartIpArray.length==0){
			destStartIpArray.unshift(destStartIp);
			destEndIpArray.unshift(destEndIp);
			return true;
		}else{
			//验证IP段是否交集
			for (var i = 0; i<destStartIpArray.length; i++) {
				if (compareIpArray(destStartIpArray[i],destEndIpArray[i],destStartIp,destEndIp)){
					$.messager.alert('提示','IP段中存在交集!');
					flag = false;
					break;
				}
			}
		}
	}else{
		$.messager.alert('提示','IP格式错误,前项应该小于等于后项');
		flag = false;
	}
	if(flag){
		destStartIpArray.unshift(destStartIp);
		destEndIpArray.unshift(destEndIp);
	}
	return flag;
}


/**
 * 比IP大小
 * @param destStartIp
 * @param destEndIp
 * @returns {Boolean}
 */
function compareIpSize(destStartIp,destEndIp){
	var ipnum1=destStartIp.split('.');
	var ipnum2=destEndIp.split('.');
	var flag=true;
	for (var i = ipnum1.length - 1; i >= 0; i--) {
		flag=flag&&Number(ipnum1[i])<=Number(ipnum2[i]);
	}
	return flag;
}
