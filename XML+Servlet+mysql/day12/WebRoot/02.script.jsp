<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Jsp语法</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <!-- Jsp表达式 -->
    <%
    	//变量
    	String name="eric";
    	int a=10;
    	int b=20;
     %>
    
    <%=name %>
    <br/>
    <%=(a+b) %>
    <hr/>
    <!-- jsp脚本 -->
    <%
    //生成随机数
    Random ran=new Random();
    float num=ran.nextFloat();
     %>
    
    随机小数<%=num %>
   
   <!-- 穿插html代码  -->
   <%
   	for(int i=1;i<=6;i++){
   	%>
   	<h<%=i %>>标题1<h<%=i %>>
   <%
   	}
    %>
   <hr/>
   <!-- 练习：使用脚本和html代码显示99乘法表 -->
   <h3>
 	<%
 		for(int i=1;i<=9;i++){
 			for(int j=1;j<=i;j++){
 			  int sun=j*i;
 			  String sum=String.valueOf(sun);
 			  out.write(j+"*"+i+"="+sum+"\n");
 			
 			  if(j==i){
 			  	%>
 			  	<br/>
 			  	<%
 			  }
 			}
 		}
 	 %>
   </h3>
   <br/>
   <hr/>
   <!-- 九九乘法表 的做法2 -->
   <%
   for(int i=1;i<=9;i++){//行
 			for(int j=1;j<=i;j++){//公式  
    %>
    	<%=i %>x<%=j %>=<%=(i*j) %>&nbsp;
   <% 
   		}
   %>
   		<br/>
   	<%
   		}
   	 %>	
   
   
   <%
   	String age="20";
   	
   	/*脚本中不能声明方法
   	 public String getAge(){
   	 	return age;
   	 }
   	
   	*/
   
    %>
   
   
   <!-- jsp声明 -->
   <%!
   	//变量
   	String name1="lixianyuan";
   	
   	public String getName(){
   		return name1;
   	}
   	
   	/* jsp声明中不能重复定义翻译好的一些方法
   	public void _jspInit(){
   	
   	}
   	 */
    %>
   
   <!-- html注释 -->
   <%-- <jsp:forword page="/01.hello.jsp"></jsp:forword> --%>
   <%-- jsp注释 --%>
  </body>
</html>

















