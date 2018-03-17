package gz.itcast.d_init;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
/**
 * 有参数的init和无参的init方法
 * @author lixian
 *
 */
public class InitDemo extends HttpServlet {

	/**
	 * 有参的init方法
	 * 该方法是servlet的生命周期方法，一定会被tomcat服务器调用的
	 */
	/**
	 * 注意事项：如果要编写初始化代码，不需要覆盖有参数的init方法，
	 */
	/*@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("有参的init方法");
	}*/
	
	
	
	
	/**
	 * 无参数的init方法
	 * 该方法是servlet的编写初始化代码的方法，是Sun公司设计出来专门给开发者覆盖，
	 * 		然后在里面编写Servlet初始化逻辑代码的方法
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("无参的init方法");
		System.out.println("以下是编写的Servlet初始化逻辑代码：");
	}
}
