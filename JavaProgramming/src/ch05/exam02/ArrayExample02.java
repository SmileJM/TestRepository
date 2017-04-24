package ch05.exam02;

public class ArrayExample02 {
	public static void main(String[] args) {
		int[] v1 = { 10, 20, 30 }; 

		int[] v2;
//		v2 =  { 10, 20, 30 };
		v2 = new int[] { 10, 20, 30 };
		
		int result1 = sum(v1);
		int result2 = sum(v2);
		int result3 = sum(new int[] {10, 20, 30});
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
	}
	
	public static int sum(int[] args) {
		int v3=0;
		for(int i=0; i<args.length; i++) {
			v3 += args[i];
		}
		return v3;
	}
}
