package ch14.exam06;

public class ConstructorReferenceExample {
	public static void main(String[] args) {
			createMember(new FunctionalInterface() {			
				@Override
				public Member createMember(String mId, String mName) {
					return new Member(mId, mName);
				}
			});
		createMember( (mId, mName) -> { return new Member(mId, mName);});
		createMember( (mId, mName) -> new Member(mId, mName));
		createMember( Member::new);
	}
	
	public static void createMember(FunctionalInterface i) {
		Member member = i.createMember("white", "홍길동");
	}
}
