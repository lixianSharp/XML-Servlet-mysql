<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 导入自定义标签库 -->
<%@taglib uri="http://gz.itcast.cn"  prefix="itcast"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>高仿的choose</title>
   

  </head>
  
  <body>
	<itcast:choose>
			<itcast:when test="${10>5 }"><!-- 这里的test="${10>5 }"就会自动调用When标签的标签处理器类中的setTest方法，为test属性赋值 -->
				条件成立
			</itcast:when>
			<itcast:otherwise>
				条件不成立
			</itcast:otherwise>
	</itcast:choose>
   	
  </body>
</html>
