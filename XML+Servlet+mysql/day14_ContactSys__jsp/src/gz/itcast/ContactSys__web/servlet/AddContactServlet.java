﻿package gz.itcast.ContactSys__web.servlet;

import gz.itcast.ContactSys__web.entity.Contact;
import gz.itcast.ContactSys__web.exception.NameRepeatException;
import gz.itcast.ContactSys__web.service.ContactService;
import gz.itcast.ContactSys__web.service.impl.ContactServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 添加联系人的逻辑
 * @author 贤元
 *
 */
public class AddContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1.接收参数
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");
		
		//封装成Contact对象
		Contact contact = new Contact();
		contact.setName(name);
		contact.setGender(gender);
		contact.setAge(Integer.parseInt(age));
		contact.setPhone(phone);
		contact.setEmail(email);
		contact.setQq(qq);
		
		ContactService service = new ContactServiceImpl();
		//2.调用dao类的添加联系人的方法
		try {
			service.addContact(contact);
		} catch (NameRepeatException e) {
			//处理自定义业务异常
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/addContact.jsp").forward(request, response);
			return;
		} 		
		
		//3.跳转到查询联系人的页面
		response.sendRedirect(request.getContextPath()+"/ListContactServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
