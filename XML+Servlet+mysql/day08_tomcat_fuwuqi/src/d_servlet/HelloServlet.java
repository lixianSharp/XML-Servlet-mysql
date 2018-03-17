package d_servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
//导入contact中lib包下的servlet-api.jar包
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 第一个servlet程序
 * @author 贤元
 *
 */
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//解决中文乱码的问题
		resp.setContentType("text/html;charset=utf-8");
		//向浏览器输出内容
		resp.getWriter().write("这是第一个servlet程序,当前时间为："+new Date());
		
	}
}
