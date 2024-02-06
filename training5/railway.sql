-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: railway
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
-- Current Database: `railway`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `railway` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `railway`;

--
-- Table structure for table `m_route`
--

DROP TABLE IF EXISTS `m_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_route` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_day_of_the_week` int(11) NOT NULL,
  `train_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd8t2pjufr7lw4qmhw0yiegmak` (`train_id`),
  CONSTRAINT `FKd8t2pjufr7lw4qmhw0yiegmak` FOREIGN KEY (`train_id`) REFERENCES `m_train` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_route`
--

LOCK TABLES `m_route` WRITE;
/*!40000 ALTER TABLE `m_route` DISABLE KEYS */;
INSERT INTO `m_route` VALUES (1,0,1),(2,1,1),(3,2,1),(4,3,1),(5,4,1),(6,5,1),(7,6,1),(8,0,2),(9,2,2),(10,4,2),(11,6,2),(12,1,3),(13,3,3),(14,5,3);
/*!40000 ALTER TABLE `m_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_route_details`
--

DROP TABLE IF EXISTS `m_route_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_route_details` (
  `seq_no` int(11) NOT NULL AUTO_INCREMENT,
  `halt_in_minutes` int(11) NOT NULL,
  `route_id` int(11) NOT NULL,
  `station_id` int(11) NOT NULL,
  PRIMARY KEY (`seq_no`),
  KEY `FKqqfr8jj1aq8wc8qqc8enhtksw` (`route_id`),
  KEY `FK4y7hbswh2foow4igc9rsub2xo` (`station_id`),
  CONSTRAINT `FK4y7hbswh2foow4igc9rsub2xo` FOREIGN KEY (`station_id`) REFERENCES `m_station` (`id`),
  CONSTRAINT `FKqqfr8jj1aq8wc8qqc8enhtksw` FOREIGN KEY (`route_id`) REFERENCES `m_route` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_route_details`
--

LOCK TABLES `m_route_details` WRITE;
/*!40000 ALTER TABLE `m_route_details` DISABLE KEYS */;
INSERT INTO `m_route_details` VALUES (1,2,1,1),(2,2,1,2),(3,2,1,3),(4,2,1,4),(5,2,1,5),(6,2,2,1),(7,2,2,2),(8,2,2,3),(9,2,2,4),(10,2,2,5),(11,2,3,1),(12,2,3,2),(13,2,3,3),(14,2,3,4),(15,2,3,5),(16,2,4,1),(17,2,4,2),(18,2,4,3),(19,2,4,4),(20,2,4,5),(21,2,5,1),(22,2,5,2),(23,2,5,3),(24,2,5,4),(25,2,5,5),(26,2,6,1),(27,2,6,2),(28,2,6,3),(29,2,6,4),(30,2,6,5),(31,2,7,1),(32,2,7,2),(33,2,7,3),(34,2,7,4),(35,2,7,5),(36,2,8,1),(37,2,8,2),(38,2,8,6),(39,2,8,7),(40,2,8,8),(41,2,8,4),(42,2,8,5),(43,2,9,1),(44,2,9,2),(45,2,9,6),(46,2,9,7),(47,2,9,8),(48,2,9,4),(49,2,9,5),(50,2,10,1),(51,2,10,2),(52,2,10,6),(53,2,10,7),(54,2,10,8),(55,2,10,4),(56,2,10,5),(57,2,11,1),(58,2,11,2),(59,2,11,6),(60,2,11,7),(61,2,11,8),(62,2,11,4),(63,2,11,5),(64,2,12,9),(65,2,12,10),(66,2,12,11),(67,2,12,12),(68,2,12,13),(69,2,12,14),(70,2,12,15),(71,2,12,1),(72,2,13,9),(73,2,13,10),(74,2,13,11),(75,2,13,12),(76,2,13,13),(77,2,13,14),(78,2,13,15),(79,2,13,1),(80,2,14,9),(81,2,14,10),(82,2,14,11),(83,2,14,12),(84,2,14,13),(85,2,14,14),(86,2,14,15),(87,2,14,1);
/*!40000 ALTER TABLE `m_route_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_station`
--

DROP TABLE IF EXISTS `m_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_station`
--

LOCK TABLES `m_station` WRITE;
/*!40000 ALTER TABLE `m_station` DISABLE KEYS */;
INSERT INTO `m_station` VALUES (1,'chennai'),(2,'nellore'),(3,'guntur'),(4,'secundrabad'),(5,'hyderabad'),(6,'ongole'),(7,'vijayawada'),(8,'warangal'),(9,'Dadar'),(10,'Pune'),(11,'Shahabad'),(12,'Saidapur'),(13,'Guntakal'),(14,'Cuddapah'),(15,'Renigunta');
/*!40000 ALTER TABLE `m_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_train`
--

DROP TABLE IF EXISTS `m_train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_train` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `no1st_ac` int(11) NOT NULL,
  `no2nd_ac` int(11) NOT NULL,
  `no3rd_ac` int(11) NOT NULL,
  `no_sl` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_train`
--

LOCK TABLES `m_train` WRITE;
/*!40000 ALTER TABLE `m_train` DISABLE KEYS */;
INSERT INTO `m_train` VALUES (1,'Hyderabad Express',3,3,3,3),(2,'Charminar Express',3,3,3,3),(3,'Chennai Express',3,3,3,3);
/*!40000 ALTER TABLE `m_train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m_train_coach_info`
--

DROP TABLE IF EXISTS `m_train_coach_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m_train_coach_info` (
  `coach` varchar(2) NOT NULL,
  `no_of_seats` int(11) NOT NULL,
  PRIMARY KEY (`coach`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m_train_coach_info`
--

LOCK TABLES `m_train_coach_info` WRITE;
/*!40000 ALTER TABLE `m_train_coach_info` DISABLE KEYS */;
INSERT INTO `m_train_coach_info` VALUES ('A1',72),('A2',72),('A3',72),('SL',72);
/*!40000 ALTER TABLE `m_train_coach_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_booking`
--

DROP TABLE IF EXISTS `t_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_booking` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `coach` varchar(3) NOT NULL,
  `date` date NOT NULL,
  `seat_no` int(11) NOT NULL,
  `route_id` int(11) NOT NULL,
  `destination` int(11) NOT NULL,
  `source` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FKg7kyti3ykhnopelq9ngxemoby` (`route_id`),
  KEY `FK2nsab5c9d19qyojevr4h3ta0g` (`destination`),
  KEY `FK539a8i24oiuki7mvswn4g4j20` (`source`),
  KEY `FKayifinbua5a16p2dbou47l0dk` (`user_id`),
  CONSTRAINT `FK2nsab5c9d19qyojevr4h3ta0g` FOREIGN KEY (`destination`) REFERENCES `m_station` (`id`),
  CONSTRAINT `FK539a8i24oiuki7mvswn4g4j20` FOREIGN KEY (`source`) REFERENCES `m_station` (`id`),
  CONSTRAINT `FKayifinbua5a16p2dbou47l0dk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FKg7kyti3ykhnopelq9ngxemoby` FOREIGN KEY (`route_id`) REFERENCES `m_route` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_booking`
--

LOCK TABLES `t_booking` WRITE;
/*!40000 ALTER TABLE `t_booking` DISABLE KEYS */;
INSERT INTO `t_booking` VALUES (1,'SL1','2023-06-28',36,4,5,1,1,'Cancelled','Normal'),(2,'SL1','2023-06-30',36,6,3,1,1,'Cancelled','Normal'),(3,'SL1','2023-06-30',36,14,1,14,1,'Booked','Normal'),(4,'A11','2023-06-30',36,6,2,1,1,'Booked','Normal'),(5,'SL1','2023-06-30',35,14,1,14,1,'Booked','Normal'),(6,'A11','2023-06-30',35,6,5,1,3,'Booked','Normal'),(7,'A21','2023-06-30',36,6,5,1,3,'Booked','Normal'),(8,'SL1','2023-07-03',36,2,4,1,1,'Booked','Tatkal'),(9,'SL1','2023-07-03',35,2,5,1,1,'Booked','Tatkal'),(10,'A11','2023-07-03',36,2,5,1,2,'Booked','Tatkal'),(11,'A21','2023-07-03',36,2,4,1,2,'Booked','Tatkal'),(12,'A31','2023-07-03',36,2,5,1,4,'Booked','Tatkal'),(13,'SL1','2023-07-03',34,2,5,1,1,'Booked','Normal'),(14,'A11','2023-07-03',35,2,4,1,2,'Booked','Tatkal'),(15,'A21','2023-07-03',35,2,4,1,2,'Booked','Tatkal'),(16,'A21','2023-07-03',34,2,5,1,1,'Booked','Normal'),(17,'SL1','2023-07-03',33,2,4,1,1,'Booked','Normal'),(18,'SL1','2023-07-03',32,2,5,1,2,'Booked','Tatkal'),(19,'SL1','2023-07-04',36,9,5,1,1,'Booked','Tatkal'),(20,'SL1','2023-07-06',36,5,5,2,1,'Booked','Normal'),(21,'SL1','2023-08-20',36,1,5,1,3,'Cancelled','Normal'),(22,'A11','2023-08-22',36,9,5,1,3,'Booked','Normal'),(23,'A11','2023-08-22',35,9,5,1,3,'Cancelled','Normal'),(24,'A11','2023-10-31',36,9,5,1,1,'Cancelled','Normal'),(25,'A11','2024-01-29',36,2,5,1,5,'Cancelled','Normal');
/*!40000 ALTER TABLE `t_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dob` date NOT NULL,
  `email_id` varchar(320) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `latest_page` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b5q5wl2w4kiod1gyimirwmfse` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'2001-09-01','gowthamkallipudi@gmail.com','kallipudi','gowtham','profile','gowtham'),(2,'2001-09-01','gowthamkallipudi@gmail.com','kallipudi','gowtham2','profile','gowtham2'),(3,'2001-09-01','user@mail.com','user2','user','book-train','user1'),(4,'2001-09-01','gowthamkallipudi@gmail.com','kallipudi','gowtham1','book-train','gowtham1'),(5,'2001-09-01','gowtham@vuedata.in','kallipudi','gowtham','profile','hellogowtham');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_password`
--

DROP TABLE IF EXISTS `t_user_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_password` (
  `sno` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(20) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`sno`),
  UNIQUE KEY `UK_klqfkp1esndsq2lsolirq9by` (`id`),
  CONSTRAINT `FK6j19shavghkrcjmu34slbdjou` FOREIGN KEY (`id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_password`
--

LOCK TABLES `t_user_password` WRITE;
/*!40000 ALTER TABLE `t_user_password` DISABLE KEYS */;
INSERT INTO `t_user_password` VALUES (1,'gowtham',1),(2,'gowtham2',2),(3,'user',3),(4,'gowtham1',4),(5,'gowtham',5);
/*!40000 ALTER TABLE `t_user_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'railway'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-06 11:51:08
