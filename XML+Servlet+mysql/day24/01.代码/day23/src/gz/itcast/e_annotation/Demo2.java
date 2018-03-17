package gz.itcast.e_annotation;

import java.lang.reflect.Method;

import org.junit.Test;


/**
 * 自定义注解
 * @author APPle
 *
 */
public class Demo2 {

	@Author(name = "eric", address = { "广州天河","广东韶关" }, value = {"传智"})
	private String name;
	
	@MyAnnotaion
	@Author(name = "eric", address = { "广州天河","广东韶关" }, value = {"传智"})
	public void save(){
		/**
		 * 反射注解：使用反射代码获取注解上面的数据
		 */
		/*
		Class c;
		Method m;
		Field f;
		Constructor con;
		//c.getAnnotation(null) 获取类上面的注解
		//m.getAnnotation(null);//获取方法上面的注解
		//f.getAnnotation(null);//获取属性上面的注解
		//con.getAnnotation(null);;//获取构造方法上面的注解
		*/
		
		try {
			//1)得到save方法对象
			Method m = this.getClass().getMethod("save", null);
			
			//2)得到方法上面的注解
			Author author = m.getAnnotation(Author.class);
			System.out.println(author);
			
			//3)获取注解里面的属性（数据）
			String name = author.name();
			String time = author.modifyTime();
			System.out.println(name);
			System.out.println(time);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	@Test
	public void test() throws Exception {
		save();
	}
	
}
