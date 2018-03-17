package cn.itcast.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.entity.Admin;
import cn.itcast.exception.UserExistsException;
import cn.itcast.service.IAdminService;
import cn.itcast.service.impl.AdminService;
import cn.itcast.utils.WebUtils;


/**
 * 4. 控制层开发
 * 
 * @author Jie.Yuan
 * 
 */
public class AdminServlet extends HttpServlet {
	
	// 调用的service
	private IAdminService adminService = new AdminService();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 编码
		request.setCharacterEncoding("UTF-8");

		// 获取操作类型
		String method = request.getParameter("method");
		if ("register".equals(method)) {
			register(request,response);
		}
	}

	/**
	 * 注册处理方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void register(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
//		//1. 获取请求参数
//		String userName = request.getParameter("userName");
//		String pwd = request.getParameter("pwd");
//		// 封装
//		Admin admin = new Admin();
//		admin.setUserName(userName);
//		admin.setPwd(pwd);
		
		// 使用BeanUtils组件处理请求数据的封装
		Admin admin = WebUtils.copyToBean(request, Admin.class);
		
		
		//2. 调用Service处理注册的业务逻辑
		try {
			adminService.register(admin);
			
			// 注册成功，跳转到首页
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			
		} catch (UserExistsException e) {
			// 用户名存在，注册失败(跳转到注册页面)
			request.setAttribute("message", "用户名已经存在");
			// 转发
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();  // 测试时候用
			// 其他错误, 跳转到错误页面
			response.sendRedirect(request.getContextPath() + "/error/error.jsp");
		}
	}

}
