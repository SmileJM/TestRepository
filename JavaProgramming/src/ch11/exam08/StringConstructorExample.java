package ch11.exam08;

import java.io.IOException;

public class StringConstructorExample {

	public static void main(String[] args) throws IOException {
		String s1 = "abc";
		System.out.println(s1);
		
		String s2 = new String("abc");
		System.out.println(s2);
		
		char[] charArray = { 'a', 'b', 'c' };
		String s3 = new String(charArray);
		System.out.println(s3);
		
		byte[] byteArray = {49, 50, 51 };
		String s4 = new String(byteArray);
		System.out.println(s4);
		
		byte[] byteArray2 = {49, 50, 51, 52, 53, 54, 55 };
		String s5 = new String(byteArray2, 3, 3);
		System.out.println(s5);
			
		byte[] inputData = new byte[100];
		int readByteNo = System.in.read(inputData);
		String strData = new String(inputData, 0,  readByteNo-2);
		System.out.println(strData);
		System.out.println(readByteNo);
		
	}

}
