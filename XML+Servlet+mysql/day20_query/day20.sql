CREATE DATABASE day20;

USE day20;
-- 部门表
CREATE TABLE department(
	id INT PRIMARY KEY AUTO_INCREMENT,
	deptName VARCHAR(20),  -- 名称
	principal VARCHAR(20),  -- 负责人
	functional VARCHAR(50)  -- 职能
);

-- 员工表
CREATE TABLE employee(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20), -- 姓名
	gender VARCHAR(2), -- 性别
	title VARCHAR(20),  -- 职位
	email VARCHAR(20),    -- 邮箱
	salary DOUBLE,      -- 薪水
	deptId INT,     -- 部门id
	CONSTRAINT employee_dept_fk FOREIGN KEY(deptId) REFERENCES department(id)
);


-- 部门数据
INSERT INTO department(deptName,principal,functional) VALUES('应用开发部','李经理','负责公司软件业务的开发');
INSERT INTO department(deptName,principal,functional) VALUES('实施部','张经理','负责公司软件维护工作');
INSERT INTO department(deptName,principal,functional) VALUES('秘书部','陈经理','负责公司行政事务及日常秘书工作');

-- 员工数据
INSERT INTO employee(NAME,gender,title,email,salary,deptId) VALUES('张三1','男','软件开发工程师','zhangsan1@126.com',4000,1);
INSERT INTO employee(NAME,gender,title,email,salary,deptId) VALUES('张三2','男','软件开发工程师','zhangsan2@126.com',6000,1);
INSERT INTO employee(NAME,gender,title,email,salary,deptId) VALUES('张三3','男','软件开发工程师','zhangsan3@126.com',6500,2);
INSERT INTO employee(NAME,gender,title,email,salary,deptId) VALUES('张三4','男','软件开发工程师','zhangsan4@126.com',5000,2);
INSERT INTO employee(NAME,gender,title,email,salary,deptId) VALUES('张三5','男','软件开发工程师','zhangsan5@126.com',5800,3);
INSERT INTO employee(NAME,gender,title,email,salary,deptId) VALUES('张三6','男','软件开发工程师','zhangsan6@126.com',5500,3);
INSERT INTO employee(NAME,gender,title,email,salary,deptId) VALUES('张三7','男','软件开发工程师','zhangsan7@126.com',7000,3);

SELECT * FROM department;
SELECT * FROM employee;


-- 查询当前员工数据: select * from employee LIMIT (当前页页码-1)*每页显示记录数,2;
-- 总共7条，每页显示2条
-- 需求：查询第1页的数据(1,2)
SELECT * FROM employee LIMIT 0,2;
-- 需求： 查询第2页的数据(3,4)
SELECT * FROM employee LIMIT 2,2;
-- 需求：查询第3页的数据（5,6）
SELECT * FROM employee LIMIT 4,2;
-- 需求：查询第4页的数据
SELECT * FROM employee LIMIT 6,2;

USE day20;
