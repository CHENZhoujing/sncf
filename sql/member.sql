DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
                          `id` BIGINT NOT NULL COMMENT 'ID',
                          `mobile` VARCHAR(11) COMMENT 'Mobile Number',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `mobile_unique` (`mobile`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
    COMMENT='Member';
