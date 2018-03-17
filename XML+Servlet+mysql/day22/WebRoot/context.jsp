<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>context对象监听器</title>  
  </head>
  
  <body>
    <%
    	application.setAttribute("name","eric"); //增加
    	application.setAttribute("name","jacky"); //修改
    	application.removeAttribute("name"); //删除
     %>
  </body>
</html>
