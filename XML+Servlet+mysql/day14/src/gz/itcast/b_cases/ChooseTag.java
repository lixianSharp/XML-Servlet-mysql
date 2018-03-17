package gz.itcast.b_cases;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
//choose标签的标签处理器类
public class ChooseTag extends SimpleTagSupport {
	//不是属性，而是一个临时变量。作用是：用于将when标签中的test的结果更方便的在Choose标签中传递
	private boolean flag;
	public boolean isFlag(){
		return flag;
	}
	
	public void setFlag(boolean flag){
		this.flag=flag;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		//输出标签体内容
		this.getJspBody().invoke(null);
	}
}
