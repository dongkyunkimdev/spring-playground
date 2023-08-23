DROP SCHEMA IF EXISTS `db_playground`;

CREATE
    DATABASE IF NOT EXISTS `db_playground` DEFAULT CHARACTER SET utf8mb4;

use db_playground;

CREATE TABLE `delivery`
(
    `delivery_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `status`      VARCHAR(255) NOT NULL,
    `arrival_at`  DATETIME,
    `order_id`    BIGINT       NOT NULL,
    `created_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `orders`
(
    `order_id`    BIGINT PRIMARY KEY AUTO_INCREMENT,
    `total_price` DECIMAL(19, 2) NOT NULL,
    `status`      VARCHAR(255)   NOT NULL,
    `consumer_id` BIGINT         NOT NULL,
    `created_at`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

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
    CONSTRAINT `fk_order_item_order_order_id` FOREIGN KEY (`order_id`) REFERENCES orders (`order_id`)
);

CREATE TABLE `payment`
(
    `payment_id`           BIGINT PRIMARY KEY AUTO_INCREMENT,
    `user_payment_card_id` BIGINT,
    `order_id`             BIGINT,
    `created_at`           datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`           datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `payment_order_order_id` FOREIGN KEY (`order_id`) REFERENCES orders (`order_id`)
);

CREATE TABLE `product_category`
(
    `product_category_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`                VARCHAR(255) NOT NULL,
    `created_at`          datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`          datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `product`
(
    `product_id`          BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`                VARCHAR(255)   NOT NULL,
    `stock`               BIGINT         NOT NULL,
    `price`               DECIMAL(19, 2) NOT NULL,
    `product_category_id` BIGINT,
    `created_at`          datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`          datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `product_product_category_product_category_id` FOREIGN KEY (`product_category_id`) REFERENCES product_category (`product_category_id`)
);

CREATE TABLE `user`
(
    `user_id`    BIGINT PRIMARY KEY AUTO_INCREMENT,
    `username`   VARCHAR(255),
    `password`   VARCHAR(255),
    `nickname`   VARCHAR(255),
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `user_payment_card`
(
    `user_payment_card_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `type`                 VARCHAR(255),
    `provider`             VARCHAR(255),
    `number`               VARCHAR(255),
    `user_id`              BIGINT,
    `created_at`           datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`           datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT `user_payment_card_user_user_id` FOREIGN KEY (`user_id`) REFERENCES user (`user_id`)
);
