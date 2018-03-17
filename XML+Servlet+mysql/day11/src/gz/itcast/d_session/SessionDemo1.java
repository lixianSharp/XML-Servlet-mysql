package gz.itcast.d_session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 保存数据到session域对象
 * @author 贤元
 *
 */
public class SessionDemo1 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//1、创建session对象
		HttpSession session=request.getSession();
		
		/**
		 * 得到session的编号
		 * 
		 */
		System.out.println("id="+session.getId());
		
		/**
		 * 修改session的有效时间
		 */
		//session.setMaxInactiveInterval(20);
		
		/**
		 * 手动发送一个硬盘保存的cookie给浏览器
		 */
		Cookie c=new Cookie("JSESSIONID",session.getId());
		c.setMaxAge(60*60);
		response.addCookie(c);
		
		//2、保存会话数据
		session.setAttribute("name", "rose");
		
	}

}
