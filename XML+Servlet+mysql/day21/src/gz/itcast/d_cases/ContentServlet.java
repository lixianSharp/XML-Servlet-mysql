package gz.itcast.d_cases;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//2)执行目标资源代码
		//t1(response);
		
		//准备内容
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<=3000;i++){
			sb.append("abcdefghijklmnopq");
		}
		/**
		 * 注意：
		 *     每次写出的网页内容都是已经经过gzip压缩的内容
		 *     
		 *   思路：
		 *   	1）现在response对象的getWriter（）方法得到的是没有缓冲功能的PrintWriter（），
		 *   	 调用write（）方法就是直接把内容输出到浏览器显示。  
		 *     2）如果我们通过改造response对象的getWriter方法，从而得到一个带有缓存功能的PrintWriter对象，
		 *      那么write写出的网页内容就是写出到PrintWriter缓存区中，我们就可以从PrintWriter的缓存区中得到网页内容
		 *     
		 */
		response.getWriter().write(sb.toString());
		
	}

	private void t1(HttpServletResponse response) throws IOException {
		//准备内容
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<=3000;i++){
			sb.append("abcd");
		}
		
		System.out.println("压缩前的数据大小： "+sb.toString().getBytes().length);	 ////12000kb
		
		
		/**
		 * 对网页内容进行gzip格式进行压缩
		 */
		//1)创建一个临时缓存容器
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		//2)创建GZIPOutputStream对象
		GZIPOutputStream gzip = new GZIPOutputStream(buf);
		//3)开始写出压缩内容
		gzip.write(sb.toString().getBytes());
		//4)调用结束方法，把缓存内容刷新
		gzip.finish();
		//5)得到压缩后的内容
		byte[] result = buf.toByteArray();
		System.out.println("压缩后的数据大小：" +result.length);
	
		/**
		 * 注意：现在的内容已经是经过gzip算法压缩，必须要告诉浏览器目前输出的内容是gzip压缩格式的内容
		 */
		response.setHeader("content-encoding", "gzip");
		
		//输出到浏览器
		response.getOutputStream().write(result);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
