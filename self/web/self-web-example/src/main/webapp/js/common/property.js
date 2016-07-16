String.prototype.format = function(args) {
	var result = this;
	if (arguments.length > 0) {
		if (arguments.length == 1 && typeof(args) == "object") {
			for (var key in args) {
				if (args[key] != undefined) {
					var reg = new RegExp("({" + key + "})", "g");
					result = result.replace(reg, args[key]);
				}
			}
		} else {
			for (var i = 0; i < arguments.length; i++) {
				if (arguments[i] != undefined) {
					var reg = new RegExp("({)" + i + "(})", "g");
					result = result.replace(reg, arguments[i]);
				}
			}
		}
	}
	return result;
}

/**
 * HTML代码转换为页面可现实的javascript字符串
 * @return {}
 */
String.prototype.displayHtml= function(){  
    //将字符串转换成数组  
    var strArr = this.split('');  
    //HTML页面特殊字符显示，空格本质不是，但多个空格时浏览器默认只显示一个，所以替换  
    var htmlChar="&<>";  
    for(var i = 0; i< strArr.length;i++){  
    	var str = strArr[i];
        //查找是否含有特殊的HTML字符  
        if(htmlChar.indexOf(str.charAt(i)) !=-1){  
            //如果存在，则将它们转换成对应的HTML实体  
            switch (str.charAt(i)) {                          
                case '<':  
                    strArr.splice(i,1,'&#60;');  
                    break;  
                case '>':  
                    strArr.splice(i,1,'&#62;');  
                    break;  
                case '&':  
                    strArr.splice(i,1,'&#38;');  
            }  
        }  
    }  
    return strArr.join('');  
}

String.prototype.endWith=function(s){
	  if(s==null||s==""||this.length==0||s.length>this.length)
	     return false;
	  if(this.substring(this.length-s.length)==s)
	     return true;
	  else
	     return false;
	  return true;
}

String.prototype.startWith=function(s){
	  if(s==null||s==""||this.length==0||s.length>this.length)
	   return false;
	  if(this.substr(0,s.length)==s)
	     return true;
	  else
	     return false;
	  return true;
}

/** 
 *  对Date的扩展，将 Date 转化为指定格式的String * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
    可以用 1-2 个占位符 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) * eg: * (new
    Date()).pattern("yyyy-MM-dd hh:mm:ss.S")==> 2006-07-02 08:09:04.423      
 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04      
 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04      
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04      
 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18      
 */        
Date.prototype.pattern=function(fmt) {         
    var o = {         
    "M+" : this.getMonth()+1, //月份         
    "d+" : this.getDate(), //日         
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时         
    "H+" : this.getHours(), //小时         
    "m+" : this.getMinutes(), //分         
    "s+" : this.getSeconds(), //秒         
    "q+" : Math.floor((this.getMonth()+3)/3), //季度         
    "S" : this.getMilliseconds() //毫秒         
    };         
    var week = {         
    "0" : "/u65e5",         
    "1" : "/u4e00",         
    "2" : "/u4e8c",         
    "3" : "/u4e09",         
    "4" : "/u56db",         
    "5" : "/u4e94",         
    "6" : "/u516d"        
    };         
    if(/(y+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));         
    }         
    if(/(E+)/.test(fmt)){         
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);         
    }         
    for(var k in o){         
        if(new RegExp("("+ k +")").test(fmt)){         
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));         
        }         
    }         
    return fmt;         
}

/**
 * 对Date进行扩展，获取指定时间是当前的第几周
	eg： 
 * (new Date()).getWeek();  ==> 当日为本年的第几周
 * 
 */
Date.prototype.getWeek = function(){
	var thisDay = this;
	var firstDay = new Date(thisDay.getFullYear(),0,1);
	var dayWeek = thisDay.getDay();
	if(dayWeek == 0){dayWeek = 7;}
	startWeek = firstDay.getDay();
	if(startWeek == 0){startWeek = 7;}
	return parseInt(((thisDay.getTime()-firstDay.getTime())/(24*60*60*1000)+startWeek-dayWeek)/7 + 1);
}

/**
 * 对Date进行扩展，获取指定时间是当前的第几季度
	eg： 
 * (new Date()).getQuarter();  ==> 当日为本年的第几季度
 */
Date.prototype.getQuarter = function(){
	var thisDay = this;
	var quarter = 0;
	var month = thisDay.getMonth();
	if(month >= 0 && month < 3){
		quarter = 1;
	}else if(month >= 3 && month < 6){
		quarter = 2;
	}else if(month >= 6 && month < 9){
		quarter = 3;
	}else if(month >= 9 && month < 12){
		quarter = 4;
	}
	return quarter;
}