package ch18.exam05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WriteExample {
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
//       String path = WriteExample.class.getResource("test.txt").getPath();       
//       OutputStream os = new FileOutputStream(path);      
        InputStream is = new FileInputStream("src/ch18/exam05/text.txt");
        OutputStream os = new FileOutputStream("src/ch18/exam05/text2.txt");
       
        byte[] data = new byte[5];
        
        int readBytes = -1;
        
        while(true) {
            readBytes = is.read(data);
            if(readBytes == -1) break;            
            os.write(data, 0, readBytes);       
            System.out.println(1);
        }
        os.flush();        
        os.close();
        is.close();
    }
}
