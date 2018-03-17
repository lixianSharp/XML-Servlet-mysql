package gz.itcast.web;

import gz.itcast.entity.FileBean;
import gz.itcast.service.FileBeanService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收需要下载的文件id
		String idStr=request.getParameter("id");
		FileBeanService service=new FileBeanService();
		if(idStr!=null&&!idStr.equals("")){
			int id=Integer.parseInt(idStr);
			
			//获取文件对象
			FileBean bean=service.findById(id);
			//文件名称
			String fileName = bean.getName();
			//文件所在路径
			String path=bean.getFile_path();//从数据库中获取文件所在的位置，得到该文件的路径
			
			InputStream in = this.getServletContext().getResourceAsStream(path);
			/**
			 * 对中文的文件名加入URLEncoding加密
			 */
			fileName = URLEncoder.encode(fileName, "utf-8");
			/**
			 * 设置一个响应头： content-Disposition 告诉浏览器以下载的方法打开资源
			 */
			/**
			 * 注意：不同的浏览器在识别content-disposition值的内容有差异 IE：attachment;filename=1.jpg
			 * 非IE：attachment;filename*=1.jpg
			 */
			String userAgent = request.getHeader("user-agent");
			String content = "";
			if (userAgent.contains("Trident")) {
				// IE
				content = "attachment;filename=" + fileName;
			} else {
				// 非IE
				content = "attachment;filename*=" + fileName;
			}

			response.setHeader("content-disposition", content);

			// 2)把文件内容发送给浏览器
			OutputStream out = response.getOutputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
