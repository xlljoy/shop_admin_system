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
-- Table structure for table `tb_award`
--

DROP TABLE IF EXISTS `tb_award`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_award` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `desc` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `point` int(10) NOT NULL,
  `priority` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `shop_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_AWARD` (`name`),
  KEY `shop_id` (`shop_id`),
  CONSTRAINT `tb_award_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_award`
--

LOCK TABLES `tb_award` WRITE;
/*!40000 ALTER TABLE `tb_award` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_award` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_daily_new`
--

LOCK TABLES `tb_daily_new` WRITE;
/*!40000 ALTER TABLE `tb_daily_new` DISABLE KEYS */;
INSERT INTO `tb_daily_new` VALUES (5,'new of the day','/upload/item/dailyNew/2020040211083810007.jpg','link',100,'2020-04-02 18:08:39','2020-04-02 18:08:39',1),(6,'good new 1','/upload/item/dailyNew/2020040211100510003.jpg','link',100,'2020-04-02 18:10:05','2020-04-02 18:10:05',1);
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
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_local_prfile` (`user_name`),
  KEY `fk_localaccount_profile` (`user_id`),
  CONSTRAINT `fk_localaccount_profile` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_local_account`
--

LOCK TABLES `tb_local_account` WRITE;
/*!40000 ALTER TABLE `tb_local_account` DISABLE KEYS */;
INSERT INTO `tb_local_account` VALUES (1,2,'Betty','50seqlsl95s52se55566sls5606ss599','2020-04-10 02:43:55','2020-04-14'),(4,1,'username','50seqlsl95s52se55566sls5606ss599','2020-04-14 21:01:13','2020-04-24'),(5,3,'Marc','50seqlsl95s52se55566sls5606ss599','2020-04-14 21:05:00','2020-04-14'),(9,18,'joy','50seqlsl95s52se55566sls5606ss599','2020-04-27 21:28:30','2020-04-27');
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES (1,'hahahaha','chi de ','/home/jli/Pictures/pic1.jpg','5','3',100,5,'2020-03-05 13:08:17','2020-03-05 13:08:17',1,NULL,32),(2,'toast pork belly','fat fat fat ','/home/jli/Pictures/image7.jpg','15','13',200,100,'2020-03-05 13:14:45','2020-04-07 18:43:56',1,7,32),(3,'modifiedProduct','not really tasty tasty ','/home/jli/Pictures/image7.jpg','155','122',199,100,'2020-03-05 13:19:57','2020-03-26 21:23:38',1,2,32),(4,'beef','tasty tasty ','/home/jli/Pictures/image7.jpg','15','13',200,100,'2020-03-05 15:35:48','2020-03-05 15:35:48',1,7,32),(5,'chuanchuanxiang','tasty tasty ','upload/item/shop/32/product/5/2020032511523210002.jpg','158','158',200,100,'2020-03-05 15:42:05','2020-03-25 18:53:23',1,7,32),(6,'test modify','tasty tasty ','/home/jli/Pictures/image7.jpg','158','158',200,100,'2020-03-06 14:38:36','2020-03-25 19:18:28',0,NULL,32),(7,'beef','tasty tasty ','/home/jli/Pictures/image7.jpg','15','13',200,100,'2020-03-09 14:26:49','2020-03-31 21:04:28',1,7,32),(8,'beef','tasty tasty ','/home/jli/Pictures/image7.jpg','15','13',200,100,'2020-03-09 14:28:05','2020-03-31 21:21:03',0,7,32),(9,'beef','tasty tasty ','/home/jli/Pictures/image7.jpg','15','13',200,100,'2020-03-09 14:41:11','2020-03-09 14:41:11',1,NULL,32),(10,'test1','testtest1',NULL,'15','13',20,100,'2020-03-09 14:44:36','2020-03-09 14:44:37',1,2,32),(11,'qishui','test','upload/item/shop/32/product/11/2020032512470510001.jpg','145','145',20,100,'2020-03-09 14:44:36','2020-03-25 19:47:17',1,NULL,32),(12,'test side image list','wow almost there',NULL,'15','15',20,100,'2020-03-09 14:50:01','2020-03-25 19:58:46',1,7,32),(13,'test1','testtest1','upload/item/shop/32/product/12/2020030914504310005.jpg','15','13',20,100,'2020-03-09 14:50:01','2020-03-09 14:50:01',1,2,32),(14,'test1','testtest1','upload/item/shop/32/product/14/2020030916135710001.jpg','15','13',20,100,'2020-03-09 16:13:57','2020-03-09 16:13:57',1,2,32),(16,'test1','testtest1','upload/item/shop/32/product/16/2020030916305210005.png','15','13',20,100,'2020-03-09 16:30:52','2020-03-09 16:30:52',1,2,32),(17,'test1','testtest1','upload/item/shop/32/product/17/2020030916391410004.jpg','15','13',20,100,'2020-03-09 16:39:15','2020-03-09 16:39:15',1,2,32),(18,'test1','testtest1','upload/item/shop/32/product/18/2020030916444610005.png','15','13',20,100,'2020-03-09 16:44:46','2020-03-09 16:44:46',1,2,32),(19,'test1','testtest1','upload/item/shop/32/product/19/2020031814471510003.png','15','13',20,100,'2020-03-18 21:47:15','2020-03-18 21:47:15',1,2,32),(20,'LALA','GOOD','upload/item/shop/32/product/20/2020032317561910004.jpg','6','6',5,8,'2020-03-24 00:56:19','2020-03-24 00:56:19',1,6,32),(21,'weiyi dounai ','hao he','upload/item/shop/32/product/21/2020032514523210004.jpg','3','3',2,5,'2020-03-25 21:52:33','2020-03-25 21:52:33',1,3,32),(22,'beef','tasty tasty ',NULL,'15','13',200,100,'2020-03-26 21:23:39','2020-03-26 21:23:39',1,7,32);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_category`
--

LOCK TABLES `tb_product_category` WRITE;
/*!40000 ALTER TABLE `tb_product_category` DISABLE KEYS */;
INSERT INTO `tb_product_category` VALUES (1,4,1,'2020-03-03 12:07:21','drinks'),(2,32,10,'2020-03-03 12:18:45','main course'),(3,32,2,'2020-03-03 12:19:28','soy milk'),(4,32,4,'2020-03-03 16:20:42','snack'),(6,32,200,NULL,'yangrouchuan'),(7,32,100,NULL,'kaorou');
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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_img`
--

LOCK TABLES `tb_product_img` WRITE;
/*!40000 ALTER TABLE `tb_product_img` DISABLE KEYS */;
INSERT INTO `tb_product_img` VALUES (14,'/Home/Pictures/pic1.jpg','smile face',7,'2020-03-05 13:08:28',1),(15,'/Home/Pictures/newnew.jpg','milktea',6,'2020-03-05 14:56:48',1),(16,'/Home/Pictures/pic1.jpg','smile face',7,'2020-03-05 14:56:48',1),(17,'upload/item/shop/32/product/4/2020030517062910001.jpg',NULL,NULL,'2020-03-05 17:06:29',4),(18,'upload/item/shop/32/product/4/2020030517063010007.jpg',NULL,NULL,'2020-03-05 17:06:31',4),(20,'upload/item/shop/32/product/4/2020030517082910004.jpg',NULL,NULL,'2020-03-05 17:08:27',4),(21,'upload/item/shop/32/product/10/2020030914460910006.jpg',NULL,NULL,'2020-03-09 14:46:07',10),(22,'upload/item/shop/32/product/10/2020030914461810004.jpg',NULL,NULL,'2020-03-09 14:46:18',10),(25,'upload/item/shop/32/product/14/2020030916135810001.jpg',NULL,NULL,'2020-03-09 16:13:58',14),(26,'upload/item/shop/32/product/14/2020030916135810003.jpg',NULL,NULL,'2020-03-09 16:13:58',14),(27,'upload/item/shop/32/product/16/2020030916305210002.jpg',NULL,NULL,'2020-03-09 16:30:53',16),(28,'upload/item/shop/32/product/16/2020030916305210000.png',NULL,NULL,'2020-03-09 16:30:53',16),(29,'upload/item/shop/32/product/4/2020030916334410007.jpg',NULL,NULL,'2020-03-09 16:33:45',4),(30,'upload/item/shop/32/product/4/2020030916334410007.jpg',NULL,NULL,'2020-03-09 16:33:45',4),(31,'upload/item/shop/32/product/4/2020030916350310002.jpg',NULL,NULL,'2020-03-09 16:35:01',4),(32,'upload/item/shop/32/product/4/2020030916352310005.jpg',NULL,NULL,'2020-03-09 16:35:22',4),(35,'upload/item/shop/32/product/18/2020030916444610004.jpg',NULL,NULL,'2020-03-09 16:44:47',18),(36,'upload/item/shop/32/product/18/2020030916444610007.jpg',NULL,NULL,'2020-03-09 16:44:47',18),(37,'upload/item/shop/32/product/19/2020031814471510002.jpg',NULL,NULL,'2020-03-18 21:47:16',19),(38,'upload/item/shop/32/product/19/2020031814471510007.jpg',NULL,NULL,'2020-03-18 21:47:16',19),(39,'upload/item/shop/32/product/20/2020032317561910006.jpg',NULL,NULL,'2020-03-24 00:56:19',20),(41,'upload/item/shop/32/product/5/2020032511542210000.jpg',NULL,NULL,'2020-03-25 18:54:22',5),(42,'upload/item/shop/32/product/5/2020032511542610002.jpg',NULL,NULL,'2020-03-25 18:54:26',5),(43,'upload/item/shop/32/product/11/2020032512543210000.png',NULL,NULL,'2020-03-25 19:54:32',11),(44,'upload/item/shop/32/product/11/2020032512543510007.jpg',NULL,NULL,'2020-03-25 19:54:35',11),(45,'upload/item/shop/32/product/12/2020032512593210005.jpg',NULL,NULL,'2020-03-25 19:59:24',12),(46,'upload/item/shop/32/product/21/2020032514523210001.jpg',NULL,NULL,'2020-03-25 21:52:33',21);
/*!40000 ALTER TABLE `tb_product_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_sell_daily_tracker`
--

DROP TABLE IF EXISTS `tb_product_sell_daily_tracker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_sell_daily_tracker` (
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(10) DEFAULT NULL,
  `product_id` int(10) NOT NULL,
  KEY `shop_id` (`shop_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `tb_product_sell_daily_tracker_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`id`),
  CONSTRAINT `tb_product_sell_daily_tracker_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_sell_daily_tracker`
--

LOCK TABLES `tb_product_sell_daily_tracker` WRITE;
/*!40000 ALTER TABLE `tb_product_sell_daily_tracker` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_product_sell_daily_tracker` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_shop`
--

LOCK TABLES `tb_shop` WRITE;
/*!40000 ALTER TABLE `tb_shop` DISABLE KEYS */;
INSERT INTO `tb_shop` VALUES (4,'milkeTea',3,2,NULL,'it is a good one','checking','vancouver','1234567','upload/item/shop/4/2020022817565210003.jpg',1,'2020-02-24 15:06:46','2020-02-28 17:56:52',1),(5,'the aly',2,2,NULL,'it is a good one','checking','montreal','1234567','a store pic',1,'2020-02-24 15:31:53','2020-02-28 19:13:18',1),(6,'modified shop',1,2,NULL,'it is a good one','checking','vancouver','1234567','upload/item/shop/6/2020030211424810004.jpg',1,'2020-02-24 15:33:07','2020-02-28 19:15:49',1),(7,'milkeTea',1,3,NULL,'it is a good one','checking','vancouver','12345678888','upload/item/shop/7/2020030211413710001.jpg',1,'2020-02-24 15:35:02','2020-03-02 11:36:55',1),(19,'milkeTea',1,2,NULL,'it is a good one','checking','lalalla','123456','upload/item/shop/19/2020022511181410005jpg',1,'2020-02-25 11:18:14','2020-02-25 11:18:14',2),(20,'rainbow drink',1,2,NULL,'it is a good one','checking','lalalla','123456','upload/item/shop/20/2020022514294810005.jpg',1,'2020-02-25 14:29:49','2020-02-25 14:29:49',2),(21,'milkeTea',1,2,NULL,'it is a good one','checking','lalalla','123456','a store pic',1,'2020-02-26 15:11:21',NULL,1),(22,'rainbow drink',1,2,NULL,'it is a good one','checking','lalalla','123456','upload/item/shop/22/2020022615112110005.jpg',1,'2020-02-26 15:11:21','2020-02-26 15:11:21',1),(26,'new new milktea',1,3,NULL,'hao he',NULL,'hahahsa ','1323454545','upload/item/shop/26/2020022617014310005.jpg',1,'2020-02-26 17:01:23','2020-02-26 17:01:23',2),(29,'test',1,3,NULL,'test',NULL,'test','test','upload/item/shop/29/2020022617411510001.jpg',1,'2020-02-26 17:41:15','2020-02-26 17:41:15',2),(30,'milkeTea',1,2,NULL,'it is a good one','checking','lalalla','123456','a store pic',1,'2020-02-28 16:15:32',NULL,1),(31,'milkeTea',1,2,NULL,'it is a good one','checking','lalalla','123456','a store pic',1,'2020-02-28 16:42:59',NULL,1),(32,'laochengdu hotpot',1,2,4,'spicy','great','first avenue','455612333','no',1,'2020-02-28 16:42:59','2020-02-28 16:42:59',6);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_shop_category`
--

LOCK TABLES `tb_shop_category` WRITE;
/*!40000 ALTER TABLE `tb_shop_category` DISABLE KEYS */;
INSERT INTO `tb_shop_category` VALUES (010,1,'coconut milktea','tasty','/home/jli/Pictures/image6.jpg',NULL,NULL,4),(011,2,'chocolate milktea','not sure flavor','/home/jli/Pictures/image5.jpg','2020-02-24 15:06:46','2020-02-24 15:06:46',3),(002,3,'rice noodles','not sure flavor','/home/jli/Pictures/image1.jpg','2020-02-24 15:06:46','2020-02-24 15:06:46',NULL),(001,4,'milktea','big category','/home/jli/Pictures/image2.jpg','2020-02-25 11:18:14','2020-02-25 11:18:14',NULL),(005,5,'hotpot','spicy','/home/jli/Pictures/image3.jpg','2020-02-25 11:18:14','2020-02-25 11:18:14',NULL),(004,6,'chengdu hotpot','spicy','/home/jli/Pictures/image4.jpg','2020-02-25 11:18:14','2020-02-25 11:18:14',5);
/*!40000 ALTER TABLE `tb_shop_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_shop_org_map`
--

DROP TABLE IF EXISTS `tb_shop_org_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shop_org_map` (
  `shop_org_id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `title_flag` int(2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `employee_id` int(10) NOT NULL,
  `shop_id` int(10) NOT NULL,
  PRIMARY KEY (`shop_org_id`),
  KEY `shop_id` (`shop_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `tb_shop_org_map_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`id`),
  CONSTRAINT `tb_shop_org_map_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_shop_org_map`
--

LOCK TABLES `tb_shop_org_map` WRITE;
/*!40000 ALTER TABLE `tb_shop_org_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_shop_org_map` ENABLE KEYS */;
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
  `authority` enum('LOW','MEDIUM','HIGH','SUPER') DEFAULT 'LOW',
  `gender` varchar(10) DEFAULT NULL,
  `enable_status` int(2) NOT NULL DEFAULT '0',
  `user_type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'cutie_user','cuite_profile_pic','lalal email',NULL,NULL,'LOW','female',1,0),(2,'Betty_user','/home/jli/Pictures/pic2.jpg','cucucu@ususu.com','2020-04-09 21:43:22','2020-04-09 21:43:22','HIGH','female',1,1),(3,'Marc_user','/home/jli/Pictures/pic1.jpg','mmiiu@ususu.com','2020-04-09 23:42:06','2020-04-09 23:42:06','LOW','male',1,2),(18,'joy','/upload/item/user/18/2020042714275510005.jpg','xli252@uottawa.ca','2020-04-27 21:27:55','2020-04-27 21:27:55','LOW','female',1,1);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_award_map`
--

DROP TABLE IF EXISTS `tb_user_award_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_award_map` (
  `user_award_id` int(10) NOT NULL AUTO_INCREMENT,
  `point` int(10) NOT NULL,
  `used_status` int(2) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(10) DEFAULT NULL,
  `operator_id` int(10) DEFAULT NULL,
  `customer_id` int(10) NOT NULL,
  `award_id` int(10) NOT NULL,
  PRIMARY KEY (`user_award_id`),
  KEY `shop_id` (`shop_id`),
  KEY `customer_id` (`customer_id`),
  KEY `operator_id` (`operator_id`),
  KEY `award_id` (`award_id`),
  CONSTRAINT `tb_user_award_map_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`id`),
  CONSTRAINT `tb_user_award_map_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `tb_user_award_map_ibfk_3` FOREIGN KEY (`operator_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `tb_user_award_map_ibfk_4` FOREIGN KEY (`award_id`) REFERENCES `tb_award` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_award_map`
--

LOCK TABLES `tb_user_award_map` WRITE;
/*!40000 ALTER TABLE `tb_user_award_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_award_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_product_map`
--

DROP TABLE IF EXISTS `tb_user_product_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_product_map` (
  `user_product_id` int(10) NOT NULL AUTO_INCREMENT,
  `point` int(10) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(10) DEFAULT NULL,
  `operator_id` int(10) DEFAULT NULL,
  `customer_id` int(10) NOT NULL,
  `product_id` int(10) NOT NULL,
  PRIMARY KEY (`user_product_id`),
  KEY `shop_id` (`shop_id`),
  KEY `customer_id` (`customer_id`),
  KEY `operator_id` (`operator_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `tb_user_product_map_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`id`),
  CONSTRAINT `tb_user_product_map_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `tb_user_product_map_ibfk_3` FOREIGN KEY (`operator_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `tb_user_product_map_ibfk_4` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_product_map`
--

LOCK TABLES `tb_user_product_map` WRITE;
/*!40000 ALTER TABLE `tb_user_product_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_product_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_shop_map`
--

DROP TABLE IF EXISTS `tb_user_shop_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_shop_map` (
  `user_shop_id` int(10) NOT NULL AUTO_INCREMENT,
  `point` int(10) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `shop_id` int(10) DEFAULT NULL,
  `customer_id` int(10) NOT NULL,
  PRIMARY KEY (`user_shop_id`),
  KEY `shop_id` (`shop_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `tb_user_shop_map_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`id`),
  CONSTRAINT `tb_user_shop_map_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_shop_map`
--

LOCK TABLES `tb_user_shop_map` WRITE;
/*!40000 ALTER TABLE `tb_user_shop_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_shop_map` ENABLE KEYS */;
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

-- Dump completed on 2020-04-28  0:28:36
