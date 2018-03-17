package gz.itcast.d_beanutils;

import java.util.Date;

public class Student {
	private int id;
	private String name;
	private boolean gender;
	private double scroe;
	private Date birth;
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
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public double getScroe() {
		return scroe;
	}
	public void setScroe(double scroe) {
		this.scroe = scroe;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Student [birth=" + birth + ", gender=" + gender + ", id=" + id
				+ ", name=" + name + ", scroe=" + scroe + "]";
	}
	
}
