package gz.itcast.c_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 案例二：定时刷新
 * @author lixia
 *
 */
public class ResponseDemo3 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 定时刷新
		 * 原理：浏览器认识refresh头，得到refresh头之后重新再请求当前资源
		 */
		//response.setHeader("refresh", "1");//设置响应头"refresh"  每隔1秒刷新一次页面
		
		/**
		 * 隔n秒之后跳转到另外的资源
		 */
		//隔3秒之后跳转到adv.html页面
		response.setHeader("refresh","3;url=/day09/adv.html");//设置响应头"refresh"  
	}
}
