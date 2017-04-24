package ch18.exam27.server;
				
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExample {	
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        
        ServerSocket serverSocket = null;        
        try {
            // 네트웍 어뎁터가 사용할 수 없는 상황이라면 예외 발생 가능
            // ServerSocket 생성
            serverSocket = new ServerSocket();
            // 포트와 바인딩
            serverSocket.bind(new InetSocketAddress(50001));	
            // 연결 기다리기                
            System.out.println("[클라이언트의 연결 기다림]");
            while(true) {                
                Socket socket = serverSocket.accept();
                // 통신 하기
                // 클라이언트로 부터 받기
                
                
                Runnable task = () -> {
                    try {
                        System.out.print(Thread.currentThread().getName() + ": ");
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
                    } catch(IOException e) {}
                };
                executorService.submit(task);    
                
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
