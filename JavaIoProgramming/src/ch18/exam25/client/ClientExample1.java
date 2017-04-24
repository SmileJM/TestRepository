package ch18.exam25.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientExample1 {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            // how1
            // socket 생성시 연결요청이 이루어짐
            // 연결 요청시 socket을 새로 생성해야 하는 단점이 있음
//            socket = new Socket("192.168.3.18", 50001);
            // how2
            // 연결하고 끊고 하는 작업을 반복할 경우 효과적
            socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.3.18", 50001));
            
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
