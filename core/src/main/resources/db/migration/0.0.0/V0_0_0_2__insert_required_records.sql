use db_user;

-- local, dev
INSERT INTO `user`(user_id, username, password, nickname, first_name, last_name, status, role, last_login_at)
VALUES (1, 'admin@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'admin', 'gildong', 'hong', 'ACTIVE', 'ADMIN', '2023-10-01 23:54:15')
;
