package gz.itcast.f_case;

@Table(name = "teacher_list")
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
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Teacher [age=" + age + ", id=" + id + ", name=" + name + "]";
	}
}
