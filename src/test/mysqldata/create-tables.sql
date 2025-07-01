drop database IF EXISTS `user_management`;

create DATABASE IF NOT EXISTS `user_management` DEFAULT CHARACTER SET utf8;

drop table IF EXISTS `user_management`.`users`;
create table IF NOT EXISTS `user_management`.`users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `user_role` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;