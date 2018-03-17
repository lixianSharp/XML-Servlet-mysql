package gz.itcast.d_generic;

import org.junit.Test;

/**
 * 泛型方法和泛型类
 * @author APPle
 *
 */
public class Demo2<T,K> {

	/**
	 * 泛型方法的类型确定：在调用方法的时候确定类型
	 * 泛型类的类型确定： 在创建类对象的时候确定类型　
	 */
	/**
	 * 设计一个通用的方法，可以接收任何类型
	 * 泛型方法的作用是可以让开发者设计出更加通过的方法
	 * @param dept
	 */
	public  T save(T t,K k){
		return t;
	}
	
	
	public  void update(T t,K k){
		
	}
	
	@Test
	public void test(){
		//Dog g = save(new Dog(),new String());
		Demo2<String,Cat> d2 = new Demo2<String,Cat>();
	}
	
	
	
}
