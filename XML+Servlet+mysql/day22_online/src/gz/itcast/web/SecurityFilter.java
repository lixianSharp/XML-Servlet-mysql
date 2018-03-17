package gz.itcast.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SecurityFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		//1)管理员
		String ip=request.getRemoteHost();
		
		if("localhost".equals(ip)||"192.168.0.100".equals(ip)){
			//1）管理员
			chain.doFilter(request, response);
		}else{
			//2)非管理员
			response.getWriter().write("别想删除了，你不是管理员！！！");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
