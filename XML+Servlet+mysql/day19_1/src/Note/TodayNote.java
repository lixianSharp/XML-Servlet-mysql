package Note;
/**
 	
  1、连接池
  	思考：
  		程序中连接如何管理？
  		1、连接资源宝贵，需要对连接进行管理。
  		2、连接：
  			a）操作数据库，创建连接
  			b）操作结束，关闭！
  		分析：
  		   	涉及频繁的连接的打开、关闭，影响程序的运行效率！
 		连接管理：
 		  	预先创建一组连接，用的时候每次取出一个，用完后，放回；
 		学习连接池：
 			a、自定义一个连接池
 			b、学习优秀的连接池组件：
 				a）DBCP
 				b）C3PO
 	
 	自定义连接池：
 		程序实现思路：
 		 1、指定“初始化拦截数目” 【3】
 		  	 （App启动的时候，就执行创创建）
 		 2、指定“最大连接数”  【6】
 		 3、指定“当前使用连接个数”【不能超出最大连接数】
 		 
 		  代码实现：
		 	1.  MyPool.java  连接池类，   
			2.  指定全局参数：  初始化数目、最大连接数、当前连接、   连接池集合
			3.  构造函数：循环创建3个连接
			4.  写一个创建连接的方法
			5.  获取连接
			------>  判断： 池中有连接， 直接拿
	 		------>      池中没有连接，
			------>            判断，是否达到最大连接数； 达到，抛出异常；没有达到最大连接数，
									创建新的连接
			6. 释放连接
	 		------->  连接放回集合中(..)
 	
 		代理：
 			如果对某个接口中的某个指定的方法的功能进行扩展，而不想实现接口里所有方法，可以使用(动态)代理模式！。
 			Java中代理模式：静态/动态/Cglib代理(spring)
 			使用动态代理，可以监测接口中方法的执行！
 			
 		如果对Connection对象，生成一个代理对象
 		|--Proxy
			static Object newProxyInstance(
				ClassLoader loader,    当前使用的类加载器
				Class<?>[] interfaces,   目标对象(Connection)实现的接口类型
				InvocationHandler h    事件处理器：当执行上面接口中的方法的时候，就会自动触发事件处理器代码，把当前执行的方法(method)作为参数传入。

 		代理的总结：(了解会用)
			使用代理，可以在不实现接口的情况，对接口的方法进行扩展，添加额外的用户需要的业务逻辑！

	2、开源的连接池技术！！
		概述：
			Sun公司约定：如果是连接池技术，需要实现一个接口！
			javax.sql.DataSource;
			
		连接池：
			DBCP
			C3P0
		
		2.1 DBCP连接池
 			•	DBCP 是 Apache 软件基金组织下的开源连接池实现，使用DBCP数据源，应用程序应在系统中增加如下两个 jar 文件：
			•	Commons-dbcp.jar：连接池的实现
			•	Commons-pool.jar：连接池实现的依赖库
			•	Tomcat 的连接池正是采用该连接池来实现的。该数据库连接池既可以与应用服务器整合使用，也可由应用程序独立使用。
			•	核心类：BasicDataSource
		使用步骤
			•	引入jar文件
				commons-dbcp-1.4.jar
				commons-pool-1.5.6.jar

 			配置方式实现DBCP连接池，需要注意的地方：配置文件中的key与BaseDataSource中的属性一样。 db.properties
 							url=jdbc:mysql:///jdbc_demo
							driverClassName=com.mysql.jdbc.Driver
							username=root
							password=root
							initialSize=3
							maxActive=6
							maxIdle=3000
 
 		2.2 C3P0连接池：
 			
 			C3P0连接池：
 				最长用的连接池技术！ Spring框架，默认支持C3P0连接池技术！
 			C3P0连接池，核心类：
 				CombopooledDataSource ds;
 			使用：
 				1、下载，引入jar文件： c3p0-0.9.1.2.jar
 				2、使用连接池，创建连接
 					a）硬编码方式
 					b）配置方式(xml)
 
 	  2.3 优化
 	  	项目：连接的管理，交给连接池！
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 * @author 贤元
 *
 */
public class TodayNote {

}
