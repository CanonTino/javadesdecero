CREATE DATABASE  IF NOT EXISTS `banco` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `banco`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: banco
-- ------------------------------------------------------
-- Server version	5.7.31-log

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
-- Table structure for table `banco`
--

DROP TABLE IF EXISTS `banco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banco` (
  `clave` char(5) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banco`
--

LOCK TABLES `banco` WRITE;
/*!40000 ALTER TABLE `banco` DISABLE KEYS */;
INSERT INTO `banco` VALUES ('AZTEC','Banco Azteca',0),('BANOR','Banorte',1),('BBVAB','BBVA Bancomer',1),('INBUR','Inbursa',1);
/*!40000 ALTER TABLE `banco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `numero_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `clave_banco` char(5) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`numero_cliente`),
  KEY `cliente_banco_idx` (`clave_banco`),
  CONSTRAINT `cliente_banco` FOREIGN KEY (`clave_banco`) REFERENCES `banco` (`clave`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=256874119 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (114125630,'Juan','Perez','Sanchez','1980-05-09','BBVAB',1),(256874110,'Marta','Magana','Diaz','1979-06-22','BANOR',1),(256874117,'Luis Miguel','Olmedi','Smith','2020-09-01','INBUR',0),(256874118,'Pedro','Paramo','Llano','1986-11-24','INBUR',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `numero_cuenta` varchar(15) NOT NULL,
  `clave_tipo_cuenta` char(5) NOT NULL,
  `numero_cliente` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`numero_cuenta`),
  KEY `cuenta_tipo_cuenta_idx` (`clave_tipo_cuenta`),
  KEY `cuenta_cliente_idx` (`numero_cliente`),
  CONSTRAINT `cuenta_cliente` FOREIGN KEY (`numero_cliente`) REFERENCES `cliente` (`numero_cliente`) ON UPDATE NO ACTION,
  CONSTRAINT `cuenta_tipo_cuenta` FOREIGN KEY (`clave_tipo_cuenta`) REFERENCES `tipo_cuenta` (`clave`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cuenta_origen` varchar(15) DEFAULT NULL,
  `cuenta_destino` varchar(15) NOT NULL,
  `clave_tipo_movimiento` char(5) NOT NULL,
  `monto` decimal(9,4) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(45) NOT NULL DEFAULT 'Pendiente',
  PRIMARY KEY (`id`),
  KEY `movimiento_cuenta_origen_idx` (`cuenta_origen`),
  KEY `movimiento_cuenta_destino_idx` (`cuenta_destino`),
  KEY `movimiento_clave_tipo_movimiento_idx` (`clave_tipo_movimiento`),
  CONSTRAINT `movimiento_clave_tipo_movimiento` FOREIGN KEY (`clave_tipo_movimiento`) REFERENCES `tipo_movimiento` (`clave`) ON UPDATE NO ACTION,
  CONSTRAINT `movimiento_cuenta_destino` FOREIGN KEY (`cuenta_destino`) REFERENCES `cuenta` (`numero_cuenta`) ON UPDATE NO ACTION,
  CONSTRAINT `movimiento_cuenta_origen` FOREIGN KEY (`cuenta_origen`) REFERENCES `cuenta` (`numero_cuenta`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfil`
--

DROP TABLE IF EXISTS `perfil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfil` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfil`
--

LOCK TABLES `perfil` WRITE;
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` VALUES (1,'Administrador'),(2,'Cajero'),(3,'Secretaria');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_cuenta`
--

DROP TABLE IF EXISTS `tipo_cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_cuenta` (
  `clave` char(5) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cuenta`
--

LOCK TABLES `tipo_cuenta` WRITE;
/*!40000 ALTER TABLE `tipo_cuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_movimiento`
--

DROP TABLE IF EXISTS `tipo_movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_movimiento` (
  `clave` char(5) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_movimiento`
--

LOCK TABLES `tipo_movimiento` WRITE;
/*!40000 ALTER TABLE `tipo_movimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `usuario` varchar(15) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido_paterno` varchar(45) NOT NULL,
  `apellido_materno` varchar(45) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`usuario`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  KEY `usuario_perfil_idx` (`id_perfil`),
  CONSTRAINT `usuario_perfil` FOREIGN KEY (`id_perfil`) REFERENCES `perfil` (`id`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('ana','ana','Ana María','Antúnez','Díaz',2,1),('sandra','sandra','Sandra','Pérez','Camacho',3,1),('tino','tino','Florentino','Salazar','García',1,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-19  8:50:03
