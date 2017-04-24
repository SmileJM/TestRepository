package ch11.exam13;

public class Member implements Comparable<Member>{
	private String name;
	private int age;

	public Member(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return name + "-" + age;
	}

	@Override
	public int compareTo(Member o) {	
//		return age-o.age;
		return Integer.compare(age, o.age);
	}	
}
