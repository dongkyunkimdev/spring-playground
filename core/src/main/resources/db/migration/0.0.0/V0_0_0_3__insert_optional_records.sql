use db_playground;

INSERT INTO `product_category`(product_category_id, name)
VALUES
    (1, 'Clothing'),
    (2, 'Shoes'),
    (3, 'Bags'),
    (4, 'Accessories'),
    (5, 'Electronics'),
    (6, 'Home Goods'),
    (7, 'Food'),
    (8, 'Sports Equipment'),
    (9, 'Cosmetics'),
    (10, 'Furniture')
;

INSERT INTO `product`(product_id, name, stock, price, product_category_id)
VALUES
    (1, 'T-shirt', 100, 19.99, 1),
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
    (23, 'Pants', 60, 29.99, 1)
;
