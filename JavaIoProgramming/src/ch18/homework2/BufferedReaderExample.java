package ch18.homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class BufferedReaderExample {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        Reader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);
        
        System.out.print("입력: ");
        // Buffered의 기능으로 line 단위로 입력을 받도록 함
        String line = br.readLine();
        System.out.println("출력: " + line);
        br.close();
        reader.close();
        is.close();
    }
}
