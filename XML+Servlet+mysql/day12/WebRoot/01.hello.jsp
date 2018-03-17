<%@ page language="java" import="java.util.*,java.text.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>这是第一个jsp页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<%
		//写java代码
		//获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat();
		String curDate=sdf.format(new Date());
		//输出到浏览器
		out.write("当前时间为："+curDate);
	%>
</body>
</html>
