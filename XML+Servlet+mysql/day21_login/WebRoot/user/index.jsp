<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 首先判断session域中的user是不是为空，如果为空的话就返回登录页面，否则就进入到主页 -->
<c:if test="${empty sessionScope.user}">
	<c:redirect url="/login.jsp"></c:redirect>
</c:if>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>用户主页</title>  
  </head>
  
  <body>
    欢迎你回来，${sessionScope.user }
  </body>
</html>
