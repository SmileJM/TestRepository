package ch15.homework;

public class Person implements Comparable<Person>{
	public String name;
	public int age;
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public int compareTo(Person o) {	
//		return name.compareTo(o.name);
		return Integer.compare(age, o.age);
	}
	
	
}
