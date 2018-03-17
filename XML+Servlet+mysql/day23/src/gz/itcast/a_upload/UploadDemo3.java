package gz.itcast.a_upload;

import gz.itcast.b_entity.FileBean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * 多文件上传的处理
 * 
 * @author 贤元
 * 
 */
public class UploadDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			//获取上传人的名字
			String upPersonName = request.getParameter("upPersonName");
			System.out.println("上传人的的名字"+upPersonName);
			
			//1)创建一个DiskFileItemFactory对象，用于设置缓存大小，和设置缓存目录
			/**
			 * 参数一：缓存大小(单位Byte). 如果文件不够缓存大小，那么该文件直接传到服务器，不通过缓存。否则就先把文件存储到缓存目录，然后再从缓存目录转移到指定保存目录中。
			 * 
			 * 参数二：缓存目录。提供给缓存文件存储的地址
			 */
			DiskFileItemFactory factory = new DiskFileItemFactory(10 * 1024,new File("d:/temp"));
			//2)使用ServletFileUpload解析文件
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");//解决中文乱码问题
			
			
			// 解析   该方法会自动读取请求的实体内容中所有上传文件的信息
			/**
			 * 3)解析文件(用于解析请求数据，提取和封装文件信息)
			 * 	FileItem类:封装一个文件的所有相关的信息javabean。包含文件名称，文件大小，文件类型，文件数据内容。
			 */
			List<FileItem> list = upload.parseRequest(request);

			List<FileBean> fileList = new ArrayList<FileBean>();

			// 4)处理文件，读取上传文件信息    遍历
			for (FileItem item : list) {
				//文件名
				String name = item.getName();
				//文件大小
				long size = item.getSize();
				//文件类型
				String contentType = item.getContentType();

				// 封装文件信息(该FileBeann是自己写的，用于存储该文件的信息)
				FileBean bean = new FileBean();
				/**
				 * e.g. 1024Byte=1kb 1024*1024b=1MB
				 * 
				 */
				String sizeStr = "";
				if (size >= 1024 && size < 1024 * 1024) {
					sizeStr = (size / 1024.0) + "KB";
				} else if (size > 1024 && size <= 1024 * 1024 * 1024) {
					sizeStr = (size / 1024 * 1024.0) + "MB";
				} else if (size >= 1024 * 1024 * 1024) {
					sizeStr = (size / (1024 * 1024.0 * 1024)) + "GB";
				} else {
					sizeStr = size + "B";
				}
				bean.setName(name);
				bean.setSize(sizeStr);
				bean.setAddTime(sdf.format(new Date()));
				bean.setType(contentType);
				fileList.add(bean);

				//文件内容
				InputStream in = item.getInputStream();
				//把源文件保存到硬盘 (使用IO流工具,	 写出到服务器的硬盘上)
				FileUtils.copyInputStreamToFile(in, new File("d:/temp/"+name));
				//删除临时文件 注意：文件上传成功处理完之后，应该把临时文件删除
				item.delete();
			}
			
			//将文件信息保存到request域中
			request.setAttribute("fileList", fileList);

			// 跳转到成功页面，显示成功的所有文件列表
			request.getRequestDispatcher("/success.jsp").forward(request,
					response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
