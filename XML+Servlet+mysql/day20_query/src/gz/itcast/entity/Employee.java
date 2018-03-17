package gz.itcast.entity;

public class Employee {

	private int id;
	private String name;
	private String gender;
	private String title;
	private String email;
	private double salary;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getTitle() {
		return title;
	}
	public String getEmail() {
		return email;
	}
	public double getSalary() {
		return salary;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Employee(int id, String name, String gender, String title,
			String email, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.title = title;
		this.email = email;
		this.salary = salary;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", title=" + title + ", email=" + email + ", salary="
				+ salary + "]";
	}
	
}
