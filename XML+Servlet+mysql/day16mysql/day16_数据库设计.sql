-- 3、数据库设计
-- 3.1引入
-- 需求分析：需求分析师-->原始需求-->抽取业务模型
-- 图书模型：图书名称，版本号，作者
-- 学生模型：学号，学生姓名  手机号码

-- 需求设计：
-- 	 概要设计：
-- 	 抽取实体： 业务模型—>实体模型(java类、C++类)内存
-- 			Class Book(name,bookNo.author)
-- 	 数据库设计：
-- 		 业务模型/实体模型-->数据模型（硬盘）
-- 	 数据库表设计：问题:如何设计？
-- 				详细设计：类详细：属性和方法
-- -------------------------------------------
-- 3.2三大范式
-- 设计原则：建议设计的表尽量遵守三大范式

-- 第一范式：要求表的每个字段必须是不可分割的独立单元
-- 		student: name       --违反第一范式
-- 			 张小名|狗娃

-- student: name   oldName--符合第一范式
-- 	    张小名   狗娃

-- 第二范式：在第一范式的基础上，要求每张表只表达一个意思。表的每一个字段都和表的主键有依赖
-- employee(员工):员工编号 员工姓名 部门名称 订单名称 ---违反第二范式
-- 员工表：员工编号  员工姓名 部门名称
-- 订单表：订单编号 订单名称 --符合第二范式

-- 第三范式：在第二范式基础上，要求每张表的主键之外的其他字段都只能和主键有直接决定依赖关系
-- 员工表：员工编号(主键) 员工姓名 部门编号 部门名 --符合第一范式，违反第二范式（要求每张表只表达一个意思）
-- 员工表：员工编号(主键) 员工姓名 部门标号  --符合第三范式（降低数据冗余）
-- 部门表：部门编号  部门名

-- -----------------------------------------------------------------------------------------------------
-- 4、关联查询（多表查询）
-- 需求：查询员工及其所在部门(显示员工姓名，部门名称)
-- 2.1交叉连接查询（不推荐，产生笛卡尔乘积现象：4*4=16  有些是重复的）
SELECT empName，deptName FROM employee,dept;

-- 需求：查询员工及其所在部门（显示员工姓名，部门名称）
-- 多表查询规则：1）确定查询哪些表 2）确定查询哪些字段 3）表与表之间连接条件(规律：连接条件数量是表数量-1)
-- 2.2 内连接查询：只有满足条件的结果才会显示（使用最频繁）
 SELECT empName ,deptName    -- 2）确定查询哪些字段
 FROM employee,dept -- 1）确定查询哪些表
 WHERE employee.empName=dept.id; -- 3)表与表之间的连接条件
 
 -- 内连接的另一种语法：
 SELECT empName,deptName
 FROM employee
 INNER JOIN dept
 ON employee.empName=dept.id;
 
 -- 使用别名
SELECT e.empName,d.depName
FROM employee  e -- e表示为表employee的别名
INNER JOIN dept d -- d表示为表dept的别名
ON e.empName=d.id;

-- 需求：查询每个部门的员工
-- 预期结果：
-- 软件开发部 张三
-- 软件开发部  李四
-- 应用维护部 王五
-- 秘书部     陈六
-- 总经办      null

-- --------------------------------
-- 2.2、左外连接查询：使用坐标表的数据去匹配右边表的数据，如果符合连接条件的结果则显示，如果不符合连接条件则显示null
-- 注意：左外连接：左表的数据一定会完成显示
SELECT d.deptName,e.empName
FROM dept d
LEFT OUTER JOIN employee e
ON d.id=e.deptId;
-- -----------------------------
-- 2.3、右外连接查询：使用游标的表的数据去匹配左边的表的数据，如果符合连接条件的结果则显示，如果不符合连接条件则不显示
-- 注意：右外连接：游标的数据一定会完成显示！
SELECT e.empName, d.deptName
FROM employee e
RIGHT OUTER JOIN dept d
ON  d.id=e.deptId;
-- ------------------------------------ 
-- 2.4、自连接查询
-- 需求：查询员工及其上司
-- 预期结果：
-- 张三  null
-- 李四  张三
-- 王五  李四
-- 陈六  王五
SELECT e.empName,b.empName
FROM employee e  -- e表示为表employee的别名
LEFT OUTER JOIN  employee b -- b表示为employee的别名
ON e.bossid=b.id;
 
-- ----------------------------------------------------------------------------------------------------------------
-- 5、存储过程
-- 5.1、什么是存储过程
-- 存储过程：带有逻辑的SQL语句
-- 之间的SQL没有条件判断，没有循环
-- 存储过程带上流程控制语句(if where)
 
-- 5.2、存储过程特点：
-- 1）执行效率非常快！存储过程是自数据库的服务器端执行的！
-- 2）移植性很差！不同数据库的存储过程是不能移植

-- 5.3、存储过程语法	
-- 创建存储过程
DELIMITER $  -- 声明存储过程的结束符
CREATE PROCEDURE pro_test()  -- 存储过程名称(参数)	
	BEGIN -- 开始
	-- 可以写多个sql语句
	SELECT * FROM employee;
	END $  -- 结束 结束符
	
   
 -- 执行存储过程
 CALL pro_test(); -- CALL存储过程名称（参数） 
 
-- 参数：
-- IN：表示输入参数，可以携带数据在存储过程中
-- OUT：表示输出参数，可以从存储过程中返回结果
-- INOUT:表示输入输出参数，既可以输入功能，也可以输出功能

--  三、存储过程
-- 声明结束符
-- 创建存储过程
DELIMITER $ -- 创建存储过程的结束符
CREATE PROCEDURE pro_test()
BEGIN -- 开始
   -- 可以写多个sql语句
END $  -- 结束 结束符



-- 执行存储过程
CALL prot_test();

--- -----------------------
-- 3.1、带有输入参数的存储过程
-- 需求：传入一个员工的id,查询员工的信息
DELIMITER $ -- 声明存储过程的结束符
CREATE PROCEDURE pro_test(IN eid INT) -- IN:输入参数
BEGIN  -- 开始
   -- 可以写多个sql语句
     SELECT * FROM employe WHERE id=eid;
     END $ -- 结束 结束符

-- 执行存储过程
CALL pro_test(4);

-- 删除存储过程
DROP PROCEDURE pro_test;

USE day16;
CREATE TABLE employe(
id INT,
NAME VARCHAR(20)
);

INSERT INTO employe(id,NAME)VALUES(1,'小明');
INSERT INTO employe(id,NAME)VALUES(2,'小红');
INSERT INTO employe(id,NAME)VALUES(3'小蓝');
INSERT INTO employe(id,NAME)VALUES(4,'小绿');

-- ----------------------------------------------------
-- 调用：
-- 如何接受返回参数的值？
-- ***mysql的变量***
-- 全局变量(内置变量):mysql数据库内置的变量(所有链接都起作用)
	-- 查看所有全局变量： show variables
	-- 查看某个全局变量： select @@变量名
	-- 修改全局变量： set 变量名=新值
	-- character_set_client : mysql服务器的接收数据的编码
        -- character_set_results: mysql服务器输出数据的编码
-- 会话变量：只存在于当前客户端与数据库服务器端的一次链接当中。如果链接断开，那么会话变量全部丢失!
	-- 定义会话变量：set @变量=值
	-- 查看会话变量： select @变量
-- 局部变量：在存储过程中使用的变量就叫局部变量。只要存储过程执行完毕，局部变量就丢失！

-- 1)定义一个会话变量 name, 2)使用name会话变量接受存储过程的返回值
     CALL pro_testout(@NAME);  -- @NAME等价于 set @NAME   
 -- 查看变量值
     SELECT @NAME
     



-- 3.2 带有输出参数的存储过程       
DELIMITER $  -- 声明存储过程的结束符
CREATE PROCEDURE pro_testOut(OUT str VARCHAR(20))  -- OUT：输出参数   -- 存储过程名称(参数)
BEGIN   -- 开始
       -- 可以写多个sql语句
        -- 给参数赋值
	SET str='helljava';
END $

-- 执行存储过程
CALL pro_testOut(@NAME);
-- 查看变量值
SELECT @NAME;
       
       
-- ---------------------------
-- 3.3 带有输入输出参数的存储过程
DELIMITER $ -- 声明存储过程的结束符
CREATE PROCEDURE pro_testInOut(INOUT n INT) -- 存储过程名称  INOUT--输入输出参数
BEGIN  -- 开始
   -- 查看变量
   SELECT n;
   SET n =500;
END $  -- 结束 结束符

-- 调用
SET @n=10; -- 定义会话变量
CALL pro_testInOut(@n);
SELECT @n;


-- 3.4 带有条件判断的存储过程
-- 要求：输入一个整数，如果是1，则返回“星期一”，如果是2，则返回“星期二’，如果是3，则返回星期三；
								-- 其他数字，则返回”输入错误“
								
DELIMITER $  -- 声明存储过程的结束符
CREATE PROCEDURE pro_testIf(IN num INT,OUT str VARCHAR(20))  -- 存储过程名称（参数）
BEGIN
	IF num=1 THEN
		SET str='星期一';
	ELSEIF num=2 THEN
		SET str='星期二';
	ELSEIF num=3 THEN
		SET str='星期三';
	ELSE
		SET str='输入错误';
	END IF;
END $  -- 结束 结束符

-- 执行存储过程
CALL pro_testIf(4,@str);
CALL pro_testIf(3,@str);
-- 查看结果
SELECT @str;

-- ---------------------------------------
-- 3.5 带有U型你换功能的存储过程
-- 需求：输入一个整数，求和。例如：输入100，统计1--100的和
DELIMITER $
CREATE PROCEDURE pro_testWhile1(IN num INT,OUT result INT)
BEGIN
	-- 定义一个局部变量
	DECLARE i INT DEFAULT 1;
	DECLARE vsum INT DEFAULT 0;
	WHILE i<=num DO
		SET vsum=vsum+i;
		SET i=i+1;
	END WHILE;
	SET result=vsum;
	END $

-- 执行存储过程
CALL pro_testWhile1(100,@result);
-- 查询结果
SELECT @result;

-- -------------------------------------
-- 3.6 使用查询的结果作为返回值

DELIMITER $
CREATE PROCEDURE pro_findById2(IN eid INT,OUT vname VARCHAR(20) )
BEGIN
	SELECT empName INTO vname FROM employe WHERE id=eid;
END $

-- 执行存储过程
CALL pro_findById2(4,@vname);
-- 查询结果
SELECT @vname;


SELECT * FROM employe;
DELETE FROM employe;
ALTER TABLE employe ADD COLUMN empName VARCHAR(20);

  INSERT INTO employe(id,NAME,empName)VALUES(1,'xm','小明');
INSERT INTO employe(id,NAME,empName)VALUES(2,'xh','小红');
INSERT INTO employe(id,NAME,enpName)VALUES(3,'xl','小蓝');
INSERT INTO employe(id,NAME,empName)VALUES(4,'xl','小绿');

-- -------------------------------------------
USE day16;
CREATE TABLE student2(
   NAME VARCHAR(20),  
   english INT
  
);

-- 插入数据
INSERT INTO student2(NAME,english)VALUES('小王',70);
INSERT INTO student2(NAME,english)VALUES('小名',80);
INSERT INTO student2(NAME,english)VALUES('小龙',100);

-- 练习：编写一个存储过程
-- 如果学生的英语平均分小于等于70，则输出‘一般’
-- 如果学生的英语平均分大于70，且小于等于90，则输出‘良好’
-- 如果学生的英语平均分大于90，则输出‘优秀’
DELIMITER $
CREATE PROCEDURE pro_testAvg(OUT str VARCHAR(20))
BEGIN 
      -- 定义局部变量，接收平均分
      DECLARE savg DOUBLE;
      -- 计算英语平均分
      SELECT AVG(english) INTO savg FROM student2;
      IF savg<=70 THEN
           SET str='一般';
      ELSEIF savg>70 AND savg<=90 THEN
           SET str='良好';
      ELSE
	   SET str='优秀';
      END IF;
END $

CALL pro_testAvg(@str);

SELECT @str;

-- ------------------------------------------------------
-- 6、触发器
SELECT * FROM employee;
-- 日志表
CREATE TABLE test_log(
 id INT PRIMARY KEY AUTO_INCREMENT,
 content VARCHAR(100)
);
SELECT *FROM test_log;

-- 6、1触发器的作用：
-- 当操作了某张表示，希望同时触发一些动作/行为，可以使用触发器完成！！

 例如：当想员工表插入一条记录时，希望同时往日志表插入数据
 需求：当向员工表插入一条记录时，希望MySQL自动同时往日志表插入数据
-- 创建触发器(添加)
CREATE TRIGGER tri_empAdd AFTER INSERT ON employee FOR EACH ROW    -- 当往员工表插入一条记录时
     INSERT INTO test_log(content) VALUES('员工表插入了一条记录');
     
-- 创建触发器(修改)
CREATE TRIGGER tri_empUpd AFTER UPDATE ON employee FOR EACH ROW    -- 当往员工表修改一条记录时
     INSERT INTO test_log(content) VALUES('员工表修改了一条记录');
     
-- 创建触发器(删除)
CREATE TRIGGER tri_empDel AFTER DELETE ON employee FOR EACH ROW
	INSERT INTO test_log(content)VALUES('员工表删除了一条记录');




CREATE TABLE employee(
  id INT,
  empName VARCHAR(20),
  deptId INT
);
-- 插入数据
INSERT INTO employee(id,empName,deptId)VALUES(7,'张古斯',1);
INSERT INTO employee(id,empName,deptId)VALUES(8,'张古斯二',2);
INSERT INTO employee(id,empName,deptId)VALUES(9,'张古二',3);
-- 修改数据
UPDATE employee SET empName='eric'WHERE id=7;
-- 删除记录
DELETE FROM employee WHERE id=8;

DROP TABLE employee;

-- -------------------------------------------------------------------
-- 7、Mysql权限问题：
-- mysql数据库权限问题：root ：拥有所有权限(可以干任何事情)
-- 权限账户，只拥有部分权限(CURD) 例如：只能操作某个数据库的某张表
-- 如何修改Mysql 的用户密码？
-- PASSWORD：MD5 加密函数(单向加密，只能加密，不能解密)
SELECT PASSWORD('root'); -- *81F5E21E35407D884A6CD4A731AEBFB6AF209E1B
 
-- mysql数据库，用户配置：user表
USE mysql;
SELECT * FROM USER;
  
-- 修改密码
UPDATE USER SET PASSWORD=PASSWORD('123456')WHERE USER='root'; 
 
--   
-- 分配权限账户  具体格式
GRANT 权限 ON 数据库.表 TO '账户名' @ '账户类型' IDENTIFIED BY '密码';
  
-- 分配权限账户
GRANT SELECT ON day16.employee TO 'eric'@'localhost' IDENTIFIED BY '123456';
  
GRANT DELETE ON day16.employee TO 'eric'@'localhost' IDENTIFIED BY '123456';
  
-- ---------------------------------------------------------------------------------------------
-- *************mysql备份和还原*************
mysqldump -u用户名 -p密码 -h主机数据库 a -w "sql条件" --lock-all-tables >路径

-- 备份：
  --  mysqldump -u root -p day16 >D:/back.sql

-- 恢复：
 -- mysql -u root -p day16 <d:/back.sql
 
 -- 注意：备份和还原不需要登录








