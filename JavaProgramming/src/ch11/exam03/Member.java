package ch11.exam03;

public class Member {
	private String id;

	public Member(String id) {		
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id;
	}

}
