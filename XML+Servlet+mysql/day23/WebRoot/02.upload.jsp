<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>My JSP '02.upload.jsp' starting page</title>



</head>

<body>
	<form action="${pageContext.request.contextPath }/UploadDemo2" method="post" enctype="multipart/form-data">
		请选择上传的文件1:<input type="file" name="attachment1" /> <input type="submit" value="上传" />
	</form>
</body>
</html>
