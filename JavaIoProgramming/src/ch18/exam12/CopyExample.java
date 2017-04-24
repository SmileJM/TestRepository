package ch18.exam12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        InputStream is = new FileInputStream("src/ch18/exam12/Tulips.jpg");
        OutputStream os = new FileOutputStream("src/ch18/exam12/Tulips2.jpg");
        
        byte[] data = new byte[1024];
        int readBytes = -1;
        
        while(true) {
            readBytes = is.read(data);
            if( readBytes == -1) break;
            os.write(data, 0, readBytes);
        }        
        os.flush();
        os.close();
        is.close();        
    }
}
