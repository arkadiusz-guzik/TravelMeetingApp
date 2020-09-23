DROP SCHEMA IF EXISTS `tripmeeting`;

CREATE SCHEMA `tripmeeting`;

use `tripmeeting`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `meetings`;

CREATE TABLE `meetings` (
  `meeting_id` int(9) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `date` varchar(10) default null,
  `description` varchar(200) default null,
  `time` int(3) default null,
  PRIMARY KEY (`meeting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `audiences`;

CREATE TABLE `audiences` (
  `audience_id` int(9) NOT NULL AUTO_INCREMENT,
  `hour` varchar(5) DEFAULT NULL,
  `price` double(7,2) default 0,
  `meeting_id` int(9) NOT NULL,
  PRIMARY KEY (`audience_id`),
  KEY `FK_audiences_meeting_id` (`meeting_id`),
  CONSTRAINT `FK_meeting_id_FK` FOREIGN KEY (`meeting_id`) 
  REFERENCES `meetings` (`meeting_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `seats`;

CREATE TABLE `seats` (
  `seat_id` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(3) DEFAULT NULL,
  `user_id` varchar(10) default '',
  `audience_id` int(9) NOT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `FK_seats_audience_id` (`audience_id`),
  CONSTRAINT `FK_audience_id_FK` FOREIGN KEY (`audience_id`) 
  REFERENCES `audiences` (`audience_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `user` (username,password,email)
VALUES 
('admin','$2y$12$/iXXeNKidBgRzCGw0XrvF.W/VfTj0NexuZ9IInNkILLglbM/mRnPO','admin@themMail.com'),
('user','$2y$12$QXWxGjaPGI2jSqTBtPacx.jiQmyO3xJ.k5jkxNuCB9T6li5OnELwq','user@themMail.com');

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `role` (name)
VALUES 
('ROLE_USER'),('ROLE_ADMIN');


DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 2),
(2, 1)
