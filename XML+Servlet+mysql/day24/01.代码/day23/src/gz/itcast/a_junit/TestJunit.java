package gz.itcast.a_junit;

import junit.framework.Assert;

import org.junit.Test;

public class TestJunit {

	/**
	 * 写一个测试MathUtil的add方法的测试方法
	 *    注意： 给一个方法加上@Test注解，那么该方法就是一个测试方法（类似于一个main方法）
	 *    
	 *    1)Junit测试方法的规则：
	 *    		1）测试方法一定在方法顶部要加上@Test注解
	 *          2）方法必须是public修饰，不能有返回值，不能有参数，可以抛出异常
	 *    2)Junit方法如何运行？
	 *    		1）运行一个方法： 双击对应的方法，右键Run As-》Junit Test (或者Outline视图，选择方法Run As-》Junit Test)
	 *    		2）运行类的所有方法：双击类，右键Run As-》Junit Test
	 *    		3）运行一个项目的所有方法：双击项目，右键Run As-》Junit Test
	 *    3）查看Junit方法的结果
	 *    		查看Junit的导航条：
	 *    				绿色：　代表测试通过了！
	 *    				红色：　代表测试不通过！
	 *    4)对结果进行判断
	 *    		Assert断言类：
	 *    			Assert.assertEquals  判断是否相等，使用equals方法比较
	 *    			assertSame           判断是否相等，使用==比较
	 */
	@Test
	public void testAdd()throws Exception{
		MathUtil mu = new MathUtil();
		int result  = mu.add(10,5);
		/**
		 * 手动判断结果(不专业)
		 */
		/*if(result!=15){
			throw new RuntimeException("错误结果");
		}*/
		
		Person p1  = new Person("eric",20);
		Person p2  = new Person("eric",20);
		
		/**
		 * 使用Junit专业的判定结果的方法：
		 * 		Assert类： 断言类(判定结果是什么??)
		 */
		//Assert.assertEquals(15, result);// 判断两个值是否相等。相等时，测试通过，否则，测试不通过.
		//Assert.assertNotSame(15, result);// 判断两个值是否不相等。不相等时，测试通过，否则，测试不通过.
		//Assert.assertSame(15, result);// 判断两个值是否相等。相等时，测试通过，否则，测试不通过.

		
		//Assert.assertEquals(p1, p2); // 可以比较的是对象内容.用equals方法进行比较
		Assert.assertSame(p1, p2); // 比较的是对象内存的地址. 用==比较比较
	}
	
	
	
	
	
	@Test
	public void testDiv(){
		MathUtil mu = new MathUtil();
		int result  = mu.div(10,5);
		if(result!=2){
			throw new RuntimeException("错误结果");
		}
	}
	
}
