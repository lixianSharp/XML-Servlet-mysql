package gz.itcast.c_reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * Method类：代表类的普通方法 
 * 		作用：调用方法
 * 
 * @author 贤元
 * 
 */
public class Demo3 {

	@Test
	public void test() throws Exception {
		//1）通过Class类对象得到Method类对象
		Class clazz=Class.forName("gz.itcast.c_reflect.Student");
		Object obj=clazz.getConstructor(null).newInstance(null);//通过无参构造方法创建一个对象
		/**
		 *  getMethod(); 获取类上的公共的方法（public）
		 *  getDeclaredMethod() : 获取类上的所有方法(公共和私有的方法)(获取类上已经声明的方法)
		 *  
		 */
		/**
		 * 参数一：方法名
		 * 参数二：形式参数列表(Class类型的)
		 */
		Method setName=clazz.getMethod("setName", String.class);
		
		//2)通过Method对象调用方法
		/**
		 * 参数一：调用方法所需的对象
		 * 参数二：实际参数值
		 */
		setName.invoke(obj, "jacky");// 等价于obj.setName("jacky");
		
		System.out.println(obj);//Student [id=0, name=jacky]
		
		
		/**
		 * 参数一：方法名
		 * 参数二：形式参数列表(Class类型的)  如果没有的话就写上null
		 */
		Method getName=clazz.getMethod("getName", null);
		//调用方法，接收方法返回值   参数一：调用方法所需的对象，参数二：实例参数值
		Object result=getName.invoke(obj, null);// 等价于 obj.getName();
		
		System.out.println(result);//jacky
		
		
		
	}
}
