package ch18.homework3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientExample {
    public static void main(String[] args) throws InterruptedException {
        Socket socket = null;
        
        try {         
            for(int i=0; i<2000; i++) {
            socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 50005));                  
           
            // 통신을 위해 socket으로부터 OutputStream을 얻어옴
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            String strData = "Hello Server " +i;
            osw.write(strData);
            osw.flush();
            
        
            // 통신을 위해 socket으로부터 InputStream을 얻어옴
            InputStream is = socket.getInputStream();
            byte[] data = new byte[100];
            int readBytes = is.read(data);
            strData = new String(data, 0, readBytes);
            System.out.println("Server로부터 받은 데이터: " + strData);
           
            osw.close();
            os.close();
            is.close();

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        if(!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }
}
