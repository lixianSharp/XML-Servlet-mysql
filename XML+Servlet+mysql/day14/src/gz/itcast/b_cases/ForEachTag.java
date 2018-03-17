package gz.itcast.b_cases;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForEachTag extends SimpleTagSupport {

	//forEach标签中的属性
	private Object items;// 需要遍历的数据 要么List 要么Map
	private String var;// 每个元素的名称
	
	//提供公共的setter方法，用于给属性赋值
	public void setItems(Object items) {
		this.items = items;
	}
	public void setVar(String var) {
		this.var = var;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// 遍历items数据
		/*
		 * // List PageContext pageContext = (PageContext) this.getJspContext();
		 * if (items instanceof List) { List list = (List) items; for (Object
		 * object : list) { // 把每个对象放入域对象中(pageContext)
		 * pageContext.setAttribute(var, object); // 显示标签体内容
		 * this.getJspBody().invoke(null);
		 * 
		 * } }
		 * 
		 * // Map if (items instanceof Map) { Map map = (Map) items; Set<Entry>
		 * entrySet = map.entrySet(); for (Entry entry : entrySet) { //
		 * 把每个对象放入域对象中(pageContext) pageContext.setAttribute(var, entry); //
		 * 显示标签体内容 // 显示标签体内容 this.getJspBody().invoke(null); } }
		 */

		// 简化代码
		// 思路：1、
		// 1）list ->Collection
		// 2）map.entrySet ->Collection
		//得到pageContext对象，也就是jsp的上下文对象，通过这个对象可以获取jsp的其他8个内置对象
		PageContext pageContext = (PageContext) this.getJspContext();
		Collection colls = null;
		if (items instanceof List) {//如果该集合可以转为List，也就意味着该集合原来就是List集合
			colls = (List) items;
		}
		if (items instanceof Map) {//如果该集合可以转为map集合，也就是意味着该集合原来就是Map集合
			Map map = (Map) items;
			colls = map.entrySet();
		}

		for (Object object : colls) {
			// 把每个对象放入域对象中(pageContext)
			pageContext.setAttribute(var, object);
			// 显示标签体内容
			this.getJspBody().invoke(null);//因为参数是null，所以默认是输出到浏览器中。如果参数是一个Writer流，则表示将标签体中的内容输入到该Writer流中保存
		}

	}

}
