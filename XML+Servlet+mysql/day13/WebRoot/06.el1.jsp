<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>EL语法</title>
    
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
    	String name="rose";
    	//放入域中
    	//pageContext.setAttribute("name", name);
    	//这两行代码等价
    	pageContext.setAttribute("name", name, pageContext.REQUEST_SCOPE);
     %>
     <%=name %>
     <br/>
     <%--
     	1)从四个域中自动搜索
      --%>
	 EL表达式： ${name }
     <%--
     	${name} 等价于
     	  pageContext.findAttribute("name");
      --%>
      <%--
      	2)从指定的域中获取数据
       --%>
       EL表达式： ${pageScope.name}
       <%--
       		 ${pageScope.name} 等价于
       		 	<%=pageContext.getAttribute("name",pageContext.PAGE_SCORE)%>;
        --%>
  </body>
</html>
