-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bddenchere
-- ------------------------------------------------------
-- Server version	5.1.30-community

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
-- Table structure for table `adresses`
--

Create database bddenchere;

use bddenchere;

DROP TABLE IF EXISTS `adresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adresses` (
  `idadresse` int(11) NOT NULL AUTO_INCREMENT,
  `num_rue` int(11) DEFAULT NULL,
  `nom_rue` varchar(45) DEFAULT NULL,
  `ville` varchar(45) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  PRIMARY KEY (`idadresse`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresses`
--

LOCK TABLES `adresses` WRITE;
/*!40000 ALTER TABLE `adresses` DISABLE KEYS */;
INSERT INTO `adresses` VALUES (24,NULL,'Callot',NULL,NULL),(25,NULL,NULL,NULL,NULL),(26,6,'Callot','Nancy',54000),(27,15,'general de gaulle','Paris',93000),(28,NULL,'Callot',NULL,NULL),(29,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `adresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_categ`
--

DROP TABLE IF EXISTS `article_categ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_categ` (
  `id_article` int(11) NOT NULL,
  `id_categ` int(11) NOT NULL,
  PRIMARY KEY (`id_article`,`id_categ`),
  KEY `fk_catgorie_idcateg_idx` (`id_categ`),
  CONSTRAINT `fk_article_idarticle` FOREIGN KEY (`id_article`) REFERENCES `articles` (`idarticle`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_catgorie_idcateg` FOREIGN KEY (`id_categ`) REFERENCES `categorie` (`idcategorie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_categ`
--

LOCK TABLES `article_categ` WRITE;
/*!40000 ALTER TABLE `article_categ` DISABLE KEYS */;
INSERT INTO `article_categ` VALUES (2,1),(22,1),(23,1),(28,2),(24,3),(25,3),(27,3),(26,4);
/*!40000 ALTER TABLE `article_categ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articles` (
  `idarticle` int(11) NOT NULL AUTO_INCREMENT,
  `nom_article` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `prix_depart` double DEFAULT NULL,
  `date_limite` datetime DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idarticle`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articles`
--

LOCK TABLES `articles` WRITE;
/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
INSERT INTO `articles` VALUES (2,'Renault Megane','Bonne etat !',6500,'2018-02-02 02:01:01','/resources/img/test.png'),(15,'Maison Nancy','Maison',1000000,'2018-02-01 12:10:10',NULL),(16,'Xbox One','Bonne Etat',150,'2018-02-01 12:10:10','/resources/img/test.png'),(18,'Xbox 360','Bonne etat',20,'2018-01-30 12:10:10',NULL),(20,'Maison','Maison Nancy',200000,'2018-01-30 11:30:00',NULL),(21,'Ordinateur Pro','Bonne Etat',400,'2017-10-10 12:10:10',NULL),(22,'Ford Fiesta','Bonne etat !',5500,'2018-02-01 18:00:00','/resources/img/test.png'),(23,'Peugeot','--',3000,'2018-01-31 03:01:00',NULL),(24,'DELL XPS','Modele 2017',550,'2018-01-31 04:09:00',NULL),(25,'Café','!!',20,'2018-01-31 10:36:50',NULL),(26,'Café 2','!!',30,'2018-01-31 11:41:00',NULL),(27,'PC 4','!!',100,'2018-01-31 10:44:00',NULL),(28,'Test','',300,'2018-01-31 11:10:00',NULL);
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorie` (
  `idcategorie` int(11) NOT NULL AUTO_INCREMENT,
  `nom_cat` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` VALUES (1,'Vehicules'),(2,'Maison'),(3,'Multimedia'),(4,'Materiel Pro');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commande` (
  `idcommande` int(11) NOT NULL AUTO_INCREMENT,
  `id_adresse` int(11) DEFAULT NULL,
  `id_facturation` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcommande`),
  KEY `fk_adresse_idadresse_idx` (`id_adresse`),
  KEY `fk_facturation_idfacturation_idx` (`id_facturation`),
  CONSTRAINT `fk2_adresse_idadresse` FOREIGN KEY (`id_adresse`) REFERENCES `adresses` (`idadresse`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk2_facturation_idfacturation` FOREIGN KEY (`id_facturation`) REFERENCES `facturation` (`idfacturation`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande`
--

LOCK TABLES `commande` WRITE;
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
INSERT INTO `commande` VALUES (21,26,13);
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturation`
--

DROP TABLE IF EXISTS `facturation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facturation` (
  `idfacturation` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `num_cb` int(11) DEFAULT NULL,
  `date_exp` date DEFAULT NULL,
  PRIMARY KEY (`idfacturation`),
  KEY `fk_user_iduser_idx` (`id_user`),
  CONSTRAINT `fk_user_iduser` FOREIGN KEY (`id_user`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturation`
--

LOCK TABLES `facturation` WRITE;
/*!40000 ALTER TABLE `facturation` DISABLE KEYS */;
INSERT INTO `facturation` VALUES (13,14,2147483647,NULL);
/*!40000 ALTER TABLE `facturation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `panier`
--

DROP TABLE IF EXISTS `panier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `panier` (
  `idparti` int(11) NOT NULL,
  `idcommande` int(11) NOT NULL,
  PRIMARY KEY (`idparti`,`idcommande`),
  KEY `fk_commande_idcommande_idx` (`idcommande`),
  CONSTRAINT `fk_commande_idcommande` FOREIGN KEY (`idcommande`) REFERENCES `commande` (`idcommande`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_part_paticipation` FOREIGN KEY (`idparti`) REFERENCES `participe_ench` (`idpartEnch`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `panier`
--

LOCK TABLES `panier` WRITE;
/*!40000 ALTER TABLE `panier` DISABLE KEYS */;
INSERT INTO `panier` VALUES (14,21),(15,21);
/*!40000 ALTER TABLE `panier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participe_ench`
--

DROP TABLE IF EXISTS `participe_ench`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participe_ench` (
  `idpartEnch` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_article` int(11) DEFAULT NULL,
  `prix_prop` double DEFAULT NULL,
  `etat_particip` tinyint(4) DEFAULT NULL,
  `etat_achat` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idpartEnch`),
  KEY `fk_user_idusers_idx` (`id_user`),
  KEY `fk_article_idx` (`id_article`),
  CONSTRAINT `fk_article` FOREIGN KEY (`id_article`) REFERENCES `articles` (`idarticle`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_idusers` FOREIGN KEY (`id_user`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participe_ench`
--

LOCK TABLES `participe_ench` WRITE;
/*!40000 ALTER TABLE `participe_ench` DISABLE KEYS */;
INSERT INTO `participe_ench` VALUES (13,14,16,160,0,0),(14,14,18,30,1,2),(15,14,23,3100,1,2),(16,14,26,400,1,0),(17,13,27,110,1,0),(18,14,26,300,0,0),(19,14,26,200,1,0),(20,14,26,200,1,0),(21,13,28,200,1,0),(22,13,28,100,0,0);
/*!40000 ALTER TABLE `participe_ench` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_adresse`
--

DROP TABLE IF EXISTS `user_adresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_adresse` (
  `id_user` int(11) NOT NULL,
  `id_adr` int(11) NOT NULL,
  `default_adr` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_user`,`id_adr`),
  KEY `fk_adresse_idadresse_idx` (`id_adr`),
  CONSTRAINT `fk_adresse_idadresse` FOREIGN KEY (`id_adr`) REFERENCES `adresses` (`idadresse`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_iduser` FOREIGN KEY (`id_user`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_adresse`
--

LOCK TABLES `user_adresse` WRITE;
/*!40000 ALTER TABLE `user_adresse` DISABLE KEYS */;
INSERT INTO `user_adresse` VALUES (13,24,1),(13,25,0),(14,26,1),(14,27,0),(15,28,1),(15,29,0);
/*!40000 ALTER TABLE `user_adresse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_article`
--

DROP TABLE IF EXISTS `user_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_article` (
  `id_user` int(11) NOT NULL,
  `id_article` int(11) NOT NULL,
  `date_create` date DEFAULT NULL,
  PRIMARY KEY (`id_user`,`id_article`),
  KEY `fk_article_idarticle_idx` (`id_article`),
  CONSTRAINT `fk2_article_idarticle` FOREIGN KEY (`id_article`) REFERENCES `articles` (`idarticle`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_article`
--

LOCK TABLES `user_article` WRITE;
/*!40000 ALTER TABLE `user_article` DISABLE KEYS */;
INSERT INTO `user_article` VALUES (13,23,'2018-01-31'),(13,24,'2018-01-31'),(13,25,'2018-01-31'),(13,26,'2018-01-31'),(14,20,'2018-01-30'),(14,21,'2018-01-30'),(14,22,'2018-01-31'),(14,27,'2018-01-31'),(14,28,'2018-01-31'),(15,2,'2018-02-02'),(15,15,'2018-02-02'),(15,16,'2018-02-02'),(15,18,'2018-02-02');
/*!40000 ALTER TABLE `user_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `idusers` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) DEFAULT NULL,
  `prenom` varchar(45) DEFAULT NULL,
  `email` varchar(145) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `login` varchar(45) DEFAULT NULL,
  `mdp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idusers`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (13,'Amiroune','Mohamed','sd','qsdsq','candyou1','test'),(14,'Amiroune','Mohamed','qsd','qsd','candyou','test'),(15,'Amiroune','Mohamed','qsd','qsd','candyou3','test'),(16,'Amiroune','Mohamed','qsd','qsd','candyou5','test');
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

-- Dump completed on 2018-01-31 14:38:11
