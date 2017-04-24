package ch18.homework3.p11;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("localhost", 50005));
        
        System.out.println("[서버 시작]");
        
        while(true) {
            try{
                Socket socket = serverSocket.accept();
                
                InputStream is =socket.getInputStream();
                
                byte[] bytes = new byte[100];
                int readCount = -1;
                
                // 먼저 파일명을 받음
                readCount = is.read(bytes);
//                System.out.println(readCount);
                String fileName = new String(bytes, 0, readCount);
                fileName = fileName.trim();
                
                System.out.println("[파일 받기 시작] " + fileName);
                
                // 파일을 저장할 경로 지정
                File file = new File("d:/Temp/" + fileName);      
                // 파일을 외부로 보내기 위해 FileOutputStream 생성
                FileOutputStream fos = new FileOutputStream(file);     
                
                while(true) {
                    // 파일을 읽고
                    readCount = is.read(bytes);
                    if(readCount == -1) break;
                    // 읽은 만큼 보냄
                    fos.write(bytes, 0, readCount);                
                }                
                fos.flush();
                System.out.println("[파일 받기 완료]");
                
                fos.close();
                is.close();
                socket.close();          
                
            }catch(Exception e) {
                System.out.println(e.getMessage());
                break;
            }            
        }
        serverSocket.close();
        System.out.println("[서버 종료]");
                
    }
}
