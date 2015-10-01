Ext.onReady(function(){
	var userGrid = Ext.widget({
		renderTo:"user_grid",
		xtype:"grid",
		title:"用户信息管理",
		width:500,
		height:300,
		plugins:"rowediting",
		store:{
			fields:["name","age","gender","identity"],
			data:[
				      ["Bill", 35, "男" , 0],
				      ["Fred", 22 , "女" , 1]
			     ]
		},
		columns:{
			defaults:{
				editor:'numberfield',
				width:120
			},
			items:[
				      {text:'姓名',dataIndex:'name',flex:1,editor:'textfield'},
				      {text:'年龄',dataIndex:'age'},
				      { text: '性别', dataIndex: 'gender' },  
	                  { text: '用户级别', dataIndex: 'identity' }  
			      ]
		}
	});
	
});