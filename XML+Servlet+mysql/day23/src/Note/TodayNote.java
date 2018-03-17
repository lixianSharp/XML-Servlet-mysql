package Note;
/**
 回顾重点内容：
 	监听器：Listener
 	ServletContext对象：
 		ServletContextListener:创建和销毁
 		ServletContextAttributeListener:属性增加，修改，删除
 	ServletRequest对象：
 		ServletRequestListener:创建何晓辉
 		ServletRequestAttributeListener：属相增加，修改，删除
 	HttpSession对象：
 		HttpSessionListener：创建和销毁
 		HttpSessionAttributeListener：属性增加，修改，删除
 
 	注意：
 		1）实现特定的接口
 		2）监听必须交个tomcat服务器运行。在web.xmlz注册监听器。
 		
 	国际化：<fmt/>标签
 		文字的国际化：
 			javase:ResourceBundle类,通过加载不同国家的资源包实现国际化。
 			javaee：<fmt:setBundle/> <fmt:message/>
 		
 		日期时间国际化：
 			javase:DateFormat类，通过设置DateFormat的日期和时间类型实现国际化
 			javaee：<fmt:formatDate/>
 
 	
 	html/css/js
 	servlet/jsp
 	数据库相关 /jdbc
 		web加强： Filter ,Listener，文件上传下载，Javamail组件，在线支付
 		
 		
 
 1. 文件上传入门
 		
 		1.1 文件上传页面的条件
 			1）必须提交表单，而且这个表单必须使用post提交方式提交
 			2）表单里面必须有一个<input type="file"/>,使用file组件来上传文件。
 			3）表单的enctype必须设置为： multipart/form-data内容
 		
 		
 		1.2 手动解析上传文件
 
 2. 使用fileupload组件进行处理上传文件
 	
 		2.1 使用步骤：
 			1）导入jar包
 				commons-fileupload-1.2.2.jar 核心包
 				commons-io-2.1.jar 辅助包
 			2）核心的API
 				DiskFileItemFactory类：用于设置上传文件的缓存大小和缓存目录。
 				ServletFileUpload类：用于解析上传的所有文件
 					List<FileItem>parserRequest(HttpServletRequest request);解析上传的文件
 					
 					FileItem对象：代表一个上传的文件
 						文件名称
 						大小
 						类型
 						内容
 						....
 			
 3.多文件上传	
 
 
 4.动态多个文件上传
 
 5.文件上传注意的细节
 		
 		5.1 文件名称中文乱码问题：
 			ServletUpload.setHeaderEncoding("utf-8");
 
 		5.2 判断文件类型
 		
 		5.3 判断文件大小
 			setFileSizeMax:设置单个文件的大小
 			setSizeMax：设置一个请求中所有文件的总大小
 
 		5.4 解决文件名重复导致文件覆盖的问题
 			使用唯一算法生成文件名称：UUID算法
 		
 		5.5 把文件随机打散到不同的目录结构
 		
 		5.6 文件的上传进度
 		
 		ProgressListener
 		
 		5.7 获取非file普通表单的数据
 
 6.下载文件
 	
 	
 7.文件的上传和下载结合数据库
 	
 	思路：
 		一、上传
 			文件上传-》服务器（1、文件保存起来 2.文件相关信息存储到数据库）
 		二、	下载
 			1.数据库中读取文件信息-2.拿着文件路径到服务器找文件服务器（文件）->使用Servlet下载
 			
 			注意：文件相关信息使用FileBean对象存储
 	
 	
 	三层结构开发顺序：
 		1）设计数据库
 		2）设计实体
 		3）编写dao
 		4）编写service
 		5）编写Servlet+JSP
 	
 	
 
 		
 * @author 贤元
 *
 */
public class TodayNote {

}
