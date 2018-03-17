package gz.itcast.b_cases;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author 贤元
 * if标签处理器类
 */
public class IfTag extends SimpleTagSupport {

	// 因为if标签中有个属性test。声明属性的成员变量
	private boolean test;

	// 关键点：必须提供公共的setter方法，用于给属性赋值
	public void setTest(boolean test) {
		this.test = test;
	}

	@Override
	public void doTag() throws JspException, IOException {

		// 根据test的返回值决定是否输出标签体内容
		if (test) {
			//如果test的结果为true，则显示标签体内容，否则不显示
			this.getJspBody().invoke(null);// 显示标签体内容
		}
	}
}
