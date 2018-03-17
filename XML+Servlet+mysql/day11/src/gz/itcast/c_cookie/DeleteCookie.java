package gz.itcast.c_cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCookie extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 需求：删除cookie
		 */
		Cookie cookie=new Cookie("name","xxx");
		cookie.setMaxAge(0);//删除同名的Cookie
		response.addCookie(cookie);
		System.out.println("删除成功");
		
	}

}
