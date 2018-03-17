package 回顾及笔记;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 今天的目标：xml约束+web开发入门
 
 2） XML约束
 		XML约束要求：大家能够看懂约束内容，根据约束写出符合规则的xml文档
 	
 	2.1 引入
 		XML语法：规范的xml文件的基本编写规则。(由w3c组织制定的)
 		XML约束：规范XML文件数据内容格式(由开发者自行定义)
 		
 	2.2 XML约束技术
 	
 	DTD约束 : 语法相对简单，功能也想对简单，学习成本比较低
 	Schema约束 : 语法相对复杂，功能也相对强大，学习成本也比较高!!(名称空间) 语法要自己学！！在w3cSchool中
 	
 	2.3 DTD约束
 		
 		1)导入DTD方式：
 			内部导入:
 			
 			<!DOCTYPE note [
  			  <!ELEMENT note (to,from,heading,body)>
  			  <!ELEMENT to      (#PCDATA)>
    		  <!ELEMENT from    (#PCDATA)>
  			  <!ELEMENT heading (#PCDATA)>
  			  <!ELEMENT body    (#PCDATA)>
			]>
 			
 			外部导入:
 				
 					本地文件系统导入：
 				<!DOCTYPE 根元素 SYSTEM "文件名">
 
 					公共的外部导入:
 				<!DOCTYPE 根元素 PUBLIC "http://gz.itcast.cn/itcast.dtd">
 
 				
 				
 		2)DTD语法
 			约束标签
 				<!ELEMENT 元素名称 类别> 或 <!ELEMENT 元素名称(元素内容)>
 
 
 			类别：
 				空标签： EMPTY . 表示元素一定是空元素(标签)
 				普通字符串：  (#PCDATA)  表示元素的内容一定是普通字符串(不能包含子标签)(注意：空字符串也叫普通字符串)
 				任何内容：	ANY。  表示元素的内容一定是任意的内容(元素的内容可以包括子标签)
 
 
 			(元素内容)：
 				 顺序问题：			注意：元素名称和括号之间一定要有空格
 				 	<!ELEMENT 元素名称  (子元素名称 1,子元素名称 2,.....)>  按顺序出现子标签
 				
 				次数问题：
 					标签： 必须只出现一次
 					标签+ ：表示至少出现一次
 					标签* ：表示0次或n次
 					标签? ：表示0或1次
 
 			
 			约束属性：
 				<!ATTLIST 元素名称 属性名称 属性类型 默认值>
 			
 			    默认值：
 			   	#REQUIRED 属性值是必需的 
				#IMPLIED 属性不是必需的 
				#FIXED value    属性不是必须的，但属性值是固定的 

 			 属性类型：控制属性值的
 			 	CDATA 表示普通字符串
				(en1|en2|..) 表示一定任选其中的一个值
				ID 表示在一个xml文档中该属性值必须唯一 不能以数字开头


 
 
 		2.4 Schema约束
 		
 		名称空间：告诉xml文档的哪个元素被哪个schema文档约束。在一个xml文档中，不同的标签可以受到不同的schema文档的约束。
 
 		1）一个名称空间受到一个schema文档约束的情况
 		2）多个名称空间受到多个schema文档约束的情况
 		3）默认名称空间的情况
 		4）没有名称空间的情况
 
 
 
 
 
 
 
 
 	3 Web开发入门
 		 3.1 入门
 				之前的程序：java桌面程序，控制台应用程序。socket GUI界面，JavaSE规范。
 				现在以后的程序：Java web程序，浏览器控制，javaee规范。
 				
 		 3.2 软件结构
 			
 			C/S(Client-Server 客户端-服务器端)
 				典型应用：QQ软件，飞秋，红蜘蛛
 				特点：
 					1）必须下载特定的客户端程序。
 					2）服务器端升级，客户端升级
 					
 			B/S(Broswer-Server 浏览器-服务器端)
 				典型应用：腾讯官方(www.qq.com)  163新闻网站 （俗称网站）
 				特点：
 					1）不需要安装特定的客户端,（只需要安装 一个浏览器即可）
 					2）服务器端升级，浏览器并不需要升级
 				
 				JavaWeb 的程序就是基于B/S结构的。
 				
 		 
 	    3.3 服务器
 		 	什么是服务器：从物理上来说，服务器就是一台PC机。 8核，内存8G以上，硬盘以T来计算。带宽100M以上。
 		 	
 		 	web服务器：PC机器安装一个具有web服务的软件，称之为web服务器
 		 	数据库服务器 ：PC机器安装一个具有数据服务管理的软件，称之为数据库服务器
 		 	邮件服务器  ：PC机器安装一个发送邮件服务的软件，称之为邮件服务器。
 		 	
 
 		3.4 web服务软件
 		
 			web服务软件的作用：把本地的资源共享给外部访问
 
 		网络通讯的基础：通过IP和端口发送/接收二进制数据
 		
 		java语言：socket
 		c语言：socket
 		c#语言：socket
 		....
 		
 		不同的语言网络通讯的数据和具体的开发语言无关！
 
 		在浏览器上输入
 		http://localhost:8888   本机访问
 		
 	其他主机访问用这种格式	http://要访问的主机的IP地址(即服务器的IP地址):要查看的端口号
 
 	
 		3.5 常见的市面上的web服务程序
 		
 				javase的规范，包含IO流、线程、集合、socket编程.....
 			WebLogic：BEA公司的产品。收费的。 支持JavaEE规范.
 			WebSphere ： IBM公司的产品。收费的。支持JavaEE规范
 			JBoss ： Redhat公司的产品。收费的。支持JavaEE规范
 			Tomcat：开源组织Apache组织的公司。 免费的。支持部分的JavaEE规范。(servlet、jsp，Jdbc，但ejb、rml不支持)中小应用用这个Tomcat足够了。
 			
 
 
 
 	  4 Tomcat基本使用
 	  		
 	  		4.1 下载并使用
 			
 			1）到Apache官网  www.aptche.org  http://jakarta.apache.org(产品的主页)
 			2）
 				安装版： Windows（exe、msi）  linux（rmp）
 				压缩版 ： Windows（rar，zip） linux（tar，tar.gz） 学习的时候使用压缩版
 			3）运行和关闭tomacat
 				3.1启动软件
 					1）找到%tomcat%/bin/startup.bat,双击这个文件 
 					2）弹出窗口，显示信息（不要关闭此窗口）
 					3）打开浏览器，在浏览器中输入一下地址： http://localhost:8080
 					4）看到一只猫的画面，就证明软件启动成功了。
 
 				3.2 关闭软件
 					1） 找到%tomcat%/bin/shutdown.bat 双击这个文件即可
 					2）打开浏览器，输入一下地址，http://localhost:8080  看到无法连接（最好先清空一下浏览器的缓存）
 				
 		   4.2 tomcat 软件使用的常见问题
 					
 			   1）闪退问题
 			   	原因：tomcat软件语言使用java语言开发的，启动时，会默认到系统的环境 变量中找一个叫 JAVA_HOME 名字的一个变量，
 			   			的作用就是找到tomcat启动所需的jvm
 				解决办法：到环境变量中去设置 JAVA_HOME 变量
 					JAVA_HOME=D:\Software\CodingSoftware\软件安装处\develop\jdk1.7.0_17 (注意别配置到bin目录下)
 
 			   2）端口占用的错误
 			   	原因：tomcat启动所需的端口被其他软件占用了
 			   	解决办法
 			   		a）关闭其他软件程序，释放所需端口
 			   		b）修改tomcat软件端口 
 			   		 找到并修改%tomcat%/conf目录下的server.xml配置文件， （目前的端口就是8080）
 						<Connector port="8080" protocol="HTTP/1.1" 
               					connectionTimeout="20000" 
               			redirectPort="8443" />
 
 
 			  3）CATALINA环境变量问题：
 			       原因： tomcat软件启动后，除了会找JAVA_HOME变量后，还会找到一个叫CATALINA_HOME变量，这个变量的作用是设置Tomcat的根目录
 			   解决办法: 建议不要设置CATALINA_HOME变量，如果有的话就把它清除掉
 	
 		  
 		  4.3 体验tomcat软件的作用
 		  	
 		  	webapps目录： tomcat共享目录。需要共享的本地的资源放到此目录中。
 		  	
 		  4.4 URL 统一资源定位符，用于定位一些互联网上的资源
 		  	问题：      	http://localhost:8081/Myweb/test.html 看到文件？
 		  	
 		  	http://  协议。http协议
 		  	localhost  域名.为了找到IP地址
 		  				本地域名 localhost
 		  				外部域名 baidu.com
 		  	
 		  	C:\Windows\System32\drivers\etc\hosts
 		  	
 			8081 	端口。 软件监听的
 					8080： tomcat默认的端口
 					3306：mysql数据库默认的默认的
 					1521：oracle数据库默认的
 			/myweb:  web应用的名称。默认情况下，会在webapps目录下找
 			/test.html : 资源名称 
 			
 			
 			
 			
 		5 Tomcat的目录结构
		|-bin: 存放tomcat的命令。
				catalina.bat 命令：
					startup.bat  -> catalina.bat start	
					shutdown.bat - > catalina.bat stop
	    |- conf: 存放tomcat的配置信息。其中server.xml文件是核心的配置文件。
		|-lib：支持tomcat软件运行的jar包。其中还有技术支持包，如servlet，jsp
		|-logs：运行过程的日志信息
		|-temp: 临时目录
		|-webapps： 共享资源目录。web应用目录。（注意不能以单独的文件进行共享）
		|-work： tomcat的运行目录。jsp运行时产生的临时文件就存放在这里

 		注意：
			1）WEB-INF目录里面的资源不能通过浏览器直接访问
			2）如果希望访问到WEB-INF里面的资源，就必须把资源配置到一个叫web.xml的文件中。


		练习：
			1）在webapps下建立一个mybbs目录
			2）创建两个文件
					2.1 index.html  里面随便写内容	，有超链接-连接到test.html	
					2.2 test.html   里面随便写
			3）通过浏览器访问到。 http://localhost:8080/mybbs/test.html
						
		
		7、手动开发动态资源
			
			7.1静态资源和动态资源的区别
				
				静态资源：当用户多次访问这个资源时，资源的源代码永远都不会改变的资源。
				动态资源：当用户多次访问这个资源时，资源的源代码可能会发生改变。
			
			7.2 动态资源的开发技术
			
			Servlet:用java语言来编写动态资源的开发技术。
			
			Servlet 特点：
				1）普通java类：继承HttpServlet类， 覆盖doGet方法
				2）Servlet 类只能交给tomcat服务器运行！！(开发者自己不能运行)
				
			
			Servlet编写步骤：
				1）编写一个servlet程序，继承HttpServlet
				
				=============================================================================
				|	public class HelloServlet extends HttpServlet {							|
				|	@Override																|
				|	protected void doGet(HttpServletRequest req, HttpServletResponse resp)	|
				|		throws ServletException, IOException {								|
				|		//解决中文乱码的问题														|		
				|		resp.setContentType("text/html;charset=utf-8");						|
				|		//向浏览器输出内容														|
				|		resp.getWriter().write("这是第一个servlet程序,当前时间为："+new Date());		|
				|		}																	|
				|	}																		|	
				|============================================================================
				
				
				2)找到HelloServlet类的class字节码，然后拷贝到tomcat 的一个web应用 中的 WEB-INF/class目录下。
				3）在当前web应用下的web.xml文件配置Servlet
				
				
				<!-- 配置一个servlet程序 -->
					<servlet>
				<!-- servlet的内部名称 ，可以自定义-->
				<servlet-name>HelloServlet</servlet-name>
				<!-- servlet类名： 包名+简单类名-->
					<servlet-class>gz.itcast.d_servlet.HelloServlet</servlet-class>
				</servlet>

				<servlet-mapping>
				<!-- servlet的内部名称，和上面的名称保持一致！！！-->
				<servlet-name>HelloServlet</servlet-name>
				<!-- servlet的访问名称： /名称 -->
				<url-pattern>/hello</url-pattern>
				</servlet-mapping>

			4）启动tomcat服务器，运行访问
				访问servlet：  http://localhost:8081/myweb/hello
				
			
		 8、工具开发动态资源
		 	
		 		1）创建web project （javaweb工程）
				2）在WebRoot下建立静态资源文件，
				3）在src下建立动态资源文件
			  		3.1 new -> Servlet( servlet的代码生成器)
			  		3.2 写pacakge  -> class名 -> 修改mapping  url 
				4）关联tomcat服务器
					4.1 window-> Preferences - > MyEcplise -> servers -> Tomcat 6.x (注意一定要enable)

				5）部署web project应用。（拷贝web应用到tomcat的webapps目录下）
				6）启动tomcat服务器
				7）访问servlet			
					http://localhost:8081/day08_web/hello
		

		今天作业
			1）手动编写并运行一个servlet程序。
			2）用myecplise建立servlet程序。

    	明天： http协议   用到servlet程序。（重点）
			后后天： servlet编程 （理解servlet原理）

			总结： 
				  web开发入门
				  1）web服务器作用：共享本地资源给外部访问
				  2）tomcat服务器： 基本操作
				  3）编写一个规范的web应用
				  4）如何编写一个动态资源（手动+工具）

				

 * @author 贤元
 *
 */



public class TodayNote {

}
