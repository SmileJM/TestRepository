package ch18.homework;

import java.io.IOException;
import java.io.OutputStream;

public class SystemOutExample {
    public static void main(String[] args) throws IOException {
        OutputStream os = System.out;
        
        for(byte b=48; b<58; b++) {
            os.write(b);
        }
        // 라인피드
        os.write(10);
        for(byte b=97; b<123; b++) {
            os.write(b);
        }
        os.write(10);
        
        String hangul = "가나다라마바사아자차카타파하";
        // String 값을 byte[] 형으로 바꾸기 위해 getBytes() 메소드 사용
        byte[] hangulBytes = hangul.getBytes();
        os.write(hangulBytes);
        os.write(10);
        
        os.flush();
        os.close();           
        
    }
}
