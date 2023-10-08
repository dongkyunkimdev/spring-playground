use db_product;

INSERT INTO `product_category`(product_category_id, name)
VALUES (1, 'Clothing'),
       (2, 'Shoes'),
       (3, 'Bags'),
       (4, 'Accessories'),
       (5, 'Electronics'),
       (6, 'Home Goods'),
       (7, 'Food'),
       (8, 'Sports Equipment'),
       (9, 'Cosmetics'),
       (10, 'Furniture'),
       (11, 'Watches'),
       (12, 'Electronics Accessories'),
       (13, 'Toys'),
       (14, 'Stationery'),
       (15, 'Kitchenware'),
       (16, 'Books'),
       (17, 'Health & Wellness'),
       (18, 'Garden & Outdoor'),
       (19, 'Pet Supplies'),
       (20, 'Jewelry'),
       (21, 'Home Decor'),
       (22, 'Art Supplies'),
       (23, 'Baby & Kids'),
       (24, 'Music & Instruments'),
       (25, 'Automotive'),
       (26, 'Travel Accessories'),
       (27, 'Fitness Gear'),
       (28, 'Party Supplies'),
       (29, 'Tech Gadgets')
;

INSERT INTO `product`(product_id, name, stock, price, product_category_id)
VALUES (1, 'T-shirt', 100, 19.99, 1),
       (2, 'Jeans', 50, 39.99, 1),
       (3, 'Running Shoes', 75, 79.99, 2),
       (4, 'Backpack', 30, 49.99, 3),
       (5, 'Watch', 20, 199.99, 4),
       (6, 'Laptop', 10, 999.99, 5),
       (7, 'Couch', 5, 499.99, 6),
       (8, 'Chocolate', 200, 2.99, 7),
       (9, 'Soccer Ball', 25, 14.99, 8),
       (10, 'Lipstick', 50, 9.99, 9),
       (12, 'Hoodie', 60, 29.99, 1),
       (13, 'Sweater', 40, 24.99, 1),
       (14, 'Dress Shirt', 45, 34.99, 1),
       (15, 'Shorts', 70, 19.99, 1),
       (16, 'Tank Top', 85, 14.99, 1),
       (17, 'Socks', 200, 2.99, 1),
       (18, 'Beanie', 25, 9.99, 1),
       (19, 'Gloves', 15, 12.99, 1),
       (20, 'Sneakers', 50, 89.99, 1),
       (21, 'Slippers', 30, 19.99, 1),
       (22, 'Jacket', 35, 69.99, 1),
       (23, 'Pants', 60, 29.99, 1),
       (24, 'Earrings', 50, 24.99, 20),
       (25, 'Bluetooth Speaker', 15, 79.99, 12),
       (26, 'Remote Control Car', 20, 39.99, 13),
       (27, 'Notebook', 100, 4.99, 14),
       (28, 'Mixing Bowl Set', 30, 19.99, 15),
       (29, 'Novel', 50, 9.99, 16),
       (30, 'Vitamin Supplements', 100, 14.99, 17),
       (31, 'Garden Hose', 25, 29.99, 18),
       (32, 'Cat Food', 80, 7.99, 19),
       (33, 'Necklace', 40, 39.99, 20),
       (34, 'Throw Pillow', 60, 12.99, 21),
       (35, 'Acrylic Paint Set', 30, 29.99, 22),
       (36, 'Baby Onesie', 70, 8.99, 23),
       (37, 'Guitar', 10, 199.99, 24),
       (38, 'Car Wax', 40, 16.99, 25),
       (39, 'Travel Adapter', 50, 9.99, 26),
       (40, 'Yoga Mat', 30, 24.99, 27),
       (41, 'Balloons', 200, 5.99, 28),
       (42, 'Smartphone Holder', 40, 12.99, 29),
       (43, 'Pearl Bracelet', 25, 34.99, 20),
       (44, 'Candle Holder', 35, 17.99, 21),
       (45, 'Watercolor Paint Set', 25, 22.99, 22),
       (46, 'Kids T-Shirt', 60, 6.99, 23),
       (47, 'Keyboard', 15, 129.99, 24),
       (48, 'Car Air Freshener', 50, 3.99, 25),
       (49, 'Luggage Tag', 40, 4.99, 26),
       (50, 'Resistance Bands', 30, 16.99, 27),
       (51, 'Party Hats', 100, 2.99, 28),
       (52, 'Wireless Charger', 25, 19.99, 29)
;

use db_user;

INSERT INTO `user`(user_id, username, password, nickname, first_name, last_name, status, role, last_login_at)
VALUES (2, 'inactiveUser@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'inactiveUser', 'gildong', 'hong', 'INACTIVE', 'USER', '2023-10-01 23:54:15'),
       (3, 'deletedUser@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'deletedUser', 'gildong', 'hong', 'DELETED', 'USER', '2023-10-01 23:54:15'),
       (4, 'blockedUser@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'blockedUser', 'gildong', 'hong', 'BLOCKED', 'USER', '2023-10-01 23:54:15'),
       (5, 'activeUser1@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser1', 'gildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (6, 'activeUser2@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser2', 'gildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (7, 'activeUser3@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser3', 'gildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (8, 'activeUser4@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser4', 'gildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (9, 'activeUser5@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser5', 'gildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (10, 'activeUser6@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser6', 'gildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (11, 'activeUser7@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser7', 'gildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00')
;

INSERT INTO `user_payment_card`(user_payment_card_id, type, provider, number, user_id)
VALUES (1, 'CREDIT_CARD', 'SAMSUNG', '1234567890', '5')
;
