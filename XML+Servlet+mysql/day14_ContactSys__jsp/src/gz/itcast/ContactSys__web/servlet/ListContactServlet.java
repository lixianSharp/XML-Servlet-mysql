package gz.itcast.ContactSys__web.servlet;

import gz.itcast.ContactSys__web.entity.Contact;
import gz.itcast.ContactSys__web.service.ContactService;
import gz.itcast.ContactSys__web.service.impl.ContactServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 显示所有联系人的逻辑
 * 
 * @author 贤元
 * 
 */
public class ListContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.从xml中读取出联系人数据
		ContactService service = new ContactServiceImpl();
		List<Contact> list = service.findAll();

		// 2.把结果保存到域对象中
		request.setAttribute("contacts", list);

		// 3、跳转到jsp页面
		// 一定要用转发，在这用重定向就完蛋了！！解释一下原因
		request.getRequestDispatcher("/listContact.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
