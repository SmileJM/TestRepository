package ch11.exam05;

public class Member {
	private String id;

	public Member(String id) {		
		this.id = id;
	}
	
	@Override
	public String toString() {
		return id;
	}

	@Override
	protected void finalize() throws Throwable {
			System.out.println(id + "가 제거됨");
	}
}
