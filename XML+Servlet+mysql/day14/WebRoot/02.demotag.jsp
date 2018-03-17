<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!-- 导入自定义的标签库 -->
<%@taglib uri="http://gz.itcast.cn" prefix="itcast" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>第二个自定义标签</title>
  
		
  </head>
  
  <body>
   <itcast:demoTag num="1">AAAAA</itcast:demoTag><br/><hr/>
   	<!-- 在jsp中使用自定义标签 -->
    <itcast:demoTag num="2">xxxx${10+5}</itcast:demoTag>
   标签余下内容
  </body>
</html>
