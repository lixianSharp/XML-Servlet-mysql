package gz.itcast.b_request;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo1 extends HttpServlet {

	/**
	 * tomcat服务器已经帮我们做了这两件事：
	 * 1）tomcat服务器把接收到的浏览器发送的请求数据，然后封装到HttpServletRequest对象中
	 * 2）tomcat服务器调用doGet方法，然后把request对象传入到Servlet中
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 3)从request对象取出请求数据
		 */

		 t1(request);//请求行

		//t2(request);// 请求头

	}

	/**
	 * 为了接受POST方式提交的数据 POST方式提交就用doPost
	 * 如果为了接收GET方式提交的数据，GET方式提交应该用doGet
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 3.3请求的实体内容
		 * 
		 * 注意：GET请求是没有实体内容的 POST请求是有实体内容的
		 */
		InputStream in = request.getInputStream();// 得到实体内容
		byte[] bys = new byte[1024];
		int len = 0;
		while ((len = in.read(bys)) != -1) {
			String str = new String(bys);
			System.out.println(str);
		}//打印结果:name=lixianyuan&password=1234

	}

	private void t2(HttpServletRequest request) {
		/**
		 * 3.2请求头
		 */
		String host = request.getHeader("Host");// 根据请求头名称得到请求头内容
		System.out.println(host);//localhost:8080

		// 获取所有的请求头名称列表
		Enumeration<String> enums = request.getHeaderNames();
		while (enums.hasMoreElements()) {// 判断是否有下一个元素
			String headerName = enums.nextElement();// 获取下一个元素
			// 根据请求头名称获取请求头的值(内容)
			String headerValue = request.getHeader(headerName);
			System.out.println(headerName + ":" + headerValue);
		}
	/*以下是该while语句打印的结果
	host:localhost:8080
	user-agent:Mozilla/5.0 (Windows NT 6.3; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0
	accept:text/html,application/xhtml+xml,application/xml;q=0.9,/*;q=0.8
	accept-language:zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3
	accept-encoding:gzip, deflate
	connection:keep-alive
	upgrade-insecure-requests:1
	*/
	}

	private void t1(HttpServletRequest request) {
		// 3.1获取请求行
		/**
		 * 3.1 格式:(GET /day09 hello Http1.1) 注意：浏览器访问资源，默认的提交方式是GET方式提交
		 */
		// 请求方式
		System.out.println("请求方式:" + request.getMethod());//请求方式:GET

		// 请求资源：
		System.out.println("URI:" + request.getRequestURI());
		//结果: URI:/day09/RequestDemo1

		System.out.println("URL:" + request.getRequestURL());
		//结果: URL:http://localhost:8080/day09/RequestDemo1

		// http协议版本
		System.out.println("http协议版本：" + request.getProtocol());
		//结果： http协议版本：HTTP/1.1

	}

}
