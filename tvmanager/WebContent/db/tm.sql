/*
SQLyog Ultimate v9.62 
MySQL - 5.5.8 : Database - tm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tm`;

/*Table structure for table `guanggao` */

DROP TABLE IF EXISTS `guanggao`;

CREATE TABLE `guanggao` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '广告的唯一编号',
  `title` varchar(300) NOT NULL COMMENT '广告标题',
  `zuozhe` varchar(100) DEFAULT NULL COMMENT '广告作者',
  `content` varchar(900) DEFAULT NULL COMMENT '广告内容',
  `fabu_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '广告发布时间',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `guanggao` */

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `id` int(60) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `mname` varchar(100) NOT NULL COMMENT '管理员登录时使用的用户名，唯一标识管理员的身份',
  `pswd` varchar(100) NOT NULL COMMENT '管理员密码',
  `realname` varchar(100) NOT NULL COMMENT '管理员真实姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '管理员性别',
  `age` varchar(10) DEFAULT NULL COMMENT '管理员年龄',
  `tel` varchar(20) NOT NULL COMMENT '管理员联系电话',
  `entitle` varchar(10) NOT NULL COMMENT '管理员的权限级别，用来限制管理员的管理权限',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `manager` */

/*Table structure for table `program` */

DROP TABLE IF EXISTS `program`;

CREATE TABLE `program` (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT '节目的唯一编号',
  `pname` varchar(100) DEFAULT NULL COMMENT '节目名称',
  `ptyp` varchar(10) DEFAULT NULL COMMENT '节目类别',
  `psttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '节目首播时间',
  `pdetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '节目截止时间',
  `ptxt` varchar(900) DEFAULT NULL COMMENT '节目简介',
  `actor` varchar(600) DEFAULT NULL COMMENT '主要演员',
  `director` varchar(300) DEFAULT NULL COMMENT '节目导演',
  `dizhi` varchar(600) DEFAULT NULL COMMENT '节目链接地址',
  `fileurl` varchar(200) DEFAULT NULL COMMENT '节目中海报的存储路径',
  `fabu_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '节目发布时间',
  KEY `pno` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `program` */

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论的唯一编号',
  `ip` varchar(15) NOT NULL COMMENT '发表评论人的用户的IP地址',
  `sj` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论发布的时间',
  `username` varchar(100) DEFAULT NULL COMMENT '发表评论的人的用户名',
  `homepage` varchar(600) DEFAULT NULL COMMENT '评论人的个人主页',
  `face` varchar(100) DEFAULT NULL COMMENT '评论的主题图案',
  `title` varchar(100) NOT NULL COMMENT '评论的主题',
  `content` varchar(600) NOT NULL COMMENT '评论的内容',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `review` */

/*Table structure for table `staff` */

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `id` int(11) NOT NULL COMMENT '节目参加人员的编号',
  `name` varchar(300) NOT NULL COMMENT '节目参加人员的姓名',
  `country` varchar(100) NOT NULL COMMENT '节目参加人员的国籍',
  `age` varchar(10) DEFAULT NULL COMMENT '节目参加人员的年龄',
  `sex` varchar(10) DEFAULT NULL COMMENT '节目参加人员的性别',
  `career` varchar(10) NOT NULL COMMENT '职业类别',
  `pname` varchar(300) DEFAULT NULL COMMENT '参加的节目的名称',
  KEY `sno` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `staff` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(100) NOT NULL COMMENT '用户登录时使用的用户名，唯一标识用户的身份',
  `password` varchar(100) NOT NULL COMMENT '用户密码',
  `realname` varchar(60) NOT NULL COMMENT '用户真实姓名',
  `sex` varchar(10) DEFAULT NULL COMMENT '用户性别',
  `age` varchar(10) DEFAULT NULL COMMENT '用户年龄',
  `tel` varchar(13) NOT NULL COMMENT '用户联系电话',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*Table structure for table `vote` */

DROP TABLE IF EXISTS `vote`;

CREATE TABLE `vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '后选内容唯一编号',
  `vname` varchar(300) NOT NULL COMMENT '后选内容的名称',
  `leibie` varchar(10) NOT NULL COMMENT '后选内容的类别',
  `c_num` int(11) DEFAULT NULL COMMENT '所获票数（初始值为零）',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vote` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
