package gz.itcast.b_mapping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 时间：
 * 功能：
 * 目的：Servlet的映射路径,用于测试精确匹配和模糊匹配的优先级。精确匹配的优先级最高，以后缀名结尾的模糊匹配的优先级最低！
 * 心得：
 * @author 贤元
 *
 */
public class Demo1 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 System.out.println("Demo1.doGet()");
	}

}
