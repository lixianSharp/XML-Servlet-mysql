package gz.itcast.b_cases;

/**
 * 只有符合以下规定才能称之为javabean
 * 	1）必须提供无参数的构造方法
 *  2）类中属性都必须私有化(private)
 *  3）该类提供公开的getter和setter方法
 *  
 *  
 * JaveBean的作用：用于封装数据，保存数据 。
 * 		访问javabean只能使用getter和settter方法。
 * @author 贤元
 *
 */
public class Student {

	private String name;
	private int age;
	private boolean flag;
	
	//boolean类型的get方法
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Student(String name) {
		super();
		this.name = name;
	}
	public Student() {
		super();
	}
	public Student(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	

	
}
