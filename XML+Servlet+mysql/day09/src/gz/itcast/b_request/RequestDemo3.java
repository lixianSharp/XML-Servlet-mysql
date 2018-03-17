package gz.itcast.b_request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 案例： 获取浏览器的类型
 * 
 * @author lixian
 * 
 */
public class RequestDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置(响应)编码格式
		response.setContentType("text/html;charset=utf-8");
		
		// 获取请求头：user-agent 中的信息    / 获取浏览器的类型，请求头user-agent代表浏览器的类型
		String userAgent = request.getHeader("user-agent");//获取请求头，并返回请求头的值
		System.out.println(userAgent);
		//使用火狐浏览器测试的运行结果如下:
		//Mozilla/5.0 (Windows NT 6.3; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0  


		// 判断用户使用的浏览器类型
		if (userAgent.contains("Firefox")) {
			response.getWriter().write("您正在使用火狐浏览器");
		} else if (userAgent.contains("Chrome")) {
			response.getWriter().write("您正在使用谷歌浏览器");
		} else if (userAgent.contains("Trident")) {
			response.getWriter().write("你正在使用IE浏览器");
		} else {
			response.getWriter().write("地球上没有这个浏览器");
		}
		
		//response.getWriter() 这个就等价于打印流PrintWriter

	}

}
