drop database IF EXISTS `user_management`;

create DATABASE IF NOT EXISTS `user_management` DEFAULT CHARACTER SET utf8;

create TABLE IF NOT EXISTS `user_management`.`company_info`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT,
    `company_name`  varchar(255) NOT NULL,
    `address`       varchar(255) DEFAULT NULL,
    `city`          varchar(100) DEFAULT NULL,
    `state`         varchar(100) DEFAULT NULL,
    `country`       varchar(100) DEFAULT NULL,
    `postal_code`   varchar(20) DEFAULT NULL,
    `phone_number`  varchar(20) DEFAULT NULL,
    `created_at`    datetime DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    datetime DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `company_name` (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;