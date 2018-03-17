package gz.itcast.e_thread;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 问题:因为Servlet是多线程的，所以当多个servlet的线程同时访问了servlet的共享数据，如成员变量，可能回引发线程安全问题。
 * 解决方式:把使用到共享数据的代码块进行同步，使用synchronized关键字进行同步。
 * 建议:在servlet类中尽量不要使用成员变量。如果确实要使用成员变量，必须使用同步。
 * @author 贤元
 *
 */
public class ThreadDemo extends HttpServlet {

	int count = 1;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应编码格式
		response.setContentType("text/html;charset=utf-8");

		synchronized (ThreadDemo.class) {// 锁对象必须是唯一的，建议使用类对象
			response.getWriter().write("您是当前的第" + count + "个访问者");
			// 线程1还没有执行count+=
			/*
			 * try { 
			 * Thread.sleep(5000); 
			 * } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */
			count++;
		}
	}

}
