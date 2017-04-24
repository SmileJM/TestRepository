package ch15.exam01;

import java.util.ArrayList;
import java.util.List;

public class ArrayExample {

	public static void main(String[] args) {
		Student[] students = new Student[3];
		students[0] = new Student("s1");
		students[1] = new Student("s2");
		students[2] = new Student("s3");
//		students[3] = new Student("s4");
		
		List<Student> list = new ArrayList<Student>();
		for(int i=0; i<100; i++) {
			list.add(new Student("s" + i));	
		}
		list.remove(2);
		
	}
}
