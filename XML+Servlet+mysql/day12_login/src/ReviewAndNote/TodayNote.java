package ReviewAndNote;
/**
	1、课程回顾
	 
	  会话管理：
	  	1）会话管理：管理浏览器和服务器之间的会话过程中产生的会话数据。
	  	2）Cookie技术：会话数据保存在浏览器客户端。
	  		  Cookie核心的API：
	  		  	  2.1 在服务器端创建Cookie对象
	  		  	  		Cookie cookie-new Cookie("name","value");
				  2.2 把cookie发送给浏览器端(通过响应头：set-cookie)
				  		response.addCookie(coolie);
				  2.3浏览器带着cookie信息访问浏览器(通过请求头：cookie)
						Cookie[] cookies=request.getCookies();
					
			
			局限：
				1）只能保存字符串类型，不能保存中文
				2）一个cookie不能超过4kb
		3）Session技术：会话数据保存在服务器端。（内存）
			  Session 核心的API：
			  		3.1 创建或得到session对象
			  			  HttpSession session=request.getSession();//创建或得到Session对象
			  			  request.getSession(false);//得到session对象

					3.2会话数据保存session对象中,和得到会话数据
						 session.setAttribute("name",Object);保存数据
						 session.getAttribute("name");得到数据
			  注意：
			  	1）session.setIntactiveInterval(时间); 设置session过期时间
				2）session.invalidate() 手动销毁session对象




	2、Session 案例
			
			用户登录场景


	
	3、 编程实战
		
		通讯录程序：
			软件的生命周期     （建议2~3年不要换行业，要扎实积累自己的行业经验。不要一年换电信的，一年换互联网的。。）
			3.1需求分析（需求分析师）
				 功能分析：
				 	1）添加联系人
				 	2）修改联系人
				 	3）删除联系人
				 	4）查询所有联系人
				 功能流转：
				 
			
				美工设计：设计软件的静态原型
				
				
			3.2 需求设计（系统分析师/架构师/资深开发人员）(以后往这个方向发展)
				  1）设计实体（抽象实体）	
						联系人实体：
							class Contact{
								private String id;
								private String name;
								private String gender;
								private int age;
								private String phone;
								private String email;
								private String qq;							
							}
				  2)设计"数据库"（用xml代替“数据库”）
				  		contact.xml
				  			<contactList>
				  				<contact id="1">	
				  					<name>张三</name>	
				  					<gender>男</gender>
				  					<age>20</age>
				  					<phone>1344556677</phone>
				  					<email>zs@qq.com</email>
				  					<qq>43222222</qq>
				  				</contact>	
				  			</contactList>
				  3)设计设计的接口
				  		DAO接口（数据访问对象）：实体对象的CRUD方法。
				  		项目原则：通常一二实体对象就会对应一个DAO接口和一个DAO实现类。
				  		interface ContactDao{
				  			public void addContact(Contact contact);//添加联系人
				  			public void updateContact(Contact contact);//修改联系人
				  			public void deleteConatct(String id);//删除联系人
				  			public List<Contact>findAll();//查询所有联系人
				  			public Contact findById(String id);//根据编号查询联系人
				  		   }
				  4)设计项目的目录结构
				  		项目名称：contactSys_web
				  		目录结构：
				  			|- contactSys_web
				  			    |-src
				  			       |- gz.itcast.contactSys_web.entity:存放实体对象
				  			       |- gz.itcast.contactSys_web.dao:存放dao的接口 
				  			       |- gz.itcast.contactSys_web.impl：存放dao的实现类	
				  			       |- gz.itcast.contactSys_web.servlet:存放Servlet的类
				  			       |- gz.itcast.contactSys_web.test：存放单元测试类
				  			       |- gz.itcast.contactSys_web.util：存放工具类
				  			       |- gz.itcast.contactSys_web.exception:存放自定义异常类
				  				|-WebRoot
				  					|-html文件
				  					|-images目录：存放图片
				  					|-css目录：存放参数是资源
				  					|- js:目录： 存放js资源
				  		
				  		
				  		
			3.3 编码实现（软件开发工程师/攻城狮/码农）
				 开发顺序：
				 	设计数据库-》实体-》DAO接口，DAO实现-》Servlet+html页面
				 
				 
			3.4 功能测试（测试攻城狮）
			3.5 性能测试（测试攻城狮）
			3.6 部署上线（实施攻城狮，(如果单纯做实施，技术含量会低一点)）
			3.7 维护阶段（实施攻城狮）

			软件公司：
				职位选择：
				 技术线：初级开发攻城狮--》高级开发工程师-》初级架构师-》高级架构师-》CTO(技术总监)
				 管理线：初级开发工程师--》初级项目助理-》项目组长-》项目经理-》CEO(运营总监)
			

	
	4 Jsp基础
		
		4.1 Jsp引入
			 
			 Servlet的作用：用java语言开发动态资源的技术！！
			 Jsp的作用：用java语言(+html语言)开发动态资源的技术！！！
			 	jsp就是Servlet
	    
	    4.2 Jsp的特点：
	    	 1）jsp的运行必须交给tomcat服务器！！
	    	 		work目录：tomcat服务器存放jsp运行时的临时文件。
	    	 2）jsp页面既可以写html代码，也可以写java代码。
	    	 	(html页面不能写java代码，而jsp页面可以写java代码)
			 ·
	    4、3 体验jsp页面作用
	    	
	    	需求：显示一个当前时间到浏览器上
			可以把jsp页面当做html页面在tomcat中访问！！
			
		4.4 jsp的执行过程
			
	   		问题：访问  http://localhost:8080/day12/01.hello.jsp 如何显示效果
				
			1）访问到01.hello.jsp页面，tomcat扫描到jsp文件，在%tomcat%/work 把jsp文件翻译成java的源文件
				(01.hello.jsp->_01_hello_jsp.java)   （翻译）
			2）Tomcat服务器把java源文件编译成class字节码文件
				(_01_hello_jsp.java->_01_hello_jsp.class)    （编译）
			3)tomcat服务器构造_01_hello_jsp类对象
			4)tomcat服务器调用_01_hello_jsp类里面方法，返回内容显示到浏览器
			
			
		第一次访问jsp：
				走（1）（2）（3）（4）
		第n次访问jsp：
				走（4）
		
		
		注意：
			1）jsp文件修改了或jsp的临时文件被删除了，要走翻译（1）和编译（2）的过程，
		
		
	   4.5 疑问
	   		
	   	  问题：为什么jsp就是Servlet ??????????
	   	  	jsp翻译的java文件：   	  		
				public final class _01_hello_jsp extends org.apache.jasper.runtime.HttpJspBase implements org.apache.jasper.runtime.JspSourceDependent 
		
		
		HttpJspBase类：
			public abstract class org.apache.jasper.runtime.HttpJspBase extends javax.servlet.http.HttpServlet implements javax.servlet.jsp.HttpJspPage {

		结论：Jsp就是一个Servlet程序！！
			servlet的技术可以用在jsp程序中！！
			但是jsp的技术并不是全部适用于Servlet程序！！
			
		
		Servlet的生命周期：
			1）构造方法(第一次访问)
			1）init方法（第一次访问）
			3）service方法
			4）destroy方法
			
		Jsp的生命周期：
			1）翻译：jsp->java文件
			2）编译：java文件-》class文件（Servlet程序）
			3）构造方法（第一次访问）
			4）init方法（第一次访问） JspInit()
			5）service方法   _jspService
			6）destroy方法     _jspDestroy()
		
		
	 5、Jsp语法
	 	
	 	5.1 Jsp模板
	 		
	 		jsp页面中的html代码就是jsp的模板。
	 		
	 	5.2 Jsp表达式
	 		
	 		语法： <%=变量或表达式%>
	 		作用：向浏览器输出变量的值或表达式计算的结果
	 		注意：
	 		  1）表达式的原理就是翻译成out.print("变量值");通过该方法向浏览器写出内容
	 		  2）表达式后面不需要带分号结束
	 		
	 		
	 		
	 	5.3 Jsp的脚本
	 	
	 		语法：<%java代码%>
	 		作用：执行java代码
	 		注意：
	 			1）原理把脚本中java代码原封不动拷贝到_jspService方法中执行
	 			
	 	5.4 Jsp的声明
	 		语法：  <%! 变量或方法 %>
	 		作用：声明jsp的变量或方法
	 		注意：
	 			1)变量翻译成成员变量，方法翻译成成员方法
	 	5.5 Jsp的注释
	 		  语法：   <%-- jsp注释  --%>	
			 注意：
			 	1）html的注释会被翻译和执行，而jsp的注释不能被翻译和执行
		
		
		6、 Jsp 的三大指令
		   
		   6.1 include指令
		   			
		   		 作用：在当前页面包含其他的页面.
		   		 语法：  <%@include file="common/header.jsp"%>
				 注意：
				 	1）原理是把被包含的页面（header.jsp）的内容翻译到包含页面(index.jsp)中，合并翻译成一个java源文件，在编译运行！！，这种包含叫静态包含(源码包含)。
				 	2）如果使用静态包含，被包含的页面中不需要出现全局的html标签了！！(如html、head、body)
		   
		   6.2 page指令
		   		作用：告诉tomcat服务器如何翻译jsp文件
		   		 <%@ page 
					language="java"  -- 告诉服务器使用什么动态语言来翻译jsp文件
					import="java.util.*"   --告诉服务器java文件使用什么包
												导入包，多个包之间用逗号分隔
					pageEncoding="UTF-8"  --告诉服务器使用什么编码翻译jsp文件（成java文件）
					contentType="text/html;charset=ISO-8859-1"	--服务器发送给浏览器的数据类型和类型的编码
							注意：在开发工具中，以后只需要设置 pageEncoding即可解决中文乱码问题。
					errorPage="error.jsp";
					isErrorPage="false"
					buffer="8kb"
					session="true"
					isELIgnored="false"
				 %>
		   		
		   6.3 taglib指令
				
				总结：(先整理思路，再弄代码)
					1）编码实战
						1.1 Servlet应用
						1.2初步了解软甲年开发流程
					2）Jsp基础
						2.1 Jsp的执行原理（翻译->编译-》Servlet的生命周期）
						2.2 语法（jsp表达式，jsp脚本）
						2.3 指令
							include 指令
							page 指令
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			





*/



public class TodayNote {

}
