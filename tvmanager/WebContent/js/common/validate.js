$.extend($.fn.validatebox.defaults.rules, {   
    equals: {   
        validator: function(value,param){   
            return value == $(param[0]).val();   
        },   
        message: '两次输入的密码不一致.'
    },
    
    phone:{
    	validator: function(value,param){
    		var telCheck = "^((d{3,4})|d{3,4}-)?d{7,8}$";
            return telCheck.test(value);   
        },   
        message: '两次输入的密码不一致.'
    }
});  