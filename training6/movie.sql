-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: movie
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `movie`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `movie` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `movie`;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL,
  `theatreid` int(10) NOT NULL,
  `timings` varchar(20) NOT NULL,
  `regionid` int(10) NOT NULL,
  `movieid` int(10) NOT NULL,
  `date` date NOT NULL,
  `seatno` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bookings_fk0` (`userid`),
  KEY `bookings_fk1` (`theatreid`),
  KEY `bookings_fk2` (`regionid`),
  KEY `bookings_fk3` (`movieid`),
  CONSTRAINT `bookings_fk0` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `bookings_fk1` FOREIGN KEY (`theatreid`) REFERENCES `theatre` (`id`),
  CONSTRAINT `bookings_fk2` FOREIGN KEY (`regionid`) REFERENCES `region` (`id`),
  CONSTRAINT `bookings_fk3` FOREIGN KEY (`movieid`) REFERENCES `movies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (2,1,1,'Morning - (9 - 12)',1,2,'2001-09-01','A1'),(3,1,1,'Morning - (9 - 12)',1,2,'2001-09-01','A2'),(4,1,1,'Morning - (9 - 12)',1,2,'2001-09-01','A3'),(5,1,1,'Morning - (9 - 12)',1,2,'2001-09-01','A4'),(17,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','A6'),(18,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','J2'),(19,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','I5'),(20,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','I7'),(21,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','G9'),(22,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','F10'),(23,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','B10'),(24,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','C11'),(25,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','E11'),(26,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','D13'),(27,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','B13'),(28,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','H12'),(29,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','G11'),(30,7,1,'Morning - (9 - 12)',1,2,'2023-07-13','I11'),(31,7,5,'Morning - (9 - 12)',2,2,'2023-07-13','A1'),(32,7,5,'Morning - (9 - 12)',2,2,'2023-07-13','A2'),(33,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','C6'),(34,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','C8'),(35,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','D9'),(36,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','D10'),(37,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','E10'),(38,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','E8'),(39,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','F9'),(40,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','F8'),(41,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','G8'),(42,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','G9'),(43,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','H8'),(44,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','H7'),(45,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','E6'),(46,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','F6'),(47,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','H5'),(48,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','F4'),(49,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','E5'),(50,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','F5'),(51,7,1,'Morning - (9 - 12)',1,2,'2023-07-14','G5'),(52,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','E9'),(53,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','E11'),(54,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','F11'),(55,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','G10'),(56,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','H9'),(57,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','F10'),(58,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','D10'),(59,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','C11'),(60,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','D12'),(61,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','F12'),(62,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','H12'),(63,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','I13'),(64,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','G13'),(65,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','E13'),(66,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','C13'),(67,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','D19'),(68,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','D20'),(69,7,5,'Morning - (9 - 12)',2,2,'2023-07-14','E19'),(70,7,1,'Night - (9 - 12)',1,2,'2023-07-14','A1'),(71,7,5,'Noon - (1 - 4)',2,2,'2023-07-14','A2'),(72,7,5,'Noon - (1 - 4)',2,2,'2023-07-14','A5'),(73,7,5,'Noon - (1 - 4)',2,2,'2023-07-14','A8'),(74,7,5,'Noon - (1 - 4)',2,2,'2023-07-14','B11'),(75,7,5,'Noon - (1 - 4)',2,2,'2023-07-14','B15'),(76,7,5,'Noon - (1 - 4)',2,2,'2023-07-14','B18'),(77,7,5,'Noon - (1 - 4)',2,2,'2023-07-14','F16'),(78,7,5,'Noon - (1 - 4)',2,2,'2023-07-14','F10'),(79,7,5,'Noon - (1 - 4)',2,2,'2023-07-14','G8'),(80,15,1,'Night - (9 - 12)',1,2,'2023-07-14','A1'),(81,15,1,'Night - (9 - 12)',1,2,'2023-07-14','A15'),(82,15,1,'Morning - (9 - 12)',1,2,'2023-07-14','D7'),(83,15,1,'Morning - (9 - 12)',1,2,'2023-07-14','D8'),(84,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','D3'),(85,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','E7'),(86,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','C9'),(87,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','B11'),(88,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','F10'),(90,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','A1'),(91,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','A2'),(92,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','A3'),(93,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','A1'),(94,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','A2'),(95,7,1,'Noon - (1 - 4)',1,2,'2023-08-20','A3'),(96,7,1,'Morning - (9 - 12)',1,2,'2023-08-20','A1'),(97,7,1,'Morning - (9 - 12)',1,2,'2023-08-20','A2');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `language` varchar(20) NOT NULL,
  `releasedate` date NOT NULL,
  `duration` varchar(6) DEFAULT NULL,
  `genre` varchar(10) NOT NULL,
  `description` varchar(500) NOT NULL,
  `casting` varchar(100) NOT NULL,
  `imageurl` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'none','none','2001-09-01','none','none','none','none','none'),(2,'123123','telugu','2001-09-01','2hrs','horror','It is a horror movie','hero, heroine','https://static.vecteezy.com/system/resources/previews/005/502/524/original/cinema-background-concept-movie-theater-object-on-red-curtain-background-and-movie-time-with-electric-bulbs-frame-illustration-free-vector.jpg'),(3,'123','telugu','2001-09-01','2.5hrs','horror','It is a horror movie','Meee','https://static.vecteezy.com/system/resources/previews/005/502/524/original/cinema-background-concept-movie-theater-object-on-red-curtain-background-and-movie-time-with-electric-bulbs-frame-illustration-free-vector.jpg'),(4,'1231234444','telugu','2001-09-01','2.5hrs','horror','It is a horror movie','Meee','https://10play.com.au/ip/s3/2019/08/09/e4b365d10829878d80a0a1871ffeee0b-551003.jpg');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password`
--

DROP TABLE IF EXISTS `password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `password` varchar(20) NOT NULL,
  `userid` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userid` (`userid`),
  CONSTRAINT `password_fk0` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password`
--

LOCK TABLES `password` WRITE;
/*!40000 ALTER TABLE `password` DISABLE KEYS */;
INSERT INTO `password` VALUES (1,'gowtham',1),(2,'gowtham',4),(3,'gowtham',5),(4,'dsvd',6),(5,'1',7),(6,'2',8),(7,'4',9),(8,'5',11),(9,'9',13),(10,'7',14),(11,'6',15);
/*!40000 ALTER TABLE `password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (2,'navalur'),(1,'perungudi');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theatre`
--

DROP TABLE IF EXISTS `theatre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theatre` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `show` varchar(20) NOT NULL,
  `seatcapacity` int(4) NOT NULL,
  `regionid` int(10) NOT NULL,
  `movieid` int(10) DEFAULT NULL,
  `rowcapacity` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `theatre_fk0` (`regionid`),
  KEY `theatre_fk1` (`movieid`),
  CONSTRAINT `theatre_fk0` FOREIGN KEY (`regionid`) REFERENCES `region` (`id`),
  CONSTRAINT `theatre_fk1` FOREIGN KEY (`movieid`) REFERENCES `movies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theatre`
--

LOCK TABLES `theatre` WRITE;
/*!40000 ALTER TABLE `theatre` DISABLE KEYS */;
INSERT INTO `theatre` VALUES (1,'perungudi theatre','Morning - (9 - 12)',150,1,2,15),(2,'perungudi theatre','Noon - (1 - 4)',150,1,2,15),(3,'perungudi theatre','Evening - (5 - 8)',150,1,2,15),(4,'perungudi theatre','Night - (9 - 12)',150,1,2,15),(5,'123 theatre','Morning - (9 - 12)',200,2,2,25),(6,'123 theatre','Noon - (1 - 4)',200,2,2,25),(7,'123 theatre','Evening - (5 - 8)',200,2,2,25),(8,'123 theatre','Night - (9 - 12)',200,2,2,25);
/*!40000 ALTER TABLE `theatre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'gowthamkallipudi2','gowtham','kallipudi','gowthamkallipudi@gmail.com2',9000315770),(4,'gowthamkallipudi','gowtham','kallipudi','gowthamkallipudi@gmail.com',999921),(5,'gowthamkallipudi312','gowtham11','kallipudi','gowthamkallipudi@gmail.com3',999923),(6,'gsdg','segerg','regsegs','rgesge',99433),(7,'1','1','1','1',1),(8,'223','2','2','2',2),(9,'4','4','4','4',4),(11,'5','5','5','5',5),(13,'9','9','9','9',9),(14,'7','7','7','7',7),(15,'6','6','6','6',6);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'movie'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-06 11:51:30
