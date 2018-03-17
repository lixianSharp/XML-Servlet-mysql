<%@ page language="java" import="java.util.*" pageEncoding="utf-8" 
isErrorPage="true" 
session="true"
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>pageContext对象</title>  
  </head>
  
  <body>
    <%
    	//1）可以获取其他8个内置对象
    	response.getWriter().write("是否相等？"+(out==pageContext.getOut()) +"<br/>");//true
    	response.getWriter().write("是否相等？"+(session==pageContext.getSession()));//true
     %>
     
     <br/>
     
     <%--
     	2)pageContext作为域对象使用
     			2.1 可以往不同域对象中存取数据
      --%>
      <%
      	//保存数据。默认情况下，保存在page域中
      	pageContext.setAttribute("message","page's message");
      	pageContext.setAttribute("message","request's messsage",PageContext.REQUEST_SCOPE);//保存到request域中
      	pageContext.setAttribute("message","session's messsage",PageContext.SESSION_SCOPE);//保存到sessio域中
      	pageContext.setAttribute("message","application's messsage",PageContext.APPLICATION_SCOPE);//保存到context域中
      	//request.setAttribute("message","request's message"); 等价于上面的代码
       %>
       
       <%
       	//获取数据
       	String message = (String)pageContext.getAttribute("message");
       	out.write("这是用out输出的message:"+message);//结果：这是用out输出的message:page's message 
        %>
        <br/>
        <%--从request域中取出数据 --%>
        <%--
     	      原则： 
           	1）在哪个域中保存数据，就必须从哪个域取出数据！！！
         --%>
        <%=pageContext.getAttribute("message",PageContext.PAGE_SCOPE) %><br/> 
        <%=pageContext.getAttribute("message",PageContext.REQUEST_SCOPE) %><br/>
        <%=pageContext.getAttribute("message",PageContext.SESSION_SCOPE) %><br/>
        <%=pageContext.getAttribute("message",PageContext.APPLICATION_SCOPE) %><br/>
        <!-- 上面四行的结果： -->
        <!-- 
	        page's message
			request's messsage
			session's messsage
			application's messsage
		-->
        <hr/>
        <%--
        	findAttribute(): 在四个域自动搜索数据
        		顺序： page域 -> request域  -> session域 -> context域
        		
         --%>
         <%=pageContext.findAttribute("message") %><!-- 结果：page's message  --><br/>
         <% //request.getAttribute("message") %><br/>
      
      
      <%
      /* 
      	域对象作用范围：
			page域： 只能在当前jsp页面中使用（当前页面）
			request域： 只能在同一个请求中使用（转发）
			session域： 只能在同一个会话（session对象）中使用（私有的）
		    context域： 只能在同一个web应用中使用。（全局的）
       */
       
      	//转发技术
      	//request.getRequestDispatcher("/03.pageContext2.jsp").forward(request,response);
      	//上行代码在03.pageContext2.jsp页面中对应的结果如下：
    	/* 
    	page域：null  因为page域的作用范围只在当前jsp页面有效。
		request域： request's messsage
		session域： session's messsage
		context域：application's messsage  	
      	 */
      	
      	//重定向技术
      	response.sendRedirect(request.getContextPath()+"/03.pageContext2.jsp");
      	////上行代码在03.pageContext2.jsp页面中对应的结果如下：
      	/* 
      	page域：null
		request域： null
		session域： session's messsage  
		context域：application's messsage
      	 */
       %>
  </body>
</html>
