package ch15.homework;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample2 {
	
	public static void main(String[] args) {
		//  Set<Integer> set = new TreeSet<>(); 으로 하면 TreeSet 만의 메소드는 사용 불가
		TreeSet<Integer> scores = new TreeSet<>();
		scores.add(87);
		scores.add(98);
		scores.add(75);
		scores.add(95);
		scores.add(80);
		
		Integer score = null;
		
		score = scores.first();
		System.out.println("가장 낮은 점수: " + score);
		
		score = scores.last();
		System.out.println("가장 높은 점수: " + score);
		
		score = scores.lower(new Integer(95));
		System.out.println("95점 아래 점수: " + score);
		
		score = scores.higher(new Integer(95));
		System.out.println("95점 위의 점수: " + score);
		
		score = scores.floor(new Integer(95));
		System.out.println("95점 이거나 바로 아래 점수: " + score);
		
		score = scores.ceiling(new Integer(85));
		System.out.println("85점 이거나 바로 위의 점수: " + score);
		
		while(!scores.isEmpty()) {
			score = scores.pollFirst();
			System.out.println(score + "(남은 객체 수: " + scores.size() + ")");
		}
		
	}

}
