create user 'app_playground'@'%' IDENTIFIED by 'password';
grant all privileges on *.* to 'app_playground'@'%' with grant option;

create user 'playground_admin'@'%' IDENTIFIED by 'password';
grant all privileges on *.* to 'playground_admin'@'%' with grant option;

FLUSH PRIVILEGES;
