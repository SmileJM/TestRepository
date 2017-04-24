package ch11.exam02;

import java.util.HashSet;

public class MemberExample {

	public static void main(String[] args) {
		Member m1 = new Member("blue");
		Member m2 = new Member("blue");
		Member m3 = new Member("blue");
		
		System.out.println(m1.hashCode());
		System.out.println(m2.hashCode());
		System.out.println(m3.hashCode());
		
//		HashSet hashSet = new HashSet();
//		hashSet.add(m1);
//		hashSet.add(m2);
//		System.out.println(hashSet.size());
	}

}
