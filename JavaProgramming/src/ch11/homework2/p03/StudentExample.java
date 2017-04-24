package ch11.homework2.p03;

import java.util.HashMap;

public class StudentExample {

	public static void main(String[] args) {
		HashMap<Student, String> hashMap = new HashMap<Student, String>();
		
		hashMap.put(new Student("1"), "95");
		
				
		String score = hashMap.get(new Student("1"));
		System.out.println("1번 학생의 총점: "+ score);
//		System.out.println("학생 수: "+ hashMap.size());

	}

}
