CREATE DATABASE day18;

USE day18;
CREATE TABLE users(
 id INT PRIMARY KEY AUTO_INCREMENT,
 userName VARCHAR(20),
 pwd VARCHAR(20)
);

SELECT * FROM users;

INSERT INTO users(userName,pwd)VALUES('123','34');

TRUNCATE users;

-- 创建表
CREATE TABLE admin(
 id INT PRIMARY KEY AUTO_INCREMENT,
 userName VARCHAR(20),
 pwd VARCHAR(20)

);

SELECT * FROM admin;

INSERT INTO admin(id,userName,pwd)VALUES(1,'Tom','888');
INSERT INTO admin(id,userName,pwd)VALUES(2,'rose','520');
INSERT INTO admin(id,userName,pwd)VALUES(3,'lixian','950');

SELECT 1 FROM admin;




-- 存储过程
-- 定义分隔符
DELIMITER $
CREATE PROCEDURE proc_login()
BEGIN 
	SELECT * FROM admin;
END $

-- 调用存储过程
CALL proc_login;



-- 设计一个账户表day18
CREATE TABLE account(
 id INT PRIMARY KEY AUTO_INCREMENT,
 accountName VARCHAR(20),
 money DOUBLE
);

-- 转账
UPDATE account SET money=money-1000 WHERE accountName='张三';
UPDATE account SET money=money+1000 WHERE accountName='李四';

INSERT INTO account(accountName,money) VALUES('张三',5000);
INSERT INTO account(accountName,money) VALUES('李四',5000);
SELECT * FROM account;

TRUNCATE account;

 
SELECT * FROM admin;

INSERT INTO admin(userName)


	
	
-- 测试大数据类型
CREATE TABLE test(
  id INT PRIMARY KEY AUTO_INCREMENT,
  content LONGTEXT,
  img LONGBLOB
);	

SELECT * FROM test;




综合案例：
 需求分析：
   登录、注册、注销
   登陆成功，
	 显示所有员工

 设计：
   数据库设计：
	admin,存放所有的登录用户
	Employee，存放所有员工信息
   系统设计：
	a,系统结构
		分层：基于mvc模式的分层
	b，项目用到的公用组件、类(了解)
	
  编码：
