/*Database creation :*/
CREATE DATABASE IF NOT EXISTS `tx`;
/*Select databse to use :*/
USE `tx`;

/*  
    *****************************************************
    *****************************************************
    ****                                             ****
    ****              USER MANAGEMENT                ****
    ****                                             ****
    *****************************************************
    *****************************************************
*/

/*
  ////////////////////
  AVATAR
  ////////////////////
*/
DROP TABLE IF EXISTS `avatar`;
CREATE TABLE `avatar` (
  `id_avatar` int NOT NULL AUTO_INCREMENT,
  `content` blob NOT NULL,
  PRIMARY KEY (`id_avatar`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


/*
--------------
INITIALIZATION
--------------
*/
LOCK TABLES `avatar` WRITE;
INSERT INTO `avatar` (`content`) VALUES (HEX('Test1')),(HEX('Test2'));
UNLOCK TABLES;

/*
  ////////////////////
  ROLE
  ////////////////////
*/
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id_role` int NOT NULL AUTO_INCREMENT,
  `label` varchar(45) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*
--------------
INITIALIZATION
--------------
*/
LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
UNLOCK TABLES;

/*
  ////////////////////
  USER
  ////////////////////
*/
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `deal_count` int DEFAULT '0',
  `comment_count` int DEFAULT '0',
  `deal_positive_rate` int DEFAULT '0',
  `deal_negative_rate` int DEFAULT '0',
  `comment_positive_rate` int DEFAULT '0',
  `comment_negative_rate` int DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `news_letter` int DEFAULT '0',
  `password` varchar(45) NOT NULL,
  `fk_avatar` int DEFAULT NULL,
  `fk_role` int NOT NULL,
  `enabled` int DEFAULT '1',
  PRIMARY KEY (`id_user`),
  KEY `fk_role_idx` (`fk_role`),
  KEY `fk_avatar_idx` (`fk_avatar`),
  CONSTRAINT `fk_avatar` FOREIGN KEY (`fk_avatar`) REFERENCES `avatar` (`id_avatar`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role` FOREIGN KEY (`fk_role`) REFERENCES `role` (`id_role`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
--------------
INITIALIZATION
--------------
*/
LOCK TABLES `user` WRITE;
INSERT INTO `user` 
(
  `first_name`,  `last_name`,  `login`,  `email`,  `phone_number`,
  `description`,  `creation_date`,  `deal_count`,  `comment_count`,  
  `deal_positive_rate`,  `deal_negative_rate`,  `comment_positive_rate`,  
  `comment_negative_rate`,  `longitude`,  `latitude`,  `news_letter`,  
  `password`,  `fk_avatar`,  `fk_role`,  `enabled`
)
VALUES 
('Benoît','Sénéchal','bsenecha','benoit.senechal1993@gmail.com',623478531,NULL,'2015-04-04',0,0,0,0,0,0,49.2915,2.19295,0,'e63f28f55ccaa429de081d5f58029a4d',NULL,1,1),
('Ulysse','Meyer','meyeruly','ulysse.meyer@gmail.com',623478531,'suis super !','2015-04-06',0,0,0,0,0,0,2.351828,48.856578,0,'09153bd1e6117f365232bcdb5c28a6e0',NULL,1,1);
UNLOCK TABLES;

/*  
    *****************************************************
    *****************************************************
    ****                                             ****
    ****              DEAL MANAGEMENT                ****
    ****                                             ****
    *****************************************************
    *****************************************************
*/

/*
  ////////////////////
  DEAL_PICTURE
  ////////////////////
*/
DROP TABLE IF EXISTS `deal_picture`;
CREATE TABLE `deal_picture` (
  `id_deal_picture` int NOT NULL AUTO_INCREMENT,
  `content` BLOB NOT NULL,  
  PRIMARY KEY (`id_deal_picture`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
--------------
INITIALIZATION
--------------
*/
LOCK TABLES `deal_picture` WRITE;
INSERT INTO `deal_picture` (`content`) VALUES (HEX('Image1:testhex')),(HEX('Image2:testhex'));
UNLOCK TABLES;

/*
  ////////////////////
  DEAL
  ////////////////////
*/
DROP TABLE IF EXISTS `deal`;
CREATE TABLE `deal` (
  `id_deal` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `initial_price` int DEFAULT NULL,
  `sale_price` int NOT NULL,
  `rate` float DEFAULT NULL,
  `creation_date` date NOT NULL,
  `expiration_date` date NOT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `fk_user` int NOT NULL,
  `fk_deal_picture` int DEFAULT NULL,
  PRIMARY KEY (`id_deal`),
  KEY `fk_user_idx` (`fk_user`),
  KEY `fk_deal_picture_idx` (`fk_deal_picture`),
  CONSTRAINT `fk_user` FOREIGN KEY (`fk_user`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_deal_picture` FOREIGN KEY (`fk_deal_picture`) REFERENCES `deal_picture` (`id_deal_picture`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
--------------
INITIALIZATION
--------------
*/
LOCK TABLES `deal` WRITE;
INSERT INTO `deal` 
(
  `title`, `description`, `initial_price`, `sale_price`,
  `rate`, `creation_date`, `expiration_date`,
  `longitude`, `latitude`, `fk_user`, `fk_deal_picture`
)
VALUES 
('Premier Deal','macbook pro retina !!!','1400','600',0,'2015-04-06',0,49.2915,2.19295,1,1),
('Deuxieme Deal','Alienware 15 !!!','1600','400',0,'2015-04-06',0,49.2915,2.19295,1,1);
UNLOCK TABLES;

/*
  ////////////////////
  DEAL_ARCHIVE
  ////////////////////
*/
DROP TABLE IF EXISTS `deal_archive`;
CREATE TABLE `deal_archive` (
  `id_deal` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `initial_price` int DEFAULT NULL,
  `sale_price` int NOT NULL,
  `rate` float DEFAULT NULL,
  `creation_date` date NOT NULL,
  `expiration_date` date NOT NULL,
  `longitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `fk_user_archive` int NOT NULL,
  `fk_deal_picture_archive` int DEFAULT NULL,
  PRIMARY KEY (`id_deal`),
  KEY `fk_user_archive_idx` (`fk_user_archive`),
  KEY `fk_deal_picture_archive_idx` (`fk_deal_picture_archive`),
  CONSTRAINT `fk_user_archive` FOREIGN KEY (`fk_user_archive`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_deal_picture_archive` FOREIGN KEY (`fk_deal_picture_archive`) REFERENCES `deal_picture` (`id_deal_picture`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
--------------
INITIALIZATION
--------------
*/
LOCK TABLES `deal_archive` WRITE;
INSERT INTO `deal_archive` 
(
  `id_deal`,
  `title`, `description`, `initial_price`, `sale_price`,
  `rate`, `creation_date`, `expiration_date`,
  `longitude`, `latitude`, `fk_user_archive`, `fk_deal_picture_archive`
)
VALUES 
(3,'Troisième Deal','x61 lenovo !!!','1400','600',0,'2015-04-06',0,49.2915,2.19295,1,1),
(4,'Quatrième Deal','Clevo barebone !!!','1600','400',0,'2015-04-06',0,49.2915,2.19295,1,1);
UNLOCK TABLES;

/*
  ////////////////////
  TAG
  ////////////////////
*/
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id_tag` int NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  PRIMARY KEY (`id_tag`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
--------------
INITIALIZATION
--------------
*/
LOCK TABLES `tag` WRITE;
INSERT INTO `tag` (`label`) VALUES ('sexy'),('coquine');
UNLOCK TABLES;

/*
  ////////////////////
  DEAL_TAG
  ////////////////////
*/
DROP TABLE IF EXISTS `deal_tag`;
CREATE TABLE `deal_tag` (
  `fk_deal` int NOT NULL,
  `fk_tag` int NOT NULL,
  PRIMARY KEY (`fk_deal`,`fk_tag`),
  KEY `fk_deal_idx` (`fk_deal`),
  KEY `fk_tag_idx` (`fk_tag`),
  CONSTRAINT `fk_deal` FOREIGN KEY (`fk_deal`) REFERENCES `deal` (`id_deal`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag` FOREIGN KEY (`fk_tag`) REFERENCES `tag` (`id_tag`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
--------------
INITIALIZATION
--------------
*/
LOCK TABLES `deal_tag` WRITE;
INSERT INTO `deal_tag` VALUES (1,1),(1,2),(2,1);
UNLOCK TABLES;

/*
  ////////////////////
  DEAL_MODIFICATION
  ////////////////////
*/
DROP TABLE IF EXISTS `deal_modification`;
CREATE TABLE `deal_modification` (
  `id_deal_modification` int NOT NULL AUTO_INCREMENT,
  `new_title` varchar(255) NOT NULL,
  `new_initial_price` int DEFAULT NULL,
  `new_sale_price` int NOT NULL,
  `new_expiration_date` date NOT NULL,
  `fk_user_modification` int NOT NULL,
  `fk_deal_modification` int NOT NULL,
  PRIMARY KEY (`id_deal_modification`),
  KEY `fk_user_modification_idx` (`fk_user_modification`),
  KEY `fk_deal_modification_idx` (`fk_deal_modification`),
  CONSTRAINT `fk_user_modification` FOREIGN KEY (`fk_user_modification`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_deal_modification` FOREIGN KEY (`fk_deal_modification`) REFERENCES `deal` (`id_deal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
--------------
INITIALIZATION
--------------
*/
LOCK TABLES `deal_modification` WRITE;
INSERT INTO `deal_modification` 
(
  `new_title`, `new_initial_price`, `new_sale_price`, `new_expiration_date`,
  `fk_user_modification`, `fk_deal_modification`
)
VALUES 
('Premier Offre','1200','600','2016-04-06',1,1),
('Deuxieme Zloup','1700','600','2015-08-06',2,2);
UNLOCK TABLES;

/*
  ////////////////////
  COMMENT
  ////////////////////
*/
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id_comment` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `rate` float DEFAULT NULL,
  `fk_user_comment` int NOT NULL,
  `fk_deal_comment` int NOT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `fk_user_comment_idx` (`fk_user_comment`),
  KEY `fk_deal_comment_idx` (`fk_deal_comment`),
  CONSTRAINT `fk_user_comment` FOREIGN KEY (`fk_user_comment`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_deal_comment` FOREIGN KEY (`fk_deal_comment`) REFERENCES `deal` (`id_deal`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*
--------------
INITIALIZATION
--------------
*/
LOCK TABLES `comment` WRITE;
INSERT INTO `comment` 
(
  `content`, `rate`,
  `fk_user_comment`, `fk_deal_comment`
)
VALUES 
('First !!',0,1,1),
('Pourquoi firster ?',0,2,1);
UNLOCK TABLES;

