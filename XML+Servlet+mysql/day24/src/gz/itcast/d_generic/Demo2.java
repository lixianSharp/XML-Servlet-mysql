package gz.itcast.d_generic;

import org.junit.Test;

/**
 * 泛型方法和泛型类
 * @author 贤元
 *
 */
//注意：泛型类定义了泛型，那么方法上如果使用的同一个类型，那么方法就不需要定义泛型。
public class Demo2<T,K> {

	/**
	 * 泛型方法的类型确定：在调用方法的时候确定类型
	 * 泛型类的类型确定：在创建类对象的时候确定其类型
	 */
	/**
	 * 设计一个通用的方法，可以接收任何类型
	 * 泛型方法的作用是可以让开发者设计出更加通用的方法。
	 * @param dept
	 */
	//	    返回值类型    使用泛型
	public  T save1(T t,K k){
		return t;
	}
	
	public <T> void update(T t){
		
	}
	
	/**
	 * 注意：泛型类的定义了泛型，那么方法上如果使用的同一个类型，那么方法就不需要定义泛型
	 * @param t
	 * @param k
	 */
	public T update2(T t,K k){
		return t;
	}
	
   /*//  泛型声明  返回值类型   使用泛型
	public <T,K> T save(T t,K k){
		return t;
	}
	*/
	
	 
	
	
	
	public void test() throws Exception {
		//Dog g=save(new Dog(),new String());
		Demo2<Teacher,Cat> d2=new Demo2<Teacher,Cat>();
		d2.update2(new Teacher(), new Cat());
		
	}
	
}
