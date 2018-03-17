package gz.itcast.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class OnLineListener implements HttpSessionAttributeListener {

	/**
	 * 当用户登录成功后，会执行session.setAttribute("user", userName); 
	 * 该方法用于监听用户的user属性的添加
	 * 
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		// 得到属性名称
		String name = se.getName();
		ServletContext context = se.getSession().getServletContext();
		if ("user".equals(name)) {
			// 同步代码块为了避免多个登录用户同时操作onLine数据是引发并发问题
			synchronized (OnLineListener.class) {

				// 1)把当前登录的session对象封装到Map集合中
				// 1.1 先从context域中获取session数据
				Map<String, HttpSession> onLine = (Map<String, HttpSession>) context
						.getAttribute("onLine");

				// 1.2如果是网站的第一个登录用户，onLine为null，这时席间一个Map集合
				if (onLine == null) {
					onLine = new HashMap<String, HttpSession>();
				}

				// 1.3把当前用户的session存入Map集合
				HttpSession session = se.getSession();
				onLine.put(session.getId(), session);

				// 2)把封装好的map保存到context域中
				context.setAttribute("onLine", onLine);
			}
		}

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub

	}

	/**
	 * 该方法调用removeAttribute()时触发 
	 * 注销的时候，一定会移除user属性名称的数据
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		String name = se.getName();
		ServletContext context=se.getSession().getServletContext();
		String sessionId=se.getSession().getId();
		if ("user".equals(name)) {
			//1)获取context域中的map集合
			Map<String,HttpSession> onLine
			 			= (Map<String,HttpSession>)context.getAttribute("onLine");
			
			//2)移除对应的session对象
			onLine.remove(sessionId);
		
			//3）把修改后的map保存到到context域中
			context.setAttribute("onLine", onLine);
			
		}

	}

}
