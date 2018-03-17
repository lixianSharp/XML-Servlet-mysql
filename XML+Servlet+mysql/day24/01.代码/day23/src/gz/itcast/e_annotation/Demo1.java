package gz.itcast.e_annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * 常见的注解
 * @author APPle
 *
 */
public class Demo1 {
	
	/**
	 * 1)告诉编译器强制对方法进行覆盖
	 */
	@Override
	public String toString() {
		return super.toString();
	}
	

	/**
	 * 2）告诉编译器压制代码中出现的警告
	 * @return
	 */
	@SuppressWarnings(value = { "unchecked" })
	public List save(){
		List list = new ArrayList();
		return list;
	}
	
	/**
	 * 3）在运行时让方法提示过期
	 */
	@Deprecated
	public void update(){
		
	}
	
	public void update(String name){
		
	}
	
	public static void main(String[] args) {
		new Demo1().update();
	}
	
}
