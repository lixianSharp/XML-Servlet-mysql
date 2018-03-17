package ReviewAndNote;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 回顾重点内容：
 	上传和下载：
 	1）上传：
 		1.1 三个条件
 			a）以post方式提交的表单
 			b)表单内必须有<file/>组件
 			c)把表单的enctype属性设置为multipart/form-data
 		1.2 使用Apache组织的上传工具
 			DiskFileItemFactory：设置上传环境。缓存大小，缓存目录。
 			核心类：ServletFileUpload：用于解析上传的文件。
 				List<FileItem> parserRequest(request); 解析上传文件，返回文件对象集合
 			
 			FileItem 对象： 代表一个上传后的文件
 			名称，大小，类型，内容...
 				
 				FileItem.isFormFiled(); 判断该文件是否是普通表单组件。
 				FileItem.getString(); 获取普通表单组件的内容。
 				
 			细节：
 				1）设置限制文件大小：
 					ServletFileUpload.setFileSizeMax(); 设置单个文件
 					ServletFileUpload.setSizeMax();设置总文件
 				2）设置文件名编码
 					ServletFileUpload.setHeaderEncoding("utf-8");
 				3)设置上传文件的进度监听器
 					ServletFileUpload.setProgressListener(监听器的实现类);
 					
 			
 	 2）下载文件：
		
		使用Servlet进行文件下载
		
			设置响应头，通知浏览器一下载方式打开文件
			response.setHeader("content-disposition","attachment;filename=xxx.jpg");
			
		
			1）读取到服务器文件
				InputStream in=xxxxx;
			2)把文件流输出到浏览器作为响应的实体内容
 				OutputStream out=response.getOutputStream();
 				byte[] buf=new byte[1024];
 				int len=0;
 				while((len=in.read(buf))!=-1){
 				out.write(buf,0,len);
 				}
 
 
 大纲：
 	1）Junit单元测试的使用
 	2）枚举
 	3）复习反射
 	4）复习泛型
 	5）反射泛型(新)
 	6）注解
 	7）反射注解(新)
 	8）类加载器
 	
 	
 	
 1.Junit单元测试工具
 	
 	1.1作用：
 		Junit单元测试，主要用于对程序进行专业化测试。
 		
 2.枚举
 	
 	2.1 定义语法：枚举类型:(jdk1.5 以后出现的类型)
 		enum Score{
 			A,B,C,D,E
 		}
 	
 	2.2 带有构造方法的枚举
 	
 	2.3 得到枚举中的所有值
 	
 
 3.复习反射
 	
 	Class类： 代表类
 		作用：得到类名称，类的实现的接口，继承的类。
 	Constructor类： 代表类的构造方法
 		作用：构造对象
 	Method类：代表类的普通方法
 		作用：调用方法
 	Field类： 代表类的属性。
 		作用：得到属性值，设置属性值
 	
 	
 	3.1 Class类
 		
 	3.2 Constructor类
 	
 	3.4 Field
 
 4. 复习泛型
 		
 		泛型的作用：泛型语法(泛型方法，泛型类，泛型接口)，泛型关键字(extends/super),反射泛型
 		
 		4.1 泛型的作用
 			
 			泛型：
 				1）可以减少手动类型装换的工作
 				2）可以把程序运行时错误提前到编译时报错
 			好处：使用泛型可以让开发者写出更加通用的代码!!
 	   4.2 泛型语法
 	   		注意：泛型类定义了泛型，那么方法上如果使用的同一个类型，那么方法就不需要定义泛型。
 			注意：泛型方法的类型确定在调用方法的时候确定。
 			注意：泛型类的确定是在创建类的对象的时候确定
 
  	   4.3 泛型关键字
  	     用于限定泛型的使用范围
  	   	？
  	   	extends
  	    super
  	   
  	   4.4 反射泛型：
 
 
 5. 注解
 	Annotation
 	
 	注解：给程序带上一些标记，从而影响编译器运行程序的结果！！！
 	注释：提高程序的可读性，对程序运行没有影响！！
 	
 	注解的作用：
 		1）可以在程序上(类，方法，属性)携带信息
 		2）注解简化(取代)配置文件(xml或properties)
 	
 	每个框架都有两种方案：
 		1）把参数放在xml文件中
 		2）把参数放在注解中 		
 	
 	5.1 常见的注解
 	
 	5.2 定义一个注解语法 
 	
 	5.3 注解细节
 		1）属性的类型可以基本数据类型。也可以是数组类型
 		2）使用default关键字给注解一个默认值
 		3）档注解中使用value名称的属性，则可以省略“value=”
 	
 	5.3元注解
 		能够规定注解的一些行为的
 
 		@Target(TYPE,FIELD,METHOD,PARAMETER,CONSTRUCTOR,LOCAL_VARIABLE)
 		
 		声明注解的使用范围：
 			TYPE：注解可以用在类上
 			FIELD：注解可以用在属性上
 			METHOD：注解可以用在方法上
 			PARAMETER：注解可以用在参数声明上面
 			CONSTRUCTOR：注解可以用在构造方法上面
 			LOCAL_VARIABLE：注解可以用在本地变量上面
 			
 		
 		@Retention(RetentionPolicy.SOURCE)
 			声明注解的有效范围
 			
 				RetentionPolicy.SOURCE:该注解只在源码中有效
 				RetentionPolicy.CLASS：该注解在源码中和字节码中有效,默认是CLASS类型
 				RetentionPolicy.RUNTIME：该注解在源码中有效，和字节码中有效，运行字节码的时候有效！！
 			
 			
 	6.反射注解
 			使用反射技术获取(类，方法，属性)注解的信息
 	
 	7.反射注解的案例
 		
 	注意：泛型和注解是学框架的最底层原理、
 	
 	
 	8. 类加载器		
 			
 * @author 贤元
 *
 */
public class TodayNote {

}
