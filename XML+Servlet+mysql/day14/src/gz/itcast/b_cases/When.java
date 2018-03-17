package gz.itcast.b_cases;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
/**
 * 高仿的when标签
 * when标签的标签处理器类
 * @author 贤元
 *
 */
//when标签的处理器类
public class When extends SimpleTagSupport {
	//这个是属性
	private boolean test;
	//提供公共的setter方法，给test属性赋值
	public void setTest(boolean test){
		this.test=test;
	}
	@Override
	public void doTag() throws JspException, IOException {
		//根据test的返回值决定是否输出标签体内容
		if(test){
			//如果test为true，则输出when标签体中的内容
			this.getJspBody().invoke(null);
		}
		//获取父标签(也就是设置when标签的父标签是谁)
		ChooseTag parent=(ChooseTag)this.getParent();//这句话的作用就是将ChooseTag标签设置为OtherWiise标签的父标签
		parent.setFlag(test);//将当前test属性中的值传递给父标签中
		/**
		 * 控制标签内容是否输出 :
		 * 		输出：调用 invoke(null);默认输出到浏览器中 
		 * 		不输出：不调用 invoke(Writer writer);输出到一个Writer流中存储
		 */
	}
}
