<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>request对象</title>
  </head>
  
  <body>
    客户端的IP地址是：${sessionScope.ip }
    <%
    	request.setAttribute("name", "eric");//第一次就是增加属性
    	request.setAttribute("name", "rose");//如果前面有了同名的属性，就是修改
    	request.removeAttribute("name");
     %>
  </body>
</html>
