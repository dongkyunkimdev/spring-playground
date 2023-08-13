use db_playground;

CREATE TABLE `delivery`
(
    delivery_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    status      VARCHAR(255) NOT NULL,
    arrival_at  DATETIME,
    order_id    BIGINT       NOT NULL
);

CREATE TABLE `orders`
(
    order_id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    total_price DECIMAL(19, 2) NOT NULL,
    status      VARCHAR(255)   NOT NULL,
    consumer_id BIGINT         NOT NULL
);

CREATE TABLE `order_item`
(
    order_item_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(255)   NOT NULL,
    price         DECIMAL(19, 2) NOT NULL,
    count         BIGINT         NOT NULL,
    product_id    BIGINT         NOT NULL,
    order_id      BIGINT         NOT NULL,
    CONSTRAINT `fk_order_item_order_order_id` FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

CREATE TABLE `payment`
(
    payment_id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_payment_card_id BIGINT,
    order_id             BIGINT,
    CONSTRAINT `payment_order_order_id` FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

CREATE TABLE `product`
(
    product_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(255)   NOT NULL,
    stock      BIGINT         NOT NULL,
    price      DECIMAL(19, 2) NOT NULL
);

CREATE TABLE `product_category`
(
    product_category_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name                VARCHAR(255) NOT NULL,
    product_id          BIGINT,
    CONSTRAINT `product_category_product_product_id` FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE `user`
(
    user_id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255),
    nickname VARCHAR(255)
);

CREATE TABLE `user_payment_card`
(
    user_payment_card_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type                 VARCHAR(255),
    provider             VARCHAR(255),
    number               VARCHAR(255),
    user_id              BIGINT,
    CONSTRAINT `user_payment_card_user_user_id` FOREIGN KEY (user_id) REFERENCES user (user_id)
);
