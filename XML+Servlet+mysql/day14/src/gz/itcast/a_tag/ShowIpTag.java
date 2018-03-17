package gz.itcast.a_tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 标签处理器类
 * @author 贤元
 *1）继承SimpleTagSupport
 */
public class ShowIpTag extends SimpleTagSupport{

	/**
	 * 以下屏蔽的代码在SimpleTagSupport代码中已经做了！这里不需要重复做了
	 */
	/*private JspContext context;
	*//**
	 * 传入pageContext  也就是jsp的上下文对象
	 *//*
	@Override
	public void setJspContext(JspContext pc) {
		this.context=pc;
		
		
	}*/
	
	
	/**
	 * 2、覆盖doTag方法
	 */
	@Override
	public void doTag() throws JspException, IOException {

		//向浏览器输出客户的ip地址
		//PageContext pageContext=(PageContext)context;//使用屏蔽掉的代码的时候用的，现在不用这个
		
		//先获取pageContext对象，也就是jsp的上下文对象，可以通过该对象获取其他8个内置对象
		PageContext pageContext=(PageContext)this.getJspContext();
		
		//通过request获取ip地址，所以需要先获取request对象，获取request对象需要通过pageContext对象获取，因为pageContext可以获取jsp中的其他八个内置对象
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		//通过request获取ip地址
		String ip=request.getRemoteHost();
		//获取jsp的输出流
		JspWriter out=pageContext.getOut();
		//将ip地址显示到浏览器中显示
		out.write("使用自定义标签输出客户的ip地址："+ip);
	}
	
	
}
