package ch05.exam02;

public class ArrayExample04 {
	public static void main(String[] args) {
		String[] v1 = { "Java", "C#", "C++" };
		
		for(int i=0; i<v1.length; i++) {
			System.out.println(v1[i]);
		}
		
		for(String lang : v1) {
			System.out.println(lang);
		}

	}
	
}
