package ch15.homework.p09;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapExample {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("blue", 96);
		map.put("hong", 86);
		map.put("white", 92);
		
		String name = null;
		int maxScore = 0;
		int totalScore = 0;
		
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		Iterator<Map.Entry<String, Integer>> iteratorSet = entrySet.iterator();
		while(iteratorSet.hasNext()) {
			Map.Entry<String, Integer> entry = iteratorSet.next();
			String key = entry.getKey();
			int value = entry.getValue();
			if(maxScore < value) {
				maxScore = value;
				name = key;
			}
			totalScore += value;
		}				
		
		System.out.println("평균점수: " + totalScore / map.size());
		System.out.println("최고점수: " + maxScore);
		System.out.println("최고점수를 받은 아이디: " + name);
		
	}

}
