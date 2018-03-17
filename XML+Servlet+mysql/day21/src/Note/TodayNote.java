package Note;
/**
 
 	回顾重点内容
			jdbc通用功能：
				1）分页查询：
					核心： 设计一个用于封装当前页分页相关数据的javabean对象
						    当前页的数据
							首页
							上一页	
							下一页
							末页/总页数
							当前页码
							总记录数
							每页显示记录数

					三层结构：
							1）dao层： 
									查询当前页的数据
									总记录数
							2）service层；
									封装分页javabean对象
									（
									首页
									上一页
									下一页
									末页/总页数
									）
							3）web层：
									当前页码
									每页显示记录数
		
				2）条件查询
					核心： 根据不同的条件拼凑条件查询的sql语句

					条件查询sql语句：
							select * from 表 where 1=1   (恒成立)
							if 条件1
								and 字段1 like 内容
							if 条件2
								and 字符2 like 内容
							。。。。。 
							
				3）分页+条件查询
					 在分页的基础上，修改两条sql语句
						3.1 查询当前页数据的sql语句
							select * from 表 where 1=1 
							if 条件1
								and 字段1 like 内容
							if 条件2
								and 字符2 like 内容
							。。。。。 
							limit 起始行,每页查询行数;			


						3.2 查询总记录数的sql语句
							select count(*) from 表 where 1=1
							if 条件1
								and 字段1 like 内容
							if 条件2
								and 字符2 like 内容
							。。。。。;


			今天的目标： 过滤器



	1 过滤器入门
		1.1 引入
		场景1：  在servlet中获取用户参数数据 ： request.getParameter("参数名") 遇到参数内容中文乱码问题，
				post提交：
					  	request.setCharacterEncoding("utf-8");
				get提交：
						手动解码： name = new String(name.getBytes("iso-8859-1"),"utf-8")

				问题： 如何把这些重复的操作抽取出来呢？
				这时就可以用到过滤器，把重复的操作代码写在过滤器中！！！
				
		场景2：
				登录页面 -> 输入用户民或密码 -》  后台检查是否成功 -》 登录成功，看到用户首页
																		( 用户名：xxxx   )
																  把登录数据放在session域对象中(user)

																	用户首页如果不登录是看不到的:
												判断用户登录权限代码：
												HttpSession session = 	request.getSession(false);
												if(session==null){
													跳转到登陆页面
												}else{
														String user = (String)session.getAttribute("user");
														if(user==null){
															跳转到登录页面
														}
												}

												用户资源修改页面（需要用户登录才能访问）
												HttpSession session = 	request.getSession(false);
												if(session==null){
													跳转到登陆页面
												}else{
														String user = (String)session.getAttribute("user");
														if(user==null){
															跳转到登录页面
														}
												}

									
			问题：如何把这些登录权限代码抽取出？？？
			这是用到了过滤器，把这些登录权限代码写在过滤器中.

		1.2 什么是过滤器？
				1）过滤器就是一个Filter接口，在javax.servlet.Filter;
				2）过滤器是servlet的三大组件之一：
					servlet的三大组件:
							2.1  (servlet) Servlet接口： javax.servlet.Servlet; 作用：用于开发动态网页
							2.2 （过滤器）Filter接口： javax.servlet.Filter; 作用：？？？
							2.3 （监听器）Listener接口： javax.servlet.*
						
					servlet组件的特点：
							1）把组件配置到web.xml文件中
							2）组件就可以交给tomcat服务器运行！！！！

				3）作用：
					过滤器的作用，就是一个实现了Filter接口的对象，这个对象可以在请求资源（可能是动态网页或者静态网页）时，或者在响应资源时，或者在请求和响应资源时，执行过滤任务。
					
		1.3 体验
		1.4 过滤器的生命周期
				构造方法： 创建过滤器对象的时候调用。在加载当前项目的时候加载过滤器，只调用1次。单								实例的多线程。
				init方法： 在创建完过滤器对象之后调用。只调用1次。
				doFilter方法： 过滤任务方法。会调用n次。每次访问目标资源的时候，doFilter就会被调用。
				destory方法：在销毁过滤器对象的时候调用。在web项目重新部署或tomcat服务器停止的时候销毁过滤器对象。

 2 映射配置
    <!-- 配置一个过滤器 -->
	<!-- 过滤器配置 -->
	<filter>
		<!-- 内部名称 -->
		<filter-name>FirstFilter</filter-name>
		<filter-class>gz.itcast.a_filter.FirstFilter</filter-class>
	</filter>
	<!-- 过滤器映射配置 -->
	<filter-mapping>
		<!-- 也是内部名称，但是和上面的名称保持一致！！！ -->
		<filter-name>FirstFilter</filter-name>
		<!--过滤器的url-pattern代表的是过滤的路径,而不是访问过滤器的路径  -->
		<url-pattern>/target</url-pattern>
	</filter-mapping>
							
	注意： servlet的url-pattern指的是访问servlet的路径，而过滤器的url-pattern指的是需要过滤的路径。
				而这个过滤的路径，可以是任意资源路径（可以是静态网页路径，页可以是动态网页路径）。
							过滤器url-pattern                 访问目标资源
		精确过滤             /target                      http://localhsot:8080/day20/target
							/index.html					http://localhsot:8080/day20/index.html
							

		模糊过滤			/*                         http://localhsot:8080/day20/任意路径
						/itcast/*                        http://localhsot:8080/day20/itcast/任意路径
						*.后缀名   					http://localhsot:8080/day20/任意路径.后缀名
						（*.do）                    （ http://localhsot:8080/day20/任意路径.do）
3 FilteConfig对象
		3.1 简介
		和servletconfig对象类似，这个FilterConfig对象加载初始化参数内容
			
		
4 FilterChain对象		
		4.1 简介
		FilterChain对象叫做过滤器链对象。
		什么是过滤器链？   当一个资源被多个过滤器所过滤,那么就形成了一个过滤器链。
5 装饰者模式
			
1)编写一个BufferedReader装饰者类，继承被装饰者类。(不能是final的)
	 *          2）在装饰类中定义一个成员变量，用于接收被装饰者类的对象。
	 *          3）在装饰者类的构造方法中传入被装饰者类，使用第二步定义的变量接收被转入的 被装饰者类。	
	 * 			4）在装饰类类中重写被装饰者类方法，对其方法进行增强。
 
	6. 案例- 请求参数的中文乱码问题			
	
	
	7.案例- 压缩网页内容
			用户浏览一个网页： 
				 服务器-> 发送一个网页的内容给用户（1k）
				一个用户一天访问10页：  服务器输出10kb内容

				网站1天10万用户：
						100000 * 10kb = 1 000 000kb =  1GB

				消耗网络带宽。

				服务器（PC机）：
					存放网站（网页）

				服务器收费的：
						按流量收费的。如果尽量减少服务器向用户输出数据量，从而减少消耗带宽。

				要求：在不影响用户浏览效果前提下，减少服务器输出的数据？？？

					这时就要用到网页内容的压缩技术！！！！
							
					压缩网页的技术： gzip压缩技术			
					
					GZIPOutputStream类进行网页内容压缩

					
8 案例- 登录权限过滤

			总结：
				过滤器：    在请求资源和响应资源时，执行过滤任务。

				什么是过滤器链？

				装饰者模式
						
					























下面是重复的！！：：



 1 过滤器入门
		1.1 引入
			场景1： 编写servlet，接收参数： 
							request.getParameter（） /getParameterValues（）
					如果不做任何处理，就出现中文乱码问题。

					解决编码问题： request.setCharacterEncoding("utf-8");

					问题：如果在项目中的每一个servlet都加上request.setCharacterEncoding("utf-8");
显示代码重复啰嗦。能不能把这部分公共代码抽取处理，放在一个地方执行？？？？

			场景2： 登录 -> 输入信息 -> 登录成功  -> 看到用户主页（欢迎xxx回来。。。）
												用于验证用户是否登录成功代码：
													if(session==null){
														跳转到登录页面
													}else{
														loginName = session.getAttribute("loginName");
														if(loginName==null){
															跳转到登录页面
														}
													}
													-> 个人信息修改页面
													-> 个人密码修改页面

				如果用户不登录，直接访问用户主页，跳转到登录页面
				在其他需要登录才能访问的页面中，同样也需要加上验证用户是否登录成功代码。
				
				问题： 能不能把这部分公共验证用户是否登录成功代码抽取处理，在一个地方执行？？
					
				结论： 以上两种场景出现的问题，可以使用过滤器（Filter）解决！！！！

		1.2 过滤器简介
			1）过滤器其实就是一个接口，Filter， javax.servet.Filter
			2）过滤器就是一个对象，可以在请求一个资源（静态或动态资源），或响应一个资源，或请求和响应一个资源的时候，执行过滤任务！！！！
			3）过滤器如何被执行？
					过滤器也需要交给tomcat服务器运行！！！！

			Servlet的三大组件：（ 1）都需要交给web服务器运行  2）在web.xml文件中配置  ）
				 Servlet接口
				 Filter接口
				 Listener接口

			4）过滤器的生命周期
					构造方法： 在web应用加载时创建过滤器对象。只执行一次。证明过滤器在web服务器中是单实例的
					init方法： 在创建完过滤器对象之后被调用。只执行一次
					doFilter方法： 执行过滤任务方法。执行多次。
					destroy方法： web服务器停止或者web应用重新加载，销毁过滤器对象。

			5）过滤器编写步骤：
				5.1 编写一个java类，实现Filter接口，并实现其中的所有方法
				5.1 在web.xml文件中配置Filter
						
    <!-- 过滤器配置 -->
	<filter>
		<!-- 内部名称 -->
		<filter-name>HelloFilter</filter-name>
		<!-- 类全名：包+简单类名 -->
		<filter-class>gz.itcast.a_hello.HelloFilter</filter-class>
	</filter>
	<!-- 过滤器映射配置 -->
	<filter-mapping>
		<!-- 内部名称，和上面的名称保持一致！ -->
		<filter-name>HelloFilter</filter-name>
		<!-- 需要拦截的路径 -->
		<url-pattern>/hello</url-pattern>
	</filter-mapping>
			 5.3 把Filter部署到tomcat服务器运行！！！！

2 过滤器的映射路径
			过滤器中的url-pattern: 表示的这个过滤器需要拦截的目标资源路径（可以servlet路径，也可以是静																			态资源名称）
			Servlet中的url-pattern: 表示访问这个servlet时的路径
			
							url-pattern                      浏览器访问目标资源的路径
			精确过滤         /hello                         http://localhost:8080/day21/hello
							/itcast/hello                     http://localhost:8080/day21/itcast/hello

			模糊过滤        /*                            http://localhost:8080/day21/任意路径
							/itcast/*						http://localhost:8080/day21/itcast/任意路径
							*.后缀名						http://localhost:8080/day21/任意路径.后缀名
				
注意：
			1）url-pattern要么以斜杠开头，要么以*开头  例如： hello
			2）不能同时使用两个模糊过滤。例如 /*.do 是非法的
			3）如果存在多个需要被过滤的资源，可以写多个url-pattern去过滤。
			4）如果是动态资源servlet，可以使用servlet的访问名称，也可以使用内部名称
					
       <!-- 使用servlet的内部名称 -->
		<servlet-name>HelloServlet</servlet-name>

			5）过滤类型：
		<!-- 过滤类型：声明哪种请求才可以被拦截（过滤） -->
		<dispatcher>REQUEST</dispatcher><!-- 默认：来自于直接访问的请求才可以被拦截 -->
		<dispatcher>FORWARD</dispatcher><!-- 来自于转发的请求才可以被拦截 -->
		<dispatcher>INCLUDE</dispatcher><!-- 来自于包含的请求才可以被拦截 -->
		<dispatcher>ERROR</dispatcher><!-- 来自于错误的请求才可以被拦截 -->

3 FilterConfig对象
		FilterConfig对象，过滤器配置对象，用于加载过滤器的参数配置

		过滤器参数使用：
				1）在web.xml文件中配置
		
<!-- 过滤器配置 -->
	<filter>
		<!-- 内部名称 -->
		<filter-name>HelloFilter</filter-name>
		<!-- 类全名：包+简单类名 -->
		<filter-class>gz.itcast.a_hello.HelloFilter</filter-class>
		<init-param>
			<param-name>AAA</param-name>
			<param-value>AAA'value</param-value>
		</init-param>
		<init-param>
			<param-name>BBB</param-name>
			<param-value>BBB'value</param-value>
		</init-param>
	</filter>
		2）在过滤器器中使用
			
/**
	 * 2）init初始化方法
	 *//*
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("2)Filter生命周期-init方法");
		
		*//**
		 * 通过FilterConfig对象得到参数配置信息
		 *//*
		//得到一个参数
		System.out.println(filterConfig.getInitParameter("AAA"));
		
		Enumeration<String> enums = filterConfig.getInitParameterNames();
		//遍历所有参数
		while(enums.hasMoreElements()){
			String paramName = enums.nextElement();
			String paramValue = filterConfig.getInitParameter(paramName);
			System.out.println(paramName+"="+paramValue);
		}
		
	}

4 过滤器链
		doFilter(ServletRequest request, ServletResponse response, FilterChain chain)： 执行过滤任务

		参数一： ServletRequest是HttpServletRequest的父接口。实际上传入的是HttpServletRequest接口的实现类。
		参数二： ServletResponse是HttpServletResponse的父接口。实际上传入HttpServletResponse接口的实现类。
		参数三： FilterChain 过滤器链接口
				doFilter(ServletRequest request, ServletResponse response)：执行过滤器链中的下一个过滤器，如果没有下一个过滤器则执行目标资源。

			*****过滤器链： 一个目标资源可以被多个过滤器过滤，那么形成一个过滤器链。***

			注意：过滤器链中的过滤器执行顺序问题：由web.xml中filter-mapping的配置决定顺序。先配置的优先被执行。

5 装饰者模式(Decorator)
		23种java设计模式。单例模式，工厂模式，适配器模式，观察者模式，代理模式。。。。。。

		装饰者模式：当开发者觉得某些类的某些方法不满足需要，向增强这些类的方法。这是就可以使用装饰者模式去装饰这些类。不满足需求的这些类，叫被装饰类。开发者需要重新编写装饰类去覆盖被装饰类。

		BufferedReader：被装饰类。readLine方法不满足需要。
		MyBufferReader: 装饰类。
				装饰步骤：
					1）继承被装饰类。BufferedReader。非final
					2）装饰类中声明一个成员变量（被装饰类类型）
					3）在构造方法，把被装饰类的实例接收到，赋值给成员变量
					4）重写被装饰的方法。

6 案例
		6.1 使用过滤器完美解决GET和Post方式提交中文乱码问题
		6.2 使用过滤器解决网页内容压缩问题
			为什么要进行网页内容压缩？
				 访问web服务器时，服务器会返回网页内容（数据）
				用户访问一个页面：
						100KB
				100万个用户访问这个页面	
						1 ,000,000 * 100Kb  = 100,000,000    = 服务器消耗了100G内容  （带宽）

				用户访问一个页面：
						100B
				100万个用户访问这个页面
						1 ,000,000 * 100B = 100,000,000  = 100M服务器消耗了100M内容  （带宽）

				买服务器：
						网络服务器运营商
						按流量收费： 我们尽可能压缩网页内容，才输出给浏览器
		
			怎么对网页内容压缩？
				可以是java 的GZIPOutputStream类进行gzip压缩。

				
*//**
		 * 对网页内容进行压缩
		 *//*
		//创建临时的字节数组容器
		ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
		//创建GZIPOutputStream对象
		GZIPOutputStream gzip = new GZIPOutputStream(byteArr);
		//开始写出压缩内容
		gzip.write(sb.toString().getBytes());
		//刷新缓冲区
		gzip.finish();
		
		//从临时的字节数组容器中得到压缩后的网页内容
		byte[] result = byteArr.toByteArray();
		
		System.out.println("压缩后的数据大小："+result.length);
		
		*//**
		 * 注意：告诉浏览器数据压缩格式  发送响应头：content-encoding:gzip
		 *//*
		response.setHeader("content-encoding", "gzip");
		
		//把压缩后的内容输出到浏览器
		response.getOutputStream().write(result);
				


 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 * @author 贤元
 *
 */
public class TodayNote {

}
