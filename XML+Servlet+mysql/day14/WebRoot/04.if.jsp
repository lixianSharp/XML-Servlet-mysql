<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 导入自定义的标签库 -->
<%@taglib uri="http://gz.itcast.cn"  prefix="itcast"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>高仿的if标签</title>
   

  </head>
  
  <body>
   <itcast:if test="${10>5}">
   			条件成立
   </itcast:if>
   	
  </body>
</html>
