<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 先判断session域中的user是否为空，如果为空，则返回登录页面，否则显示当前页面 -->
<c:if test="${empty sessionScope.user}">
	<c:redirect url="/login.jsp"></c:redirect>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>用户修改页面</title>  
  </head>
  
  <body>
    用户修改页面
  </body>
</html>
