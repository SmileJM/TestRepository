package ch15.exam06;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {
	
	public static void main(String[] args) {
		//  Set<Integer> set = new TreeSet<>(); 으로 하면 TreeSet 만의 메소드는 사용 불가
		TreeSet<Integer> set = new TreeSet<>();
		set.add(87);
		set.add(98);
		set.add(75);
		set.add(95);
		set.add(80);
		
		int min = set.first();
		int max = set.last();
		
		System.out.println(min);
		System.out.println(max);
		System.out.println();
		
		Set<Integer> s = set.subSet(80, true, 90, false);
		for(int score : s) {
			System.out.println(score);
		}
		System.out.println();
		Set<Integer> set3 = set.descendingSet();
		for(int score : set3) {
			System.out.println(score);
		}
		System.out.println();
		
		Set<Integer> set4 = set.descendingSet().descendingSet();
		for(int score : set4) {
			System.out.println(score);
		}
		
	}

}
