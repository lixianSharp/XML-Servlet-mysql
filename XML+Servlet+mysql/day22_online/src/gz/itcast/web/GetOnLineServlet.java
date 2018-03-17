package gz.itcast.web;

import gz.itcast.entity.OnLineBean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 改Servlet用于将存储的map集合的登录用户的数据转换到List<OnLineBean> 集合
 * 
 * @author 贤元
 * 
 */
public class GetOnLineServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1）.从context域中取出map集合
		Map<String, HttpSession> onLine = (Map<String, HttpSession>) this
				.getServletContext().getAttribute("onLine");

		// 2)创建一个新的List集合
		List<OnLineBean> list = new ArrayList<OnLineBean>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// 3)遍历Map集合
		if (onLine != null) {
			synchronized (GetOnLineServlet.class) {
				for (Entry<String, HttpSession> entry : onLine.entrySet()) {
					// 封装登录用户的javabean数据
					OnLineBean bean = new OnLineBean();
					bean.setSessionId(entry.getKey());
					HttpSession session = entry.getValue();
					bean.setName((String) session.getAttribute("user"));
					bean.setIp((String) session.getAttribute("ip"));
					bean.setLoginTime(sdf.format(new Date(session
							.getCreationTime())));
					bean.setLastTime(sdf.format(new Date(session
							.getLastAccessedTime())));
					// 把封装好的javabean放入list集合中
					list.add(bean);
				}
			}
		}
		// 3)把list转发到jsp页面中
		request.setAttribute("list", list);
		request.getRequestDispatcher("/online.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
