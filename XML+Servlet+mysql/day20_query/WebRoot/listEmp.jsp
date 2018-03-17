<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>分页查询员工数据</title>


</head>

<body>
	<table border="1" align="center" width="800px">
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>职位</th>
			<th>邮箱</th>
			<th>薪水</th>
		</tr>
		<c:forEach items="${pageBean.data }" var="emp">
		<tr>
			<td>${emp.id }</td>
			<td>${emp.name }</td>
			<td>${emp.gender}</td>
			<td>${emp.title }</td>
			<td>${emp.email }</td>
			<td>${emp.salary }</td>
		</tr>
		</c:forEach>
		<tr>
			<td align="center" colspan="6">
			<%--
			需求：
				1）如果当前页面时首页，那么不能点击"首页"和”上一页“，否则可以点击
				2）如果当前页是末页，那么不能点击"下一页"和"末页"，否则可以点击
				
			 --%>
			  <c:choose>
			  		<c:when test="${pageBean.currentPage==pageBean.firstPage }">
			  			首页&nbsp;
			  			上一页
			  		</c:when>
			  		<c:otherwise>
						<a href="${pageContext.request.contextPath }/ListEmpServlet?currentPage=${pageBean.firstPage}&pageSize=${pageBean.pageSize}">首页</a>${pageBean.firstPage }&nbsp;
						<a href="${pageContext.request.contextPath }/ListEmpServlet?currentPage=${pageBean.prePage}&pageSize=${pageBean.pageSize}">上一页${pageBean.prePage }</a>&nbsp;
			  		</c:otherwise>
			  </c:choose>
				
			  <c:choose>
			  	<c:when test="${pageBean.currentPage==pageBean.totalPage }">
			  		下一页&nbsp;
			  		末页
			  	</c:when>
			  	<c:otherwise>
			  		<a href="${pageContext.request.contextPath }/ListEmpServlet?currentPage=${pageBean.nextPage}&pageSize=${pageBean.pageSize}">&nbsp;下一页 ${pageBean.nextPage }&nbsp;
					<a href="${pageContext.request.contextPath }/ListEmpServlet?currentPage=${pageBean.totalPage}&pageSize=${pageBean.pageSize}">末页</a>${page.totalPage }</a>&nbsp;
			  	</c:otherwise>
			  </c:choose>
				当前为第${pageBean.currentPage }页/共${pageBean.totalPage }页&nbsp;
				共${pageBean.totalCount }条数据&nbsp;每页显示${pageBean.pageSize }
				每页显示<input type="text" size="2" id="pageSize" value="${pageBean.pageSize }" onblur="changePageSize()"/>条
				跳转到<input type="text" size="2" id="curPage" onblur="changePage()"/>页
			</td>
		</tr>
		
		
	</table>
</body>
	<script type="text/javascript">
		/*改变每页显示记录数*/
		function changePageSize(){
		//1、得到用户输入
		var pageSize=document.getElementById("pageSize").value;
		//判断规则：只能输入1~2个的数字
		var reg=/^[1-9][0-9]?$/;//如果不加^ $ 则叫局部匹配
		if(!reg.test(pageSize)){
			alert("只能输入1~2位的数字！");
			return;
		}
		//2、请求ListEmpServlet，同时发送参数(pageSize)
		var url="${pageContext.request.contextPath}/ListEmpServlet?pageSize="+pageSize;
		window.location.href=url;
		}
		
		
		/*跳转页面*/
		
		function changePage(){
			var curPage=document.getElementById("curPage").value;
			var reg=/^[1-9][0-9]?$/;//如果不加^ $ 则叫局部匹配
			if(!reg.test(curPage)){
				alert("只能输入1~2位的数字！");
				return;
			}
		var url="${pageContext.request.contextPath}/ListEmpServlet?currentPage="+curPage+"&pageSize=${pageBean.pageSize}";
		window.location.href=url;
		
		/*如果输入的页码大于等于总页数*/
		var totalPage="${pageBean.totalPage}";		
		if(curPage>totalPage){
			alert("已经超过总页数");
			return;
		}
	   }
	</script>

</html>
