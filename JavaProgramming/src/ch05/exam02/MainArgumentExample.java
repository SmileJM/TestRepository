package ch05.exam02;

public class MainArgumentExample {

	public static void main(String[] args) {
		if(args.length == 2) {
			String v1 = args[0];
			int intV1 = Integer.parseInt(v1);
			String v2 = args[1];
			int intV2 = Integer.parseInt(v2);
			
			System.out.println("v1: " + v1);
			System.out.println("v2: " + v2);
			System.out.println("v1+v2= " +(intV1+intV2));
			
		} else {
			System.out.println("2개의 실행 매개 값이 필요합니다.");
		}
		
	}

}

