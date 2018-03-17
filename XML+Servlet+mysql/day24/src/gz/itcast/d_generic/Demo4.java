package gz.itcast.d_generic;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

/**
 * 泛型关键字
 * @author 贤元
 *
 */
public class Demo4 {

	
	public ArrayList getList(){
		return new ArrayList();
	}
	
	/**
	 * 该方法只能接收存放着Number类型的子类对象的List集合
	 * 	extends : 只能传入指定类的对象或指定类的子类
	 * @param list
	 */
	public void add(List<? extends Number> list){
		
	}
	
	/**
	 * 该方法只能接收存放着Number类型的父类对象的List集合
	 * super : 只能传入指定类的对象或指定类的父类
	 * @param list
	 */
    public void add2(List<? super Number>list){
    	
    }
	
	@Test
	public void test() throws Exception {
		/**
		 *  1）  ? 号   让某个类保持泛型特征
		 */
		Class<?> clazz=Class.forName("gz.itcast.c_reflect.Student");
		
		/**
		 * 没有加上泛型，则会报警告，希望保持泛型特征
		 * 注意：
		 * 		如果一个泛型加上？号泛型，则该类不能在进行编译，只能用于接收数据
		 */
		List<?> list=getList();
		
		
		/**
		 * 需求：该List可以保存Integer，Float，Double类型的数据，除此之外，别的类型不能保存。
		 * 2) extends: 只能是指定对象或指定类的子类
		 * 
		 */
		List<? extends Number> list1=new ArrayList<>();
		List<Integer> list2=new ArrayList<Integer>();
		List<Float> list3=new ArrayList<Float>();
		List<Double> list4=new ArrayList<Double>();
		
		add(list2);
		add(list3);
		add(list4);
		
		/*list2.add(1);
		//报错！！！
		//list2.add(1F);
		//报错！！
		list1.add(e)
		*/
		
		
		
		List<Number> list5=new ArrayList<Number>();
		/**
		 * 3)super: 只能是指定类的父类！
		 * 
		 */
		add2(list5);
		
		
		
	}
	
	
	
	
}
