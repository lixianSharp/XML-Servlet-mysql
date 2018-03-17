package gz.itcast.a_path;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * web应用中路径问题
 * @author 贤元
 *
 */
public class PathDemo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//目标资源： target.html
		/**
		 * 思考：目标资源是给谁使用的
		 * 		给服务器使用的：  / 表示在当前web应用的根目录（webRoot下）
		 * 		给浏览器使用的：	/ 表示在webapps的根目录下
		 */
		
		/**
		 * 1、转发  
		 */
		//request.getRequestDispatcher("/target.html").forward(request, response);//给服务器用
		
		
		/**
		 * 2、请求重定向
		 */
		//response.sendRedirect("/day11/target.html");//给浏览器用的
		
		/**
		 * 3、html页面的超链接href
		 */
		response.getWriter().write("<html><body><a href='/day11/target.html'>超链接</a></hody></html>");//给浏览器用的
		
		
		
		/**
		 * 4、html页面中的form提交地址
		 * 
		 */
		response.getWriter().write("<html><body><form action='/day11/target.html'><input type='submit'/></form></body></html>");//给浏览器用的
		
		
	}

}
