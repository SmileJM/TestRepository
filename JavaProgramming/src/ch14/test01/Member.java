package ch14.test01;

public class Member {
	private String name;
	private int age;
	private String phone;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Member(String name, int age, String phone) {
		this.name = name;
		this.age = age;
		this.phone = phone;
	}	
}
