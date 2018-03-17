package gz.itcast.c_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * content-Type作用
 * 		表示：表示服务器发送给浏览器的数据类型及内容编码
 * @author lixian
 *
 */
public class ResponseDemo4 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 设置响应实体内容编码（服务器发给浏览器的）
		 */
		response.setCharacterEncoding("utf-8");
		/*
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		//下面这句等价于上面的两句
		response.setContentType("text/html;charset=utf-8");
		//表示服务器发送给浏览器的数据类型及内容编码  */
		/**
		 * 1、服务器发送给浏览器的数据类型
		 */
		//response.setHeader("content-type", "text/html");
		//response.setContentType("text/html");//和上面的代码等价，推荐使用此方法
		response.setContentType("text/xml");//设置服务器发送给浏览器的数据类型
		//response.setContentType("image/jpg");
		response.getWriter().write("<html><head><title>this is title</title>"
				+ "</head><body>中国</body></html>");//将内容发送到浏览器中显示
	}
}
