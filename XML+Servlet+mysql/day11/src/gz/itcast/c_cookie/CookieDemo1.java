package gz.itcast.c_cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 第一个Cookie程序
 * Cookie技术：会话数据保存在浏览器客户端
 * @author 贤元
 *响应头
 *请求头
 */
public class CookieDemo1 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、创建Cookie对象
		Cookie cookie=new Cookie("name","eric");
		//Cookie cookie2=new Cookie("il","eric@qq.com");
		//Cookie cookie3 = new Cookie("lixianyuan","李贤元");//这个会报错，因为Cookie只能保存非中文字符串类型的数据

		/**
		 * 1）设置cookie的有效路径。默认情况：有效路径在当前web应用下： /day11  因为资源是给浏览器用的，所以/代表tomcat的webapps目录
		 */
		//cookie.setPath("/day11");
		//cookie2.setPath("/day12");
		
		/**
		 * 2）设置cookie的有效时间
		 * 正整数：表示cookie数据保存浏览器的缓存目录(硬盘中)，数值表示保存的时间。
  		  	       负整数：表示cookie数据保存浏览器的内存中。浏览器关闭cookie就丢失了！！！
  		  	        零 ：   表示删除同名的cookie数据。
		 */
		//cookie.setMaxAge(8);//8秒  从你开始不调用cookie的时候调用
		cookie.setMaxAge(-1);//cookie保存在浏览器内存（会话cookie） 任何负数都可以
		//cookie.setMaxAge(0);
		
		//2、把Cookie数据发送到浏览器（通过响应头发送：set-cooki名称）    （服务器发送数据给浏览器叫响应   浏览器发送数据给服务器叫请求）
		//response.setHeader("set-cookie",cookie.getName()+"="+cookie.getValue()+"name-eric");
		//推荐使用这种方式，避免手动发送Cookie信息
		response.addCookie(cookie);//把cookie发送到浏览器
		//response.addCookie(cookie2);
		
		//3、接收浏览器发送的cookie信息
		/*String name=request.getHeader("cookie");
		System.out.println(name);*/
		Cookie[] cookies=request.getCookies();//接收所有的cookie对象
		//注意：判断null,否则空指针
		if(cookies!=null){
			//遍历
			for(Cookie c:cookies){
				String name=c.getName();//获取cookie的名称
				String value=c.getValue();//获取cookie的值
				System.out.println(name+"="+value);
			}
		}else{
			System.out.println("没有接收到cookie的数据");
		}
		
	}

}
