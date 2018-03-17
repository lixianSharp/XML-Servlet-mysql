<%@ page 
	language="java" 
	import="java.util.*" 
	pageEncoding="UTF-8"
	errorPage="common/error.jsp"
	session="true"
	%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>page指令</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  		<%
  			//String name=null;
  			//name.charAt(1);
  			
  			int i=100/0;
  			
  			HttpSession session1=request.getSession();
  			
  			session.getId();
  			
  		 %>
  <body>
    
  </body>
</html>
