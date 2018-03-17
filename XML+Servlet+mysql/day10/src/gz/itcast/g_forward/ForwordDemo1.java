package gz.itcast.g_forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 转发
 * 结论：如果要使用request域对象进行数据共享，只能用转发技术！！
 * 转发的注意：
 * 	1、转发地址栏不会改变
 * 	2、转发只能转发到当前web应用内的资源
 *  3、可以在转发过程中把数据保存到request域对象中。
 * @author 贤元
 *
 */
public class ForwordDemo1 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 保存数据到request域对象
		 */
		request.setAttribute("name", "rose");
		
		//转发
		/**
		 * 注意：不能转发当前web应用以外的资源
		 * 注意： 如果要使用request域对象进行数据共享，只能用转发技术！！！
		 * 如果只是跳转到另外一个页面，转发和重定向都可以用
		 */
		/*RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/GetDataServlet");
		rd.forward(request, response);*/
		this.getServletContext().getRequestDispatcher("/GetDataServlet").forward(request, response);
		
		
		
		
	}

}
