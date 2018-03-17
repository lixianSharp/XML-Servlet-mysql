package gz.itcast.f_context;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 得到web应用路径
 * @author lixia
 *
 */
public class ContextDemo1 extends HttpServlet {

	/**
	 * ServletContext对象，叫做Servlet的上下文对象。表示一个当前web应用环境。
	 * 		一个web应用中只有一个ServletContext对象
	 * 
	 * ServletContext对象创建时间：加载web应用时创建ServletContext对象。
	 * 				    得到对象：从ServletConfig的getServletContext()方法中得到ServletContext对象
	 * 
	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、得到ServletContext对象
		//ServletContext context=this.getServletConfig().getServletContext();
		ServletContext context=this.getServletContext();//推荐使用这种方式得到ServletContext对象
		
		//2.得到web应用路径  /day10
		/**
		 * web应用路径：部署到tomcat服务器上运行的web应用名称
		 */
		String contextPath = context.getContextPath();
		System.out.println(contextPath);//   /day10
		//上面两句等价于 request.getContextPath()  
		//System.out.println(request.getContextPath());//  /day10
		/**
		 * 请求重定向：一共向服务器发送了两次请求，第一次发送的是http://localhost:8080/day10/ContextDemo1，之服务器向浏览器返回一个302状态码，浏览器能识别302状态码，之后浏览器再发送请求:http://localhost:8080/day10/index.html
		 * 案例：应用到请求重定向    注意：在这里所说的路径都是相对于tomcat服务器而言的，因为servlet是在tomcat服务器上运行的，servlet自身是不能运行的。
		 */
		response.sendRedirect(contextPath+"/index.html");
		
	}

}
