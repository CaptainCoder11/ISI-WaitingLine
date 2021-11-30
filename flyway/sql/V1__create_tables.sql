DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` binary(16) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `password_hash` binary(64) DEFAULT NULL,
  `password_salt` binary(64) DEFAULT NULL,
  `date_added` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
);

INSERT INTO `user` VALUES ('RIc1IvmAiUu==','Jack','Jack123@gmail.com','0001112222',NULL,NULL,'2021-11-29 00:00:00'),('RIc5IvmAiUu==','Dwyane','Dwyane123@gmail.com','0111112222','dummy','dummy','2021-11-29 00:00:00'),('RIc8IvmAiUu==','Lily','Lily123@gmail.com','1111112222','dummy','dummy','2021-11-29 00:00:00'),('RIc9IvmAiUu==','Ted','Ted146@gmail.com','1112112222',NULL,NULL,'2021-11-29 00:00:00');

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `id` binary(16) NOT NULL,
  `owner_id` binary(16) NOT NULL,
  `name` varchar(100) NOT NULL,
  `logo` blob,
  `address` varchar(200) NOT NULL,
  `store_capacity` int NOT NULL,
  `waiting_capacity` int DEFAULT NULL,
  `category` enum('Food','Drugs','Liquor','Clothing','Others') NOT NULL,
  `opening_hour` time DEFAULT NULL,
  `closing_hour` time DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `store_user_owner_FK_idx` (`owner_id`),
  CONSTRAINT `store_user_owner_FK` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`)
);

INSERT INTO `store` VALUES ('JiWqILqMr/Q==','RIc5IvmAiUu==','BBQ Delight',NULL,'2000 Rue Saint Mathew, Montreal (QC), H3H2P5',50,20,'Food','08:00:00','20:00:00');

DROP TABLE IF EXISTS `store_user`;

CREATE TABLE `store_user` (
  `user_id` binary(16) NOT NULL,
  `store_id` binary(16) NOT NULL,
  `role` enum('Store_Owner','Customer','Employee') NOT NULL,
  PRIMARY KEY (`user_id`,`store_id`),
  KEY `store_storeuser_storeid_FK_idx` (`store_id`),
  CONSTRAINT `store_storeuser_storeid_FK` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `user_storeuser_userid_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

INSERT INTO `store_user` VALUES ('RIc1IvmAiUu==','JiWqILqMr/Q==','Customer'),('RIc5IvmAiUu==','JiWqILqMr/Q==','Store_Owner'),('RIc8IvmAiUu==','JiWqILqMr/Q==','Employee');

DROP TABLE IF EXISTS `appointment`;

CREATE TABLE `appointment` (
  `user_id` binary(16) NOT NULL,
  `store_id` binary(16) NOT NULL,
  `status` enum('In_Queue','In_Store','Cancelled','Rejected','Departed') NOT NULL,
  `appointment_number` int NOT NULL,
  `time_of_arrival` datetime DEFAULT NULL,
  `time_of_departure` datetime DEFAULT NULL,
  `date_created` datetime NOT NULL,
  PRIMARY KEY (`user_id`,`store_id`),
  KEY `appointment_store_storeid_FK_idx` (`store_id`),
  CONSTRAINT `appointment_store_storeid_FK` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `appointment_user_userid_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);