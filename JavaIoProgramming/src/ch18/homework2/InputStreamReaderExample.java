package ch18.homework2;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class InputStreamReaderExample {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        Reader reader = new InputStreamReader(is);
        
        int readCharNo;
        char[] cbuf = new char[100];
        
        // 반복문을 통해서 반복적으로 키보드로부터 데이터를 입력 받고 출력함
        while((readCharNo = reader.read(cbuf)) != -1) {
            String data = new String(cbuf, 0, readCharNo);
            System.out.println(data);
        }
        reader.close();
        is.close();               
    }
}
