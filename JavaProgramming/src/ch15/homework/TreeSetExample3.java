package ch15.homework;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetExample3 {
	
	public static void main(String[] args) {
		//  Set<Integer> set = new TreeSet<>(); 으로 하면 TreeSet 만의 메소드는 사용 불가
		TreeSet<Integer> scores = new TreeSet<>();
		scores.add(87);
		scores.add(98);
		scores.add(75);
		scores.add(95);
		scores.add(80);
		
		NavigableSet<Integer> descendingSet = scores.descendingSet();
		for(Integer score : descendingSet) {
			System.out.println(score);
		}
		System.out.println();
		
		NavigableSet<Integer> ascendingSet = scores.descendingSet().descendingSet();
		for(Integer score : scores) {
			System.out.println(score);
		}
		
	}

}
