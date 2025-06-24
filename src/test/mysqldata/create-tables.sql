drop database IF EXISTS `user_management`;

create DATABASE IF NOT EXISTS `user_management` DEFAULT CHARACTER SET utf8;

drop table IF EXISTS `user_management`.`company_info`;
create TABLE IF NOT EXISTS `user_management`.`company_info`
(
    `id`                int(11) unsigned NOT NULL AUTO_INCREMENT,
    `company_name`      varchar(255) NOT NULL,
    `address`           varchar(255) DEFAULT NULL,
    `city`              varchar(100) DEFAULT NULL,
    `state`             varchar(100) DEFAULT NULL,
    `country`           varchar(100) DEFAULT NULL,
    `postal_code`       varchar(20) DEFAULT NULL,
    `phone_number`      varchar(20) DEFAULT NULL,
    `website`           varchar(100) DEFAULT NULL,
    `created_at`        datetime DEFAULT CURRENT_TIMESTAMP,
    `updated_at`        datetime DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `company_name` (`company_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table IF EXISTS `user_management`.`contact_info`;
create TABLE IF NOT EXISTS `user_management`.`contact_info`
(
    `id`                int(11) unsigned NOT NULL AUTO_INCREMENT,
    `first_name`        varchar(100) NOT NULL,
    `last_name`         varchar(100) NOT NULL,
    `contact_type`      varchar(50) DEFAULT NULL,
    `email`             varchar(255) NOT NULL,
    `address`           varchar(255) DEFAULT NULL,
    `phone_number`      varchar(100) DEFAULT NULL,
    `city`              varchar(100) DEFAULT NULL,
    `state`             varchar(100) DEFAULT NULL,
    `postal_code`       varchar(20) DEFAULT NULL,
    `country`           varchar(20) DEFAULT NULL,
    `created_at`        datetime DEFAULT CURRENT_TIMESTAMP,
    `updated_at`        datetime DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table IF EXISTS `user_management`.`user_info`;
create TABLE IF NOT EXISTS `user_management`.`user_info`
(
    `id`                int(11) unsigned NOT NULL AUTO_INCREMENT,
    `username`          varchar(255) NOT NULL,
    `role`              varchar(50) DEFAULT NULL,
    `company_id`        int(11) unsigned NOT NULL,
    `contact_id`        int(11) unsigned NOT NULL,
    `created_at`        datetime DEFAULT CURRENT_TIMESTAMP,
    `updated_at`        datetime DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`company_id`) REFERENCES `company_info`(`id`) ON delete CASCADE,
    FOREIGN KEY (`contact_id`) REFERENCES `contact_info`(`id`) ON delete CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
