package gz.itcast.c_reflect;

import java.lang.reflect.Field;

import org.junit.Test;

/**
 * Field类： 代表类的属性。
 		作用：得到属性值，设置属性值
 * @author 贤元
 *
 */
public class Demo4 {

	public static void main(String[] args) throws Exception {
		test();
	}
	
	public static void test() throws Exception {
		//1)通过Class得到Field对象
		Class clazz=Class.forName("gz.itcast.c_reflect.Student");
		//Object obj=clazz.getConstructor(null).newInstance(null);
		Object obj=clazz.newInstance();//这是简化版本，但只能使用无参的构造方法构造
		/**
		 *  getDeclaredField():得到任意修饰的属性(public/private)(也就是得到已经声明的属性，包括公有和私有的)
		 *  getField():得到公开的属性(public)
		 */
		Field name=clazz.getDeclaredField("name");
		
		//2)得到属性名
		System.out.println(name.getName());//name
		//得到属性的类型
		System.out.println(name.getType());//String
		
		/**
		 * 赋值：
		 * 	  以前：name="eric"
		 * 	 现在：使用反射技术直接类似地直接给属性赋值，而不是调用setName方法赋值
		 */
		/**
		 * 参数一：赋值给哪个对象
		 * 参数二：赋值
		 */
		//打破私有修饰符的限制
		name.setAccessible(true);//忽略private修饰符的作用
		name.set(obj, "rose");
		
		/**
		 * 获取属性值
		 */
		Object result=name.get(obj);
		System.out.println(result);
		
		
	}
	
	
	
	
}
