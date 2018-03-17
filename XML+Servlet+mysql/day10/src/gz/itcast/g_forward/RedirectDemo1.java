package gz.itcast.g_forward;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 重定向
 * 结论：如果要使用request域对象进行数据共享，只能用转发技术！！
 * 注意：
 * 4）重定向会向服务器发送两次请求！
 * 1）重定向地址栏会改变，变成重定向的地址
 * 2）重定向可以跳转到web应用，或其他应用，设置是外部域名网站
 * 3）不能在重定向的过程中把数据保存到request域中
 * 
 * @author 贤元
 *
 *
 */
public class RedirectDemo1 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//保存数据到requst域对象
		request.setAttribute("name", "lixian");
		
		
		//重定向
		/**
		 * 注意：可以跳转到web应用内，或其他web应用，甚至其他外部域名
		 *  注意： 如果要使用request域对象进行数据共享，只能用转发技术！！！如果要使用request域对象进行数据共享，使用重定向没有数据共享的效果
		 */
		//response.sendRedirect("/day09/adv.html");
		//response.sendRedirect("/day10/GetDataServlet");
		//上面一行代码等价于下面一行代码        this.getServletContext().getContextPath()得到web应用路径  web应用路径：部署到tomcat服务器上运行的web应用名称
		response.sendRedirect(this.getServletContext().getContextPath()+"/GetDataServlet");
	}

}
