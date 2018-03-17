package gz.itcast.b_request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 案例： 防止非法链接
 * 这是需要下载的资源
 * @author lixia
 * 
 * 这个案例的目的：获取当前的请求来自哪里，并对之进行相应处理。
 *
 */
public class RequestDemo4 extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置响应编码格式(响应：服务器-》浏览器)
		response.setContentType("text/html;charset=utf-8");
		
		//得到referer头        referer:表示当前请求来自哪里
		String referer=request.getHeader("referer");//获取当前的请求头
		System.out.println("referre="+referer);//  referre=http://localhost:8080/day09/adv.html

		
		
		
		/**
		 * 判断非法链接
		 * 1）直接访问的话referer=null
		 * 2）如果当前请求不是来自广告则向浏览器显示"当前是非法链接，请回到首页"
		 * 3）如果链接来自http://localhost:8080/day09/adv.html则打印"资源正在下载"
		 */
		if(referer==null||!referer.contains("/day09/adv.html")){
			response.getWriter().write("当前是非法链接，请回到首页");
		}else{
			//正确的链接
			response.getWriter().write("资源正在下载");
		}
		
		
	}

	

}
