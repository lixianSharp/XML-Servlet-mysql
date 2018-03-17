<%@ page language="java"  import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>动作标签,也叫内置标签</title>  
  </head>
  
  <body>
    <%--转发 --%>
    <%
	    //注意：写jsp页面的时候使用的java代码越少证明你的水平越高
    	//request.getRequestDispatcher("/09.action2.jsp?name=eric").forward(request,response);
     %>
    <%-- 参数 ，这种转发可以携带参数，但是如果参数只能是非中文字符，如果是中文字符会出现乱码--%>
<%--     
    <jsp:forward page="/09.action2.jsp" >
    	<jsp:param value="李贤元" name="name"/>
    	<jsp:param value="123456" name="password"/>
    </jsp:forward>
   --%>   
      
   <%--包含标签。这种包含是先各自翻译，最后再合并，动态包含。可以从包含页面中向被包含页面中传递参数。但是如果参数只能是非中文字符，如果是中文字符会出现乱码--%>
   <%-- 
  <jsp:include page="/common/header.jsp">
   		<jsp:param value="lucy李" name="name"/>
   	</jsp:include>
    --%>
   	
   	
   	<%--包含标签。这种包含是静态包含，先合并，再翻译。但是不能向被包含页面中传递参数 --%>
   	 <%@include file="common/header.jsp" %>
   	
      主页的内容
     
  </body>
</html>
