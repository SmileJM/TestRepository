package ch18.exam25.server;
				
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample2 {	
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // 네트웍 어뎁터가 사용할 수 없는 상황이라면 예외 발생 가능
            // ServerSocket 생성
            serverSocket = new ServerSocket();
            // 포트와 바인딩
            serverSocket.bind(new InetSocketAddress(50001));	
            // 연결 기다리기            
            while(true) {
                Socket socket = serverSocket.accept();
                // 클라이언트의 정보 얻기 (클라이언트의 주소- SocketAddress를 리턴하지만
                // 실제로 InetSocketAddress를 리턴함
                // 강제 타입변환을 함 (자식 클래스 타입 으로)
//                InetSocketAddress isa = (InetSocketAddress)socket.getRemoteSocketAddress();
//                System.out.println(isa.toString());
//                System.out.println(isa.getAddress());
//                System.out.println(isa.getHostName());
                // 통신 하기
                // 클라이언트로 부터 받기
                InputStream is = socket.getInputStream();
                byte[] data = new byte[100];
                int readBytes = is.read(data);         
                if(readBytes == -1 ){
                    throw new IOException("클라이언트가 정상 종료 됨");
                }
                String strData = new String(data, 0, readBytes,  "UTF-8");

                System.out.println("받은 데이터: " + strData);

                // 클라이언트에게 데이터 보내기
                OutputStream os = socket.getOutputStream();
                data = strData.getBytes("UTF-8");
                os.write(data);
                os.flush();

                System.out.println("데이터 보내기 성공");

                // 연결 끊기
                socket.close();
            }            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        // null이 아니면서 포트가 바인드 되어 있다면 닫기(50001번을 사용한다면)
        if(serverSocket != null && !serverSocket.isClosed()){
            // ServerSocket 닫기, 50001번 포트 해제
            try {       
                serverSocket.close();
            } catch (IOException ex1) { }
        }        
    }
}
