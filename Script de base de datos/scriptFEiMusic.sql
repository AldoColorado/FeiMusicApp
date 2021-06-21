-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: feimusic
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album` (
  `idAlbum` bigint NOT NULL AUTO_INCREMENT,
  `nombreAlbum` varchar(50) NOT NULL,
  `anioLanzamiento` datetime DEFAULT NULL,
  `imagenAlbum` blob,
  `idArtista` bigint DEFAULT NULL,
  PRIMARY KEY (`idAlbum`),
  KEY `idArtista` (`idArtista`),
  CONSTRAINT `album_ibfk_1` FOREIGN KEY (`idArtista`) REFERENCES `artista` (`idArtista`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `albumlike`
--

DROP TABLE IF EXISTS `albumlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `albumlike` (
  `idAlbum` bigint NOT NULL,
  `idConsumidor` bigint NOT NULL,
  PRIMARY KEY (`idAlbum`,`idConsumidor`),
  UNIQUE KEY `AlbumLike_idConsumidor_idAlbum_unique` (`idAlbum`,`idConsumidor`),
  KEY `idConsumidor` (`idConsumidor`),
  CONSTRAINT `albumlike_ibfk_1` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `albumlike_ibfk_2` FOREIGN KEY (`idConsumidor`) REFERENCES `consumidor` (`idConsumidor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `artista`
--

DROP TABLE IF EXISTS `artista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artista` (
  `idArtista` bigint NOT NULL AUTO_INCREMENT,
  `nombreArtista` varchar(50) NOT NULL,
  `imagenArtista` longblob,
  `descripcion` varchar(255) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `idGenero` bigint DEFAULT NULL,
  PRIMARY KEY (`idArtista`),
  KEY `username` (`username`),
  KEY `idGenero` (`idGenero`),
  CONSTRAINT `artista_ibfk_1` FOREIGN KEY (`username`) REFERENCES `cuenta` (`username`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `artista_ibfk_2` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `artistlike`
--

DROP TABLE IF EXISTS `artistlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artistlike` (
  `idArtista` bigint NOT NULL,
  `idConsumidor` bigint NOT NULL,
  PRIMARY KEY (`idArtista`,`idConsumidor`),
  UNIQUE KEY `ArtistLike_idConsumidor_idArtista_unique` (`idArtista`,`idConsumidor`),
  KEY `idConsumidor` (`idConsumidor`),
  CONSTRAINT `artistlike_ibfk_1` FOREIGN KEY (`idArtista`) REFERENCES `artista` (`idArtista`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `artistlike_ibfk_2` FOREIGN KEY (`idConsumidor`) REFERENCES `consumidor` (`idConsumidor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cancion`
--

DROP TABLE IF EXISTS `cancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cancion` (
  `idCancion` bigint NOT NULL AUTO_INCREMENT,
  `nombreCancion` varchar(50) NOT NULL,
  `letra` longblob,
  `imagenCancion` longblob,
  `track` longblob,
  `idAlbum` bigint DEFAULT NULL,
  `idGenero` bigint DEFAULT NULL,
  PRIMARY KEY (`idCancion`),
  KEY `idAlbum` (`idAlbum`),
  KEY `idGenero` (`idGenero`),
  CONSTRAINT `cancion_ibfk_1` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `cancion_ibfk_2` FOREIGN KEY (`idGenero`) REFERENCES `genero` (`idGenero`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `consumidor`
--

DROP TABLE IF EXISTS `consumidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumidor` (
  `idConsumidor` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idConsumidor`),
  KEY `username` (`username`),
  CONSTRAINT `consumidor_ibfk_1` FOREIGN KEY (`username`) REFERENCES `cuenta` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `username` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `tipoUsuario` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `idGenero` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`idGenero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `listacancion`
--

DROP TABLE IF EXISTS `listacancion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listacancion` (
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  `idCancion` bigint NOT NULL,
  `idListaReproduccion` bigint NOT NULL,
  PRIMARY KEY (`idCancion`,`idListaReproduccion`),
  KEY `idListaReproduccion` (`idListaReproduccion`),
  CONSTRAINT `listacancion_ibfk_1` FOREIGN KEY (`idCancion`) REFERENCES `cancion` (`idCancion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `listacancion_ibfk_2` FOREIGN KEY (`idListaReproduccion`) REFERENCES `listareproduccion` (`idListaReproduccion`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `listareproduccion`
--

DROP TABLE IF EXISTS `listareproduccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listareproduccion` (
  `idListaReproduccion` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `idConsumidor` bigint DEFAULT NULL,
  PRIMARY KEY (`idListaReproduccion`),
  KEY `idConsumidor` (`idConsumidor`),
  CONSTRAINT `listareproduccion_ibfk_1` FOREIGN KEY (`idConsumidor`) REFERENCES `consumidor` (`idConsumidor`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `songlike`
--

DROP TABLE IF EXISTS `songlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `songlike` (
  `idCancion` bigint NOT NULL,
  `idConsumidor` bigint NOT NULL,
  PRIMARY KEY (`idCancion`,`idConsumidor`),
  UNIQUE KEY `SongLike_idConsumidor_idCancion_unique` (`idCancion`,`idConsumidor`),
  KEY `idConsumidor` (`idConsumidor`),
  CONSTRAINT `songlike_ibfk_1` FOREIGN KEY (`idCancion`) REFERENCES `cancion` (`idCancion`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `songlike_ibfk_2` FOREIGN KEY (`idConsumidor`) REFERENCES `consumidor` (`idConsumidor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `suscripcion`
--

DROP TABLE IF EXISTS `suscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suscripcion` (
  `idSuscripcion` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL,
  `fechaInicio` datetime DEFAULT NULL,
  `fechaFin` datetime DEFAULT NULL,
  `idConsumidor` bigint DEFAULT NULL,
  PRIMARY KEY (`idSuscripcion`),
  KEY `idConsumidor` (`idConsumidor`),
  CONSTRAINT `suscripcion_ibfk_1` FOREIGN KEY (`idConsumidor`) REFERENCES `consumidor` (`idConsumidor`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-21 11:22:11
