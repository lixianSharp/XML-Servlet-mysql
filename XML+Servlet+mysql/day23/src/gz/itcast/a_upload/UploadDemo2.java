package gz.itcast.a_upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
 * 单文件上传处理
 * @author 贤元
 *
 */
public class UploadDemo2 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1)创建一个DiskFileItemFactory对象，用于设置缓存大小，和设置缓存目录
			/**
			 * 参数一：缓存大小(单位Byte). 如果文件不够缓存大小，那么该文件直接传到服务器，不通过缓存。否则就先把文件存储到缓存目录，然后再从缓存目录转移到指定保存目录中。
			 * 
			 * 参数二：缓存目录。提供给缓存文件存储的地址
			 */
			DiskFileItemFactory factory= new DiskFileItemFactory(10*1024,new File("d:/temp"));
			
			//2)使用ServletFileUpload解析文件
			ServletFileUpload upload=new ServletFileUpload(factory);
			
			/**
			 * 注意：通过设置文件头编码格式来解决文件名称中文乱码问题
			 */
			upload.setHeaderEncoding("utf-8");
			
			
			/**
			 * 3)解析文件(用于解析请求数据，提取和封装文件信息)
			 * 	FileItem类:封装一个文件的所有相关的信息javabean。包含文件名称，文件大小，文件类型，文件数据内容。
			 */
			List<FileItem> list=upload.parseRequest(request);//该方法会自动读取请求的实体内容中所有上传文件的信息
			
			//4）读取上传文件的信息
			if(list!=null){
				//取出第一个文件
				FileItem item=list.get(0);
				
				//文件信息
				String name=item.getName();
				//大小
				long size=item.getSize();
				//类型
				String contentType=item.getContentType();
				
				//文件内容
				InputStream in=item.getInputStream();
				
				//使用IO流工具,	 写出到服务器的硬盘上
				FileUtils.copyInputStreamToFile(in, new File("d:/Files/"+name));
				
				//注意：文件上传成功处理完之后，应该把临时文件删除
				item.delete();
				
				System.out.println(name);
				System.out.println(size);
				System.out.println(contentType);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
