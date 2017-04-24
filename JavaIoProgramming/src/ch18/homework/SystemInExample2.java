package ch18.homework;

import java.io.IOException;
import java.io.InputStream;

public class SystemInExample2 {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        byte[] datas = new byte[100];
        
        System.out.print("이름: ");
        // 입력을 크기가 100인 byte 배열로 받아서 읽음
        int nameBytes = is.read(datas);
        // String 생성자를 통하여 키보드로부터 읽은 데이터를 저장
        // 캐리지 리턴, 라인피드 제외 
        String name = new String(datas, 0, nameBytes-2);
        
        System.out.print("하고 싶은 말: ");
        int commentBytes = is.read(datas);
        String comment = new String(datas, 0, nameBytes-2);
        
        System.out.println("입력한 이름: " + name);
        System.out.println("입력한 하고 싶은 말: " + comment); 
    }
}
