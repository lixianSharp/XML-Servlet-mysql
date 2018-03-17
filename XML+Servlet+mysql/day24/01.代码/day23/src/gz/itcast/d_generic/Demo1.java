package gz.itcast.d_generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 泛型的作用
 * @author APPle
 *
 */
public class Demo1 {
	@Test
	public void test(){
		/**
		 * jdk1.4或以前的做法
		 */
		List list = new ArrayList();
		//存储
		list.add(new Cat());
		list.add(new Dog());
		
		//取出
		Cat cat = (Cat)list.get(0);
		//运行的时候报错,类型转换错误
		//Cat cat2 = (Cat)list.get(1);
		
		/**
		 * jdk1.5 之后
		 *  泛型的作用1： 把运行的可能经常导致的类型转换错误，提前到编译时检测类型。
		 *  泛型的作用2： 减少手动类型转换的工作
		 */
		List<Cat> list2 = new ArrayList<Cat>();
		list2.add(new Cat());
		//编译的时候报错，类型无法存入List集合
		//list2.add(new Dog());
		
		List<String> list3 = new ArrayList<String>();
		list3.add("eric");
		list3.add("jacky");
		list3.add("rose");
		
		/**
		 *  jdk1.4或以前的做法
		 */
		//遍历
		for (Object obj : list3) {
			//如果这时需要调用String特有的方法
			String str = (String)obj;
			
			System.out.println(obj);
		}
		
		/**
		 *  jdk1.5之后的做法
		 */
		for (String obj : list3) {
		
			System.out.println(obj);
		}
	}
}

class Cat{}
class Dog{}