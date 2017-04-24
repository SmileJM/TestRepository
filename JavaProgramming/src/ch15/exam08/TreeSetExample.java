package ch15.exam08;

import java.util.TreeSet;

public class TreeSetExample {	
	public static void main(String[] args) {		
//		TreeSet<Person> set = new TreeSet<>(new CompareByName());
		TreeSet<Person> set = new TreeSet<>(new CompareByAge());
		
		set.add(new Person("홍길동", 45));
		set.add(new Person("감자바", 55));
		set.add(new Person("박지원", 31));		
		
		for(Person person: set) {
			System.out.println(person.getName() +"(" + person.getAge() + ")");
		}		
	}
}
