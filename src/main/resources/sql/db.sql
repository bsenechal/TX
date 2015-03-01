CREATE DATABASE `projet_api01` /*!40100 DEFAULT CHARACTER SET latin1 */;

drop table `notes`;
drop table `questions`;
drop table `evaluation`;
drop table `book`;
drop table `user`;
drop table `role`;

CREATE TABLE `book` (
  `idBook` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `image` mediumblob,
  PRIMARY KEY (`idBook`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

CREATE TABLE `questions` (
  `idQuestions` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(45) NOT NULL,
  `valMax` int(11) NOT NULL DEFAULT '5',
  `ponderation` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idQuestions`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `role` (
  `idrole` int(11) NOT NULL,
  `libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(60) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telephone` int(11) NOT NULL,
  `creation_date` date DEFAULT NULL,
  `fkrole` int(11) NOT NULL,
  PRIMARY KEY (`idUser`),
  KEY `role_idx` (`fkrole`),
  CONSTRAINT `fkrole` FOREIGN KEY (`fkrole`) REFERENCES `role` (`idrole`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `evaluation` (
  `idEval` int(11) NOT NULL AUTO_INCREMENT,
  `Status` int(11) NOT NULL DEFAULT '0',
  `fkBook` int(11) NOT NULL,
  `fkUser` int(11) NOT NULL,
  PRIMARY KEY (`idEval`),
  KEY `fkBook_idx` (`fkBook`),
  KEY `fkUser_idx` (`fkUser`),
  CONSTRAINT `fkBook` FOREIGN KEY (`fkBook`) REFERENCES `book` (`idBook`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkUser` FOREIGN KEY (`fkUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `notes` (
  `idnotes` int(11) NOT NULL AUTO_INCREMENT,
  `fkEval` int(11) NOT NULL,
  `fkQuestion` int(11) NOT NULL,
  `note` int(11) NOT NULL,
  PRIMARY KEY (`idnotes`),
  KEY `fkEval_idx` (`fkEval`),
  KEY `fkQuestion_idx` (`fkQuestion`),
  CONSTRAINT `fkEval` FOREIGN KEY (`fkEval`) REFERENCES `evaluation` (`idEval`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkQuestion` FOREIGN KEY (`fkQuestion`) REFERENCES `questions` (`idQuestions`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

