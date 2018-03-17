package gz.itcast.ContactSys__web.servlet;

import gz.itcast.ContactSys__web.entity.Contact;
import gz.itcast.ContactSys__web.service.ContactService;
import gz.itcast.ContactSys__web.service.impl.ContactServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 修改前查询联系人逻辑
 * 
 * @author 贤元
 * 
 */
public class QueryContactServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.接收id
		String id = request.getParameter("id");
		
		//2.调用service根据id查询联系人的方法
		ContactService service = new ContactServiceImpl();
		Contact contact = service.findById(id);
		
		//3.把查询的结果保存到request域中
		request.setAttribute("contact", contact);
		
		//4.跳转到修改联系人的页面
		request.getRequestDispatcher("/updateContact.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
