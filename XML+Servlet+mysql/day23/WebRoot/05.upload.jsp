<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>文件上传的细节</title>  
  </head>
  
  <body>
  	<font color="red">${msg }</font><!-- 打印保存在request域的异常信息 -->
    	<form action="${pageContext.request.contextPath }/UploadDemo4" method="post" enctype="multipart/form-data">
    		请选择上传的文件1:<input type="file" name="attachment1" />描述1： <input type="text" name="info1"/><br/>
    		请选择上传的文件2:<input type="file" name="attachment1" />描述2： <input type="text" name="info2"/><br/>
    		<!-- 
    		请选择上传的文件3:<input type="file" name="attachment1" /><br/>
    		请选择上传的文件4:<input type="file" name="attachment1" /><br/>
    		请选择上传的文件5:<input type="file" name="attachment1" /><br/>
    		 -->
    		<input type="submit" value="上传"/>
    	</form>
  </body>
</html>
