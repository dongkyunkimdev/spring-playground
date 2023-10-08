use db_user;

-- local, dev
INSERT INTO `user`(user_id, username, password, nickname, first_name, last_name, status, role, last_login_at)
VALUES (1, 'admin@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'admin', 'gildong', 'hong', 'ACTIVE', 'ADMIN', '2023-10-01 23:54:15'),
       (2, 'inactiveUser@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'inactiveUser', 'gildong', 'hong', 'INACTIVE', 'USER', '2023-10-01 23:54:15'),
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
