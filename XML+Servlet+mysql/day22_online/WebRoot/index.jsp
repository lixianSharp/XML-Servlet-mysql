<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>

  </head>
  
  <body>
    欢迎您回来，${sessionScope.user },<a href="${pageContext.request.contextPath }/LogoutServlet">【安全退出】</a></br>
  <a href="${pageContext.request.contextPath }/GetOnLineServlet">【查看在线登录用户】</a>
    
  </body>
</html>
