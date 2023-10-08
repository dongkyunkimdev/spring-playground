use db_user;

-- local, dev
INSERT INTO `user`(user_id, username, password, nickname, first_name, last_name, status, role, last_login_at)
VALUES (1, 'admin@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'admin', 'kildong', 'hong', 'ACTIVE', 'ADMIN', '2023-10-01 23:54:15'),
       (2, 'inactiveUser@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'inactiveUser', 'kildong', 'hong', 'INACTIVE', 'USER', '2023-10-01 23:54:15'),
       (3, 'deletedUser@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'deletedUser', 'kildong', 'hong', 'DELETED', 'USER', '2023-10-01 23:54:15'),
       (4, 'blockedUser@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'blockedUser', 'kildong', 'hong', 'BLOCKED', 'USER', '2023-10-01 23:54:15'),
       (5, 'activeUser1@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser1', 'kildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (6, 'activeUser2@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser2', 'kildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (7, 'activeUser3@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser3', 'kildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (8, 'activeUser4@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser4', 'kildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (9, 'activeUser5@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser5', 'kildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (10, 'activeUser6@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser6', 'kildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (11, 'activeUser7@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser7', 'kildong', 'hong', 'ACTIVE', 'USER', '2023-10-02 00:00:00')
;
