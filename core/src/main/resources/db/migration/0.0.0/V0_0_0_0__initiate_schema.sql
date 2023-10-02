# db_user
DROP SCHEMA IF EXISTS `db_user`;
CREATE DATABASE IF NOT EXISTS `db_user` DEFAULT CHARACTER SET utf8mb4;
use db_user;

CREATE TABLE `user`
(
    `user_id`       BIGINT PRIMARY KEY AUTO_INCREMENT,
    `username`      VARCHAR(255) NOT NULL,
    `password`      VARCHAR(255) NOT NULL,
    `nickname`      VARCHAR(255) NOT NULL,
    `status`        VARCHAR(255) NOT NULL,
    `role`          VARCHAR(255) NOT NULL,
    `last_login_at` datetime     NOT NULL,
    `created_at`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY `uix_username` (`username`),
    UNIQUE KEY `uix_nickname` (`nickname`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `user_payment_card`
(
    `user_payment_card_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `type`                 VARCHAR(255) NOT NULL,
    `provider`             VARCHAR(255) NOT NULL,
    `number`               VARCHAR(255) NOT NULL,
    `user_id`              BIGINT       NOT NULL,
    `created_at`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`           datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_user_payment_card_user_user_id`
        FOREIGN KEY (`user_id`) REFERENCES user (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

# db_product
DROP SCHEMA IF EXISTS `db_product`;
CREATE DATABASE IF NOT EXISTS `db_product` DEFAULT CHARACTER SET utf8mb4;
use db_product;

CREATE TABLE `product_category`
(
    `product_category_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`                VARCHAR(255) NOT NULL,
    `created_at`          datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`          datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY `uix_name` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `product`
(
    `product_id`          BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`                VARCHAR(255)   NOT NULL,
    `stock`               BIGINT         NOT NULL,
    `price`               DECIMAL(19, 2) NOT NULL,
    `product_category_id` BIGINT         NOT NULL,
    `created_at`          datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`          datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_product_product_category_product_category_id`
        FOREIGN KEY (`product_category_id`) REFERENCES product_category (`product_category_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

# db_order
DROP SCHEMA IF EXISTS `db_order`;
CREATE DATABASE IF NOT EXISTS `db_order` DEFAULT CHARACTER SET utf8mb4;
use db_order;

CREATE TABLE `orders`
(
    `order_id`    BIGINT PRIMARY KEY AUTO_INCREMENT,
    `total_price` DECIMAL(19, 2) NOT NULL,
    `status`      VARCHAR(255)   NOT NULL,
    `consumer_id` BIGINT         NOT NULL,
    `created_at`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `order_item`
(
    `order_item_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`          VARCHAR(255)   NOT NULL,
    `price`         DECIMAL(19, 2) NOT NULL,
    `count`         BIGINT         NOT NULL,
    `product_id`    BIGINT         NOT NULL,
    `order_id`      BIGINT         NOT NULL,
    `created_at`    datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_order_item_order_order_id`
        FOREIGN KEY (`order_id`) REFERENCES orders (`order_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE `payment`
(
    `payment_id`           BIGINT PRIMARY KEY AUTO_INCREMENT,
    `user_payment_card_id` BIGINT   NOT NULL,
    `order_id`             BIGINT   NOT NULL,
    `created_at`           datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`           datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `fk_payment_order_order_id`
        FOREIGN KEY (`order_id`) REFERENCES orders (`order_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

# db_delivery
DROP SCHEMA IF EXISTS `db_delivery`;
CREATE DATABASE IF NOT EXISTS `db_delivery` DEFAULT CHARACTER SET utf8mb4;
use db_delivery;

CREATE TABLE `delivery`
(
    `delivery_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `status`      VARCHAR(255) NOT NULL,
    `arrival_at`  DATETIME,
    `order_id`    BIGINT       NOT NULL,
    `created_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
