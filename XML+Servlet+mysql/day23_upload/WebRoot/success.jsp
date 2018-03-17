<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> <title>文件列表</title>  </head>
  <body>
    <h3>文件上传成功！ </h3>
    <table border="1" width="500px">
    	<tr>
    		<th>编号</th>
    		<th>文件名</th>
    		<th>大小</th>
    		<th>类型</th>
    		<th>上传时间</th>
    	</tr>
    	<c:forEach items="${fileList}" var="bean" varStatus="varSta">
    	<tr>
    		<td>${varSta.count }</td>
    		<td>${bean.name }</td>
    		<td>${bean.size }</td>
    		<td>${bean.type }</td>
    		<td>${bean.addTime }</td>
    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>