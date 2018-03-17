<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 导入自定义标签库 -->
<%@taglib uri="http://gz.itcast.cn" prefix="itcast"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>登录页面</title>

</head>

<body>
	<form method="post">
		<itcast:login password="pwd" userName="user"></itcast:login>
	</form>
</body>
</html>
