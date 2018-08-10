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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `photo_path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `received_message_id` bigint(20) DEFAULT NULL,
  `sent_message_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FK3mqi2kjhcv407r9hcxmfx03h5` (`received_message_id`),
  KEY `FK8053o2q32hg628fl5eargo4ks` (`sent_message_id`),
  CONSTRAINT `FK3mqi2kjhcv407r9hcxmfx03h5` FOREIGN KEY (`received_message_id`) REFERENCES `message` (`id`),
  CONSTRAINT `FK8053o2q32hg628fl5eargo4ks` FOREIGN KEY (`sent_message_id`) REFERENCES `message` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@mail.com','\0','admin','$2a$10$bBpL0wzKCuh3BwDoPbnNH.2jVFwiGfIdvA25O6pjviwODzMj0CMgO','adminSure','../uploads/admin@mail.com',NULL,NULL),(7,'lukk@mail.com','','LukkEdit','$2a$10$xuPO1jmv5EBd/ETinfa41OKcLhHFKLZ9EuUs48AeGEnkF5xqnvgEi','Sarna','../uploads/lukk@mail.com',NULL,NULL),(8,'owner@mail.com','','Ra','$2a$10$VTSPEehoulWeRNUrLzdJju8F.NoTchQakjcaACDxkpJmNC5oMHTsK','Owner','../uploads/profile.jpg',NULL,NULL),(9,'some@mail.com','','some','$2a$10$C4hFmK54KOOLZcqVNAgAyOTjmIO.8qimCXuAPxh9lLcGi6Srsqoki','some','../uploads/profile.jpg',NULL,NULL),(10,'someOwner@mail.com','\0','ownerEdited','$2a$10$Eb9MD28LiVsJAai0ZOPm8OPTWIPHoQko00mCoIe4n2Mty7pQpb6IS','owner','../uploads/someOwner@mail.com',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-09 18:10:37
