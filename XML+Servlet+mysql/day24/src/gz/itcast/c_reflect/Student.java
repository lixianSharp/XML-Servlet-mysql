package gz.itcast.c_reflect;

public class Student implements Eatable{

	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	//有参数的构造方法
	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	//无参数的构造方法
	public Student() {
		super();
	}
	
	//重写toString()方法
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
