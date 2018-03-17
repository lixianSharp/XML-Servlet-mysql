package gz.itcast.a_upload;

import gz.itcast.exception.FileTypeErrorException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

public class UploadDemo4 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1)创建一个DiskFileItemFactory对象，用于设置缓存大小，和设置缓存目录
			/**
			 * 参数一：缓存大小(单位Byte). 如果文件不够缓存大小，那么该文件直接传到服务器，不通过缓存。
			 * 否则就先把文件存储到缓存目录，然后再从缓存目录转移到指定保存目录中。
			 * 
			 * 参数二：缓存目录。提供给缓存文件存储的地址
			 */
			DiskFileItemFactory factory = new DiskFileItemFactory(10 * 1024,
					new File("d:/temp"));
			// 2)使用ServletFileUpload解析文件
			ServletFileUpload upload = new ServletFileUpload(factory);

			/**
			 * 注意： 注册一个文件上传的监听器,（这个可以用来做上传进度）
			 */
			upload.setProgressListener(new MyProgressListener());
			upload.setHeaderEncoding("utf-8");

			/**
			 * 注意： 设置限制文件上传的大小
			 */
			upload.setFileSizeMax(100 * 1024);// 单个文件大小
			upload.setSizeMax(300 * 1024);// 总文件大小

			// 解析 该方法会自动读取请求的实体内容中所有上传文件的信息
			/**
			 * 3)解析文件(用于解析请求数据，提取和封装文件信息)
			 * FileItem类:封装一个文件的所有相关的信息javabean。包含文件名称，文件大小，文件类型，文件数据内容。
			 */
			List<FileItem> list = upload.parseRequest(request);

			/**
			 * 尝试获取上传表单的输入域的数据 注意：
			 * 1)因为表单已经是multipart/form-data类型，text组件数据格式已经发送改变，类似file组件的格式。
			 * 2)text组件已经被fileUpload组件同样封装到FileItem对象中。
			 * 
			 */
			/*
			 * String info1 = request.getParameter("info1");
			 * System.out.println("info1"+info1);
			 */

			if (list != null) {
				for (FileItem item : list) {

					if (item.isFormField()) {
						// 除file组件外的其他表单组件(text/password/select/hidden。。。。。)
						// 取出text组件内容
						/**
						 * getFieldName()获取表单组件的name属性值 (根据name获取其对应的值)
						 */
						if (item.getFieldName().equals("info1")) {
							String info1 = item.getString("utf-8");
							item.getString();
							
							
							System.out.println("info1=" + info1);

						}
						if (item.getFieldName().equals("info2")) {
							String info2 = item.getString("utf-8");
							System.out.println("info2=" + info2);
						}
					} else {
						// file组件

						/**
						 * 注意：限制文件的类型。 文件的mime-type: image/* 需求： 只能上传图片.
						 */
						// 得到文件的类型
						String contentType = item.getContentType();
						if (!contentType.contains("image/")) {
							// 非法的文件类型
							throw new FileTypeErrorException("文件类型错误，请换一张！！");
						}

						/**
						 * “” 注意： 使用UUID算法生成唯一的文件名，避免因为文件名相同覆盖原有的文件
						 */
						String uuid = UUID.randomUUID().toString();
						String fileName = item.getName();
						// 后缀名
						fileName.substring(fileName.lastIndexOf("."));
						// 新文件名
						String newFileName = uuid + fileName;

						// 新的目录结构
						String baseDir = "d:/files/";
						String subDir = makeDirectory(newFileName);// 使用算法生成的子目录结构
						String finalDir = baseDir + subDir;
						// 生成目录结构
						File file = new File(finalDir);
						if (!file.exists()) {
							// 构建目录结构
							file.mkdirs();
						}

						// 保存
						FileUtils.copyInputStreamToFile(item.getInputStream(),
								new File(finalDir + newFileName));

						// 删除临时文件
						item.delete();
					}

				}
			}
		} catch (FileTypeErrorException e) { // 先捕获业务异常进行处理
			request.setAttribute("msg", e.getMessage());// 将异常信息保存在request域中
			request.getRequestDispatcher("/05.upload.jsp").forward(request,
					response);
		} catch (FileSizeLimitExceededException e) {
			request.setAttribute("msg", "单个文件超过设定大小：100KB");
			request.getRequestDispatcher("/05.upload.jsp").forward(request,
					response);
		} catch (SizeLimitExceededException e) { // 捕获超过总文件大小的异常
			request.setAttribute("msg", "超过总文件大小：300KB");
			request.getRequestDispatcher("/05.upload.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 使用算法生成子目录结构： 返回新的目录结构： e.g 11（第一级目录）/2(第二级目录)
	 */
	private String makeDirectory(String fileName) {
		// 1.得到文件名的hashCode值
		int code = fileName.hashCode();

		// 2.算出第一层目录的名称
		int first = code & 0xF;

		// 3.算出第二层目录的名称
		int second = code & (0xF >> 1);
		return first + "/" + second + "/";
	}

	/**
	 * 实现ProgressListener监听文件上传进度
	 * 
	 */
	class MyProgressListener implements ProgressListener {
		/**
		 * 参数： 1: 目前上传了多少字节 2: 总长度 3： 目前正在上传第几个文件
		 */
		public void update(long pBytesRead, long pContentLength, int pItems) {
			System.out.println("已经上传了" + pBytesRead + "字节，总字节数为"
					+ pContentLength + "字节,现在正在上传第" + pItems + "个文件！");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
