package gz.itcast.b_cases;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 自定义登录的页面标签
 * 
 * @author 贤元
 * 
 */
public class LoginTag extends SimpleTagSupport {

	//声明属性
	private String userName;
	private String password;
	

	//提供公开的settter方法，用于给属性赋值
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void doTag() throws JspException, IOException {
		//设置输出内容类型和编码
		HttpServletResponse response=(HttpServletResponse)((PageContext)this.getJspContext()).getResponse();
		response.setContentType("text/html;charset=utf-8");
		
		String html = "";

		html += " 		<center><h3>用户登录页面</h3></center>";
		html += " 		<table border='1' align='center' width='400px'>";
		html += " 			<tr>";
		html += " 				<th>用户名：</th>";
		html += " 				<td><input tyep='text' name='"+userName+"'/></td>";
		html += " 			</tr>";
		html += " 			<tr>";
		html += " 				<th>密码：</th>";
		html += " 				<td><input type='password' name='"+password+"' ></td>";
		html += " 			</tr>";
		html += " 			<tr>";
		html += " 				<td colspan='2' align='center'><input type='submit' value='登录'>&nbsp;<input type='reset' value='重置'></td>";
		html += " 			</tr>";
		html += " 		</table>";

		JspWriter out = this.getJspContext().getOut();
		out.write(html);//将html代码输出到浏览器中显示

	}
}
