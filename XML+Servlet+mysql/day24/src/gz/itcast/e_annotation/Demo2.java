package gz.itcast.e_annotation;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 使用自定义注解
 * @author 贤元
 *
 */
public class Demo2 {

	@Author(name = "eric", address = { "广州天河","广东韶关" }, value = {"传智"})
	private String name;
	
	@MyAnnotation
	@Author(name = "eric", address = { "广州天河","广东韶关" }, value = {"传智"})
	public void  save(){
		/**
		 * 反射注解：使用反射的代码来获取注解上面的数据
		 */
		/*
		Class c;
		Method m;
		Field f;
		Constructor con;
		//c.getAnnotation(null);获取类上面的注解
		//m.getAnnotation(null);获取方法上面的注解
		//f.getAnnotation(null);获取属性上面的注解
		//con.getAnnotation(null);获取构造方法上面的注解
		*/
		
		try {
			//1)得到save方法对象
			Method m=this.getClass().getMethod("save", null);
			//2)得到方法上面的注解
			Author author=m.getAnnotation(Author.class);
			System.out.println(author);
			/**
			 * 打印结果：
		：   @gz.itcast.e_annotation.Author(modifyTime=2015-06-25, name=eric, address=[广州天河, 广东韶关], value=[传智])
			 */

			
			//3)获取注解里面的属性(数据)
			String name=author.name();
			String time=author.modifyTime();
			System.out.println(name);//eric
			System.out.println(time);//2015-06-25
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		
	}
	
	@Test
	public void test() throws Exception {
		save();
	}
	
	
}
