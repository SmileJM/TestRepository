package ch18.homework;

import java.util.Scanner;

public class ScannerExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("문자열 입력> ");
        String InputString = scanner.nextLine();
        System.out.println(InputString);
        System.out.println();
        
        System.out.print("점수 입력> ");
        // 정수만 입력 가능하기 때문에 실제 사용에서는 예외 처리가 필요해보임
        int InputInt = scanner.nextInt();

        System.out.println(InputInt);
        System.out.println();
        
        System.out.print("실수 입력> ");
        double InputDouble = scanner.nextDouble();
        System.out.println(InputDouble);
        
    }
}
