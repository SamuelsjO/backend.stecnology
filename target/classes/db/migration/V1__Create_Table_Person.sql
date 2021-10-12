CREATE TABLE IF NOT EXISTS `person` (
  `id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `first_name` varchar(80) NOT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `last_name` varchar(80) NOT NULL
)