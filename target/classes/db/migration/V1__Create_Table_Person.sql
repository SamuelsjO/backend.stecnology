CREATE TABLE IF NOT EXISTS `person` (
  `id` int(20) NOT NULL,
  `first_name` varchar(80) NOT NULL,
  `gender` varchar(20) DEFAULT NULL,
  `last_name` varchar(80) NOT NULL
)