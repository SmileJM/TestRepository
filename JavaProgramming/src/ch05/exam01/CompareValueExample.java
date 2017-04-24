package ch05.exam01;

public class CompareValueExample {

	public static void main(String[] args) {
		int v1 = 10;
		int v2 = 10;
		System.out.println(v1 == v2);

		int[] v3 = {10};
		int[] v4 = {10};
		System.out.println(v3 == v4);
		
		String v5 = "Java";
		String v6 = new String ("Java");
		System.out.println(v5 == v6);
		System.out.println(v5.equals(v6));
		
	}

}
