package gz.itcast.c_reflect;

import java.lang.reflect.Method;

import org.junit.Test;

/**
 * Method类：代表类的普通方法
		作用：调用方法 
 * @author APPle
 *
 */
public class Demo3 {
	@Test
	public void test() throws Exception{
		//1)通过Class类对象得到Method类对象
		Class clazz = Class.forName("gz.itcast.c_reflect.Student");
		Object obj = clazz.getConstructor(null).newInstance(null);
		/**
		 *  getMethod() :获取类上的公共的方法（public）
		 *  getDeclaredMethod(): 获取类上的所有方法（公共和私有的方法）
		 */
		/**
		 * 参数一： 方法名
		 * 参数二： 形式参数列表
		 */
		Method setName = clazz.getMethod("setName", String.class);
		
		
		//2)通过Method对象调用方法
		/**
		 * 参数一: 调用方法所需的对象
		 * 参数二：实际参数值
		 */
		setName.invoke(obj, "jacky"); // obj.setName("jacky")
		
		System.out.println(obj);
		
		
		Method getName = clazz.getMethod("getName", null);
		//调用方法，接收方法返回值
		Object result = getName.invoke(obj, null); // obj.getName();
		
		System.out.println(result);
		
		
		
	}
}
