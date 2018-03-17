<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>My JSP '02.upload.jsp' starting page</title>



</head>

<body>
	<!-- 实现文件上传的三个条件：
		1）表单的提交方式必须是POST方式(才有content-type属性)
		2）有文件上传表单，表单中有<input type="file"/>的选择文件的标签
		3）把表单设置为enctype="multipart/form-data",提交的数据不再是key-value键值对,而是字节数据
  	-->
	<form action="${pageContext.request.contextPath }/UploadDemo3" 
			method="post" enctype="multipart/form-data">
		上传人：<input name="upPersonName" value=""/>	
		请选择上传的文件1:<input type="file" name="img1"/><br/>
		请选择上传的文件2:<input type="file" name="img2" /><br/>
		请选择上传的文件3:<input type="file" name="img3" /><br/>
		 <input type="submit" value="上传" />
	</form>
</body>
</html>
