package ch18.homework;

import java.io.FileReader;

public class FileReaderExample {

                public static void main(String[] args) throws Exception {
		FileReader fr = new FileReader("src/ch18/homework/FileReaderExample.java");
		
		int readCharNo;
		char[] cbuf = new char[100];
		
		while((readCharNo = fr.read(cbuf)) != -1) {
			String data = new String(cbuf, 0, readCharNo);
			// 100개씩 읽고 출력하기 때문에 print() 메소드로 출력
			// println()을 사용할 경우 100개 읽고 라인 개행을 함
			System.out.print(data);
		}
		fr.close();
	}

}
