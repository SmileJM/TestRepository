package ch06.exam09;

public class Member {
	String name;
	final String ssn;
	static final String NATION = "한국";
	
	Member(String name, String ssn) {
		this.name = name;
		this.ssn = ssn;
	}
}
