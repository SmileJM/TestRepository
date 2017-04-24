package ch15.exam04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapExample2 {

	public static void main(String[] args) {
		// Map 컬렉션 생성
		Map<Student, Integer>map = new HashMap<Student, Integer>();
		
		map.put(new Student(1, "홍길동"), 95);
		map.put(new Student(1, "홍길동"), 95);
		
		System.out.println("총 Entry 수: " + map.size());
		
	}

}
