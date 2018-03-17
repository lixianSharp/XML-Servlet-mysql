package gz.itcast.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 登录权限过滤器
 * @author APPle
 *
 */
public class SecurityFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//强制转换
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		//是否登录判断逻辑   (判断是否有权限)
		//先判断有无session对象存在
		HttpSession session = request.getSession(false);//获取session域对象，不是以创建新的session对象得到的
		if(session==null){
			//没有登录
			response.sendRedirect(request.getContextPath()+"/noAuth.html");
			return;
		}else{
			String user = (String)session.getAttribute("user");
			if(user==null){//如果用户名为空，则认为没有登陆成功
				//没有登录成功   重定向到另外一个页面
				response.sendRedirect(request.getContextPath()+"/noAuth.html");
				return;
			}
		}
		
		//如果已经登录成功了，则放行！
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
