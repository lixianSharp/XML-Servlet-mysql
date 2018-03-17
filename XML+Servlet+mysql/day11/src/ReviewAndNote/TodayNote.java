package ReviewAndNote;

import javax.servlet.http.HttpSession;
/*
*//**
 1、课程回顾
   
   1）Servlet生命周期（重点）：
   	 构造方法：创建servlet对象。默认情况下，第一次访问Servlet对象时只调用一次。
   	 init方法(有参)：创建完Servlet对象后调用。只调用1次。
   	 		注意：会调用无参的init方法。
   	 service方法：servlet提供服务的方法。每次发出请求调用。
   	 		注意：request对象。response对象
     destroy方法：Tomcat服务器停止或web应用重新部署，Servlet对象销毁，destroy被调用
  
   2）ServletConfig对象：
   		获取Servlet的初始化参数：
   			getInitParameter("name")
  			getInitParameterNames()
   3）ServletContext对象
   		得到web应用路径
   			context.getContextPath();
   			HttpServletRequest.getContextPath();等价于上面的代码
   		得到web应用参数
   			context.getInitParameter("name")
   			context.getInitParameterNames()
   		域对象：
   			context.setAttribute("name",Object);保存数据
   			context.getAttribute("name");得到数据
   			context.removeAttribute("name");请输入数据
   		转发：
   			context.getRequestDispatcher("路径").forword(request,response);
  			request.getRequestDispatcher("路径").forword(request,response);等价于上面的代码
  
  		得到web应用中的资源路径
  			context.getRealPath("路径");
  			context.getResourceAsStream("路径");
  
  
  
  
           今天的目标：会话管理
  	
  	2、会话管理入门
  	
  		2.1生活中的会话
  			我：小张，你会跳小苹果吗？
  			小张： 会，怎么了？
  			我：公司你那会上要表演节目，你教教我吧
  			小张：没问题，一顿饭而已
  			我：ok
  			.......
  			
  		    在这次生活中的会话中产生通话记录（会话数据）
  
  		2.2软件中的会话
  		  
  		   一次会话：打开浏览器--》访问一些服务器内容--》关闭浏览器
  		
  		登录场景：
  			打开浏览器-》登浏览到登录的页面-》输入用户名和密码-》访问到用户主页(显示用户名)
  													修改密码
  													修改收货地址
  													......
  				问题：在此处登录会话过程中产生的数据（用户会话数据）如何保存下来呢？
  			购物场景：
  				打开浏览器-》浏览商品列表-》加入购物车（把商品信息保存下来）-》关闭浏览器
  				打开这个浏览器-》直接进入购物车-》查看到上次加入购物车的商品-》下订单-》支付
  				
  				问题：在购物会话过程中，如何保存商品信息？？？
  				
  			会话管理：管理浏览器客户端 和 服务器端 之间的会话过程中产生的数据。
  			
  			域对象：实现资源之间的数据共享。
  			
  			request 域对象
  			response 域对象
  			
  			转发是同一个请求
  		  登录场景：
  		  	小张： 输入“张三”  (保存数据：context.setAttribute("name","张三"))--》用户主页(显示张三)
  		  	小李：输入“李四”  (保存数据：context.setAttribute("name","李四"))--》用户主页(显示李四)
  		  	
  		  		问题：context是所有用户共有的资源!! 会覆盖数据
  		  	
  		  	小张：输入"张三"  (保存数据：request.setArribute("name","张三"))；-->用户主页(显示张三)
  		  			跳转，一定要使用转发技术要跳转页面！！
  		  	
  		  	
  		  	
  		   解决办法： 可以使用session域对象来保存会话数据！！！
  		   
  		   	
  	  2.3 会话技术
  	  	
  	  	 Cookie技术：会话数据保存在浏览器客户端
  	  	 Session技术：会话数据保存在服务器端
  		  	
   3、Cookie技术
   		3.1特点：、
   			Cookie技术：会话数据保存在浏览器客户端
   		3.2Cookie技术核心
   			
   			Cookie类：用于存储会话数据。（注意：在Servlet文档中）
  		  	
  		  	1）构造Cookie对象
  		  		Cookie(java.lang.String name, java.lang.String value) 构造方法
  		  	
  		  	2）
  		  		void setPath(java.lang.String uri) 设置Cookie有效访问路径
  		  		void setMaxAge(int expiry)  设置cookie的有效时间
				void setValue(java.lang.String newValue) 设置cookie的值
			3）发送cookie到浏览器端
				void response.addCookie(Cookie cookie) 发送cookie
			4）服务器接受cookie
				Cookie[] request.getCookies(); 接收Cookie
  		  	
  		  	
  		 3.3 Cookie原理
  		 	1）服务器创建cookie对象，把会话数据存储到cookie对象中
  		 		new Cookie("name","value");
  		  	2）服务器发送cookie信息到浏览器
  		  		response.addCookie(cookie);
  		  		
  		  		举例：set-cookie:name=eric (隐藏发送了一个set-cookie名称的响应头)
  		  	3）浏览器得到服务器发送的cookie，然后保存在浏览器端。
  		  	4）浏览器在下次访问服务器时，会带着cookie信息
  		  		举例：cookie:name=eric (隐藏带着一个叫cookie名称的请求头)
  		  	5）服务器接受到浏览器发送来的的cookie信息
  		  		request.getCookies();
  		  	
  		 3.4Cookie的细节
  		 	1）void setPath(java.lang.String uri) 设置Cookie有效访问路径
  		 		有效路径指的是cookie的有效路径保存在哪里，那么浏览器在有效路径下访问服务器时就会带着cookie信息，否则不带cookie信息。
  		  	
  		 	2）void setMaxAge(int expiry);设置cookie的有效时间
  		  	       正整数：表示cookie数据保存浏览器的缓存目录(因盘中)，数值表示保存的时间。
  		  	       负整数：表示cookie数据保存浏览器的内存中。浏览器关闭cookie就丢失了！！！
  		  	        零 ：   表示删除同名的cookie数据。
  		
  		  	3）Cookie数据类型只能保存非中文字符串类型的。
  		  	一个Cookie只能标识一种信息，它至少含有一个标识该信息的名称（NAME）和设置值（VALUE）。 
				一个WEB站点可以给一个WEB浏览器发送多个Cookie，一个WEB浏览器也可以存储多个WEB站点提供的Cookie。
				浏览器一般只允许存放300个Cookie，每个站点最多存放20个Cookie，每个Cookie的大小限制为4KB。

  		  	
  		 3.5 案例：显示用户上次访问的时间。
  		  	
  		 3.6 案例：查看用户浏览过的商品。
  		  	
  		  	
  		  	一个技巧：  ctrl+F
  		  	
  		  	
  		  	类分包：（按照功能/模块来分）
  		  	  
  		  	   gz.itcast.hist.entity  存放实体对象
  		  	   gz.itcast.hist.dao   Data Access Object 数据访问对象。存放实体对象的操作方法
  		  	   						（CRUD方法 --create read update delete）
  		  	   gz.itcast.hist.servlet  存放Servlet程序
  		  	   gz.itcast.hist.util   存放工具类
  		  	   gz.itcast.hist.test   存放测试类
  		  	   .....
  		  	   
  		  	   	  顺序：实体对象--》DAO类--》Servlet程序
  		  	   	  	
  		  	   	  	util+test
  		  	
  		  思路是最重要的！！！！！！！！！
  		 
  		  	
  		  	
  	4、Session技术
  	  	
  	  	4.1 引入
  	  	 Cookie的局限：
  	  	 	1）Cookie只能存字符串类型，不能保存对象
  		  	2）只能存非中文
  		  	3）1个cookie的容量不超过4kB
  		  	
  		 如果要保存非字符串，超过4kb内容，只能使用session技术 	
  		 
  		 Session特点：
  		 	 会话数据保存在服务器端、(内存中（机器的内存中）)
  		  	
  		4.2 Session技术核心
  		
  			HttpSession类：用于保存会话数据
  		  	1）创建得到session对象
  		  		HttpSession  getSession()
  		  		HttpSession  getSession(boolean create)
  		  	2)设置Session对象
  		  		void setMaxInactiveInterval(int interval);设置Session的有效时间
  		  		void invalidate()  销毁Session对象
  		  		java.lang.String.getId()   得到Session编号
  		  	3）保存会话数据到session对象中
  		  		void setAttribute(java.lang.String name, java.lang.Object value)  ： 保存数据
				java.lang.Object getAttribute(java.lang.String name)  ： 获取数据
				void removeAttribute(java.lang.String name) ： 清除数据

  	   4.3 Session原理：
  	   		问题：服务器能识别不同的浏览者！！！
  	   	  现象：
  	   	前提：在那个session域对象保存数据，就必须从哪个域对象取出！！！
  	   	   浏览器1：（给s1分配一个唯一的标记： s001,把标记s001发送给浏览器）
  	   	   	  1)创建session对象，保存会话数据
				HttpSession session=request.getSession();  -保存会话数据 s1
  		  
  		 浏览器1的新窗口：(带着s001的标记到服务器查询，s001->s1, 返回s1)
  		 	  2）得到session对象的会话数据
		 	     HttpSession session=request.getSession(); -可以取出  s1
		
		新的浏览器1：
				 1）得到session对象的会话数据
		 	     HttpSession session=request.getSession(); -不可以取出       不是s1
		
		浏览器2：
  		  	 1）得到session对象的会话数据
		 	   HttpSession session=request.getSession(); -不可以取出	不是s1
		
  		
  		  	
  	!!!代码解读：HttpSession session=request.getSession();
  		 
  		 1）第一次访问创建session对象，给session对象分配一个唯一的ID，叫JSESSIONID
					new HttpSession();
			2）把JSESSIONID作为Cookie的值发送给浏览器保存
					Cookie cookie = new Cookie("JSESSIONID", sessionID);
					response.addCookie(cookie);
			3）第二次访问的时候，浏览器带着JSESSIONID的cookie访问服务器
			4）服务器得到JSESSIONID，在服务器的内存中搜索是否存放对应编号的session对象。
					if(找到){
						return map.get(sessionID);
					}
					Map<String,HttpSession>]


					<"s001", s1>
					<"s001,"s2>
			5）如果找到对应编号的session对象，直接返回该对象
			6）如果找不到对应编号的session对象，创建新的session对象，继续走1的流程
	
			结论：通过JSESSION的cookie值在服务器找session对象！！！！！

  		  	
  		  	
  		  	
  		 4.4 Session细节
  		 	 
  		 	4.4 Sesson细节
			1）java.lang.String getId()  ： 得到session编号
			2）两个getSession方法：
					getSession(true) / getSession()  : 创建或得到session对象。没有匹配的session编号，自动创												建新的session对象。
					getSession(false):              得到session对象。没有匹配的session编号，返回null
			3）void setMaxInactiveInterval(int interval)  ： 设置session的有效时间
						session对象销毁时间：
							3.1 默认情况30分服务器自动回收
							3.2 修改session回收时间
							3.3 全局修改session有效时间
							
			<!-- 修改session全局有效时间:分钟 -->
				<session-config>
					<session-timeout>1</session-timeout>
				</session-config>

				 3.4.手动销毁session对象
					  void invalidate()     ： 销毁session对象
			4）如何避免浏览器的JSESSIONID的cookie随着浏览器关闭而丢失的问题
					
		/**
		 * 手动发送一个硬盘保存的cookie给浏览器
		 *//*
		Cookie c = new Cookie("JSESSIONID",session.getId());
		c.setMaxAge(60*60);
		response.addCookie(c);

		总结：
				1）会话管理： 浏览器和服务器会话过程中的产生的会话数据的管理。

				2）Cookie技术：
						new Cookie("name","value")
						response.addCookie(coookie)
						request.getCookies()
				3）Session技术
						request.getSession();
						
						setAttrbute("name","会话数据");
						getAttribute("会话数据")

		
			   
  		  	
  		  	
  		  	
  		  	
  		  	
  		  	
  		  	
  		  	
  		  	
  		  	
 * @author 贤元
 *
 */



























public class TodayNote {

}
