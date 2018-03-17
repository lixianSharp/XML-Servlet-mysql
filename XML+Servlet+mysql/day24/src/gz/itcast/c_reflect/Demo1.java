package gz.itcast.c_reflect;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Class类：
 * 	作用：得到类名称，类的实现接口，继承的类..
 * @author 贤元
 *
 */
public class Demo1 {

	@Test
	public void test() throws Exception {
		//1)得到Class类的对象
		//方式一：
		//Class clazz=Student.class;
		//方式二：(依赖性比较低，只依赖字符串)推荐使用这种
		Class clazz=Class.forName("gz.itcast.c_reflect.Student");//填上该类的全名
		//方式三：
		//Class clazz=new Student().getClass();
		
		//得到类名
		System.out.println(clazz.getName());//全名(包含包结构)  gz.itcast.c_reflect.Student
		System.out.println(clazz.getSimpleName());//简单名  Student
		
		//得到类的继承结构
		//得到Student类的父类
		Class parent=clazz.getSuperclass();//得到该类的父类的Class对象
		System.out.println(parent.getName());//java.lang.Object
		
		
		//得到类的接口
		Class[] arrayInterface=clazz.getInterfaces();//得到该类所有的接口的Class对象
		for(Class cla:arrayInterface){
			System.out.println(cla.getSimpleName());//Eatable
		}
	}
	
}
