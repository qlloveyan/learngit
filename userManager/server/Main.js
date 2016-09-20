var express = require('express');//web开发框架
var fsUtils = require("fs");//引入本地文件读取依赖

//引入其他模块
var userRoutes = require("./UserCtrl");

var MainCtrl = express();

//返回参数
var ExtjsObj = {
		'flag' : false,
		'msg' : 'no message',
		'obj' : ''
};

//设置静态资源访问
MainCtrl.use(express.static('../js'));
MainCtrl.use(express.static('../views'));
/**
 * 设置路由访问
 *  让 /user 下的请求跳转至UserCtrl.js文件中
 */
MainCtrl.use('/user',userRoutes);

/**
 * 创建服务器监听程序
 */
var MainServer = MainCtrl.listen( 8080 , function(){
	console.log("应用服务实例已开启!")
});

/**
 * 初始页面
 */
MainCtrl.get('/',function(request,response){
	response.writeHead(200, {"Content-Type": "text/html"});
	fsUtils.readFile('../login.html',function (err,data){
		response.end(data);
    });
});

/**
 * 用户登录
 */
MainCtrl.get('/userLogin',function(request,response){
	var userName = request.query.userName;
	var password = request.query.password;
	response.end( JSON.stringify( ExtjsObj ) );
});


