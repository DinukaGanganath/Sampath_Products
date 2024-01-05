-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: sampathproducts
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `material_details`
--

DROP TABLE IF EXISTS `material_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_details` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `material_name` varchar(45) NOT NULL,
  `material_code` varchar(12) NOT NULL,
  `material_added_date` datetime DEFAULT NULL,
  `material_updated_date` datetime DEFAULT NULL,
  `material_deleted_date` datetime DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  UNIQUE KEY `material_id_UNIQUE` (`material_id`),
  UNIQUE KEY `material_code_UNIQUE` (`material_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_details`
--

LOCK TABLES `material_details` WRITE;
/*!40000 ALTER TABLE `material_details` DISABLE KEYS */;
INSERT INTO `material_details` VALUES (1,'Raw Bread','mat/001','2024-01-01 20:33:00',NULL,NULL),(2,'Dry Bread','mat/002','2024-01-01 20:34:00',NULL,NULL),(3,'Bun','mat/003','2024-01-01 20:35:12',NULL,NULL);
/*!40000 ALTER TABLE `material_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_details`
--

DROP TABLE IF EXISTS `product_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_details` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) NOT NULL,
  `product_code` varchar(8) NOT NULL,
  `product_usable_time` int DEFAULT NULL,
  `product_unit_price` decimal(6,2) NOT NULL,
  `product_unit_quantity` varchar(45) NOT NULL,
  `product_created_date` datetime DEFAULT NULL,
  `product_updated_date` datetime DEFAULT NULL,
  `Product_deleted_date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`),
  UNIQUE KEY `product_code_UNIQUE` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_details`
--

LOCK TABLES `product_details` WRITE;
/*!40000 ALTER TABLE `product_details` DISABLE KEYS */;
INSERT INTO `product_details` VALUES (1,'Biscuit Powder','prod/001',4,150.00,'50',NULL,NULL,NULL),(2,'Biscuit Powder','prod/002',4,250.00,'100',NULL,NULL,NULL),(3,'Biscuit Powder','prod/003',4,500.00,'250',NULL,NULL,NULL),(4,'Biscuit Powder','prod/004',4,800.00,'500',NULL,NULL,NULL),(5,'Biscuit Powder','prod/005',4,1000.00,'1000',NULL,NULL,NULL);
/*!40000 ALTER TABLE `product_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_details`
--

DROP TABLE IF EXISTS `supplier_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier_details` (
  `supplier_id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(45) NOT NULL,
  `supplier_code` varchar(7) NOT NULL,
  `supplier_address_line1` varchar(45) NOT NULL,
  `supplier_address_line2` varchar(45) DEFAULT NULL,
  `supplier_address_city` varchar(45) DEFAULT NULL,
  `supplier_address_postal` varchar(45) DEFAULT NULL,
  `supplier_business_name` varchar(45) DEFAULT NULL,
  `supplier_contact_no1` varchar(45) NOT NULL,
  `supplier_contact_no2` varchar(45) DEFAULT NULL,
  `supplier_email` varchar(45) NOT NULL,
  `created_date_time` datetime DEFAULT NULL,
  `updated_date_time` datetime DEFAULT NULL,
  `deleted_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`supplier_id`),
  UNIQUE KEY `supplier_id_UNIQUE` (`supplier_id`),
  UNIQUE KEY `supplier_code_UNIQUE` (`supplier_code`),
  UNIQUE KEY `supplier_email_UNIQUE` (`supplier_email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_details`
--

LOCK TABLES `supplier_details` WRITE;
/*!40000 ALTER TABLE `supplier_details` DISABLE KEYS */;
INSERT INTO `supplier_details` VALUES (1,'Wasantha','sup/001','No.50, Samagi Mawatha','Narangodapaluwa','Batuwatta','11012','Wasantha Bakers','0714526789',NULL,'wasantha@gmail.com',NULL,NULL,NULL),(2,'Gamini','sup/002','No. 17',NULL,'Walpola','11010','Gamini Bakery','0778870890',NULL,'gam@gmail.com',NULL,NULL,NULL),(3,'Ravindu','sup/003','No. 42/B','Madagoda','Batuwatta','11012','Ravindu Bakers','0718579674',NULL,'ravindu@gmail.com',NULL,NULL,NULL);
/*!40000 ALTER TABLE `supplier_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-05  9:06:17
