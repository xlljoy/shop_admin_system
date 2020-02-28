-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: o2o
-- ------------------------------------------------------
-- Server version	5.7.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_daily_new`
--

DROP TABLE IF EXISTS `tb_daily_new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_daily_new` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `img` varchar(1024) NOT NULL,
  `link` varchar(1024) NOT NULL,
  `priority` int(3) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_daily_new`
--

LOCK TABLES `tb_daily_new` WRITE;
/*!40000 ALTER TABLE `tb_daily_new` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_daily_new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_enum-authority`
--

DROP TABLE IF EXISTS `tb_enum-authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_enum-authority` (
  `col` enum('LOW','MEDIUM','HIGH','SUPER') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_enum-authority`
--

LOCK TABLES `tb_enum-authority` WRITE;
/*!40000 ALTER TABLE `tb_enum-authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_enum-authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_local_account`
--

DROP TABLE IF EXISTS `tb_local_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_local_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_local_prfile` (`user_name`),
  KEY `fk_localaccount_profile` (`user_id`),
  CONSTRAINT `fk_localaccount_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_local_account`
--

LOCK TABLES `tb_local_account` WRITE;
/*!40000 ALTER TABLE `tb_local_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_local_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `product_desc` varchar(2000) DEFAULT NULL,
  `img_addr` varchar(2000) DEFAULT '',
  `normal_price` varchar(100) DEFAULT NULL,
  `promotion_price` varchar(100) DEFAULT NULL,
  `priority` int(3) NOT NULL DEFAULT '0',
  `point` int(10) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `product_category_id` int(10) DEFAULT NULL,
  `shop_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_product_procate` (`product_category_id`),
  KEY `fk_product_shop` (`shop_id`),
  CONSTRAINT `fk_product_procate` FOREIGN KEY (`product_category_id`) REFERENCES `tb_product_category` (`id`),
  CONSTRAINT `fk_product_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_category`
--

DROP TABLE IF EXISTS `tb_product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `shop_id` int(10) NOT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_shop` (`shop_id`),
  CONSTRAINT `fk_shop` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_category`
--

LOCK TABLES `tb_product_category` WRITE;
/*!40000 ALTER TABLE `tb_product_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_img`
--

DROP TABLE IF EXISTS `tb_product_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_img` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `img_addr` varchar(1024) NOT NULL,
  `img_desc` varchar(1024) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `product_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product` (`product_id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_img`
--

LOCK TABLES `tb_product_img` WRITE;
/*!40000 ALTER TABLE `tb_product_img` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_product_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_shop`
--

DROP TABLE IF EXISTS `tb_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shop` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `owner_id` int(10) NOT NULL,
  `zone_id` int(2) DEFAULT NULL,
  `priority` int(3) DEFAULT '0',
  `shop_desc` varchar(1024) DEFAULT NULL,
  `advice` varchar(1024) DEFAULT NULL,
  `addr` varchar(1024) DEFAULT NULL,
  `phone` varchar(1024) DEFAULT NULL,
  `img` varchar(1024) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `shop_category_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_shop_owner` (`owner_id`),
  KEY `fk_shop_zone` (`zone_id`),
  KEY `fk_shop_category` (`shop_category_id`),
  CONSTRAINT `fk_shop_category` FOREIGN KEY (`shop_category_id`) REFERENCES `tb_shop_category` (`id`),
  CONSTRAINT `fk_shop_owner` FOREIGN KEY (`owner_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `fk_shop_zone` FOREIGN KEY (`zone_id`) REFERENCES `tb_zone` (`zone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_shop`
--

LOCK TABLES `tb_shop` WRITE;
/*!40000 ALTER TABLE `tb_shop` DISABLE KEYS */;
INSERT INTO `tb_shop` VALUES (4,'milkeTea',1,2,NULL,'it is a good one','checking','vancouver','1234567','a store pic',1,'2020-02-24 15:06:46','2020-02-24 15:36:32',1),(5,'the aly',1,2,NULL,'it is a good one','checking','montreal','1234567','a store pic',1,'2020-02-24 15:31:53','2020-02-26 15:11:21',1),(6,'milkeTea',1,2,NULL,'it is a good one','checking','vancouver','1234567','a store pic',1,'2020-02-24 15:33:07',NULL,1),(7,'milkeTea',1,2,NULL,'it is a good one','checking','vancouver','1234567','a store pic',1,'2020-02-24 15:35:02',NULL,1),(19,'milkeTea',1,2,NULL,'it is a good one','checking','lalalla','123456','upload/item/shop/19/2020022511181410005jpg',0,'2020-02-25 11:18:14','2020-02-25 11:18:14',1),(20,'rainbow drink',1,2,NULL,'it is a good one','checking','lalalla','123456','upload/item/shop/20/2020022514294810005.jpg',0,'2020-02-25 14:29:49','2020-02-25 14:29:49',1),(21,'milkeTea',1,2,NULL,'it is a good one','checking','lalalla','123456','a store pic',1,'2020-02-26 15:11:21',NULL,1),(22,'rainbow drink',1,2,NULL,'it is a good one','checking','lalalla','123456','upload/item/shop/22/2020022615112110005.jpg',0,'2020-02-26 15:11:21','2020-02-26 15:11:21',1),(26,'new new milktea',1,3,NULL,'hao he',NULL,'hahahsa ','1323454545','upload/item/shop/26/2020022617014310005.jpg',0,'2020-02-26 17:01:23','2020-02-26 17:01:23',2),(29,'test',1,3,NULL,'test',NULL,'test','test','upload/item/shop/29/2020022617411510001.jpg',0,'2020-02-26 17:41:15','2020-02-26 17:41:15',2);
/*!40000 ALTER TABLE `tb_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_shop_category`
--

DROP TABLE IF EXISTS `tb_shop_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shop_category` (
  `priority` int(3) unsigned zerofill DEFAULT NULL,
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `category_desc` varchar(1024) NOT NULL,
  `img` varchar(1024) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_parent_category` (`parent_id`),
  CONSTRAINT `fk_parent_category` FOREIGN KEY (`parent_id`) REFERENCES `tb_shop_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_shop_category`
--

LOCK TABLES `tb_shop_category` WRITE;
/*!40000 ALTER TABLE `tb_shop_category` DISABLE KEYS */;
INSERT INTO `tb_shop_category` VALUES (010,1,'coconut milktea','tasty','a nice picture',NULL,NULL,4),(011,2,'chocolate milktea','not sure flavor','no pic yet','2020-02-24 15:06:46','2020-02-24 15:06:46',1),(002,3,'rice noodles','not sure flavor','no pic yet','2020-02-24 15:06:46','2020-02-24 15:06:46',NULL),(001,4,'milktea','big category','nope','2020-02-25 11:18:14','2020-02-25 11:18:14',NULL);
/*!40000 ALTER TABLE `tb_shop_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `head_img` varchar(1024) DEFAULT NULL,
  `email` varchar(1024) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `authority` enum('LOW','MEDIUM','HIGH','SUPER') NOT NULL DEFAULT 'LOW',
  `gender` varchar(10) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `user_type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'cutie','cuite_profile_pic','lalal email',NULL,NULL,'LOW','female',0,0);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_wechat_account`
--

DROP TABLE IF EXISTS `tb_wechat_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_wechat_account` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `open_id` varchar(1024) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `open_id` (`open_id`),
  KEY `fk_wechataccount_profile` (`user_id`),
  CONSTRAINT `fk_wechataccount_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_wechat_account`
--

LOCK TABLES `tb_wechat_account` WRITE;
/*!40000 ALTER TABLE `tb_wechat_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_wechat_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_zone`
--

DROP TABLE IF EXISTS `tb_zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_zone` (
  `zone_id` int(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`zone_id`),
  UNIQUE KEY `UK_ZONE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_zone`
--

LOCK TABLES `tb_zone` WRITE;
/*!40000 ALTER TABLE `tb_zone` DISABLE KEYS */;
INSERT INTO `tb_zone` VALUES (2,'east yard',1,NULL,NULL),(3,'west yard',2,NULL,NULL);
/*!40000 ALTER TABLE `tb_zone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-27 15:13:18
