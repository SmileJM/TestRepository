package ch18.exam15;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BufferdExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("src/ch18/exam15/test.txt");
        BufferedOutputStream bos = new BufferedOutputStream(os);
        
        byte[] data = "가나다".getBytes();
        
        long startTime = System.nanoTime();
        
        for(int i=0; i<1000; i++) {
            bos.write(data);                    
        }
        bos.flush();
        
        long endTime = System.nanoTime();
        System.out.println("bos: " + (endTime-startTime) + " ns");
        System.out.println();
        
        startTime = System.nanoTime();
        for(int i=0; i<1000; i++) {
            os.write(data);                    
        }
        os.flush();
        
        endTime = System.nanoTime();        
        System.out.println("os: " + (endTime-startTime) + " ns");
        
        bos.close();
        os.close();        
    }
}
