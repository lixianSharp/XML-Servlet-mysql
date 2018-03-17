package gz.itcast.c_reflect;

import java.lang.reflect.Constructor;

import org.junit.Test;

/**
 * Constructor类:代表类的构造方法
 * 		作用：构造对象
 * @author 贤元
 *
 */
public class Demo2 {

	@Test
	public void test() throws Exception {
		//1）通过Class类得到Constructor类
		Class clazz=Class.forName("gz.itcast.c_reflect.Student");
		/**
		 * 1）调用无参的构造方法
		 */
		//根据不同的参数列表获取不同的构造方法(Constructor)对象
		Constructor cons=clazz.getConstructor(null);//获取无参数的构造方法对象
		
		//2)通过Constructor类的方法构造对象
		Object obj=cons.newInstance(null);//null表示不对该对象进行初始化
		
		System.out.println(obj.getClass().getSimpleName());//Student
		
		/**
		 * 调用有参数的构造方法
		 */
		Constructor cons2=clazz.getConstructor(int.class,String.class);//获取有参数的构造方法对象
		Object obj2=cons2.newInstance(10,"eric");
		
		System.out.println(obj2.getClass().getSimpleName());//Student
		System.out.println(obj2);//Student [id=10, name=eric] 打印一个对象相当于调用该对象所处的类的toString()方法
		
		
	}
}
