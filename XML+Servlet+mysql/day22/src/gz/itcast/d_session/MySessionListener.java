package gz.itcast.d_session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * HttpSession对象的监听器
 * 
 * @author 贤元
 * 
 */
public class MySessionListener implements HttpSessionListener ,HttpSessionAttributeListener{

	// 用于存储当前网站的访问人数
	private int count = 0;

	/**
	 * 用于监听HttpSession对象的创建
	 */
	/**
	 * 每次的创建一个session对象就代表一个在线访问用户进入网站
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		 
		// System.out.println("一个session对象呗创建了"+se.getSession());
		
		//保存到context域中
		ServletContext context = se.getSession().getServletContext();
		/**
		 * 使用代码同步避免了因为多个用户同时访问引发的并发问题(导致count的数量不对)
		 */
		synchronized (MySessionListener.class) {//注意：锁的对象必须是唯一的，类对象就是唯一的
			//访客人数+1
			count++;
			// 把count通过context域对象共享到jsp页面
			// 可以通过session对象获取到ServletContext对象的
			context.setAttribute("onLine", count);
		}
	}

	/**
	 * 当用户对应的session对象销毁了
	 */
	/**
	 * 用于监听HttpSession对象的销毁，代表访问离线了
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		 
		System.out.println("一个session对象被销毁了" + se.getSession());
		
		ServletContext context = se.getSession().getServletContext();
		
		synchronized (MySessionListener.class) {
			count--;
			context.setAttribute("onLine", count);
		}
	}

	/*************属性相关*******************/
	
	//属性增加
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		String name=se.getName();
		Object value=se.getValue();
		System.out.println("属性增加："+name+"="+value);
	}

	//属性修改
	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		String name=se.getName();
		//需要从HttpSession事件源对象中再次获取属性,才能得到最新的属性值
		HttpSession session=se.getSession();
		Object value=session.getAttribute(name);
		System.out.println("属性修改："+name+"="+value);
	}

	//属性删除
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		String name=se.getName();
		Object value=se.getValue();
		System.out.println("属性删除："+name+"="+value);
	}
}
