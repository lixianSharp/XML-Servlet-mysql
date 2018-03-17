package gz.itcast.d_session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 从session域对象中取出会话数据
 * @author 贤元
 *
 */
public class SessionDemo2 extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//1、得到session对象
		HttpSession session=request.getSession();
		
		/**
		 * 得到session的编号
		 * 
		 */
		System.out.println("id="+session.getId());
		
		if(session==null){
			System.out.println("没有找到对应的session对象");
			return;
		}else{
			System.out.println("session的内容为："+session.getAttribute("name"));
		}
		
		//2、取出数据
		String name=(String)session.getAttribute("name");
		System.out.println("name="+name);
		
		
		
	}

}
