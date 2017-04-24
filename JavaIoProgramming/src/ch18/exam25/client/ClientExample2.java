package ch18.exam25.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientExample2 {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // how1
            // socket 생성시 연결요청이 이루어짐
            // 연결 요청시 socket을 새로 생성해야 하는 단점이 있음
            // socket = new Socket("192.168.3.18", 50001);

            // how2
            // 연결하고 끊고 하는 작업을 반복할 경우 효과적
            
            // socket 생성
            socket = new Socket();
            // 연결 요청
            socket.connect(new InetSocketAddress("192.168.3.18", 50001));
            
//            String str = null;
//            str.length();
//             통신 하기
//             서버에 데이터 보내기
            OutputStream os = socket.getOutputStream();
            String strData = "가나다";
            byte[] data = strData.getBytes("UTF-8");
            os.write(data);
            os.flush();
       
            System.out.println("데이터 보내기 성공");
            
            // 서버로부터 데이터 받기
            InputStream is = socket.getInputStream();
            data = new byte [100];
            int readBytes = is.read(data);
            strData = new String(data, 0, readBytes, "UTF-8");
    
            System.out.println("받은 데이터: " + strData);
                        
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        // 다 사용 하였을 때
        if(!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException ex) { }
        }        
    }
}
