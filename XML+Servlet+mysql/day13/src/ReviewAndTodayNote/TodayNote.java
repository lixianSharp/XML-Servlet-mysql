package ReviewAndTodayNote;
/**
  1、课程回顾：
  	Jsp基础
  		1）Jsp的执行过程
  			tomcat服务器完成：jsp文件-》翻译成java文件-》编译成class字节码文件-》构造类对象-》调用方法
  				Tomcat的work目录下存放jsp运行时的临时文件！！
  		
  		2）Jsp语法
  			1）jsp表达式：  <%=变量或表达式%> 作用；用于向浏览器输出变量或表达式计算的结果
  			2）jsp脚本： <%java代码%>  作用：执行java代码 原理：翻译到_jspService方法中
  			3）jsp声明：<%！变量或方法%>声明jsp的成员变量或成员方法
  			4）jsp注释：<%!-- jsp注释 --%> 用于只是jsp代码，不会翻译到java文件中，也不会执行
  		
  		3）jsp的三大指令
  			3.1 include指令：用于包含其他页面。原理：先合并再翻译。叫静态包含
  			3.2 page指令：包宿Tomcat服务器如何把jsp文件翻译成java文件。
  					language:翻译的动态语言
  					import： 类导入
  			jsp文件编码问题：
  					pageEncoding： 告诉Tomcat服务器使用什么编码翻译jsp文件(jsp->java文件)
  					contentType：tomcat服务器发送给浏览器的数据编码(tomcat服务器-》浏览器)
  
  			异常错误相关的：
  				errorPage：指定当前jsp页面的错误处理页面。
  				isErrorPage:指定当前页面是否为错误处理页面.false: 不是错误处理页面，则不能使用exception内置对象；
  												true:是错误处理页面，可以使用exception内置对象
  												
  			 配置全部的错误处理页面：
  			 <!-- 全局错误处理页面配置  -->
  			 <error-page>
  			 	<error-code>500</error-code>
  		 	 	<location>/common/500.jsp</location>  	
  			 </error-page>
    		 <error-page>
    			<error-code>404</error-code>
    			<location>/common/404.html</location>
    	     </error-page>
  		
  
  			
  			session： 是否开启session功能。 false：不能用session内置对象。true：可以使用session内置对象。
  			buffer： jsp页面的缓存区大小
  			isELIgnore： 是否忽略EL表达式。
  
  
  
  2、Jsp的内置对象(重点)
  	
  		2.1 什么是内置对象？
  		
  		在jsp开发中，会频繁使用到一些对象。例如HttpSession，ServletContext，ServletConfig，HttpServletRequest。
  				如果我们每次要使用这些对象都去创建这些对象，就显得非常麻烦。所以Sun公司设计Jsp是，在jsp页面加载 完毕之后就会自动帮开发者创建好这些对象，
  					而开发者只需要直接使用这些对象调用方法即可！！这些创建好的对象就叫内置对象！！！
  			  		举例：
  			  		Servlet:
  			  			HttpSession session=request.getSession(true);（需要开发者做）
  					
  					jsp:
  					    tomcat服务器： HttpSession session=request.getSession(true);（不需要开发者做）
  		
  		
  	    2.2  9大内置对象
  	  		内置对象名 			类型
  	  		request	     HttpServletRequest
  	  		response	 HttpServletResponse
  	  		config		 ServletConfig
  	  		application  ServletContext
  	   		session		 HttpSession
  	   		exception	 Throwable
  	   		page		 Object(this)（01.hello.jsp当前这个类的对象）
  	   		out			 JspWriter
  			pageContext	 PageContext
  		
  	   
  	   2.3 Out对象
  	   		 out对象类型，JSPWriter类，相当于带缓冲的PrintWriter
  				
  				PrintWriter:
  						writer("内容");直接向浏览器写出内容
  				JspWriter：
  					 writer("内容");向jsp缓冲区写出内容
  
  
  	    2.4 pageContext对象
  	    	
  	    	pageContext对象的类型是PageContext，叫jsp的上下文对象
  	    	1)可以获取其他八个内置对象
  				
  				public class 01_hello_jsp{
  					public void jspService(request,response){
  						创建内置对象
  						HttpSession session=...;
  						ServletConfig config=..;
  						
  						把8个经常使用的内置对象封装到PageContext对象中
  						PageContext pageContext =封装；
  						调用method1()方法
  						method1(session,config);
  					}
  					
  					public void method1(PageContext pageContext){
  						希望使用内置对象
  						从pageContext对象中获取其他8个内置对象
  						JspWriter out=pageContext.getOut();
  						HttpSession session=out.get。。。
  					}
  				
  				}
  
  				
  			  使用场景：在自定义标签的时候，PageConxt对象频繁使用到！！！
  		   
  		   2）page域本身也是一个域对象
  		   		
  		   		ServletCOntext context 域
  				HttpServletRequest  request 域
  				HttpSession    session 域 --Servlet学习的
  				PageContext   page 域  --jsp学习的
  				jsp有四个域对象 ， Servlet有三个域对象
  
  				
  			  作用：保存数据和获取数据，用于共享数据。
  			  	
  			   #保存数据
  			   	2.1 默认情况下，保存到page域
  			   		pageContext.setAttribute("name");
  				2.2可以向四个域对象保存数据
  					pageContext.setAttribute("name","域范围常量");
  				
  				域范围常量：
  					PageContext.PAGE_SCOPE
  					PageContext.REQUEST_SCOPE
  					PageContext.REQUEST_SCOPE
  					PageContext.APPLICATION_SCOPE
  
  			  #获取数据
  			  	1）默认情况下，从page域获取
  			  		pageContext.getAttribute("name");
  				2）可以从四个域中获取数据
  					pageContext.getAttribute("name",域范围常量);
  				  
  				  域范围常量：
  				  	PageContext.PAGE_SCOPE
  					PageContext.REQUEST_SCOPE
  					PageContext.REQUEST_SCOPE
  					PageContext.APPLICATION_SCOPE
  				
  				3）自动在四个域中搜索数据
  					pageContext.findAttribute("name");
  				   顺序：page域-》request域-》session域-》context域(application域)
  
  
  	3、Jsp中的四个域对象
  		
  		四个：
  			pageContext  page域
  			request      request域
  			session		 session域
  			application  context域
  		
  		1）域对象作用
  			保存数据 和 获取数据，用于数据共享。
  		2）域对象方法：
  			setAttribute("name",Object);保存数据
  			getAttribute("name");获取数据
  			removeAttribute("name");清除数据
  		
  		3）域对象作用范围
  			 page域： 只能在当前jsp页面中使用
  			 request域： 只能在同一个请求中使用
  			 session域：只能在同一个会话（session对象）中使用
  			 context域：只能在同一个web应用中使用
  			
	
	4、Jsp的最佳实践
		
		 Servlet技术：开发动态资源。是一个java类，最擅长写java代码。
		 jsp技术：开发动态资源。通过java代码最擅长输出html代码。
  		
  		各取所长：
  			在web项目中涉及到的逻辑：
				1）接收参数		Servlet做
				2）处理业务逻辑，返回结果	Servlet做
				3）显示数据到浏览器	 Jsp做
				4）跳转到其他页面		 Servlet做
  
  		Servlet+Jsp模式
  			
  			Servlet：
  				1）接收参数
  				2）处理业务逻辑
  				3）把结果保存到域对象中
  				3）跳转到jsp页面
  			 Jsp
  			 	1）从域对象取出数据
  			 	2）把数据显示到浏览器
  			 	
  			servlet的数据 --> jsp页面
  			 List<Contact> 使用域对象  共享数据
  		
  		
  	 5、EL表达式
  	 	 5.1 EL作用
  	 	 	
  	 	 	Jsp的核心语法，jsp表达式<%=%>和jsp脚本<% %>。
  	 	 	以后开发jsp的原则：尽量在jsp页面少些甚至不写java代码。
  			
  			使用EL表达式替换掉jsp表达式。
  			
  			EL表达式作用：向浏览器输出 域对象中的 变量值或表达式计算的结果！！！
  			
  			语法： ${变量或表达式}
  
  		5.2 EL语法
  			
  			1）输出基本数据类型变量
  				1.1 从四个域获取
  				   ${name}
  				1.2 指定域获取
  					$(pageScope.name)
  			  域范围：pageScope/requestScope/sessionScope/applicationScope
  
  			2) 输出对象的属性值
  					Student
  			3）输出集合对象
  			    List 和  Map
  			4) EL表达式计算
  
   6 Jsp标签
     	
     	6.1 Jsp标签的作用：
     		jsp标签作用，替换jsp脚本。
     		
     		1）流程判断(if for 循环)
     		2）跳转页面(转发，重定向)
     		3）
     	6.2 Jsp标签分类
     		1）内置标签(动作标签) 特点：不需要在jsp页面导入标签
     		2）jspl标签(外置标签) 特点：需要在jsp页面导入标签
     		3）自定义标签：开发者自行定义，需要在jsp页面导入标签
     		
     	6.3 动作标签：
     		转发标签：<jsp:forword/>
  			参数标签：<jsp:pararm/>
  			包含标签：<jsp:inclu>
  				原理：包含于被包含的页面先各自翻译成java源文件，然后在运行时合并在一起。
  				    (先翻译，再合并),动态包含
  
  			  	静态包含       vs	 动态包含
  			  	
  			   1）语法不同：
  			   	静态包含语法：  <%@include file="被包含的页面"%>
  				动态包含语法： <jsp:include page="被包含的页面">
  				
  			   2)参数传递不同
  			   	静态包含不能向被包含页面传递参数
  			   	动态包含可以向被包含页面传递参数
  			   
  			   3）原理不同：
  			   	静态包含：先合并在翻译。
  			   	动态包含：先翻译再合并。
  
  		
  		6.4 JSTL标签
  		  
  		  JSTL(全名：java stander tag library --java标准标签库)
  		  
  		  核心标签库 (c标签库)（天天用）
  		 国际化标签库(fmt标签库)
  		 EL函数库(fn函数库)
  		 xml标签库(x标签库)（这套等于废了，很少使用）
  		 sql标签库(sql标签库)（这套等于废了，也很少使用）
  
  		6.5 使用JSTL标签步骤：
  		   
  		   1）导入jstl支持的jar包(标签背后隐藏的java代码)
  		   		注意：使用javaee5.0的项目自动导入jstl支持jar包
  		   		
  		   2）使用taglib指令导入标签库
  			<%@taglib uri="tld文件的uri名称" prefix="简写" %>
  		   	 
  		   3)在jsp中使用标签	
  		   
  		   保存数据：
  			  <c:set></c:set>
    	   获取数据：	 
    		 <c:out value=""></c:out>
    	  单个条件判断：
    		  <c:if test=""></c:if>
    	  多条件判断：  
    		  <c:choose></c:choose>
   		   	  <c:when test=""></c:when>
    		  <c:otherwise></c:otherwise>
    	  循环数据：  
    		  <c:forEach></c:forEach>
    		  <c:forTokens items="" delims=""></c:forTokens>
                         重定向：
    		  <c:redirect></c:redirect>
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
*/

















































































public class TodayNote {

}
