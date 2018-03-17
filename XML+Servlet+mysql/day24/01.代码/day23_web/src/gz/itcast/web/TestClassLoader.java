package gz.itcast.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestClassLoader extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 	һ��web��servlet����˳��
		 * 		1��WebappClassLoader��tomcat�Զ�������������  �� 
		 * 				����web/WEB-INF/classes/  ��
		 *   			����web/WEB-INF/lib/*.jar  jar��
		 *   
		 *   			1.1 WebappClassLoader��Ƶ�Ŀ¼Ϊ�˷����������ÿ��webӦ�ã���ÿ��webӦ�û������ŵ�
		 *   				ServletContext
		 *   			1.2 WebappClassLoader������ί�л��ơ�  Ϊ�˱������ȼ��ص�ǰwebӦ�õ�������Դ
		 *    
		 *   
				2��StandardClassLoader��tomcat�Զ��������������� ����tomcat/lib/*.jar  
						StandardClassLoader���ڼ�������web�õ���jar�����ࡣ    A.jar
						
				3��AppClassLoader :��jdk��CLASSPATH
				4��ExtClassLoader:  jre/lib/ext/*.jar
				5��BootStrap        : jar/lib/rt.jar
		 */
		ClassLoader cl = this.getClass().getClassLoader();
		while(cl!=null){
			System.out.println(cl.getClass().getName());
			cl = cl.getParent();
		}
		System.out.println(cl);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
