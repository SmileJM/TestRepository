package ch15.homework;

import java.util.Comparator;

public class DescendingComparator implements Comparator<Fruit>{
	@Override
	public int compare(Fruit o1, Fruit o2) {
	
		return Integer.compare(o2.price, o1.price);
	}
}
