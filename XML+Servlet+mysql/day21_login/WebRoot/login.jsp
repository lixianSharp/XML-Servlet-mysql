<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>登录页面</title>  
  </head>
  
  <body>
  <font color="red">${msg }</font>
    <form action="${pageContext.request.contextPath }/LoginServet" method="post">
    	用户名：<input type="text" name="userName"/><br/>
    	密码： <input type="password" name="userPwd"/><br/>
    	<input type="submit" value="登录"/>
    </form>
  </body>
</html>
