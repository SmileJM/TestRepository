package ch18.homework2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferedInputStreamExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        long start = 0;
        long end = 0;
        
        FileInputStream fis1 = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        start = System.currentTimeMillis();
        while((fis1.read() != -1)) {
            
        }
        end = System.currentTimeMillis();
        System.out.println("사용하지 않았을 때: " + (end-start) + " ms");
        fis1.close();
        
        FileInputStream fis2 = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\Tulips.jpg");
        BufferedInputStream bis = new BufferedInputStream(fis2);
         start = System.currentTimeMillis();
        while((bis.read() != -1)) {
            
        }
        end = System.currentTimeMillis();
        System.out.println("사용했을 때: " + (end-start) + " ms");
        bis.close();
        fis2.close();
    }
}
