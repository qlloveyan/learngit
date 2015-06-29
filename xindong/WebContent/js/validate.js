Ext.apply(Ext.form.VTypes, {
	
	//验证最小输入长度
	eqPass:function(val , field){
		console.info(field);
		if( field.confirmTo ){
			var pass2 = Ext.get(field.confirmTo);
			return val == pass2.getValue();
		}
		return false;
	},
	minLengthText:"两次输入 的密码不相符"
		
});