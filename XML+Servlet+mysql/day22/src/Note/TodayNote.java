package Note;
/**
 回顾重点内容：
 	过滤器(Filter接口)
 	1）作用：在请求资源(静态资源或动态资源)，或者响应资源，或者请求和响应资源时，
 			执行过滤任务。
 	2）Filter的生命周期：
 		Init方法：初始化方法。参数FilterConfig对象，用于读取Filter配置的Init-param参数。
 		doFilter方法：执行过滤任务。每次访问目标资源时被执行。
 			参数一：ServletRequest：是HttpServletRequest接口的弗雷，可以强制转换为HttpServletRequest
 			参数二：ServletResponse：是HttpServletResponse的父类，可以强制转换为HttpServletResponse。
 			参数三：FilterChain：是过滤器链对象。当一个资源同事被多个过滤器所过滤，就形成一个过滤器链。调用FilterChain的doFilter(request,response)方法，
 					执行过滤器链的下一个过滤器，如果没有过滤器则调用目标资源
 		destroy方法：销毁Filter对象的时候调用。
 	
 	3）装饰着模式：当需要对某些累的方法进行增强，那么可以对该类进行装饰。
 		3.1 编写一个装饰着累，继承被装饰者累(被装饰着累是非final的)
 		3.2 声明一个成员变量，用于接收被装饰者类对象
 		3.3 在装饰者类的构造方法中，接收被装饰者类对象
 		3.4 覆盖需要增强的方法
 	4）解决参数中文乱码问题：
 		核心：MyHttpRequest装饰者类，对HttpServletRequest的getParameter方法进行增强。
 						getParameterValues("name")/getParamaterMap();
 	
 	5）网页内容压缩问题
 		核心：MyHttpResponse装饰者类，对HttpServletResponse的getWriter方法进行增强，
 				让getWriter()方法返回带缓存功能的PrintWriter对象，这样PrintWriter的writer方法
 				写入的内容就直接写入到PrintWriter中的缓存对象中。然后可以通过缓存对象取出内容。
 				
 			ByteArrayOutputStream buf=new ByteArrayOutputStream()
 			GZIPOutputStream gzip=new GZIPOutputStream(bug);
 			gzip.write(内容);
 			gzip.finish();//内容写入到ByteArrayOutputStream中
 			byte[] result=buf.toByteArray();//从ByteArrayOutputStream中得到压缩后的内容
 			
 			
 	6）登录权限的过滤
 		核心：SecurityFilter过滤器中，进行登录权限的判断，这个过滤器要过滤哪些需要权限才能访问的资源。(注意：不要过滤登录页面，登录的servlet)
 		
 		if(session==null){
 			没有权限的处理
 		}else{
 			String user=(String)session.getAttribute("user");
 			if(user==null){
 				没有权限的处理
 			}
 		}
 		//登录成功后，应该要放行资源
 		chain.doFilter(request,response);
 		
 		
   	
   	今天的目标：监听器
   	
   	
  1、监听器入门
  	
  	  1.1 引入
  	  	AWT:重量级的GUI组件，依赖操作系统的界面。java.awt
  	  	Swing:轻量级的GUI组件，不依赖操作系统的界面。javax.swing
  	  	
  	  	GUI的时间编程的三要素：
  	  		事件源：Frame/JFrame,Button/JButton....
  	  		事件：MouseEvent WindowEvent KeyEvent
  	  		事件监听器：MouseListener WindowListener KeyListener
  	  	
  	  
  	  1.2 web的事件编程：
  	  		事件源：ServletContext对象，ServletRequest对象HttpSession对象 (这三个都是域对象)
  			事件：ServletContextEvent,ServletRequestEvent,HttpSessionEvent,xxx(创建或销毁对象，对象的属性修改)
  			事件监听器(接口)：ServletContextListener，ServletRequestListener，HttpSessionListener,...
  		web监听器：是一些实现特定接口的Java程序，用于监听web开发中常用的对象(ServletContext,ServletRequest,HttpSession)的创建和销毁行为，以及这些对象的属性修改行为(setAttribute,removeAttribute)
  
  
  			事件源							事件对象								web监听器
  		ServletContext对象		ServletContextEvent(创建和销毁)				ServletContextListener接口
  		ServletContext对象的属性	ServletContextAttributeEvent(增加,修改,删除)	ServletContextListener接口
  		ServletRequest对象		ServletRequestEvent(创建和销毁)				ServletRequestListener接口
  		ServletRequest对象的属性	ServletRequestAttributeEvent(增加,删除,修改)	ServletRequestAttributeListener接口
  		HttpSession对象			HttpSessionEvent(创建和销毁)					HttpSessionListener接口
  		HttpSession对象属性		HttpSessionBindingEvent(增加，修改，删除)		HttpSessionAttributeListener接口
  
  
  2.ServletContextListener
  		
  		用于监听ServletContext对象的创建和销毁行为。
  		
  		ServletContext对象
  			创建：加载当前web项目的时候
  			销毁：关闭服务器或重新不是web项目
  
  3.ServletCOntextAttributeListener
  		
  		用于监听ServletContext对象属性的增加，修改，删除行为。
  		
  		增加：setAttribute("name",Object);
  		修改：setAttribute("name",Object);//把同名的属性进行修改
  		删除：removeAttribute("name");
  
  4.ServletRequestListener
  		
  		用于ServletRequest对象(request请求对象)的创建和销毁行为。
  		
  		ServletRequest对象
  			创建：用户每次发出请求的时候都会创建一个请求对象
  			销毁：完成了整个请求之后请求对象销毁。
  		
   注意：包b c http://localhost:8080/day22/context.jsp
 		
 
 5.ServletRequestAttributeListener
 		
 		用于监听ServletRequest对象的属性增加，修改，删除
 			
 		增加：setAttribute("name",Object);
  		修改：setAttribute("name",Object);//把同名的属性进行修改
  		删除：removeAttribute("name");
  	
  	
 6.HttpSessionListener
 	
 		用于监听HttpSession对象的创建和销毁行为。
 		
 		HttpSession对象
 			创建：调用 request.getSession(true)方法
 			销毁: 
 				1)默认情况下，等待30分钟，服务器自动回收session对象
 				2）session.setMaxInactiveInterval(秒数);设置服务器回收session的时间
 					全局设置：
 						<session-config>
 							<session-timeout>分钟数</session-timeout>
 						<session-config>
 				3）session.invalidate():手动销毁session对象
  	
  7.HttpSessionAttributeListener
  		用于监听HttpSession对象的属性的增加，修改，删除
  	
  	
  	案例：统计当前网站的登录用户信息
  	1）用户可以登录本网站，用户也可以退出本网站
  	2）用户登录成功网站后，可以查看单签网站所有在线的登录用户信息。
 	3）管理员可以踢除已经登录的用户
 
 8、国际化
 	
 	8.1 简介
 	
 	文字国际化
 	日期时间国际化
 		中国：年月日时分秒
 		美国：月日年时分秒
 		英国：日月年时分秒
 
 	8.2 文字国际化
 		ResourceBundle类：用于根据不同的国家语言环境加载不同的资源包
 	
 	8.3 日期时间国际化
 
 		DateFormat
 	
 	总结：
 		监听器：三对监听器
 		国际化：
 			文本国际化：ResourceBundle累
 			日期国际化：DateFormat类
 			
 		  web国际化：
 		  		fmt标签进行文本国际化和日期国际化
 
 
 
 
 
 
 
 
 
 * @author 贤元
 *
 */
public class TodayNote {

}
