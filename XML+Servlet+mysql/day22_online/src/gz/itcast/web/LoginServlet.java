package gz.itcast.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 登录逻辑
 * @author 贤元
 *
 */
public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1）接收参数
		String userName=request.getParameter("userName");
		
		//2)登录成功
		//保存用户数据到session域对象中
		HttpSession session=request.getSession(true);
		/**
		 * 编写一个HttpSession的属性监听器，用于监听user属性名称的增加
		 */
		
		session.setAttribute("user", userName);
		//把登陆用户的Ip地址存放到session中
		session.setAttribute("ip",request.getRemoteHost());
		
		
		//3)跳转用户主页
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

}
