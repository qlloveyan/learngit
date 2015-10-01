Ext.onReady(function(){
	 //加载用户身份标识的下拉列表框的值
	 var identityStore = new Ext.data.JsonStore({  
         url: "user/getIdentity",  
         fields:['name','value']
     });  
//	 identityStore.load(); 
	
	var login_form = Ext.create("Ext.form.Panel", {
	    width: 500,
	    height: 300,
	    margin: 20,
	    title: '欢迎注册成为本系统会员',
	    renderTo: "register_div",
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
	                { xtype: "textfield", name: "username", fieldLabel: "登录名", allowBlank: false ,emptyText: "请输入用户名",vtype:"alphanum"},
	                { xtype: "textfield", name: "realname", fieldLabel: "真实姓名", allowBlank: false,emptyText: "请输入真实姓名" }
	            ]
	        },
	        {
	            xtype: "container",
	            layout: "hbox",
	            items: [
	                { xtype: "textfield",id:"password", name: "password", fieldLabel: "密码", inputType: 'password',allowBlank: false, emptyText: "请输入密码",minLength:8,maxLength:30 },
//	                , vtype: "email" 
	                { xtype: "textfield", name: "password2", fieldLabel: "确认密码",inputType: 'password', allowBlank: false, emptyText: "请确认密码",vtype:"eqPass",confirmTo:"password"}
	            ]
	        },
	        {
	        	xtype: "container",
	            layout: "hbox",
	            items: [
	                 //初始化下拉列表框
	                { xtype:'combobox',name:'identity',fieldLabel:'用户类型',allowBlank: false, mode:'local',
	                  store:Ext.create('Ext.data.JsonStore', {
                	  		fields:['name','value'],
                	  		data:[{'name':'普通用户','value':'2'},{'name':'管理员','value':'1'}]
//                	  		url: 'user/getIdentity',  
//                	        autoLoad : true
	                  }),
	                  forceSelect:true,//不允许自己输入值
	                  multiSelect:false,//允许多选
	                  displayField:'name',
	                },
	                //初始化单选按钮
	                {  xtype: 'radiogroup',fieldLabel: '性别',name: 'gender',id:'gender', 
                	   items: [          
		                	{boxLabel:'男',name:'gender',inputValue:'1',id:'male',checked: true}, 
		                	{boxLabel:'女',name:'gender',inputValue:'2',id:'female'}   
	                	]    
                	}   
	            ]
	        }
	    ],
	    buttons: [
	        { 
	        	xtype: "button", 
	        	text: "保存",
	        	handler: function() {
	        		window.location.href="jump/manage-users";
	            }
	        }
	    ]
	});
});