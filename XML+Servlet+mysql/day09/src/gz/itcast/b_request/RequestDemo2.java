package gz.itcast.b_request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 目的：Servlet的执行流程
 * @author 贤元
 *
 */
public class RequestDemo2 extends HttpServlet {

	/**
	 * 注意：tomcat服务器首先会调用servlet的service方法,
	 * 		然后在service方法中再根据请求方式来分别调用对应的doXXX方法。
	 * 			(例如：如果是GET请求方式，在service方法中调用doGet方法)
	 * 			(例如：如果是POST请求方式，在service方法中调用doPost方法)
	 * 
	 * 因为最常用的是GET 和POST，所以编写servlet程序，只需要覆盖doGet和doPost即可！！
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//getMethod() 返回与该请求的HTTP方法的名称/获取请求的方式
		System.out.println(request.getMethod());//打印提交的方式
		System.out.println("service方法被调用");
		
	}
	
	/**
	 * 该方法用于接收浏览器Get方式提交的请求
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GET方式提交");
		
	}

	
	

	/**
	 * 该方法用于接收浏览器的POST方式提交的请求
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Post 方法提交");
	
	}

}
