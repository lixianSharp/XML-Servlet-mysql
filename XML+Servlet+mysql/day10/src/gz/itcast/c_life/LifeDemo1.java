package gz.itcast.c_life;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 时间：
 * 功能：Servlet的生命周期
 * 目的：测试Servlet的生命周期
 * 心得：Servlet是单实例多线程的，也就是一个Servlet只能创建一个Servlet对象
 * @author 贤元
 *
 */
public class LifeDemo1 extends HttpServlet {


	/**
	 * 1、构造方法:
	 * 	创建Servlet对象的时候调用。
	 * 	默认情况下，第一次访问Servlet对象的时候创建Servlet对象，只调用1次。证明Servlet对象在Tomcat中是单实例的。
	 */
	public LifeDemo1(){
		System.out.println("1.servlet对象被创建了");
	}
	
	/**
	 * 2.init方法：创建完Servlet对象的时候调用。只调用一次
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("2.init方法被调用了");
	}
	
	/**
	 * 3.service方法:每次发出请求的时候调用。调用n次。
	 * 通过service方法根据请求方式是get还是post去调用相应的doGet或doPost方法。
	 * 
	 */
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		System.out.println("3.service方法被调用了");
	}
	
	
	/**
	 * 4.destroy方法：销毁对象的时候调用。停止服务器或者重新部署web应用时销毁Servlet对象，只调用1次。
	 */
	@Override
	public void destroy() {
		System.out.println("4.destroy被销毁了");
	}
	


}
