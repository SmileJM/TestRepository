package ch18.exam13;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class InputStreamReaderExample {
    public static void main(String[] args) throws IOException {
//        InputStream is = System.in;
//        InputStreamReader isr=  new InputStreamReader(is);
//        Reader reader = new InputStreamReader(is);
//        byte[] data2 = new byte[10];
//        int dataBytes = -1;
//        while(true) {
//            dataBytes = reader.read(data2);
//        }
//        int data = isr.read();
//        
//        System.out.println((char)data);

        InputStream is = new FileInputStream("src/ch18/exam13/test2.txt");
        InputStreamReader isr=  new InputStreamReader(is, "EUC-KR");
        int data = isr.read();
        System.out.println((char)data);
    }
}
