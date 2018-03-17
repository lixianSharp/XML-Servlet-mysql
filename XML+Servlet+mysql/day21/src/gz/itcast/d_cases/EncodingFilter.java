package gz.itcast.d_cases;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * 参数中文乱码问题的过滤器
 * @author APPle
 *
 */
public class EncodingFilter implements Filter {

	//对象销毁或者tocat服务器停止的时候执行
	public void destroy() {

	}
	/**
	 * 初始化方法，在tomcat启动的时候创建完过滤器对象的时候就进行初始化，
	 * FilterConfig对象封装了所有当前过滤器配置的初始化参数 
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	/**
	 * 执行过过滤任务
	 */
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//强制转换
		HttpServletRequest request = (HttpServletRequest)req;
		
		/**
		 * post提交参数
		 */
		request.setCharacterEncoding("utf-8");
		//创建一个HttpServletRequest实现类的装饰者类,重写getParameter方法
		MyHttpRequest myRequest = new MyHttpRequest(request);
		
		/**
		 * 放行
		 * 注意：
		 * 这里放行的应该是装饰后的request对象，这样在业务的servlet调用getParameter才是新的重写后的方法。
		 */
		chain.doFilter(myRequest , resp);
		
	}
}
/**
 * 1）装饰者类
 * @author APPle
 *
 */
class MyHttpRequest extends HttpServletRequestWrapper{
	/**
	 * 2)声明一个被装饰者类型的成员变量
	 * @param request
	 */
	private HttpServletRequest request;
	
	/**
	 * 3)接收被装饰者类对象
	 * @param request
	 * 构造函数
	 */
	public MyHttpRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	/**
	 * 4)重写getParameter方法
	 */
	@Override
	public String getParameter(String name) {
		try {
			/**
			 * 对get提交参数处理
			 */
			//1)得到原来的参数
			String value = request.getParameter(name); //iso-8859-1
			
			//2）手动解码。如果提交方式是get方式，则对参数进行手动解码，编码设置为utf-8
			if("GET".equals(request.getMethod())){
				value = new String(value.getBytes("iso-8859-1"),"utf-8");
			}
			return value;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
} 






