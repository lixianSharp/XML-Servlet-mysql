CREATE DATABASE day23;
USE day23;
-- 文件信息表
CREATE TABLE file_list(
id INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(50),-- 文件名
size VARCHAR(50),-- 文件大小
TYPE VARCHAR(20),-- 文件类型
ADDTIME DATETIME,-- 上传时间
file_path VARCHAR(100),-- 文件在服务器的实际地址
info VARCHAR(200) -- 文件描述
);
SHOW TABLES;
DROP TABLE file_list;
SELECT * FROM file_list;










































