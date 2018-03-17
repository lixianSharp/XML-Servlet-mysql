USE day16;

CREATE TABLE deptIT(
 deptId INT PRIMARY KEY,
 deptName VARCHAR(20)
);

#级联操作
CREATE TABLE employeeIT2(
 empId INT PRIMARY KEY,
 empName VARCHAR(20),
 deptId INT,
 CONSTRAINT emp2_dept FOREIGN KEY(deptId) REFERENCES deptIT(deptId) ON  DELETE CASCADE ON UPDATE CASCADE
)
INSERT INTO employeeIT2(empId,empName,deptId) VALUES(1520,"王五",4);
DELETE FROM deptIT WHERE deptid = 4;#因为这里有级联删除和级联修改，所以直接修改或删除主表数据，可以直接影响到副表的数据

SELECT * FROM deptIT

CREATE TABLE employeeIT(
 empId INT PRIMARY KEY,
 empName VARCHAR(20),
 deptId INT,
 CONSTRAINT deptIT_employeeIT FOREIGN KEY(deptId) REFERENCES deptIT(deptId)
)
#左外连接   左表的数据一定会显示
SELECT d.deptName,e.empName
FROM deptIT d LEFT OUTER JOIN employeeIT e
ON d.deptId = e.deptId;

#右外连接 右表的数据一定会显示
SELECT d.deptName,e.empName
FROM deptIT d RIGHT OUTER JOIN employeeIT e
ON d.deptId = e.deptId;

#使用别名
SELECT d.deptName,e.empName
FROM deptIT d INNER JOIN employeeIT e
ON d.deptId = e.deptId;


#内连接
SELECT deptName,empName
FROM deptIT INNER JOIN employeeIT
ON deptIT.deptId = employeeIT.deptId;


SELECT deptName,empName 
FROM deptIT,employeeIT
WHERE deptIT.deptId = employeeIT.deptId;


DESC employeeIT;
SELECT * FROM employeeIT;

INSERT INTO deptIT(deptId,deptName) VALUES(1,"研发一部");
INSERT INTO deptIT(deptId,deptName) VALUES(2,"Java开发部");
INSERT INTO deptIT(deptId,deptName) VALUES(3,"C#开发部");
INSERT INTO deptIT(deptId,deptName) VALUES(4,"大数据开发部");
SELECT * FROM deptIT

INSERT INTO employeeIT(empId,empName,deptId) VALUES(1001,"张三",1);
INSERT INTO employeeIT(empId,empName,deptId) VALUES(1002,"李四",1);
INSERT INTO employeeIT(empId,empName,deptId) VALUES(1003,"张三",2);
INSERT INTO employeeIT(empId,empName,deptId) VALUES(1004,"张三",2);
INSERT INTO employeeIT(empId,empName,deptId) VALUES(1005,"张三",1);


#存储过程
DELIMITER $
CREATE PROCEDURE pro_test()
BEGIN
 SELECT * FROM deptIT;

END $

#调用存储过程
CALL pro_test();

#有输入参数的存储过程
DELIMITER $
CREATE PROCEDURE pro_test1(IN did INT)
BEGIN 
 SELECT deptName FROM deptIT WHERE deptId = did;
END $

#调用存储过程

CALL pro_test1(3);
#有输出参数的存储过程
DELIMITER $
CREATE PROCEDURE pro_test2(OUT n INT)
BEGIN
 SET n = 100;

END $

#调用存储过程
CALL pro_test2(@number);#将值赋给会话变量number
SELECT @number;#查询会话变量中的值

#有输入输出参数的存储过程
DELIMITER $
CREATE PROCEDURE pro_test3(INOUT num INT)
BEGIN
 #查看变量原来的值
 SELECT num;
 SET num=1520;
END $

#调用
SET @num = 920;
CALL pro_test3(@num);
SELECT @num;#查询最后的值


#带有条件判断的存储过程
DELIMITER $
CREATE PROCEDURE pro_if(IN n INT,OUT result VARCHAR(20))
BEGIN
 IF n=1 THEN
	SET result="星期一";
 ELSEIF n=2 THEN 
	SET result="星期二";
 ELSEIF n=3 THEN
	SET result="星期三";
 ELSE
	SET result="输入错误";
 END IF;

END $

#调用存储过程
CALL pro_if(3,@NAME);
SELECT @NAME;

#带有循环功能的存储过程
DELIMITER $
CREATE PROCEDURE pro_while(IN n INT,OUT num INT)
BEGIN
 #定义一个局部变量
 DECLARE i INT DEFAULT 1;
 DECLARE vsum INT DEFAULT 0;
 WHILE i<n DO
	SET vsum = vsum+i;
	SET i = i+1;
 END WHILE;
 #这里循环已经结束了
 SET num = vsum;
END $

#调用存储过程
CALL  pro_while(100,@result)
SELECT @result;

#触发器
SELECT * FROM test_log;
TRUNCATE test_log;
#创建触发器
CREATE TRIGGER tri_add AFTER INSERT ON deptIT FOR EACH ROW  #当往部门表插入每一行的时候
	 INSERT INTO test_log(content) VALUES("往部门表中插入了一条数据");
	 
CREATE TRIGGER tri_update AFTER UPDATE ON deptIT FOR EACH ROW #当往部门表修改每一行的时候
	INSERT INTO test_log(content) VALUES("更改了部门表的一行数据")
	
CREATE TRIGGER tri_del AFTER DELETE ON deptIT FOR EACH ROW #当删除部门表的每一行的时候
	INSERT INTO test_log(content) VALUES("删除了部门表的一行数据")



SELECT * FROM deptIT;

SELECT * FROM test_log;

INSERT INTO deptIT(deptId,deptName) VALUES(4,".net高级研发部");
INSERT INTO deptIT(deptId,deptName) VALUES(5,".net高级研发部");
INSERT INTO deptIT(deptId,deptName) VALUES(6,".net高级研发部");

UPDATE deptIT SET deptName = "大数据开发部" WHERE deptId = 4;

DELETE FROM deptIT WHERE deptId = 4;
DELETE FROM deptIT WHERE deptId = 5;
DELETE FROM deptIT WHERE deptId = 6;
	
day16Events
USE day16;
#视图 start*********************************************************************************************
#创建视图的语法：  create view 视图名 as  查询语句(可以是各种查询语句)
CREATE VIEW deptIT_view AS SELECT deptName FROM deptIT;#创建视图
SELECT * FROM deptIT_view;

CREATE VIEW deptIT_view2 AS SELECT * FROM deptIT;
SELECT * FROM deptIT_view2 WHERE deptId = 2;
# show table status from 数据库名 \G;查询数据库里表和视图的详细信息（要在dos窗口下）
SELECT TABLE STATUS FROM day16 
#show create view 视图名;查看视图定义
SHOW CREATE VIEW deptIT_view;
#desc 视图名;查看视图设计信息
DESC deptIT_view;

#修改视图
# 方式一：create or replace view 视图名  as 查询语句;
CREATE OR REPLACE VIEW deptIT_view AS SELECT * FROM deptIT WHERE deptId = 1;
# 方式二：alter view 视图名 as 查询语句
ALTER VIEW deptIT_view AS SELECT * FROM deptIT;

#删除视图
# drop view 视图名1,视图名2,..;可以同时删除多个视图
DROP VIEW deptIT_view;

#利用视图操作基本表
SELECT * FROM deptIT_view;
#通过视图添加一条新的数据（由于values后的数据常量与视图中的列一一对应，所以视图名deptIT_view后的列名可以不写）
INSERT INTO deptIT_view VALUES(4,'.NET开发部');
#通过视图删除数据。
DELETE FROM deptIT_view WHERE deptId = 4;
#通过视图更新数据
UPDATE deptIT_view SET deptName='CSharp开发部' WHERE deptId =3;

#视图 end*********************************************************************************************


#mysql自定义无参函数
DELIMITER $
CREATE FUNCTION sp_test() RETURNS VARCHAR(20)
BEGIN
 RETURN (SELECT deptName FROM deptIT WHERE deptid = 1);

END $

#调用自定义函数
SELECT sp_test();

#mysql的含有参数的自定义函数
DELIMITER $
CREATE FUNCTION testFormateDate(fdate DATETIME) RETURNS VARCHAR(255)
BEGIN 
  DECLARE X VARCHAR(255) DEFAULT '';
  SET X = DATE_FORMAT(fdate,'%Y年%m月%d日%h时%i分%s秒');
  RETURN X;

END $ 

#调用有参数的自定义函数
SELECT testFormateDate(NOW());

#
DELIMITER $
CREATE FUNCTION myfun(in_num INT,out_str VARCHAR(20)) RETURNS VARCHAR(20)
BEGIN
 #声明一个局部变量
 DECLARE i INT DEFAULT 2;
 IF (in_num < i) THEN
	SET out_str = "小于";
 ELSEIF (in_num>i) THEN
	SET out_str = "大于";
 ELSE 
	SET out_str = "相等";
 END IF;
 RETURN out_str;
 
END $

#调用
SELECT myfun(2,'');

##索引  start*******************************************************************************
#创建和查看普通索引
-- 1、创建表的时候创建索引
CREATE TABLE table_name(
 属性名 数据类型，
 属性名 数据类型，
 INDEX|KEY 索引名(属性名1 长度 ASC|DESC)
);
-- 案例：创建表的同时创建索引
CREATE TABLE t_dept(
 deptno INT,
 dname VARCHAR(20),
 loc VARCHAR(40),
 INDEX index_deptno(deptno)
);
SHOW CREATE TABLE t_dept;-- 校验数据库表t_dept中索引是否创建成功(在dos窗口中运行)

INSERT INTO t_dept(deptno,dname,loc ) VALUES(1,'维修部','大门左侧');
#校验表中的索引是否被使用
EXPLAIN SELECT * FROM t_dept WHERE deptno =1\G;（dos窗口中运行）

#2、在已存在的表上创建索引
# create index 索引名 on 表名(表的字段名);
CREATE INDEX index_dname ON t_dept(deptno);

#3、通过sql语句alter table创建普通索引
# alter table 表名 add index|key 索引名(属性名 长度 asc|desc)
ALTER TABLE t_dept ADD INDEX index_loc(loc);


#4、创建表时创建唯一索引  就是在前面加上unique. 如果在前面加上fulltext，则就叫做全文索引。其他的操作都一样
CREATE TABLE table_name(
 属性名 数据类型，
 属性名 数据类型，
 UNIQUE INDEX|KEY 索引名(属性名1 长度 ASC|DESC)
);

#5、删除索引
DROP INDEX 索引名 ON 表名;
-- 示例
DROP INDEX index_loc ON t_dept;



##索引  end************************************************************************************

#声明存储过程的结束符
DELIMITER $
CREATE PROCEDURE pro_test()
BEGIN 
	SELECT * FROM dept;

END $

#调用存储过程
CALL pro_test();

#带有输入参数的存储过程
DELIMITER $ #声明存储过程的结束符
CREATE PROCEDURE pro_test1(IN eid INT)
BEGIN 
	SELECT * FROM employee WHERE employee.id = eid;

END $

#调用存储过程
CALL pro_test1(1)

#带有输出参数的存储过程
DELIMITER $
CREATE PROCEDURE pro_testOut(OUT str VARCHAR(20))
BEGIN
	#给参数赋值
	SET str ="hello李贤元";

END $

#定义一个会话变量
SET @str = "";
#调用存储过程
CALL pro_testOut(@str);
#查询会话变量
SELECT @str;

#带有输入输出的存储过程
DELIMITER $
CREATE PROCEDURE pro_testInOut(INOUT n INT)
BEGIN 
	SELECT n;#查看变量
	SET n = 1520;

END $

#设置一个会话变量
SET @n = 1;
#调用存储过程
CALL pro_testInOut(@n);
#查看会话变量最终的值
SELECT @n;

#带有条件判断的存储过程
DELIMITER $
CREATE PROCEDURE pro_testif(IN num INT,OUT str VARCHAR(20))
BEGIN 
	IF num =1 THEN
		SET str= "周一";
	ELSEIF num =2 THEN 
		SET str = "周二";
	ELSE
		SET str ="放假了";
	END IF;
	
END $

#会话变量使用上边定义了的@str
CALL pro_testif(2,@str);
SELECT @str;

#带有循环功能的存储过程
DELIMITER $
CREATE PROCEDURE pro_testwhile(IN num INT,OUT result INT)
BEGIN 
	#声明一个局部变量
	DECLARE i INT DEFAULT 1;
	DECLARE vsum INT DEFAULT 1;
	WHILE i<=num DO
		SET vsum = vsum*i;
		SET i = i+1;
	END WHILE;
	SET result = vsum;
	
END $

#声明一个会话变量
SET @result=0;
#调用存储过程
CALL pro_testwhile(10,@result);
SELECT @result;
#删除存储过程
DROP PROCEDURE pro_testwhile;

#在存储过程中使用查询的结果给变量(into)赋值
DELIMITER $
CREATE PROCEDURE pro_findById(IN eid INT,OUT vname VARCHAR(20))
BEGIN
	SELECT empName INTO vname FROM employee WHERE id=eid;


END $

SET @NAME="";
CALL pro_findById(1,@NAME);
SELECT @NAME;