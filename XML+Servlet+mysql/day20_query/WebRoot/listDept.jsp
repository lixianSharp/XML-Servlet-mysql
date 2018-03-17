<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>所有部门类别</title>


</head>

<body>
	<table align="center" border="1">
		<tr>
			<th>编号</th>
			<th>部门名称</th>
			<th>负责人</th>
			<th>部门职能</th>
		</tr>
		<c:forEach items="${list }" var="dept">
		<tr>
			<td>${dept.id }</td>
			<td>${dept.deptName }</td>
			<td>${dept.principal }</td>
			<td>${dept.functional }</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
