package ch18.exam03;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample {
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
//       String path = WriteExample.class.getResource("test.txt").getPath();       
//       OutputStream os = new FileOutputStream(path);
       OutputStream os = new FileOutputStream("src/ch18/exam03/text.txt");
       
       for(int i=0; i<20; i++) {
           os.write(97);
       }       
       os.flush();
       os.close();
    }
}
