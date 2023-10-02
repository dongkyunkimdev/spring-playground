use
db_user;

-- local, dev
INSERT INTO `user`(user_id, username, password, nickname, status, role, last_login_at)
VALUES (1, 'admin@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'admin', 'ACTIVE', 'ADMIN', '2023-10-01 23:54:15'),
       (2, 'inactiveUser@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'inactiveUser', 'INACTIVE', 'USER', '2023-10-01 23:54:15'),
       (3, 'deletedUser@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'deletedUser', 'DELETED', 'USER', '2023-10-01 23:54:15'),
       (4, 'blockedUser@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'blockedUser', 'BLOCKED', 'USER', '2023-10-01 23:54:15'),
       (5, 'activeUser1@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser1', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (6, 'activeUser2@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser2', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (7, 'activeUser3@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser3', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (8, 'activeUser4@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser4', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (9, 'activeUser5@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser5', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (10, 'activeUser6@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser6', 'ACTIVE', 'USER', '2023-10-02 00:00:00'),
       (11, 'activeUser7@playground.com', '$2a$10$Tu7KuI7Ctw90fGwALAoEQuzFT0/2zV7J2mqZeTcV.x3eioMw7rtDC', 'activeUser7', 'ACTIVE', 'USER', '2023-10-02 00:00:00')
;
