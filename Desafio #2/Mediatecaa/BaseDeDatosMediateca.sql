-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: mediateca
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `cd_audio`
--

DROP TABLE IF EXISTS `cd_audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cd_audio` (
  `codigo` varchar(10) NOT NULL,
  `artista` varchar(100) DEFAULT NULL,
  `genero` varchar(50) DEFAULT NULL,
  `duracion` time DEFAULT NULL,
  `num_canciones` int DEFAULT NULL,
  `unidades_disponibles` int DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `cd_audio_ibfk_1` FOREIGN KEY (`codigo`) REFERENCES `material` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cd_audio`
--

LOCK TABLES `cd_audio` WRITE;
/*!40000 ALTER TABLE `cd_audio` DISABLE KEYS */;
/*!40000 ALTER TABLE `cd_audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dvd`
--

DROP TABLE IF EXISTS `dvd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dvd` (
  `codigo` varchar(10) NOT NULL,
  `director` varchar(100) DEFAULT NULL,
  `duracion` time DEFAULT NULL,
  `genero` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `dvd_ibfk_1` FOREIGN KEY (`codigo`) REFERENCES `material` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dvd`
--

LOCK TABLES `dvd` WRITE;
/*!40000 ALTER TABLE `dvd` DISABLE KEYS */;
INSERT INTO `dvd` VALUES ('45898','Lana Wachoski','01:30:00','Terror');
/*!40000 ALTER TABLE `dvd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `codigo` varchar(10) NOT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `num_paginas` int DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `anio_publicacion` int DEFAULT NULL,
  `unidades_disponibles` int DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`codigo`) REFERENCES `material` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `codigo` varchar(10) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `tipo` enum('LIBRO','REVISTA','CD_AUDIO','DVD') NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES ('002545','NatGeo','REVISTA'),('45898','Matrix','DVD');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revista`
--

DROP TABLE IF EXISTS `revista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revista` (
  `codigo` varchar(10) NOT NULL,
  `editorial_revista` varchar(100) DEFAULT NULL,
  `periodicidad` varchar(50) DEFAULT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `unidades_disponibles` int DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  CONSTRAINT `revista_ibfk_1` FOREIGN KEY (`codigo`) REFERENCES `material` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revista`
--

LOCK TABLES `revista` WRITE;
/*!40000 ALTER TABLE `revista` DISABLE KEYS */;
INSERT INTO `revista` VALUES ('002545','Planeta','Mensual','2023-05-12',15);
/*!40000 ALTER TABLE `revista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vista_materiales_completos`
--

DROP TABLE IF EXISTS `vista_materiales_completos`;
/*!50001 DROP VIEW IF EXISTS `vista_materiales_completos`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vista_materiales_completos` AS SELECT 
 1 AS `codigo`,
 1 AS `titulo`,
 1 AS `tipo`,
 1 AS `autor`,
 1 AS `num_paginas`,
 1 AS `editorial_libro`,
 1 AS `isbn`,
 1 AS `anio_publicacion`,
 1 AS `unidades_libro`,
 1 AS `editorial_revista`,
 1 AS `periodicidad`,
 1 AS `fecha_publicacion`,
 1 AS `unidades_revista`,
 1 AS `artista`,
 1 AS `genero_cd`,
 1 AS `duracion_cd`,
 1 AS `num_canciones`,
 1 AS `unidades_cd`,
 1 AS `director`,
 1 AS `duracion_dvd`,
 1 AS `genero_dvd`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `vista_materiales_completos`
--

/*!50001 DROP VIEW IF EXISTS `vista_materiales_completos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_materiales_completos` AS select `m`.`codigo` AS `codigo`,`m`.`titulo` AS `titulo`,`m`.`tipo` AS `tipo`,`l`.`autor` AS `autor`,`l`.`num_paginas` AS `num_paginas`,`l`.`editorial` AS `editorial_libro`,`l`.`isbn` AS `isbn`,`l`.`anio_publicacion` AS `anio_publicacion`,`l`.`unidades_disponibles` AS `unidades_libro`,`r`.`editorial_revista` AS `editorial_revista`,`r`.`periodicidad` AS `periodicidad`,`r`.`fecha_publicacion` AS `fecha_publicacion`,`r`.`unidades_disponibles` AS `unidades_revista`,`c`.`artista` AS `artista`,`c`.`genero` AS `genero_cd`,`c`.`duracion` AS `duracion_cd`,`c`.`num_canciones` AS `num_canciones`,`c`.`unidades_disponibles` AS `unidades_cd`,`d`.`director` AS `director`,`d`.`duracion` AS `duracion_dvd`,`d`.`genero` AS `genero_dvd` from ((((`material` `m` left join `libro` `l` on((`m`.`codigo` = `l`.`codigo`))) left join `revista` `r` on((`m`.`codigo` = `r`.`codigo`))) left join `cd_audio` `c` on((`m`.`codigo` = `c`.`codigo`))) left join `dvd` `d` on((`m`.`codigo` = `d`.`codigo`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-20 21:50:39
