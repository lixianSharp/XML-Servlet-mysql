package gz.itcast.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收参数
		String userName = request.getParameter("userName");
		String userPwd = request.getParameter("userPwd");
		
		//判断登录
		if("eric".equals(userName) && "123456".equals(userPwd)){
			//登录成功
			//把用户数据存到session域对象中
			HttpSession session = request.getSession(true);
			session.setAttribute("user", userName);
			//跳转到用户主页
			response.sendRedirect(request.getContextPath()+"/user/index.jsp");
		}else{
			//登录失败
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
