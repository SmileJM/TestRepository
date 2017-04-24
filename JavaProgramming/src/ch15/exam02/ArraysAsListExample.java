package ch15.exam02;

import java.util.Arrays;
import java.util.List;

public class ArraysAsListExample {

	public static void main(String[] args) {
		List<String> list1 = Arrays.asList("신용권", "홍길동", "감자바");		
		for(String list : list1) {
			System.out.println(list);
		}
		
		List<Integer> list2 = Arrays.asList(1 ,2 ,3);
		for(int list : list2) {
			System.out.println(list);
		}
	}

}
