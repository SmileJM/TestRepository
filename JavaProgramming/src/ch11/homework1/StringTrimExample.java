package ch11.homework1;

public class StringTrimExample {

	public static void main(String[] args) {
		String tel1 = "  02";
		String tel2 = "1234   ";
		String tel3 = "  5678   ";
		
		String tel = tel1.trim() +tel2.trim() + tel3.trim();
		System.out.println(tel);
	}

}
