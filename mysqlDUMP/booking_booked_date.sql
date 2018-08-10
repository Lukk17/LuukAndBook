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
-- Table structure for table `booked_date`
--

DROP TABLE IF EXISTS `booked_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booked_date` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `booked_date` tinyblob,
  `offer` bigint(20) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ddaw5qq8t3pde4yjen7egymy` (`offer`),
  KEY `FK5vngs9p46swf8l2ye3uvym91l` (`user`),
  CONSTRAINT `FK5vngs9p46swf8l2ye3uvym91l` FOREIGN KEY (`user`) REFERENCES `users` (`id`),
  CONSTRAINT `FK8ddaw5qq8t3pde4yjen7egymy` FOREIGN KEY (`offer`) REFERENCES `offer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booked_date`
--

LOCK TABLES `booked_date` WRITE;
/*!40000 ALTER TABLE `booked_date` DISABLE KEYS */;
INSERT INTO `booked_date` VALUES (1,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\â\rx',1,1),(2,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',1,1),(3,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',1,1),(4,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',1,1),(5,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',1,1),(6,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',4,7),(7,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',4,7),(8,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',4,7),(9,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',4,7),(10,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',4,7),(11,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',1,9),(12,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',1,9),(13,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',1,1),(14,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',1,1),(15,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',1,7),(16,'¬\í\0sr\0\rjava.time.Ser•]„º\"H²\0\0xpw\0\0\âx',1,7);
/*!40000 ALTER TABLE `booked_date` ENABLE KEYS */;
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
