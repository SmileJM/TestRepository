package ch18.homework3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 50005));
            
            while(true) {
                
                // 클라이언트로부터 연결을 수락하면 Socket 객체를 만듬
                Socket socket = serverSocket.accept();
                long start1 = System.nanoTime();
                Runnable task = ()->{
                    long start = System.nanoTime();
                    InputStream is = null;
                    try {
//                        System.out.println(Thread.currentThread().getName());
                        InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                        System.out.println("Client IP: " + isa.getHostName());
                        // 통신을 위해 socket으로부터 InputStream을 얻어옴
                        is = socket.getInputStream();
                        byte[] data = new byte[100];
                        int readBytes = is.read(data);
                        String strData = new String(data, 0, readBytes);
                        System.out.println("Client로부터 받은 데이터: " + strData);
         
            
                        // 통신을 위해 socket으로부터 OutputStream을 얻어옴
                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        strData = "Hello Client";
                        osw.write(strData);
                        osw.flush();
                        osw.close();
                        os.close();
                        is.close();
                        socket.close();
                        System.out.println();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    } 
                    long end = System.nanoTime();
                    System.out.println(Thread.currentThread().getName() + ": " + (end-start) + " ns");
                };
                executorService.submit(task);
     
                long end1 = System.nanoTime();
                System.out.println(Thread.currentThread().getName() + ": " + (end1-start1) + " ns");
                
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        if(serverSocket != null && !serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
