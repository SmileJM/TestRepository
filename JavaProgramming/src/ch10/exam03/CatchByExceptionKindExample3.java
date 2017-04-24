package ch10.exam03;

public class CatchByExceptionKindExample3 {

	public static void main(String[] args) {
		try {
			String str = "null";
			System.out.println(str.length());
			
			int[] scores = { 90, 85 };
			for( int i=0; i<2; i++ ) {
				System.out.println(scores[i]);
			}
			
			String strNum = "a";
			int num = Integer.parseInt(strNum);
		} catch(NullPointerException | ArrayIndexOutOfBoundsException e) {
			// 코드1
			System.out.println("예외처리코드1");
		} catch(NumberFormatException e) {
			// 코드2
			System.out.println("예외처리코드2");
		} catch(Exception e) {
			
		}
	}

}
