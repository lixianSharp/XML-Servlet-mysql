CREATE DATABASE day17;
USE day17;
SHOW TABLES;
CREATE TABLE student(
id INT PRIMARY KEY,
NAME VARCHAR(20),
gender VARCHAR(2)
);
DESC student; -- 显示该表的详细信息

SELECT * FROM student;

UPDATE student SET NAME='小米' WHERE id=3;
INSERT INTO student(id,NAME,gender) VALUES(3,'李贤元','男');
DELETE FROM student WHERE id=3;


-- 模拟用户登录
-- 用户表
USE day17;
CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(20),
    PASSWORD VARCHAR(20)

);

INSERT INTO users(NAME,PASSWORD )VALUES('aric','123456');
INSERT INTO users(NAME,PASSWORD )VALUES('root','654321');

SELECT * FROM users;
DROP TABLE users;
DELETE FROM users;
-- 登录成功：用户输入的用户名和密码和users中的相匹配
-- 登录失败：找不到匹配的数据
SELECT * FROM users WHERE NAME='aric'AND PASSWORD='123456';

SELECT * FROM users WHERE 1=1; -- 1=1 表示恒成立(永远成立，永远真)

SELECT * FROM users WHERE NAME='aric' OR 1=1 -- ' and password='123456';

DELETE FROM USER WHERE id=1 OR 1=1; -- 这样能把整个表都删了


SELECT * FROM users WHERE NAME='aric' OR 1=1;
-- 上一条语句等价于一下两条语句
-- select * from users where name='aric';
-- select * from users where 1=1;

-- 创建存储过程
-- 带有输入参数的存储过程
DELIMITER $
CREATE PROCEDURE pro_findById(IN sid INT)
BEGIN 
	SELECT * FROM student WHERE id=sid;
END $

CALL pro_findById(2);

-- 带有输出参数的存储过程
DELIMITER $
CREATE PROCEDURE  pro_findById2(IN sid INT,OUT sname VARCHAR(20))
BEGIN 
	SELECT NAME INTO sname FROM student WHERE id=sid;
END $
	
CALL pro_findById2(3,@NAME);	
SELECT @NAME;

-- 创建通讯录系统数据库和表
CREATE DATABASE contact_sys;

USE contact_sys;

CREATE TABLE contact(
	id VARCHAR(32) PRIMARY KEY 
	NAME VARCHAR(20),
	gender VARCHAR(2),
	age INT,
	phone VARCHAR(20),
	qq VARCHAR(20)
	
);
