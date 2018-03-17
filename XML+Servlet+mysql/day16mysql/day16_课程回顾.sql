-- 课程回顾：
-- mysql基础：
-- 1）mysql存储结构、数据库--》表-->数据-》  Sql语句
-- 2）管理数据库
-- 增加：create database 数据库 default character utf8;
-- 删除：drop database 数据库;
-- 修改；alter database 数据库 default character gbk;
-- 查询：show databases/show create database 数据库;
-- 3)管理表：
-- 选择数据库： use 数据库;
-- 增加：create table 表名(字段名1 字段类型，字段名2 字段类型，...);
-- 删除：drop table 表;
-- 修改：
-- 添加字段:alter table 表 add[column] 字段名 字段类型;
-- 删除字段：alter table 表 drop [column] 字段名；
-- 修改字段类型：alter table 表 modify 字段名 新的字段类型；
-- 修改字段名称：alter table 表 change 旧字段名  新字段宁  字段类型；
-- 查询： show tables/desc student;
USE day15;
SHOW TABLES ;
-- 4)管理数据
-- 增加：insert into 表(字段1，字段2，...）values(值1，值2，...);
-- 删除：delete from 表 where 条件;
-- 修改：update 表 set 字段1=值1，字段2=值2，... where 条件;
-- 查询：
-- 1）所有字段： select * from 表；
-- 2）指定字段： select 字段1，字段2，... from 表；
-- 3）指定列名： select 字段1 as 别名 from 表；
-- 4）合并列：select （字段1+字段2） from 表；
-- 5）去重： select distinct 字段 from 表；
-- 6）查询条件：
--    a)逻辑条件： and（与）  or（或）
SELECT * FROM 表 WHERE  条件1 AND/OR 条件2；
--    b)比较条件： >   <   >=  <=   <>  between and (在...之间)
SELECT * FROM 表 WHERE servet>=90;
--    c)判定条件：
--         判断null： is null     is not null
--         判断空字符串： =''     <>''
--    d)模糊条件： like
-- 		% :替换任意个字符
-- 		_ :替换一个字符
-- 7）分页查询： limit 起始行，查询行数
--  			起始行从0开始
-- 8）排序： order by 字段 asc/desc
-- 		asc:正序,顺序          desc：倒序，反序
-- 9）分组查询： group by 字段
-- 10）分组后筛选： having 条件

--  SQL语句的分类：
--  DDL:数据定义语言
--       create/drop/alter
-- DML:数据操作语言
-- 	 insert/delete/update/truncate
-- DQL:数据查询语言
-- 	select/show


-- -----------------------------------------------------------------------------------------------------------------

