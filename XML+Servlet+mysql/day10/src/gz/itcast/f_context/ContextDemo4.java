package gz.itcast.f_context;

import gz.itcast.f_context.ContextDemo3.Student;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 获取数据
 * @author lixian
 *目的：取出在context域对象中的数据
 */
public class ContextDemo4 extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.得到域对象 ServletContext对象
		ServletContext context=this.getServletContext();
		
		//2、从域对象中取出数据
		Student student=(Student) context.getAttribute("student");
		System.out.println(student);
		
		/*String name=(String) context.getAttribute("name");
		System.out.println("name="+name);*/
		
	}

}
