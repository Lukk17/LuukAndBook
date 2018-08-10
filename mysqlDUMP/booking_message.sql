CREATE DATABASE  IF NOT EXISTS `booking` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `booking`;
-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: booking
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.31-MariaDB

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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message_receiver` bigint(20) DEFAULT NULL,
  `message_sender` bigint(20) DEFAULT NULL,
  `created` tinyblob,
  `message_perma_receiver` bigint(20) DEFAULT NULL,
  `message_perma_sender` bigint(20) DEFAULT NULL,
  `readed` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4x5g9b1fbvjxmicxx21v19utn` (`message_receiver`),
  KEY `FKdgl2ypqt8pacwikyoq7juwe5k` (`message_sender`),
  KEY `FKo31ix2o4b1diqsohwdcli4rco` (`message_perma_receiver`),
  KEY `FKl9hiiprn7daubny2ounxb9je9` (`message_perma_sender`),
  CONSTRAINT `FK4x5g9b1fbvjxmicxx21v19utn` FOREIGN KEY (`message_receiver`) REFERENCES `users` (`id`),
  CONSTRAINT `FKdgl2ypqt8pacwikyoq7juwe5k` FOREIGN KEY (`message_sender`) REFERENCES `users` (`id`),
  CONSTRAINT `FKl9hiiprn7daubny2ounxb9je9` FOREIGN KEY (`message_perma_sender`) REFERENCES `users` (`id`),
  CONSTRAINT `FKo31ix2o4b1diqsohwdcli4rco` FOREIGN KEY (`message_perma_receiver`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (99,'hey Ra owner !',8,1,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â\n,#¤Á€x',8,1,''),(100,'hey Lukk !',7,1,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â\n5¤š\0x',7,1,''),(101,'hey admin !',NULL,7,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â\n\nœ€x',1,7,'\0'),(102,'hey admin :D',1,8,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â\n9Á\Ä@x',1,8,''),(103,'hey owner i booked your room',8,7,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â\n7Àx',8,7,''),(104,'hey lukk ! have a good time ',7,8,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â\n\á£\0x',7,8,''),(105,'hi i want to rent a room',1,9,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â³È€x',1,9,'\0'),(106,'you are owner now',10,1,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â6\"E\ÍÀx',10,1,''),(107,'siema ',1,NULL,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â:õôÀx',1,7,'\0'),(108,'msg',1,1,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â	9\Ú\ê@x',1,1,'\0');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-09 18:10:38
