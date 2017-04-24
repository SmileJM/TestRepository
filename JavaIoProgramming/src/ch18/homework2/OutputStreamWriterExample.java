package ch18.homework2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class OutputStreamWriterExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("d:/Temp/file.txt");
        // 보조 스트림을 이용하여 스트링 값을 바이트로 변환시키지 않고 출력
        Writer writer = new OutputStreamWriter(fos);
        
        String data = "바이트 출력 스트림을 문자 출력 스트림으로 변환";
        writer.write(data);
        
        writer.flush();
        writer.close();        
    }
}
