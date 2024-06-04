/*
SQLyog v10.2 
MySQL - 5.5.27 : Database - jiejie
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jiejie` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jiejie`;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `pquantity` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

insert  into `cart`(`cid`,`pid`,`pquantity`,`uid`) values (1,1,1,1),(3,3,3,3),(4,4,4,4),(14,1,1,8),(15,2,1,8);

/*Table structure for table `location` */

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `realName` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `identity` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `oid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `location` */

insert  into `location`(`aid`,`realName`,`phone`,`identity`,`address`,`oid`,`uid`) values (1,'雷佳豪','15527926811','121212','湖北工业大学工程技术学院',1,1),(2,'小雷','110','1111','湖工工程',2,2),(3,'张三','911','22222','湖北工业大学',3,3),(4,'小A','2233','3333','2222',4,4),(33,'ljh','15527926811','11111','hg',6,2),(35,'888','888','888','999',11,8),(37,'ljh','15527926811','11111','hg',12,8);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`rid`,`roleName`) values (1,'管理员'),(2,'svip用户'),(3,'vip用户'),(4,'普通用户');

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `total_price` decimal(12,3) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `goodsStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `t_order_cid_fk0` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`oid`,`total_price`,`uid`,`createtime`,`status`,`goodsStatus`) values (1,'1000.000',1,'2024-05-21 21:36:04','已支付','已发货'),(2,'2000.000',2,'2024-05-21 21:36:38','已支付','未发货'),(3,'22222.000',3,'2024-05-10 21:37:24','已支付','已发货'),(4,'33333.000',4,'2024-05-15 21:37:49','未支付','未发货'),(5,'100000.123',1,'2024-05-29 00:22:01','未支付','已发货'),(6,'600.000',2,'2024-05-29 00:29:24','已支付','未发货'),(8,'1000.000',2,'2024-05-30 00:35:25','未支付','未发货'),(9,'1000.000',2,'2024-05-30 00:35:42','未支付','未发货'),(10,'1000.000',2,'2024-05-30 00:36:13','未支付','未发货'),(11,'1000.000',8,'2024-05-30 00:38:03','已支付','已发货'),(12,'100.000',8,'2024-05-30 00:48:38','已支付','未发货');

/*Table structure for table `t_order_detail` */

DROP TABLE IF EXISTS `t_order_detail`;

CREATE TABLE `t_order_detail` (
  `opid` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`opid`),
  KEY `t_order_product_oid_fk0` (`oid`),
  KEY `t_order_product_pid_fk0` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_order_detail` */

insert  into `t_order_detail`(`opid`,`oid`,`pid`) values (1,6,2),(4,8,1),(5,9,1),(6,10,1),(7,11,1),(8,12,4);

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) DEFAULT NULL,
  `price` decimal(12,3) DEFAULT NULL,
  `pfile` varchar(200) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `t_product` */

insert  into `t_product`(`pid`,`pname`,`price`,`pfile`,`description`) values (1,'金龙油','1000.000','/img/0006.jpg','鲁花5s压榨花生油'),(2,'ipad pro','300.000','/img/0007.jpg','地表最强M4,你买得起吗'),(3,'智能手表','200.000','/img/0008.jpg','小天才智能手表'),(4,'小米巨能写','100.000','/img/0009.jpg','大学四年都用不完一支'),(5,'vivo pad 2','2000.000','/img/0010.jpg','vivo pad2，你值得拥有'),(6,'羽毛球拍','300.000','/img/0011.jpg','林丹同款'),(7,'海澜之家','80.000','/img/0012.jpg','死贵死贵'),(8,'良品铺子','11111.000','/img/0013.jpg','不整两袋再走？');

/*Table structure for table `t_stock` */

DROP TABLE IF EXISTS `t_stock`;

CREATE TABLE `t_stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_name` varchar(200) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `stock_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_stock` */

insert  into `t_stock`(`stock_id`,`stock_name`,`price`,`stock_number`) values (1,'苹果',5,93),(2,'香蕉',4,50),(3,'西瓜',3,200);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `telephone` bigint(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`name`,`password`,`telephone`,`createTime`,`role_id`) values (1,'ljh','111111',123123,'2022-06-24 23:10:18',2),(2,'admin','admin',12312312312,'2022-06-11 22:46:33',1),(4,'王胖子','王胖子',18871243931,'2022-06-14 00:00:00',3),(5,'雪莉杨','雪莉杨',18871243931,'2022-06-21 00:00:00',3),(6,'大金牙','大金牙',18871243931,'2022-06-28 00:00:00',2),(7,'张三','张三',12312312312,'2024-05-29 00:00:00',2),(8,'ljh','123456',15527926811,'2024-05-29 00:00:00',4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
