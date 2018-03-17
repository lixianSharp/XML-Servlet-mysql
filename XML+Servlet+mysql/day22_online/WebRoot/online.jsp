<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>当前在线登录用户</title>  
  </head>
  
  <body>
    <table align="left" border="1" width="600px">
    	<tr>
    		<th>编号</th>
    		<th>登陆名</th>
    		<th>登录时间</th>
    		<th>最后访问时间</th>
    		<th>IP</th>
    		<th>操作</th>
    	</tr>
    	<c:forEach items="${requestScope.list}" var="bean" varStatus="varSta">
    	<tr>
    		<td>${varSta.count }</td>
    		<td>${bean.name }</td>
    		<td>${bean.loginTime }</td>
    		<td>${bean.lastTime }</td>
    		<td>${bean.ip }</td>
    		<td><a href="${pageContext.request.contextPath }/KickOutServlet?sessionId=${bean.sessionId}">踢除</a></td>
    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>
