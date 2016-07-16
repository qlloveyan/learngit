#关于self项目  

##一、self-framework  
- 框架部分，主要为系统管理部分，包括基础的用户，单位，权限、字典等信息，是整个self项目的基础。
结构如下(参考framework.jpg)
![framework.jpg](http://i.imgur.com/mF2oJQ8.jpg)

##二、web
- 编写自己的代码部分，类似framework结构，只不过缺少framework层，在各自的模块中添加对应的ctrl、service、dao、model和自己对应的mapper文件，如下图所示


##三、附件
- 其中添加了一下项目中用到的文件备注

##四、备注
- 编写所有代码时，请注意在web中新建各自模块对应的包，在各自的包中新建自己对应的业务，严格区分各业务包，framework在编写各自模块代码时，请不要做相应的修改，framework更新，请注意编写对应的更新说明。