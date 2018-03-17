package gz.itcast.c_request;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ServletRequest的监听器
 * 
 * 	监听request对象的创建、销毁，和对request属性的增、删、改的监听。
 * @author 贤元
 *
 */
public class MyRequestListener implements ServletRequestListener, ServletRequestAttributeListener{

	/**
	 * 用于监听request对象的创建
	 */
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("一个请求被创建了");
		
		//得到请求相关的信息
		HttpServletRequest request=(HttpServletRequest)sre.getServletRequest();
		//得到客户的IP地址
		String ip=request.getRemoteHost();
		//共享数据到页面
		HttpSession session=request.getSession(true);
		session.setAttribute("ip", ip);
	}
	
	/**
	 * 用于监听request对象的销毁
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("一个请求对象被销毁了");
	}

	
	
	
	/*******属性相关***********/
	
	/**
	 * 属性的增加
	 */
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		//获取属性名
		String name=srae.getName();
		//获取属性值
		Object value=srae.getValue();
		System.out.println("属性增加:"+name+"="+value);
	}

	/**
	 * 属性的修改
	 */
	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		//获取属性名
		String name=srae.getName();
		//需要从ServletRequest事件源对象中再次获取属性,才能得到最新的属性值
		ServletRequest request= srae.getServletRequest();
		Object value=request.getAttribute(name);
		
		System.out.println("属性修改:"+name+"="+value);

	}

	/**
	 * 属性的删除
	 */
	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		String name=srae.getName();
		Object value=srae.getValue();
		System.out.println("属性删除:"+name+"="+value);

	}

	
}
