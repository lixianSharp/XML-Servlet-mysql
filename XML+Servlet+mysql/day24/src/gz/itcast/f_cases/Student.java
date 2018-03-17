package gz.itcast.f_cases;
//一个javabean
public class Student {

	private int age;
	private int id;
	private String name;
	public int getAge() {
		return age;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Student [age=" + age + ", id=" + id + ", name=" + name + "]";
	}
	
	
	
}
