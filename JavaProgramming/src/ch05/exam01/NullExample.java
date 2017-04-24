package ch05.exam01;

public class NullExample {

	public static void main(String[] args) {
		String v1 = null;
		
		String v2;
		v2 = null;
		
		int[] v3 = null;
		
		if(v3 == null) {
			System.out.println("null");
		} else if(v3 != null){
			System.out.println("not null");
		}

	}

}
