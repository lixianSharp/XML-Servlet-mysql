package gz.itcast.c_response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 设置响应的信息
 * @author lixian
 *
 */
public class ResponseDemo1 extends HttpServlet {

	/**
	 * 1）tomcat服务器把请求信息封装到HttpServletRequest对象中，
	 * 			且把响应信息封装到HttpServletResponse对象中
	 * 2）tomcat服务器调用doGet方法，传入request和response对象
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/**
		 * 3）通过response对象改变响应信息
		 */
	
		/**
		 * 响应行
		 */
		//修改状态码
		//response.setStatus(404);//修改状态码
		//response.sendError(404);//发送404状态码+404的页面
		
		
		/**
		 * 3.2响应头
		 */
		response.setHeader("server", "JBOSS");//将响应头"server" 的值设置成 "JBOSS"

		/**
		 * 3.3 实体内容(浏览器直接能看到的内容就是实体内容)
		 * 两种将内容发送到浏览器中显示的方式
		 */
		//字符内容
		//response.getWriter().write("01.hello world");
		//字节内容
		response.getOutputStream().write("02.hello world我就是字节流哦".getBytes("utf-8"));
		//response.getOutputStream().write()//等价于OutputStream 字节输入流
		//response.getWriter();等价于PrintWriter打印流
		//response.getWriter().write("我就是打印流哦");
		//这两种向浏览器输出内容的方式不能同时使用，只能选择其中一种来使用
	}

}
