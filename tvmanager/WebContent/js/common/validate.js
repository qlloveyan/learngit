$.extend($.fn.validatebox.defaults.rules, {   
    equals: {   
        validator: function(value,param){   
            return value == $(param[0]).val();   
        },   
        message: '两次输入的密码不一致.'
    },
    
  //不为空
	notNull : {
		validator : function(value, param) {
			var reg = new RegExp("^\s*$");
			return reg.test(value);
		},
		message : '该输入项为必输项'
	},
	//验证最小输入长度
	minLength : {
		validator : function(value, param) {
			return value.length >= param[0];
		},
		message : '请输入不小于{0}个字符'
	},
	//验证最大输入长度
	maxLength : {
		validator : function(value, param) {
			return value.length <= param[0];
		},
		message : '请输入不大于{0}个字符'
	},
	//验证正整数
	number : {
		validator : function(value, param) {
			var reg = new RegExp("^[0-9]*$");
			return reg.test(value);
		},
		message : '必须输入大于0的数字'
	},
	//时间比较
	dateCompare1 : {
		validator : function(value, param) {
				var str =null;
				var check=0;
				if(value!=null && param[1]!=null){
					str=new Date(value.replace(/-/g,"/"));
					check = (param[1].getTime()-str.getTime())/3600/1000;
				}
				if(check > 0){
					return false;
				}else{
				    return value > $(param[0]).val();
				}
		},
		message : '过期时间必须大于当前时间并且大于生效时间'
	},
	
	//时间比较
	dateCompare : {
		validator : function(value, param) {
			return value >= $(param[0]).val();
		},
		message : '过期时间必须大于生效时间'
	},
	
	//会议时间比较
	dateCompareMeeting : {
		validator : function(value, param) {
			return value >= $(param[0]).val();
		},
		message : '会议结束时间必须大于会议开始时间'
	},
	//dns监控时间比较
	dnsDateCompare : {
		validator : function(value, param) {
			var paramValue = $("input[name='"+param[0]+"']").val();
			return value >= paramValue;
		},
		message : '监控结束时间必须大于起始时间'
	},
	// 验证手机号码 
    mobile : {
        validator : function(value) { 
            return /^1(3|4|5|8)\d{9}$/i.test(value); 
        }, 
        message : '手机号码格式不正确,如13800138000' 
    },
    mobiles : {
    	validator : function(value) {
    		var flag;
    		var mobiles = value.split(";");
    		for( var index in mobiles ){
    			flag = /^1(3|4|5|8)\d{9}$/i.test(mobiles[index])
    			if( !flag ){
    				break;
    			}
    		}
    		return flag; 
    	}, 
    	message : '手机号码格式不正确,如13800138000;13813800138001' 
    },
    checkSsisp : {
    	validator : function(value) {
    		var url = "br/allProvinceEnterprbre/checkSsispExist.do";
    		var m_result =$.ajax({   type: "POST",//http请求方式
    			                     url: url,    //服务器段url地址
    			                     data:{'id':value},      //发送给服务器段的数据
    			                     dataType: "json", //告诉JQuery返回的数据格式
    			                     async: false
    			                 }).responseText;
    		console.log(m_result);
    		if(m_result=="true"){
    			return true;
    		}else {
    			return false;
    		}
    	}, 
    	message : '企业编号无效' 
    },
    // 验证固定电话
    tel : {
    	validator : function(value) { 
    		return /^\d{3,4}-\d{7,8}(-\d{3,4})?$/.test(value); 
    	//	return /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test(value); 
    	}, 
    	message : '固定电话格式不正确,号码中区号、分机号以-分割，如0571-87059184-4321' 
    },
    //^[+]?[0-9]+\d*$  /^[^0]\d
    ///^[1-9]\d*$/   ^(0|[1-9][0-9]*)$
    integer : {// 验证整数
		validator : function(value) {
		return /^(0|[1-9][0-9]*)$/i.test(value);
		},
		message : '整数输入格式不正确'
	},
	// 验证中文 
	chinese : {
        validator : function(value) { 
            return /^[\u0391-\uFFE5]+$/i.test(value); 
        }, 
        message : '请输入中文' 
    }, 
    
	// 验证英语 
    english : {
        validator : function(value) { 
            return /^[A-Za-z]+$/i.test(value); 
        }, 
        message : '请输入英文' 
    }, 
    
	// 验证传真 
    faxno : {
        validator : function(value) { 
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
        }, 
        message : '传真号码不正确,如0571-87059184' 
    }, 
    
	// 验证邮政编码 
    zip : {
        validator : function(value) { 
            return /^[1-9]\d{5}$/i.test(value); 
        }, 
        message : '邮政编码格式不正确,如310000' 
    },
    
    // 验证email 
    email : {
        validator : function(value) { 
            return /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/i.test(value); 
        }, 
        message : '邮箱格式不正确,如someone@example.com' 
    },
    
    // 验证qq 
    qq : {
        validator : function(value) { 
            return /^\s*[.0-9]{5,12}\s*$/i.test(value); 
        }, 
        message : 'QQ格式不正确,如88888' 
    },
    
    // 验证msn 
    msn : {
        validator : function(value) { 
            return /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/i.test(value); 
        }, 
        message : 'MSN格式不正确,如someone@msn.com' 
    },
//    //ip比较
//	ipCompare : {
//		validator : function(value, param) {
//			if(value > $(param[0]).val()){
//				return true;
//			}else if(value > $(param[0]).val())
//			return value > $(param[0]).val();
//		},
//		message : '终止IP要大于起始IP'
//	},
    //域名
      domain : {
        validator : function(value) { 
            return /^[a-zA-Z0-9\u0391-\uFFE5][-a-zA-Z0-9\u0391-\uFFE5]{0,62}(\.[a-zA-Z0-9\u0391-\uFFE5][-a-zA-Z0-9\u0391-\uFFE5]{0,62})+\.?$/i.test(value); 
        }, 
        message : '域名输入不正确,如google.com' 
   	 },
   //多个顶级域名
     topDomains : {
       validator : function(value) { 
    	   var topDomains = value.split(";");
    	   var flag = false;
    	   for(var i = 0; i < topDomains.length; i ++){
    		   if(topDomains[i].indexOf("www.") == 0){
    			   flag = false;
    		   }else{
    			   flag = /^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?$/i.test(topDomains[i]);
    		   }
    		   if(!flag){
    			   return false;
    		   }
    	   }
            return true;
       }, 
       message : '顶级域名输入不正确,如google.com;baidu.com' 
  	 },
  	//多个网站地址
     urls : {
       validator : function(value) { 
    	   var urls = value.split(";");
    	   var flag = false;
    	   for(var i = 0; i < urls.length; i ++){
    		   if(urls[i].indexOf("www.") != 0){
    			   flag = false;
    		   }else{
    			   flag = /^[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\.?$/i.test(urls[i]);
    		   }
    		   if(!flag){
    			   return false;
    		   }
    	   }
            return true;
       }, 
       message : '首页地址输入不正确,如www.google.com;www.baidu.com' 
  	 },
   	 //域名网址
      domainUrl : {
        validator : function(value) { 
        	var domainStrRegex = "^(www\\.)?([0-9A-Za-z]{1,62}\\.){1,10}[a-z]{1,4}$";
        	var urlStrRegex = "^(http\\:\/\/)?(([1-9][0-9]{0,2}\\.)([0-9]{0,3}\\.){2}[1-9][0-9]{0,2})(\\:([1-9][0-9]{1,4}))?|(([0-9A-Za-z]{1,62}\\.){1,10}[a-z]{1,4})(\/([0-9A-Za-z\\?=\\.@#&\\$!~*%_\\-|;])*)*$";
        	var domainRe = new RegExp(domainStrRegex);
            var urlRe = new RegExp(urlStrRegex);
            if (domainRe.test(value) || urlRe.test(value)) {
            	return true;
            }else{
            	return false;
            }
        }, 
        message : '网址输入不正确' 
   	 },
   	 //ip
   	 ip : {
        validator : function(value) { 
        	return /^((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)(\.((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]\d)|\d)){3}$/i.test(value);
        }, 
        message : 'ip输入不正确,如192.168.0.1' 
   	 },
   	 //port
   	 port: {
        validator : function(value) { 
        	return /^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/i.test(value);
        }, 
        message : '端口输入不正确,请输入0到65535的整数' 
   	 },
   	 //ip比较
   	 compareIp:{
         validator : function(value, param) {
        	var flag = false;
        	var temp1 = value.split('.');
        	var temp2 = ($(param[0]).val()).split('.');
        	if( parseInt(temp1[0]) > parseInt(temp2[0]) ){
        		flag = true;
        	}else if( parseInt(temp1[0]) == parseInt(temp2[0]) ){
        		if( parseInt(temp1[1]) > parseInt(temp2[1]) ){
            		flag = true;
            	}else if( parseInt(temp1[1]) == parseInt(temp2[1]) ){
            		if( parseInt(temp1[2]) > parseInt(temp2[2]) ){
                		flag = true;
                	}else if( parseInt(temp1[2]) == parseInt(temp2[2]) ){
                		if( parseInt(temp1[3]) >= parseInt(temp2[3]) ){
                    		flag = true;
                    	}else if( parseInt(temp1[3]) <= parseInt(temp2[3]) ){
                    		flag = false;
                    	}
                	}else if( parseInt(temp1[2]) < parseInt(temp2[2]) ){
                		flag = false;
                	}
            	}else if( parseInt(temp1[1]) < parseInt(temp2[1]) ){
            		flag = false;
            	}
        	}else if( parseInt(temp1[0]) < parseInt(temp2[0]) ){
        		flag = false;
        	}
         	return flag;
         }, 
         message : 'ip结束值必须大于起始值' 
    },
    // 判断字符串strDateTime是否为一个正确的日期时间格式： yyyy-M-d H:m:s或yyyy-MM-dd HH:mm:ss
    dateTime : {
    	validator : function(value) { 
			// 先判断格式上是否正确
			var regDateTime = /^(\d{4})-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
			if (!regDateTime.test(value))
			    return false;
			     
			// 将年、月、日、时、分、秒的值取到数组arr中，其中arr[0]为整个字符串，arr[1]-arr[6]为年、月、日、时、分、秒
			var arr = regDateTime.exec(value);
			 
			// 判断年、月、日的取值范围是否正确
			if (!IsMonthAndDateCorrect(arr[1], arr[2], arr[3]))
			    return false;
			     
			// 判断时、分、秒的取值范围是否正确
			if (arr[4] >= 24)
			    return false;
			if (arr[5] >= 60)
			    return false;
			if (arr[6] >= 60)
			    return false;
			 
			// 正确的返回
			return true;
        }, 
        message : '请输入正确的时间格式，如yyyy-M-d H:m:s或yyyy-MM-dd HH:mm:ss'
    },
    
    // 判断字符串strDate是否为一个正确的日期格式：yyyy-M-d或yyyy-MM-dd
    date : {
    	validator : function(value){
		    // 先判断格式上是否正确
		    var regDate = /^(\d{4})-(\d{1,2})-(\d{1,2})$/;
		    if (!regDate.test(strDate)){
		        return false;
		    }
		    // 将年、月、日的值取到数组arr中，其中arr[0]为整个字符串，arr[1]-arr[3]为年、月、日
		    var arr = regDate.exec(strDate);
		     
		    // 判断年、月、日的取值范围是否正确
		    return IsMonthAndDateCorrect(arr[1], arr[2], arr[3]);
    	},
    	 message : '请输入正确的时间格式，如yyyy-M-d或yyyy-MM-dd'
    }
});  