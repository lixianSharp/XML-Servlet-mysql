package gz.itcast.c_reflect;

import org.junit.Test;

/**
 * Class类：
 * 	作用：　到类名称，类的实现的接口，继承的类..
 * @author APPle
 *
 */
public class Demo1 {

	@Test
	public void test()throws Exception{
		//1)得到Class类的对象
		//方式一： 
		//Class clazz = Student.class;
		//方式二： (依赖性比较低，只依赖字符串)
		Class clazz = Class.forName("gz.itcast.c_reflect.Student");
		//方式三：
		//Class clazz = new Student().getClass();
		
		
		//得到类名
		System.out.println(clazz.getName());//全名（包含包结构）
		System.out.println(clazz.getSimpleName());//简单名
		
		//得到类的继承结构
		//得到Student类的父类
		Class parent = clazz.getSuperclass();
		System.out.println(parent.getSimpleName());
		
		//得到类的接口
		Class[] interArray = clazz.getInterfaces();
		for (Class cla : interArray) {
			System.out.println(cla.getSimpleName());
		}	
	}
	
}
