<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>百万富翁数字竞猜游戏</title>  
  </head>
  
  <body>
  	<%
  		//从request域中取出信息
  		String msg = (String)request.getAttribute("msg");
  		if(msg!=null){
  			out.write("<font color='red'>"+msg+"</font>");
  		}
  	 %>
  	 <%
  	 	//获取竞猜次数
  	 	Integer times = (Integer)request.getAttribute("times");
  	 	if(times!=null){
  	 		out.write(",你还有"+(5-times)+"次机会！");
  	 	}
  	  %>
  	  
    <form action="/day13/GuessServlet" method="post">
    	请输入30以下的整数：<input type="text" name="lucyNo"/><br/>
    	<%
    		if(times!=null){
    	 %>
    	<input type="hidden" name="times" value="<%=times %>"/>
    	<%
    	}
    	 %>
    	<input type="submit" value="开始竞猜"/>
    </form>
  </body>
</html>
