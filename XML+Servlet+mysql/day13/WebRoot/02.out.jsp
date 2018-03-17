<%@ page language="java" 
	import="java.util.*" 
	buffer="1kb"
	pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>out对象</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>

	<%
		for (int i = 1; i <= 1023; i++) {
			out.write("a");
		}

		out.write("abc");
		//查看缓存区剩余大小
		System.out.println("缓存区剩余大小：" + out.getBufferSize());
		//查看缓存区剩余大小
		System.out.println("缓存区剩余大小：" + out.getRemaining());
		//刷新缓冲
		out.flush();
		response.getWriter().write("123");
	%>





</body>
</html>
