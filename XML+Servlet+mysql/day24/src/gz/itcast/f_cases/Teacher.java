package gz.itcast.f_cases;
@Table(name = "teacher_list") //这个代表表名
public class Teacher {

	@Column(name = "tid")
	private int id;
	
	@Column(name = "tname")
	private String name;
	
	@Column(name = "tage")
	private int age;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	
}
