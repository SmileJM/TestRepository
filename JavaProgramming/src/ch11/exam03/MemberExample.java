package ch11.exam03;

public class MemberExample {

	public static void main(String[] args) {
		Member m1 = new Member("blue");
			
		System.out.println(m1.toString());
		System.out.println(m1);
		
		String result = m1 + " good";
		System.out.println(result);
	
	}

}
