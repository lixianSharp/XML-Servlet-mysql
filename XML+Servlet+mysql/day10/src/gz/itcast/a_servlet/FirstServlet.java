package gz.itcast.a_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 时间：
 * 功能：	
 * 目的：向浏览器输出"this is first servlet"这个字符串
 * 心得：
 * @author 贤元
 *
 */
public class FirstServlet extends HttpServlet {
	
	public FirstServlet(){
		System.out.println("FirstServlet对象被创建了");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().write("this is first servlet");
		// 向浏览器输出内容  等价于字符打印流PrintWriter

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
