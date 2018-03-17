package ReviewAndNote;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 
  1、课程回顾
  	Jsp加强：
  		1）Jsp的9大内置对象
  			request   HttpServletRequest
  			response	HttpServletResponse
  			config		ServletConfig
  			application	ServletContext
  			exception	Throwable
  			page		Object
  			pageContext	PageContext
  			out			JspWriter
  			session		HttpSession
  		
  		2） Jsp的4个域对象
  			request
  			session
  			application
  			pageContext
  			
  			作用范围：
  				pageContext:	处于当前jsp页面中有效的！！
  				request:		处于同一个请求中有效的！
  				session：		处于同一个会话中有效的！
  				application:	处于同一个web应用中有效的
  		
  		3）EL表达式
  				替代jsp表达式，用于向浏览器输出域对象中的变量值和表达式计算的结果。
  				
  				语法：
  					${变量}
  				  
  				  3.1 输出普通字符串： ${name}
  				  3.2输出对象属性：	${student.name} 注意： .name 相当于 .getName()方法
  				  3.3输出List集合： ${list[0].name} 注意：[0] 相当于get(下标) 方法
  				  3.4输出Map集合：	${map[key].name} 注意： [key] 相当于 .get(Key)方法
  		
  		4）jsp标签：
  			替代jsp脚本，用于在jsp页面中执行java代码
  			
  			4.1 内置标签：
  				<jsp:forword/>request.getRuquestDipsacher("/路径").forward(request.response);
  				<jsp:param/> 参数标签     ?name="eric"
  				<jsp:include/>	包含其他页面，动态包含。
  						静态包含：先合并在翻译。不能传递参数。
  						动态包含：先翻译在合并。可以传递参数。
  			
  		   4.2 jstl标签库(java标砖标签库)
  				使用步骤：
  					1）确保jstl支持的jar包存在于项目中
  					2）在jsp页面中导入标签库
  						<%@taglib uri="标签库声明文件 tld文件的标记" prefix="前缀"%>
  					3）使用标签库中的标签
  				
  				核心标签库：
  				 	<c:set/> 	保存数据到域对象中
  				 	<c:out/>	从域中取出数据
  				 	<c:if/>		但条件判断
  				 	<c:choose/>+<c:when/>+<c:otherwise/> 多条件判断
  				 	<c:forEach>	遍历数据
  				 	<c:forTokens/>	遍历特殊字符串
  				 	<c:redirect/>	重定向   
  
  今天的目标：  自定义标签+编码实战（下）
  
   2、自定义标签
   		2.1 引入
   			需求：向浏览器输出当前客户的IP地址（只能使用jsp标签）
  		
  		2.2 第一个自定义标签开发步骤
  			1）编写一个普通的java类，继承SimpleTagSupport类，叫标签处理器类。
  				/**
 				* 标签处理器类
 				* @author 贤元
 				*1）继承SimpleTagSupport
 				*//*
		public class ShowIpTag extends SimpleTagSupport{

			private JspContext context;
 			*//**
 			* 传入pageContext
 			*//*
			@Override
			public void setJspContext(JspContext pc) {
				this.context=pc;
		
		
			}
			
 			*//**
 			 * 2、覆盖doTag方法
 			 *//*
			@Override
			public void doTag() throws JspException, IOException {

				//向浏览器输出客户的ip地址
				PageContext pageContext=(PageContext)context;
		
				HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		
				String ip=request.getRemoteHost();
		
				JspWriter out=pageContext.getOut();
		
				out.write("使用自定义标签输出客户的ip地址："+ip);
					}
			  }
  
  			
  	   2）  在web项目的WEB-INF目录下建立itcast.tld文件，这个tld也叫标签库的声明文件。（参考核心标签库的写法）
  			<?xml version="1.0" encoding="UTF-8" ?>

		<taglib xmlns="http://java.sun.com/xml/ns/javaee"
    			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    			xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-jsptaglibrary_2_1.xsd"
    		version="2.1">

  			<!-- 标签库的版本 -->
  			<tlib-version>1.1</tlib-version>
  			<!-- 标签库前缀 -->
  			<short-name>itcast</short-name>
  			<!-- tld文件的唯一标记 -->
  			<uri>http://gz.itcast.cn</uri>

   			<!-- 一个标签的声明  -->
  			<tag>
    			<!-- 标签名称 -->
    			<name>showIp</name>
    			<!-- 标签处理器类的全名 -->
    			<tag-class>gz.itcast.a_tag.ShowIpTag</tag-class>
    			<!-- 输出标签体内容格式 -->
    			<body-content>scriptless</body-content>
  			</tag>
		</taglib>

  
       3）在jsp也忙的头部导入自定义标签库
       		<%@taglib uri="http://gz.itcast.cn" prefix="itcast" %>
  	   
  	   4）在jsp中使用自定义标签
  			<itcast:showIp></itcast:showIp>
  
  	   2.3 自定义标签的执行过程
  	   		问题：  http://localhost:8080/day14/01.hellotag.jsp  如何访问到自定义标签
  	   	
  	   	前提：tomcat服务器启动时，加载到每一个web应用的WEB-INF目录下的所有文件！！！   例如：web.xml ,  tld文件
  	   		1）访问 01.hellotag.jsp 资源
  			2）tomcat服务器吧jsp文件翻译成java的源文件-->编译成class字节码文件-->构造类对象--》调用jspService()方法。
  			3）检查jsp文件的taglib指令，检查是否存在一个名为 http://gz.itcast.cn的tld文件。如果没有则报错。
  			4）上一步已经读到itcast.tld文件
  			5）读到<itcast:showIp>到itcast.tld文件中查找是否存在<name>为showIp的<tag>标签
  			6）找到对应的<tag>标签，则读取到<tag-class>内容
  			7）得到gz.itcast.a_tag.ShowIpTag
  			
  			构造showIpTag对象，然后调用showIpTag里面的方法
  
  
  	   2.4 自定义标签处理器类的生命周期
  	   		SimpleTag接口：
  	   			void setJspContext(JspContext pc) -设置pageContext对象，传入pageContext(一定调用)
  	   												 通过getJspContext()方法得到pageContext对象
  	   			void setParent(JspTag parent) -设置父标签对象，传入父标签对象，如果没有父标签，则不调用此方法。通过getParent()方法的到父标签对象。
  	   			
  	   			void setJspBody(JspFragment jspBody) --设置标签体内容。标签体内容封装到JSPFragment对象中，然后传入JSPFragment对象。
  	   								通过getJspBody()方法得到标签体内容。如果没有标签体内容，则不会调用此方法。
  	   			
  	   			void  doTag()      -- 执行标签时调用的方法(一定调用)
  
  
  	   2.5 自定义标签的作用：
  	   	  
  	   	   1）控制标签体内容是否输出
  	   	   2）控制标签余下内容是否输出
  	   	   3）控制重复输出标签体内容
  	   	   4）改变标签体内容
  	   	   5）带属性的标签
  	   	   		步骤：
  	   	   			5.1 在标签处理器中添加一个成员变量和setter方法
  	   	  //1.声明属性的成员变量
		  private Integer num;
	
	      //2.关键点： 必须提供公开的setter方法，用于给属性赋值
          public void setNum(Integer num) {
		  this.num = num;
	        }

  	   2.6 输出标签体内容格式
				JSP：   在传统标签中使用的。可以写和执行jsp的java代码。
				scriptless:  标签体不可以写jsp的java代码
				empty:    必须是空标签。
				tagdependent : 标签体内容可以写jsp的java代码，但不会执行。

  
  	   2.7 案例
  	   		
  	   		核心标签库：   c:if  c:choose+c:when+c:otherwise  c:forEach
  			高仿核心标签库：
  
  	
  	3 编码实战：
  		
  		3.1 JavaBean
  		
  		JavaBean， 咖啡豆. JavaBean是一种开发规范，也可以说是一种技术。
  		
  		 JavaBean就是一个普通的java类。只有符合以下的规定才能称之为JavaBean。
  		 		1）必须提供无参数的构造方法。
  		 		2）类中属性都必须私有化(private)。
  		 		3）该类提供公开的getter和setter方法。
  				
  			JavaBean的作用：用于封装数据，保存数据。
  					访问JavaBean只能使用getter和setter方法
  			JavaBean的使用场景：
  				1）项目中用到实体对象(entity) 符合JavaBean的规范
  				2）EL表达式访问对象属性。 ${student.name} 调用getName()方法，符合JavaBean规范。
  				3）jsp标签中的属性赋值。setNum(Integer num)。符合JavaBean规范。
  				4）jsp页面中使用JavaBean,符合JavaBean规范。
  				
  			   问题：
  			   	一下方法哪些是属于JavaBean规范的方法? 答案：(1、3、5、6)
  			   	  注意：boolean类型的get方法名称叫 isXxx()方法
  			    1）getName()  2）getName(String name)
  			    3）setName(String name)	  4）setName()
  				5）setFlag(boolean flag)	  6）isFlag()
  				
  		3.2 web 开发模式：
  			 第一种开发模式：Jsp+JavaBean开发模式：
  			 
  			 第二种开发模式：Servlet+jsp+javabean开发模式：(也叫MVC开发模式)(MVC模式就是JavaBean+jsp+servlet模式)
  			
  			Model: javabean:封装业务数据，模型
  			View:  jsp:显示数据，视图
  			Controller: Servlet:调度jsp和JavaBean资源，控制器
  
  
  			之后的web项目开发模式：MVC+三层结构
  			
  			
  		总结：
  			1）自定义标签：
  				自定义标签作用
  				案例
  			2）编码实战
  				JavaBean规范：三点
  				MVC开发模式：
  					Model-JavaBean实现。用于封装业务数据
  					View-jsp实现。用于显示数据
  					Controller- servlet实现。用于控制model和view
  				三层结构：
  					dao层：和数据访问相关的操作
  					service层：和业务逻辑相关的操作
  					web层：和我用户直接交互香瓜你的操作（传接参数，跳转页面）。
  					
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 * @author 贤元
 *
 */
public class TodayNote {

}
