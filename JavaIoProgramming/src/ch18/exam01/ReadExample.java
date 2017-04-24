package ch18.exam01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = ReadExample.class.getResource("test.txt").getPath();  
        System.out.println(path);
        InputStream is =new FileInputStream(path);
//        while(true) {
//            int v1 = is.read();
//            if(v1 == -1) break;
//            System.out.print((char)v1 + " ");    
//        }
        
        int data = -1;
        while((data = is.read()) != -1) {
            System.out.print((char)data);
        }
        is.close();                
        System.out.println("가나다라");
    }
}
