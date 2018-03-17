package gz.itcast.f_context;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 得到web应用参数
 * 注意：Servlet的参数只能由当前的这个ServletConfig获取！！
 * @author lixia
 * 
 */
public class ContextDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到ServletContext上下文对象
		ServletContext context = this.getServletContext();

		System.out.println("参数：" + context.getInitParameter("AAA"));// 根据参数名称获取web应用的初始化参数    打印结果：参数：AAA's value
		

		// 获取web应用中的所有初始化参数  ServletContext可以得到在web应用中所有的初始化参数
		Enumeration<String> enums = context.getInitParameterNames();// 获取web应用中的所有的参数名称
		while (enums.hasMoreElements()) {
			String paramName = enums.nextElement();
			String paramValue = context.getInitParameter(paramName);// 根据参数名称获取web应用的初始化参数
			System.out.println(paramName + "=" + paramValue);
		}
		/**
		 * 打印结果：
		 * BBB=BBB's value
			AAA=AAA's value
			CCC=CCC's value
		 */

		System.out.println("=============================");
		
		
		// 尝试得到ConfigDemo中的servlet参数,得不到，当前的ServletConfig只能得到当前Servlet中配置的参数，若要得到Servlet中配置的参数，只能在当前Servlet中才能得到该Servlet中配置的参数   （得不到，因为每一个servlet对应一个ServletConfig）
		String path = this.getServletConfig().getInitParameter("path");
		System.out.println("path=" + path);//path==null
		

	}

}
