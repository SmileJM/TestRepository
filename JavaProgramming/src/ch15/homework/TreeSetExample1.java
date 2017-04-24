package ch15.homework;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TreeSetExample1 {
	
	public static void main(String[] args) {
		//  Set<Integer> set = new TreeSet<>(); 으로 하면 TreeSet 만의 메소드는 사용 불가
		TreeSet<String> treeSet = new TreeSet<>();
		
		treeSet.add("apple");
		treeSet.add("forever");
		treeSet.add("description");
		treeSet.add("ever");
		treeSet.add("zoo");
		treeSet.add("base");
		treeSet.add("guess");
		treeSet.add("cherry");
		
		System.out.println("[c~f 사이의 단어 검색]");
		NavigableSet<String> rangeSet = treeSet.subSet("c", true, "f", true);
		for(String word : rangeSet) {
			System.out.println(word);
		}
		
		
	}

}
