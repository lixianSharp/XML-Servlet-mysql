package gz.itcast.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 注销逻辑
 * @author 贤元
 *
 */
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 用户注销：删除保存在登录成功时保存的session域对象中的数据
		 *
		 */
		HttpSession session=request.getSession();
		if(session!=null){
			session.removeAttribute("user");
			session.removeAttribute("ip");
		}

		//1)获取context域中的map集合
		
		//2)移除对应的session对象
		
		
		//3)把修改后的map保存到context域中
		response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

}
