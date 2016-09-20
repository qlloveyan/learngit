var express = require('express');
var UserCtrl = express.Router();

/**
 * 添加用户信息
 */
UserCtrl.get('/addUser', function(request, response) {
	response.render('index', { title: 'Express' });
});

module.exports = UserCtrl;