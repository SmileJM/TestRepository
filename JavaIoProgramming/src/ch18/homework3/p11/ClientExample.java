package ch18.homework3.p11;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientExample {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 50005);
        OutputStream os = socket.getOutputStream();
        
        String filePath = "src/ch18/homework3/p11/ClientExample.java";
        
        File file = new File(filePath);
        
        String fileName = file.getName();
        byte[] data = new byte[100];
        data = fileName.getBytes();
        // 먼저 파일 이름을 보냄
        os.write(data, 0, data.length);
//        System.out.println(data.length);
        
        System.out.println("[파일 보내기 시작] " + fileName);
        // 파일을 보내기 위해서 FileInputStream 생성
        FileInputStream fis = new FileInputStream(filePath);
        
        data = new byte[100];
        int readBytes = -1;
        while(true) {
            // 파일을 읽고
            readBytes = fis.read(data);
//            System.out.println(readBytes);
            if(readBytes == -1)break;
            // 읽은 만큼 보냄
            os.write(data, 0, readBytes);
        }        

        os.flush();
        System.out.println("[파일 보내기 성공]");      
        
        fis.close();
        os.close();        
        socket.close();
    }
}
