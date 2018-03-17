package gz.itcast.f_context;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 保存数据
 * 
 * @author lixian 目的：将一个对象保存在context域对象中，在另外一个地方从context域对象中取出该对象
 */
public class ContextDemo3 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到域对象ServetContext对象
		ServletContext context = this.getServletContext();

		// 2.把数据保存到ServletContext域对象中
		// context.setAttribute("name", "lixianyuan");
		context.setAttribute("student", new Student("xian", 22));

		System.out.println("保存成功");
	}

	class Student {
		private String name;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public Student(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Student [age=" + age + ", name=" + name + "]";
		}

	}

}
