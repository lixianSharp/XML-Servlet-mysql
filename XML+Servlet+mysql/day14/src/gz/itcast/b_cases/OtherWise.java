package gz.itcast.b_cases;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
//otherwise标签的标签处理器类
public class OtherWise extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		//通过父标签传递，when标签中test的值
		//获取父标签
		//this.getParent();//返回的是一个JspTag,也就是一个jsp的标签
		ChooseTag parent=(ChooseTag)this.getParent();//这句话的作用就是将ChooseTag标签设置为OtherWiise标签的父标签
		boolean test=parent.isFlag();//获取父标签中when中test属性的值
		
		if (!test) {
			//只有当when标签中的test结果为false的时候，才输出otherwise标签中的内容。
			this.getJspBody().invoke(null);//如果条件成立则输出
		}

	}
}
