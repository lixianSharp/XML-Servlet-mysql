package gz.itcast.b_down;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 使用Servlet进行文件的下载
 * @author 贤元
 *
 */
public class DownServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO 扣除会员的积分...
		
		
		
		
		//1）读取服务器的文件
		//读取当前web应用的根目录下的文件，返回绝对路径
		/*String path=this.getServletContext().getRealPath("/upload/1.jpg");
		FileInputStream in=new FileInputStream(new File(path));
		*/
		
		//String fileName="1.jpg";
		String fileName="美女.jpg";
		
		InputStream in=this.getServletContext().getResourceAsStream("/upload/"+fileName);
		
		/**
		 * 对中文的文件名加入URLEncoding加密   对文件名进行URL编码
		 */
		fileName=URLEncoder.encode(fileName,"utf-8");

		/**
		 * 注意：不同的浏览器在识别content-disposition值的内容有差异
		 * 		IE：attachment;filename=1.jpg
		 * 		非IE：attachment;filename*=1.jpg
		 */
		String userAgent=request.getHeader("user-agent");
		String content="";
		if(userAgent.contains("Trident")){
			//IE
			content="attachment;filename="+fileName;
		}else{
			//非IE
			content="attachment;filename*="+fileName;
		}
		
		/**
		 * 设置一个响应头： content-Disposition 告诉浏览器以下载的方法打开资源
		 */		
		response.setHeader("content-disposition",content);
		
		
		//2)把文件内容发送给浏览器
		OutputStream out=response.getOutputStream();
		byte[] buf=new byte[1024];
		int len=0;
		while((len=in.read(buf))!=-1){
			out.write(buf,0,len);//将内容写入到浏览器中
		}
		out.close();
		in.close();
		
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

