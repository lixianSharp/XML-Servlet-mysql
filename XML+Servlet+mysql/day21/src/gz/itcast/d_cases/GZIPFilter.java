package gz.itcast.d_cases;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
/**
 * 集中对网页内容进行gzip压缩
 * @author APPle
 *
 */
public class GZIPFilter implements Filter {

	public void destroy() {

	}
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//1)过滤请求
		
		//创建一个response的装饰者对象
		MyHttpResponse myResponse = new MyHttpResponse((HttpServletResponse)response);
		/**
		 * 放行
		 */
		chain.doFilter(request, myResponse);
		
		//3）过滤响应
		//从缓存容器对象得到压缩前的内容
		//注意：response对象中没有方法获取实体内容，怎么办？
		char[] content = myResponse.getCharArray();

		//gzip压缩
		//创建临时的字节数组容器
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		//创建GZIPOutputStream对象
		GZIPOutputStream gzip = new GZIPOutputStream(buf);
		//开始写出压缩内容  //这步相当于进行GZIP压缩，只能对字节或者字节数组进行gzip压缩，所以要压缩的内容要转为字节数组
		gzip.write(new String(content).getBytes());
		//刷新缓冲区
		gzip.finish();
		//从临时的字节数组容器中 得到压缩后的网页内容
		byte[] result = buf.toByteArray();//获取进行gzip压缩后的内容
		
		/**
		 * 注意：告诉浏览器数据压缩格式  发送响应头：content-encoding:gzip
		 */
		myResponse.setHeader("content-encoding", "gzip");
		
		//输出
		response.getOutputStream().write(result);//将压缩后的内容输出到浏览器中显示
		//myRresponse.getWriter().write(new String(result,0,result.length));
	}
}



/**
 * HttpServletResponse的装饰者类
 */
//目的：让response对象的getWriter（）方法得到的是有缓冲功能的PrintWriter（）
class MyHttpResponse extends HttpServletResponseWrapper{

	//2）声明一个被装饰者类型的成员变量
	private HttpServletResponse response;
	
	//定义一个缓存容器对象
	private CharArrayWriter charArray = new CharArrayWriter();
	
	/**
	 * 提供一个获取charArray内容的方法(包含网页内容)
	 * @param response
	 */
	public char[] getCharArray(){
		return charArray.toCharArray();
	}
	
	//3）接收被装饰者类对象
	public MyHttpResponse(HttpServletResponse response) {
		super(response);
		this.response = response;
	}
	
	/**
	 * 重写getWriter()方法，让其返回一个带缓存功能的PrintWriter
	 */
	@Override
	public PrintWriter getWriter() throws IOException {
		/**
		 * 现在已经创建了一个带CharArrayWriter缓存容器的PrintWriter了，
		 * 如果我们调用带缓存PrintWriter对象的write()方法，那么内容会直接写入到CharrArrayWriter缓存容器中。
		 */
		return new PrintWriter(charArray);
		
	}
	
}
