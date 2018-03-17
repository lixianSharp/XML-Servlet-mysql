package ReviewAndNote;
/**
 
  1、课程回顾
  		
  	  http协议：
  	  	1）http协议：对浏览器客户端和服务器端之间数据传输的格式规范。(B/S)
  		2）http请求：浏览器--》服务器端
  			格式：
  				请求行：     (请求方式(GET/POST) 请求资源(URI) http协议版本(http1.1))    //有三块内容，每块以空格分开
  				请求头：    (键值对形式存在。 host、user-agent、referer)
				一个空行
				实体内容(POST提交的参数)
  				
  		    HttpServletRequest对象： 请求对象。获取请求信息
  		         请求行：request.getMethod()   request.getRequestURI/getRequestURL()  request.getProtocol()
  			请求头： request.getHeader("name"); request.getHeaderName();
  			实体内容：	request.getInputStream()
  			
  			获取参数数据。(GET或POST)
  				request.getParameter("name"); 一个值的参数
  				request.getParameterValues("name");多个值的对象
  				request.getParameterName();  所有参数
  				
  	  3）http响应： 服务器--》浏览器端
  	  		格式：
  	  			响应行(http协议版本 状态码 描述)
  	  				常用的状态码：	200 302 404 500
  	  			响应头(location（结合302状态码完成请求重定向功能）、refresh（定时刷新）content-type（数据类型、编码格式）、content-disiposition（以下载方式打开）)
  	  			一个空行
  	  			实体内容（在浏览器中不按F12直接能看到的，即不用FireBug工具能直接看到的）
  	 		HttpServletResponse对象：响应对象。设置相应信息。
  	 			响应行：response.setStatus();
  	 			响应头：response.setHeader("name","value");
  				实体内容：
  					(PrintWriter) response.getWriter();字符内容
  					(OutputStream) response.getOutputStream.write();字节内容
  
  
  
  今天的目标： servlet编程
  		第一天：入门    第二天：http协议	第三天 ：如何写servlet程序
  
  	Servlet学习大纲：
  		1. servlet概念及相关接口简介
		2. servet 执行过程
		3. servlet路径映射
		4. 缺省servlet	   --- 前四个属于应用级别的
		5. servlet生命周期(重点)	---要理解才懂得的(今天的重点，Servlet的原理，如果这个理解不好的话，对于写Servlet是一个障碍)
		6. Servlet自动加载		--6之后的属于零散的知识点
		7. Servlet线程安全
		8. servletConfig对象
		9. Servlet相关接口详解
		10. ServletContext对象

  
  1、如何开发一个Servlet
  		
  	  1.1步骤：
  	  	1）编写java类，继承HttpServlet类
  	  	2）重写doGet和doPost方法
  		3）Servlet程序交个tomcat服务器运行
  			3.1servlet程序的class字节码拷贝到WEB-INF/classes目录
  			3.2在web.xml文件中进行配置
  
  		<!-- 配置一个servlet -->
  		<!-- 这是一个servet的配置  -->
  	<servlet>
  		<!-- servlet的内部名称，自定义，尽量有意义 -->
  		<servlet-name>FirstServlet</servlet-name>
  		<!-- servlet的类全名: 包名+简单类名 -->
  		<servlet-class>gz.itcast.a_servlet.FirstServlet</servlet-class>
  	</servlet>
 
 	<!-- servlet的映射配置 -->
  	<servlet-mapping>
  		<!-- servlet的内部名称：一定要和上面的内部名称保持一致！ -->
  		<servlet-name>FirstServlet</servlet-name>
  		<!-- servlet的映射路径（访问Servlet的名称） -->
  		<url-pattern>/first</url-pattern>
    </servlet-mapping>
  
  	问题：访问次URL：        http://localhost:8080/day10/first
  	
  	  前提：tomcat服务器启动时，首先加载webapps中的每个web应用中的web.xml配置文件
  
  		http:// ：  http协议
  		localhost：   到本地的hosts文件中查找是否存在该域名对应的地址
  						192.168.1.101
  		8080：       找到tomcat服务器
  		/day10      在tomcat的webapps目录下找day10的目录
  		/first	资源名称
  		 		1）在day10的web.xml中查找是否有匹配的url-pattern的内容（/first）
  		 		2）如果找到匹配的url-pattern,则使用当前servlet-name的名称到web.xml文件中查询是否相同名称的
  		 				servlet配置。
  				3）如果找到，则取出对应的servlet配置信息中的servlet-class内容
  					字符串：     gz.itcast.a_servlet.FirstServlet
  			
  			通过反射：
  				1）构造FirstServlet的对象
  				2）然后调用FirstServlet里面的方法
  
  	
   2、Servlet的映射路径
  
  	  <!-- servlet的映射配置 -->
  	  <servlet-mapping>
  		  <!-- servlet的内部名称：一定要和上面的内部名称保持一致！ -->
  		  <servlet-name>FirstServlet</servlet-name>
  		  <!-- servlet的映射路径（访问Servlet的名称） -->
  		  <url-pattern>/first</url-pattern>
      </servlet-mapping>
  

     		   url-pattern		           浏览器输入
         精确匹配： 		/first			    http://localhost:8080/day10/first
     			/itcast/Demo1		http://localhost:8080/day10/itcast/Demo1
  
  
        模糊匹配		/*					http://localhost:8080/day10/任意路径
  				/itcast/*			http://localhost:8080/day10/itcast/任意路径
  				*.后缀名				http://localhost:8080/day10/任意路径.后缀名
  				*.html(伪静态)		http://localhost:8080/day10/任意路径.html
  				*.action			http://localhost:8080/day10/任意路径.action
  				*.do				http://localhost:8080/day10/任意路径.do
  	
  	
  	注意：
  		1）url-pattern 要么以 / 开头  ， 要么以 * 号开头
  		2）不能同时使用两种的模糊匹配：例如：    /itcast/*.do
  		3）当我们输入的URL有多个servlet同时被匹配的情况下：
  				3.1 精确匹配优先  长的最像的优先匹配
  				3.2 以后缀名结尾的模糊url-pattern匹配优先级最低！！！
  
  
   3、servlet缺省路径
  
  		servlet的缺省路径(<url-pattern><url-pattern>)是在tomcat服务器内置的一个路径。该路径对应的是一个DefaultServlet（缺省Servlet）。
  		这个缺省的Servlet的作用是用于解析web应用的静态文件。
  Tomcat总的配置文件conf文件夹中的web.xml  动态文件就是servlet程序，静态文件就例如xml、html之类的文件。
  
  	问题： URL输入http://localhost:8080/day10/index.html 如何读取文件？
  
  		1）到当前的day10应用下的web.xml文件查找是否有匹配的url-pattern.
  		2）如果没有匹配的url-pattern,则交给tomcat的内置的DefaultServlet处理。
  		3）DefaultServlet程序到day10应用的根目录下查找是否存在一个名称为index.html的静态文件，
  				如果找到该文件，则读取该文件内容，返回给浏览器。如果找不到该文件，则返回404错误页面。
  				
  		结论：先找动态资源，再找静态资源
  
  
    4、Servlet的四个生命周期(重点)
  		
  		4.1引入
  			Servlet的生命周期：servlet类对象什么时候创建。什么时候调用什么方法，什么时候销毁
  
  			以前的对象：   new Student(); stu.study(); stu=null;
  			
  			Servlet程序的声明周期由tomcat服务器控制的！！！
  			
  		4.2 Servlet重要的生命周期
  		
  			构造方法：创建servlet对象的时候调用。默认情况下，第一次访问Servlet的时候创建servlet对象。
  					只调用了一次。证明servlet对象在Tomcat是单实例的。
  			init方法：创建完servlet对象的时候。只调用一次。
  			service方法：每次发出请求时调用。 调用n次
  			destroy方法：销毁servlet对象的时候调用。停止服务器或者重新部署web应用时销毁servlet对象。 只调用一次
  			
   		4.3 伪代码演示servlet的生命周期
  		  Tomcat内部代码运行：	
  			1）通过映射查找到servlet-class的内容。 字符串：gz.itcast.a_servlet.FirstServlet
  			2）通过反射构造FirstServlet对象
  				2.1 得到字节码对象
  				     Class clazz= class.forName("gz.itcast.a_servlet.FirstServlet");
  				2.2 调用无参数的构造方法来构造对象
  					Object obj=clazz.newInstance();	---1、servlet的构造方法被调用
  					
  			3）创建ServletConfig对象，通过反射调用init方法。
  					3.1得到方法对象
  					 Method m = class.getDeclareMethod("init",ServletConfig.class);
  					3.2调用方法
  					 m.invoke(obj,config);--2、servlet中的init方法被调用
  					 
  			4）创建request、response对象，通过反射调用service的方法。
  				4.1得到过方法对象
  					Method m = clazz.getDeclareMethod("service",HttpServletRequest.class,HttpServletResponse.class);
  				4.2调用方法
  					m.invoke(obj,request,response); --3.servlet的service方法被调用
  			5）当tomcat服务器停止或web应用重新部署，通过反射调用destroy方法
  				5.1得到方法对象
  				 clazz.getDeclareMethod("destroy",null);
  				5.2 调用方法
  				 m.invoke(obj,null); --4、Servlet的destroy方法被调用
  
  	   
  	   4.4 用时序图来演示Servlet的生命周期
  				
  	   5、Servlet的自动加载
  		默认情况下，第一次访问Servlet的时候创建Servlet对象，通过Servlet的构造方法或init方法中执行了比较多的逻辑代码，那么导致用户第一次访问servlet的时候比较慢。
  		
  		改变Servlet创建对象的时机，提前到加载web应用的时候！！！
  		
  		做法：在Servlet的配置信息中，加上一个<load-on-startup>即可！
  		  <servlet>
    		<servlet-name>LifeDemo</servlet-name>
    		<servlet-class>gz.itcast.c_life.LifeDemo</servlet-class>
    		<!-- 让Servlet对象自动加载 -->
    		<load-on-startup>1</load-on-startup>    注意：整数值越大，创建优先级越低！！
  		  </servlet>
  
  	   
  	   7、有参的init方法和无参的init方法
  	   
  	   8、Servlet的多线程并发问题
  	   		注意：Servlet对象在tomcat是单实例多线程的。
  		
  			因为servlet是多线程的，所以当多个Servlet的线程同时访问了Servlet的共享数据，如成员变量，可能会引发线程安全问题。
  			
  			解决办法：
  				1）把使用到共享数据的代码块进行同步（使用synchronize关键字进行同步）
  				2）建议在Servlet类中尽量不要使用成员变量，如果确实要使用成员变量，必须要同步，而且要尽量缩小同步代码快的范围。（哪里使用到了成员变量就同步哪里！！）
  						以避免因为同步而导致并发的效率降低。
  						
  	    Servlet学习：
  	    	HttpServletRequest：请求对象：获取请求信息。
  	    	HttpServletResponse 响应对象：设置响应对象。
  	    	ServletConfig对象：  servlet配置对象
  	    	ServletContext对象： servlet的上下文对象
  						
  						
  	  9、ServletConfig对象
  	  		9.1作用：
  	  			ServletConfig对象：主要是用于加载Servlet的初始化参数。一个web应用中可以存在多个ServletConfig对象。（一个Servlet对应一个ServletConfig对象）
  	  		
  	  		9.2对象创建和得到
  	  		 
  	  		 创建时机：在创建完servlet对象之后，在调用init方法之前创建。
  	  		 得到对象：直接从有参数的init方法中得到！！！
  	  		 
  	  		9.3 Servlet的初始化参数配置
  				
  				  <!--  初始化参数：这些参数会在加载web应用的时候，封装到ServletConfig对象中 -->
    			<init-param>
    				<param-name>path</param-name>
    				<param-value>d:/a.txt</param-value>
    			</init-param>
     			<init-param>
    				<param-name>BBB</param-name>
    				<param-value>BBB's value</param-value>
    			</init-param>  
     			<init-param>
    				<param-name>CCC</param-name>
    				<param-value>CCC's value</param-value>
    			</init-param>
  				
  				
  				
  				
  				ServletConfig的API：
  					java.lang.String getInitParameter(java.lang.String name); 根据参数名获取参数值
					java.util.Enumeration getInitParameterNames(); 获取所有参数
 					ServletContext getServletContext(); 得到servlet上下文对象  
 					String getServletName();得到Servlet的名称  
  						
  				
  	10、ServletContext对象		（可以理解为代表整个web.xml的信息）		
  		 10.1 引入
  		 	  ServletContext对象，叫做Servlet的上下文对象。表示一个当前的web应用环境。一个web应用中只有一个ServletContext对象
  			  
  		10.2 对象是如何创建和得到？
  			  创建时间：加载web应用时创建ServletContext对象。
  			  得到对象：从ServletConfig的getServletContext方法得到。
  			 
  			 
  			 	
			我们设计：
				创建ServletConfig对象
				public void init( ServletConfig config，ServletContext context ){  多了一个参数
					得到ServletConfig对象
					得到ServletContext对象；
				}


  			  
  			 Sun公司设计
  			  	1）创建ServletContext对象  ServletContext context=new ServletContext();
  			  	
  			  	2）创建ServletConfig对象   ServletConfig  config=new ServletCongif();
  			  						  config.setServletContext(context);
  			  									
  			  		class ServletConfig{
  			  			ServletContext context;
  			  			public ServletContext getServletContext(){
  			  				return context;
  			  			}
  			  		}
  			  		
  			  		public void init(ServletConfig config){
  			  			 得到ServletConfig对象
  			  			 
  			  			从ServletConfig中得到ServletContext对象 
  			  			ServletContext context=config.getServletContext();
  			  		}
  			  
  			  
  		 10.1 ServletContext对象核心的API
  					
			java.lang.String getContextPath()   --得到当前web应用的路径

			java.lang.String getInitParameter(java.lang.String name)  --得到web应用的初始化参数
			java.util.Enumeration getInitParameterNames()  

			void setAttribute(java.lang.String name, java.lang.Object object) --域对象有关的方法
			java.lang.Object getAttribute(java.lang.String name)  
			void removeAttribute(java.lang.String name)  

			RequestDispatcher getRequestDispatcher(java.lang.String path)   --转发（类似于重定向）

			java.lang.String getRealPath(java.lang.String path)     --得到web应用的资源文件
			java.io.InputStream getResourceAsStream(java.lang.String path)  
	
  				
  						
  		10.3 得到web应用的路径
  			  java.lang.String.getContextPath(); 用在请求重定向的资源名称中
  			  
  		10.4 得到web应用的参数	  
  			java.lang.String getInitParameter(java.lang.String name)  --得到web应用的初始化参数
			java.util.Enumeration getInitParameterNames()  

				web应用参数可以让当前web应用的所有servlet获取！！！
				
		10.5域对象有关的方法
				域对象：作用是用于保存数据，获取数据。可以在不同的动态资源之间共享数据。

					案例：   
					Servlet1                   Servlet2
			        name=eric                     
				response.sendRedirect("/Servlet2?name=eric")             String request.getParameter("name");
					保存到域对象中          				  从域对象获取
					Student                  
				方案1： 可以通过传递参数的形式，共享数据。局限：只能传递字符串类型。
				方案2： 可以使用域对象共享数据，好处：可以共享任何类型的数据！！！！！

				ServletContext就是一个域对象！！！！

			保存数据：void setAttribute(java.lang.String name, java.lang.Object object)					
			获取数据： java.lang.Object getAttribute(java.lang.String name)  
			删除数据： void removeAttribute(java.lang.String name)  

			ServletContext域对象：作用范围在整个web应用中有效！！！

					所有域对象：
						HttpServletRequet 域对象
						ServletContext域对象
						HttpSession 域对象
						PageContext域对象	


	10.6 转发
		 RequestDispatcher getRequestDispatcher(java.lang.String path)

			1）转发
				 a）地址栏不会改变
				 b）转发只能转发到当前web应用内的资源
				c）可以在转发过程中，可以把数据保存到request域对象中

			2）重定向			
				a）地址栏会改变，变成重定向到地址。
				b）重定向可以跳转到当前web应用，或其他web应用，甚至是外部域名网站。
				c）不能再重定向的过程，把数据保存到request中。

			结论： 如果要使用request域对象进行数据共享，只能用转发技术！！！


			总结：
				Servlet编程:
					Servlet生命周期（重点）
					其他都是应用的东西（敲代码练习）


	作业：
				改造通讯录程序
				servlet+xml版本

			要求：
					1）使用浏览器操作系统

			提示：				
				添加联系人：
						设计一个添加联系人html页面		
				保存逻辑：
						AddServlet  （接收页面数据（getParameter（）），使用dom4j保存到xml）
				修改联系人
						QueryServlet （xml查询修改的联系人， 把联系人显示到一个html页面（response.getWriter.write("<html></html>")））;
				修改保存逻辑：		
						UpdateServlet ( 接收页面数据，把数据保存xml )
				删除联系人：
					    输入删除id的html页面
				删除逻辑：
						DeleteServle（ 接收id， 在xml文件中删除对应的联系人）
			
					


	
  						
  						
  						
  
 * @author 贤元
 *
 */
public class TodayNote {

}
