package Note;
/**
 
 开发顺序：
 	1）数据库
 	2）实体对象：Employee
 	3）编写dao
 	4）编写service
 	5）servlet
 	6）jsp页面
 
 1、分页查询：  pageBean是核心
 
 
 
 
 
 2、条件查询:
 		条件查询的核心：条件查询的sql语句
 			
-- 改造sql
IF 全部为空 THEN
SELECT * FROM department WHERE 1=1;-- 恒等
IF 部门名称不为空 THEN
	AND deptName LIKE'%应用%';
IF 负责人不为空 THEN
	AND principal LIKE'%李%';
IF 职能不为空 THEN
	AND functional LIKE'开发';

 
 * @author 贤元
 *
 */
public class TodayNote {

}
