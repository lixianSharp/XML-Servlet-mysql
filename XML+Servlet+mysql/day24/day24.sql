CREATE DATABASE day24;

CREATE TABLE student(
	id INT PRIMARY KEY,
	NAME VARCHAR(20),
	age INT 
)

CREATE TABLE teacher(
	id INT PRIMARY KEY,
	NAME VARCHAR(20),
	age INT 
)

CREATE TABLE student_list(
	sid INT PRIMARY KEY,
	sNAME VARCHAR(20),
	sage INT 
)

CREATE TABLE teacher_list(
	tid INT PRIMARY KEY,
	tNAME VARCHAR(20),
	tage INT 
)


USE day24;
DROP TABLE teacher_list;
SELECT * FROM teacher_list;
INSERT INTO teacher_list VALUES(1,'lixian'，22);
INSERT INTO teacher_list VALUES(2,'李贤元'，21);
INSERT INTO teacher_list VALUES(3,'lixianyuan'，23);
day24
