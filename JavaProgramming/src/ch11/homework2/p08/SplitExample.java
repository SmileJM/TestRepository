package ch11.homework2.p08;

import java.util.StringTokenizer;

public class SplitExample {

	public static void main(String[] args) {
		String str = "아이디,이름,패스워드";
		
		String[] strArr = str.split(",");
		for(String sa : strArr) {
			System.out.println(sa);
		}
		System.out.println();
		
		StringTokenizer st = new StringTokenizer(str,",");
		while(st.hasMoreTokens()) {
			String sa = st.nextToken();
			System.out.println(sa);
		}
	}

}
