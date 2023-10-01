use
db_user;

-- local, dev
INSERT INTO `user`(user_id, username, password, nickname, status, role, last_login_at)
VALUES (1, 'admin@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'admin', 'ACTIVE', 'ADMIN', '2023-10-01 23:54:15'),
       (2, 'inactive@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'admin', 'INACTIVE', 'USER', '2023-10-01 23:54:15'),
       (3, 'deleted@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'admin', 'DELETED', 'USER', '2023-10-01 23:54:15'),
       (4, 'blocked@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'admin', 'BLOCKED', 'USER', '2023-10-01 23:54:15')
;
