<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>web页面的文字国际化</title>
</head>
<%
	//读取用户请求过来的语言环境
	//Locale locale=request.getLocale();
	//根据浏览器的语言环境来加载不同的资源包
	//ResourceBundle bundle=ResourceBundle.getBundle("message",locale);
	//bundle.getString("username");
	//在jsp中尽量不要用脚本
%>

	<%--绑定资源包 --%>
	<fmt:setBundle basename="message"/>
<body>
	<table align="left" border="1">
		<tr>
			<th><fmt:message key="username"></fmt:message></th>
			<td><input type="text" name="userName" /></td>
		</tr>
		<tr>
			<th><fmt:message key="password"></fmt:message></th>
			<td><input type="text" name="userPwd" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="<fmt:message key="login"></fmt:message>" /></td>
		</tr>
	</table>
</body>
</html>
