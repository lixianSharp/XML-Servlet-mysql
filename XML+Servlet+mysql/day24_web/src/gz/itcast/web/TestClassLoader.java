package gz.itcast.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestClassLoader extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 *
		 * 一个web的Servlet加载顺序
		1）WebappClassLoader(tomcat自定义类加载器):
				加载web/WEB-INF/classes/  类
				加载web/WEB-INF/lib/*.jar jar包
				
				1.1 WebappClassLoader设计的目的就是为了分离服务器中每个web应用，让每个web应用互补干扰的
					ServletContext
				1.2 WebAppClassLoader打破了委托机制。 为了保证优先加载当前web应用的所有资源
				
				
		2）StandardClassLoader(tomcat自定义类加载器)，加载tomcat/lib/*.jar
				StandartClassLoader用于加载所有web用到的jar包或类。
				
		
		3）AppClassLoader : jdk的CLASSPATH
		4）ExtClassLoader :jre/lib/ext/*.jar
		5)BootStrap      ：jar/lib/rt.jar
		*/
		
		ClassLoader cl=this.getClass().getClassLoader();
		while(cl!=null){
			System.out.println(cl.getClass().getName());
			cl=cl.getParent();
			
			
		}
		System.out.println(cl);
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

}
