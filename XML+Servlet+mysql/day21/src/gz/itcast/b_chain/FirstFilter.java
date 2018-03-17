package gz.itcast.b_chain;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 第一个过滤器
 * @author APPle
 *
 */
public class FirstFilter implements Filter {

	public void destroy() {
	}
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/**
	 * 执行过滤任务
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("1)执行第一个过滤器的请求过滤");
		
		/**
		 * FilterChian就是过滤器链对象，其中这个doFilter方法作用是把请求或响应交给下一个过滤器，
		 * 								如果没有下一个过滤器，就访问到目标资源了或返回给用户显示。
		 * 过滤器链的过滤执行顺序由web.xml文件中过滤器的filter-mapping的顺序决定的，先配置的先被访问到！！！！
		 * 
		 */
		
		//放行
		chain.doFilter(request, response);
		
		System.out.println("5)执行第一个过滤器的响应过滤");
		
	}
}