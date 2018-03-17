package gz.itcast.c_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *案例一：请求重定向
 * 	   （相当于超链接跳转页面）
 * 
 * 注意：请求重定向浏览器一共向服务器发送了2次请求
 * @author lixia
 *
 */
public class ResponseDemo2 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 需求：跳转到adv.html
		 * 使用请求重定向：发送一个302状态码+location的响应头
		 */
		/*response.setStatus(302);
		response.setHeader("location", "/day09/adv.html");
		*/
		
		//请求重定向简化写法
		response.sendRedirect("/day09/adv.html");
		
		/**
		 * 注意：浏览器认识302状态码，当浏览器得到302状态码之后，
		 * 			会再次自动向服务器发送一个请求，请求的地址就是location的value值的地址。
		 * 
		 * http://localhost:8080/day09/ResponseDemo2
		 * http://localhost:8080/day09/adv.html
		 */
	}
}
