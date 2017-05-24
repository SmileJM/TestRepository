package midas;

import java.util.Scanner;

public class P2 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("N: ");
		int n = scanner.nextInt();
		System.out.print("M: ");
		int m = scanner.nextInt();
		int sum = 0;
		
		for(int i=2 ; i<n; i++) {
			if(n%i != 0) {
				sum += n;
				System.out.println(n);
			}
		}
	}

}
