package gz.itcast.g_forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetDataServlet extends HttpServlet {

	/**
	 * 如果要使用request域对象进行数据共享，只能用转发技术！！！
	 */
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 从request域对象中得到数据
		 */
		String name=(String) request.getAttribute("name");
		System.out.println("name="+name);
	}

}
