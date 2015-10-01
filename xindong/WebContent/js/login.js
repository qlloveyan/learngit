Ext.onReady(function(){
	var login_form = Ext.create("Ext.form.Panel", {
	    width: 300,
	    height: 200,
	    margin: 20,
	    url:'user/login',
	    title: '欢迎登陆本系统',
	    renderTo: "login_div",
//	    collapsible: true,  //可折叠
	    autoScroll: true,   //自动创建滚动条
	    defaultType: 'textfield',
	    defaults: {
	        anchor: '100%',
	    },
	    fieldDefaults: {
	        labelWidth: 80,
	        labelAlign: "center",
	        flex: 1,
	        margin: 5
	    },
	    items: [
	        {
	            xtype: "container",
	            layout: "hbox",
	            items: [
	                { xtype: "textfield", name: "username", fieldLabel: "用户名", allowBlank: false },
	            ]
	        },
	        {
	            xtype: "container",
	            layout: "hbox",
	            items: [
	                { xtype: "textfield", name: "password", fieldLabel: "密码", allowBlank: false, inputType: 'password' },
	            ]
	        }
	    ],
	    buttons: [
	        { 
	        	xtype: "button", 
	        	text: "保存",
	        	handler: function() {
	        		var form = this.up('form').getForm();
	        		if (form.isValid()) {
	        			form.submit({
	                        success: function(form, action) {
	                           Ext.Msg.alert('Success', action.result.msg);
	                        },
	                        failure: function(form, action) {
	                            Ext.Msg.alert('Failed', action.result ? action.result.msg : 'No response');
	                        }
	                    });
	                }
//	        		window.location.href="jump/manage-users";
	            }
	        },{ 
	        	xtype: "button", 
	        	text: "注册",
	        	handler: function() {
	        		window.location.href="jump/register";
	            }
	        }
	    ]
	});
});