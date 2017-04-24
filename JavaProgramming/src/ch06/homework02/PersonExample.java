package ch06.homework02;

public class PersonExample {

	public static void main(String[] args) {
		Person person = new Person("123456-1234567", "홍길동");
		
		System.out.println(person.nation);
		System.out.println(person.ssn);
		System.out.println(person.name);
		
//		person.nation = "Usa";
//		person.ssn = "222222-2222222";
		person.name = "Usa";

	}

}
