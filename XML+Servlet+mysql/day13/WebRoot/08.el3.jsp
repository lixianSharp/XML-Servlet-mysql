<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>EL表达式计算</title>
    
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
    <%--
    	1)算术表达式
    	  + - * /
     --%>
     ${10+5 }
     <hr/>
     <%--
    	2)比较运算
    	 > < >= <= == !=
     --%>
     ${10>5 }<br/>
     ${10<5 }<br/>
     <hr/>
     <%--
    	3)逻辑运算
    	  &&  ||  !
     --%>
       ${true && true }<br/>
       ${true || false }<br/>
       ${!false }<br/>
     <hr/>
     <%--
    	4)判空
    	null 或   空字符集: empty
     --%>
       <%
       		//String name="eric";
       		String name=null;
       		//String name="";
       		pageContext.setAttribute("name",name);
       
        %>
      判断null: ${name==null }<br/><!-- true -->
   判断空字符：${name=="" }<br/>  <!-- false -->
       判空：${name==null  || name==""}<br/><!-- true -->
    另一种判空写法： ${empty name }<!-- true -->
  </body>
</html>
