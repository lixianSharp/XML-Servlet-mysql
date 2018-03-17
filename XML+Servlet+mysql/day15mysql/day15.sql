CREATE  DATABASE day15;
SHOW DATABASES;
-- 创建表
CREATE TABLE student(
 sid INT,
 sname VARCHAR(20),
 sage INT
);
DESC student;
-- 创建数据库
CREATE DATABASE day15;
-- --指定默认字符集创建数据库
CREATE DATABASE day13 DEFAULT CHARACTER SET utf8;
-- --查看数据库的默认字符集
SHOW CREATE DATABASE day15;

-- 删除数据库
DROP DATABASE day15;
-- 修改数据库 修改数据库的字符集
ALTER DATABASE day15 DEFAULT CHARACTER SET gbk;
-- --查看所有表
SHOW TABLES;



-- 创建表
USE day15;
CREATE TABLE student(
sid INT,/*sid是字段名称*/
sname VARCHAR(20),/*--20是字段长度*/
sage INT  /*int是字段类型*/
);

SELECT *FROM student;
-- --查看表结构、
DESC student;
-- --删除表
DROP TABLE student;

-- --修改表
-- --1）添加字段????
ALTER TABLE student ADD COLUMN sgender VARCHAR(2);
-- --2)删除字段
ALTER TABLE student DROP COLUMN sname;
-- --3)修改字段类型???
  ALTER TABLE student MODIFY COLUMN sname VARCHAR(100);
-- --4)修改表名称???
ALTER TABLE student RENAME TO teacher;

-- --创建一个员工表
CREATE TABLE employee(
id INT,
NAME VARCHAR(20),
gender VARCHAR(2),
birthday DATETIME,
email VARCHAR(10),
remark VARCHAR(50) 
);
SELECT *FROM employee;

-- --1)在员工表基础上增加age列
 ALTER TABLE employee ADD age INT;
-- --2)修改email列长度为50？？
ALTER TABLE employee MODIFY email VARCHAR(50);
-- --3)删除remark列???
 ALTER TABLE employee DROP remark;
-- --4)列名name修改为username???
ALTER TABLE employee CHANGE NAME username VARCHAR(20);


-------------------------------------------------------------------------------------------
-- --增删改数据
-- --增加数据
-- --插入所有字段。一定依次按顺序插入
USE day15;
CREATE TABLE student(
sno INT,
sname VARCHAR(20),
sgender VARCHAR(2),
sage INT
);
SELECT *FROM student;
-- 插入所有字段
INSERT INTO student VALUES(1,'lixianyuan','男',21);
-- 注意：不能少或多字段值
-- insert into student values(1,'lisi','女',);--错误

-- --插入部分字段
INSERT INTO student(sno,sname)VALUES(2,'lisi');

--------------------
-- 修改数据
-- 修改所有数据(建议少用)
UPDATE student SET sgender='女';
-- 带条件的修改（推荐使用）
UPDATE student SET sgender='男' WHERE sno=1;/*--修改sno为1的学生，修改性别为男*/
-- 修改多个字段，注意：set 字段名=值; 字段名=值,...
UPDATE student SET sgender='男',sage=30 WHERE sno=2; 

-------------------------
-- 删除数据
-- 删除所有数据（建议少用）
USE day15;
DELETE FROM student;
-- 带条件的删除（推荐使用）
DELETE FROM student WHERE sno=2;

---- 另一种方式
--DELETE FROM:可以全表删除 1）可以带条件删除 2）只能删除表的数据，不能删除表的约束
-- 3)使用delete from删除的数据可以回滚（事务）

-- truncate table:可以全表删除 1）不能带条件删除 2）既可以删除表的数据，也可以删除表的约束
-- 3）使用truncate删除的数据不能回滚
TRUNCATE TABLE student;--全表删除


-----------------------------------------------------------------------------------------
-- 7、数据查询（重点）
USE day15;
CREATE TABLE test(
id INT PRIMARY KEY,-- 自增长约束
NAME VARCHAR(20)
);

INSERT INTO test VALUES(1,'lixianyuan');
INSERT INTO test VALUES(2,'zhagnsan');
INSERT INTO test VALUES(3,'lisi');

-- 2、1查询所有列
SELECT*FROM test;
-- truncate table student where id=2;-- 不能带条件

-- 查询指定列
SELECT id FROM test;

------- 2、3查询时指定别名（as）
-- 注意：在多表查询时经常使用表的别名
 SELECT id AS '编号',NAME AS '姓名' FROM test;

----- 2、4查询时添加常量列
-- 需求：在查询test表的时候添加一个班里列，内容为java就业班
SELECT id,NAME,'java就业班' AS '年级' FROM test;

USE day15;
-- 2、5查询时合并列
CREATE TABLE student(
 id INT,
NAME VARCHAR(20),
servlet INT,
jsp INT,
gender VARCHAR(2),
address VARCHAR(20)
);
SELECT * FROM student;

INSERT INTO student VALUES(1,'lixianyuan',89,90,'男','fujian');
INSERT INTO student VALUES(2,'zhangsan',81,78,'女','jiangxi');
INSERT INTO student VALUES(3,'lisi',56,60,'男','shanxi');
INSERT INTO student VALUES(4,'zhangsan',81,78,'女',NULL);
INSERT INTO student VALUES(5,'lisi',56,60,'男',' ');

INSERT INTO student VALUES(6,'李贤元',89,90,'男','fujian');
INSERT INTO student VALUES(2,'李琦',81,78,'女','jiangxi');
INSERT INTO student VALUES(3,'李瑞王',56,60,'男','shanxi');

SELECT *FROM student;
-- 需求：查询每个学生的servlet和jsp的总成绩
SELECT id,NAME,(servlet+jsp)AS'总成绩'FROM student;
-- 注意：和并列只能合并数值类型的字段
--SELECT id,(NAME+servlet) FROM student;-- 在将 varchar 值 'lixianyuan' 转换成数据类型 int 时失败。

---- 2.6查询时去除重复记录（distinct）
-- 需求：查询学生的性别  男 女
SELECT DISTINCT  gender FROM student;
-- 另一种语法：(得出的还是同样的效果)
SELECT DISTINCT(gender) FROM student;
-- 需求：查询学生所在的地区
SELECT DISTINCT address FROM student;


----- 2、7条件查询(where)
-- 2.7.1逻辑条件： and(与) or(或)
-- 需求：查询id为2 且姓名为zhangsan的学生
SELECT *FROM student WHERE id=2 AND NAME='zhangsan';-- 交集
-- 需求：查询id为2，或姓名为zhagnsan的学生
SELECT *FROM student WHERE id=2 OR NAME='zhangsan';
-- 2.7.2：比较条件： >  <  >=  <=  =  <>(不等于)
 SELECT * FROM student WHERE servlet>85;
 
-- 2.7.3 判断条件（null空字符串）: is null/is not null /=' '  /<>' '
-- 需求：查询地址为空的学生（包括null和空字符串）
-- null vs 空字符串
-- null: 表示没有值
-- 空字符串：有值的！
-- 判断null
SELECT * FROM student WHERE address IS NULL;
-- 判断空字符串
SELECT *FROM  student WHERE address=' ';
SELECT *FROM student WHERE address IS NULL OR address=' ';-- 包括null和空字符串
-- 需求：查询有地址的学生（不包括null和空字符串）
SELECT *FROM student WHERE address <>' 'AND  address IS NOT NULL;

-- -----------------------------------------------------------------------------------
-- 2.7.4模糊条件like
-- 通常使用以下替换标记
-- % ：表示任意个字符
--   _ :表示一个字符
-- 需求：查询姓'李'的学生
SELECT * FROM student WHERE NAME LIKE '李%';
-- 需求：查询姓李，且姓名只有两个字的学生
SELECT * FROM student WHERE NAME LIKE '李_';

-- -------------------------------------------------------------------------------------------
-- 2.8聚合查询（使用聚合函数的查询）
-- 常用的聚合函数：sum()  avg() max()  min() count() 
-- 需求：查询学生的servlet的总成绩（sum():求和函数）
SELECT SUM(servlet) AS 'servlet 的总成绩' FROM student;
-- 需求：查询学生的servlet的平均分 
SELECT AVG(servlet) AS 'sevlet 的平均分' FROM student;
-- 需求：查询最低分
SELECT MIN(servlet) AS 'servlet 的最低分' FROM student;
-- 需求：查询servlet的最高分
SELECT MAX(servlet) AS 'servlet 的最高分' FROM student;
-- 需求：统计当前有多少学生
SELECT COUNT(*) FROM student;
SELECT COUNT(id) FROM student;
-- 注意：count函数统计的数量不包括null的数据
-- 使用count统计表的记录数，要使用不包含null值的字段
SELECT COUNT(gender) FROM student;
SELECT COUNT(age) FROM student;

-- --------------------------------------------------------------------------------------------------
-- 2.9分页查询（limit起始行，查询几行）
-- 起始行从0开始
-- 分页：当前页，每页显示多少条
-- 分页查询当前页的数据的sql:select * from student limit (当前页-1)*每页显示多少条，每页显示多少条;

-- 需求：查询第1，2条记录（第一页的数据）
SELECT * FROM student LIMIT 0,2;
-- 查询第3，4条记录（第二页的数据）
SELECT *FROM student LIMIT 2,2;
-- 查询第5，6条记录 （第三页的数据）
SELECT* FROM student LIMIT 4,2;
-- 查询第7，8条记录（没有记录不显示）
SELECT * FROM student LIMIT 6,2;

-- -----------------------------------------------------------------------------------------------
-- 2.10查询排序（order by）
-- 语法：order by 字段 asc/desc
-- asc:
-- 顺序：正序。数值：递增     字母：自然顺序（a-z）
-- desc:
-- 倒序：反序。数值：递增   字母：自然反序（z-a）

-- 默认情况下，按照插入记录顺序排序
SELECT *FROM student;
-- 需求：按照id顺序排序
SELECT *FROM student ORDER BY id ASC;
SELECT * FROM student ORDER BY id;-- 默认正序
SELECT * FROM student ORDER BY id DESC;-- 反序
-- 注意：多个排序条件
-- 需求：按照servlet正序，按照jsp倒序
SELECT * FROM student ORDER BY servlet ASC,jsp DESC;

-- -------------------------------------------------------------------------------------------
-- 2.11分组查询后筛选
-- 需求：查询总人数大于2的性别
-- 1）查询男女的人数
-- 2）筛选出人数大于2的记录（having）
-- 注意：分组之前条件使用where关键字，分组之后条件使用having关键字
SELECT gender ,COUNT(*) FROM  student GROUP BY gender HAVING COUNT(*)>2;

-- 总结：
-- MySql基础：
-- 1）mysql数据库作用：管理数据
-- 2）mysql存储结构
--   数据库：管理数据库（CRUD）
--    表：管理表（CRUD）
--    数据：管理数据