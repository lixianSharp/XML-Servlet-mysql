<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>日期时间格式化</title>
    
	
  </head>
  
  <body>
  	<%-- 生成一个当前日期时间对象 --%>
  	<jsp:useBean id="now" class="java.util.Date" scope="page" ></jsp:useBean>
  	 <!-- 
  	 	value:需要转换的日期时间值
  	 	type:选择日期或者时间或者日期时间生效
  	  -->	
  		
     <fmt:formatDate value="${now }" dateStyle="full" timeStyle="long" type="both"/>
  </body>
</html>
