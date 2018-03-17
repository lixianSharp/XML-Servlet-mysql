package gz.itcast.f_config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfigDemo extends HttpServlet {

	// ServletConfig对象的创建时机：在创建完Servlet对象之后，在调用init方法之前创建。
    //ServletConfig是Servlet的配置对象，一个Servlet对应一个ServletConfig对象
	/**
	 * 以下两段代码(被注释的)GenericServlet已经写了，我们无需再编写了
	 */
	//private ServletConfig config;

	// ServletConfig得到对象：直接从有参数的init方法中得到对象

	/**
	 * 一个Servlet对应一个ServletConfig
	 * 1）tomcat服务器把这些参数在加载web应用的时候封装到ServletConfig对象中 
	 * 2）tomcat服务器调用init方法
	 * 		  传入ServletConfig对象
	 */
	/*@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}*/

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 读取Servlet的初始化参数,注意：Servlet的参数只能由当前的这个Servlet获取
		 */
		//System.out.println(this);//gz.itcast.f_config.ConfigDemo@1344e23 就是当前这个CinfigDemo的对象
		String path = this.getServletConfig().getInitParameter("path");//根据参数名获取参数值   在web.xml文件中 path对应的是一个自定义的配置路径，得去web.xml文件中看一下。
		File file = new File(path);
		
		// 读取内容
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = null;
		while ((str = br.readLine()) != null) {
			System.out.println(str);
		}//这个while循环会打印出对应路径下的文件中的内容
		
		System.out.println("========================-----------------");
		
		//获取当前Servlet的所有初始化参数
		Enumeration<String> enums = this.getServletConfig().getInitParameterNames();//获取所有初始化参数的名称列表(就是在web.xml文件中配置的参数)
		while(enums.hasMoreElements()){
			String paramName=enums.nextElement();//获取初始化参数名称
			String paramValue=this.getServletConfig().getInitParameter(paramName);//根据初始化参数名称获取初始化参数名称的值
			System.out.println(paramName+"="+paramValue);
		}
		/**
		 * while循环打印的内容：
		 * BBBB=BBBB's value
			CCCC=CCCC's value
			path=d:/note_01.txt
		 */
		
		//得到Servlet的名称
		String servletName=this.getServletConfig().getServletName();//通过先得到ServletConfig对象，之后通过ServletConfig对象获取Servlet的名称  原因是：tomcat服务器把这些参数在加载web应用的时候封装到ServletConfig对象中了 
		System.out.println(servletName);//ConfigDemo
	}

}
