package gz.itcast.a_tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
//自定义标签的作用：
//1   2   3   4
/**
 * 标签处理器类
 * 
 * @author 贤元
 * 
 */
public class DemoTag extends SimpleTagSupport {

	// 1.声明属性的成员变量
	private Integer num;

	// 2.关键点： 必须提供公开的setter方法，用于给属性赋值
	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public void doTag() throws JspException, IOException {
		System.out.println("执行了标签");

		/**
		 * 1）控制标签内容是否输出 
		 * 		输出：调用 JSPFragment.invoke(); 
		 * 		不输出：不调用 JSPFragment.invoke();
		 */
		// 1.1得到标签体内容
		JspFragment jspBody = this.getJspBody();

		/**
		 * 执行invoke方法：会把标签体内容输出到指定的Writer对象中
		 */
		// 1.2往浏览器输出内容，Writer为null，默认往浏览器输出
		//JspWriter out = this.getJspContext().getOut();
		// jspBody.invoke(out);
		jspBody.invoke(null);//等价于上面的代码，运行结果：AAAAA
		/**
		 * 3）控制重复输出标签体内容 
		 * 		方法：执行多次jspBody.invoke()方法
		 */
		/*
		 * for(int i=1;i<=5;i++){ jspBody.invoke(null); }
		 */

		/**
		 * 4)改变标签体内容
		 */
		// 4.1创建StringWriter临时容器
		StringWriter sw = new StringWriter();
		// 4.2把标签体拷贝(输入)到临时容器
		jspBody.invoke(sw);
		// 4.3从临时容器中得到标签体内容
		String content = sw.toString();
		// 4.4 该变内容
		content = content.toLowerCase();
		// 4.5把改变的内容输出到浏览器
		// jspBody.invoke(null);不能使用此方式输出，因为JSPBody内容没有改变过
		this.getJspContext().getOut().write(content);

		/**
		 * 2）控制标签余下内容是否输出 输出：什么都不干 
		 * 		不输出：抛出 SkipPageException异常 
		 * 小提示：这个可以用来搞权限哦！！
		 */
		throw new SkipPageException();

	}

}
