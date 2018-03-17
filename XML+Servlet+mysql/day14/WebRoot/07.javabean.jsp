<%@ page language="java" import="java.util.*,gz.itcast.b_cases.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>jsp页面中使用javabean</title>
   

  </head>
  
  <body>
    	<%
    		//Student stu=new Student();
    		//stu.setName("rose");
    		//stu.getName();
    		//stu.setAge(20);
    	 %>
    	 
		<%--创建对象 --%>
		<jsp:useBean id="stu" class="gz.itcast.b_cases.Student"></jsp:useBean>
		
		<%--赋值 --%>
		<jsp:setProperty property="name" name="stu" value="jacket"/>
		
		<%--获取值 --%>
		<jsp:getProperty property="name" name="stu"/>



  </body>
</html>
