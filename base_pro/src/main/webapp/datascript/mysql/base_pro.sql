/*
SQLyog Ultimate v9.62 
MySQL - 5.1.73 : Database - etribe_community
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`etribe_community` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `etribe_community`;

/*Table structure for table `t_code` */

DROP TABLE IF EXISTS `t_code`;

CREATE TABLE `t_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_type` varchar(50) DEFAULT NULL,
  `code_name` varchar(50) NOT NULL,
  `code_key` varchar(50) DEFAULT NULL,
  `code_sort` varchar(50) DEFAULT '0',
  `pid` int(11) DEFAULT NULL,
  `is_using` int(2) DEFAULT '1' COMMENT '用于区分确认订单规则启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `t_code` */

insert  into `t_code`(`id`,`code_type`,`code_name`,`code_key`,`code_sort`,`pid`,`is_using`) values (4,'USER_SEX','用户性别',NULL,'0',NULL,1),(5,'USER_SEX','男','1','0',4,1),(6,'USER_SEX','女','2','1',4,1),(7,'USER_STATE','用户状态',NULL,'0',NULL,1),(8,'USER_STATE','启用','1','0',7,1),(9,'USER_STATE','禁用','3','2',7,1),(10,'USER_STATE','审核','2','1',7,1),(11,'USER_STATUS','用户类型',NULL,'0',NULL,1),(12,'USER_STATUS','录入','1','0',11,1),(13,'USER_STATUS','配送','2','1',11,1),(14,'USER_STATUS','管理员','100','2',11,1),(15,'ORDER_STATUS','订单状态',NULL,'0',NULL,1),(16,'ORDER_STATUS','取消','-1','0',15,1),(17,'ORDER_STATUS','录入','1','1',15,1),(18,'ORDER_STATUS','取货','2','2',15,1),(19,'ORDER_STATUS','配送','3','3',15,1),(20,'ORDER_STATUS','确认','4','4',15,1),(21,'ORDER_STATUS','异常','5','5',15,1),(26,'ORDER_STATUS','小票','10','6',15,1),(27,'PAYMENT_RULE','劳务薪酬规则',NULL,NULL,NULL,NULL),(28,'PAYMENT_RULE','比例','0.5','0',27,2),(29,'PAYMENT_RULE','金额','4','1',27,1),(30,'SHARE_RULE','分红规则',NULL,NULL,NULL,NULL),(31,'SHARE_RULE','配送员获益比','0.5','0',30,1),(32,'SHARE_ACCOUNT','分红帐号',NULL,NULL,NULL,NULL),(33,'SHARE_ACCOUNT','管理员','admin','0',32,1);

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon_cls` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `page_cmp_url` varchar(255) DEFAULT NULL,
  `render_level` int(11) DEFAULT NULL,
  `show_index` int(11) DEFAULT NULL,
  `parent_menu` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_arxk0dwkhikhfynk2911vegml` (`parent_menu`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`icon_cls`,`name`,`page_cmp_url`,`render_level`,`show_index`,`parent_menu`) values (1,NULL,'订单管理','/sys/order',0,0,NULL),(2,NULL,'系统管理','/sys/yunwei',0,1,NULL),(3,NULL,'订单管理','/toPage.do?destPage=orderList',1,0,1),(8,NULL,'用户管理','/toPage.do?destPage=userList',1,1,2),(9,NULL,'地址库管理','/toPage.do?destPage=commonAddrs',1,2,2),(10,NULL,'系统菜单管理','/toPage.do?destPage=menu',1,3,2),(11,NULL,'数据字典管理','/toPage.do?destPage=code',1,4,2);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` int(11) DEFAULT NULL COMMENT '用户id',
  `identityCard` varchar(20) DEFAULT NULL COMMENT '身份证',
  `name` varchar(200) NOT NULL COMMENT '用户名',
  `phone` varchar(20) NOT NULL COMMENT '手机号码',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `smallAvatar` varchar(255) DEFAULT NULL COMMENT '小头像',
  `provinceId` int(11) NOT NULL COMMENT '省份',
  `cityId` int(11) NOT NULL COMMENT '城市',
  `registerTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '注册日期',
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '个人资料更新日期',
  `active` int(11) NOT NULL COMMENT '账户状态 1->激活 2-审核 3 -> 禁用',
  `status` int(1) NOT NULL COMMENT '1录入 2配送  100 -> 管理员',
  `addr` varchar(300) DEFAULT NULL COMMENT '地址',
  `clientId` varchar(100) DEFAULT NULL COMMENT '用户登录客户端个推id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9994106 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`userId`,`identityCard`,`name`,`phone`,`email`,`password`,`avatar`,`smallAvatar`,`provinceId`,`cityId`,`registerTime`,`updateTime`,`active`,`status`,`addr`,`clientId`) values (1,1,NULL,'管理员','admin','202cb962ac59075b964b07152d234b70','202cb962ac59075b964b07152d234b70',NULL,NULL,0,0,'2015-06-28 16:52:38','2015-08-04 16:11:55',1,100,NULL,NULL);

/*Table structure for table `t_user_login` */

DROP TABLE IF EXISTS `t_user_login`;

CREATE TABLE `t_user_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '主键',
  `tokenId` varchar(50) NOT NULL DEFAULT '' COMMENT '当前登录tokenId',
  `tokenTime` timestamp NULL DEFAULT NULL COMMENT '最后接口时间戳',
  `loginTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '用户登录时间',
  `version` varchar(50) DEFAULT NULL COMMENT '登录客户端版本',
  PRIMARY KEY (`id`,`userId`),
  KEY `tokenId` (`tokenId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7578 DEFAULT CHARSET=utf8 COMMENT='用户登录信息表';

/*Data for the table `t_user_login` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
