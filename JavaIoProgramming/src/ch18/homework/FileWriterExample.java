package ch18.homework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterExample {

	public static void main(String[] args) throws IOException {
		File file = new File("src/ch18/homework/file.txt");
		// FileWriter 생성자에 두번째 매개값으로 true를 줄 경우
		// 파일을 덮어쓰지 않고 이어서 저장함
		FileWriter fw = new FileWriter(file, true);
		fw.write("FileWriter는 한글로된 " + "\r\n");
		fw.write("문자열을 바로 출력할 수 있다. " + "\r\n");
		fw.flush();
		fw.close();
		System.out.println("파일에 저장되었습니디.");
	}

}
