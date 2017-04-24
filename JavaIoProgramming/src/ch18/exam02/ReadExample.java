package ch18.exam02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ReadExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = ReadExample.class.getResource("test.txt").getPath();  
        InputStream is =new FileInputStream(path);
        
        byte[] data = new byte[5];                
        int readBytes = -1;      
        String strData = "";
        

        while(true) {
            readBytes = is.read(data);
            if( readBytes == -1)  break;
            
            System.out.println("읽은 바이트 수: " + readBytes);
            System.out.println("읽은 바이트 값: " + Arrays.toString(data));
            strData += new String(data, 0, readBytes);
            
        }
        System.out.println("-------------------------------------");
        System.out.println("읽은 문자열: " + strData);
        is.close();
//        while((readBytes = is.read(data)) != -1) {    
//            System.out.println("읽은 바이트 수: " + readBytes);
//            System.out.println("읽은 바이트 값: " + Arrays.toString(data));
//            strData += new String(data, 0, readBytes);
//        }
//        System.out.println("읽은 문자열: " + strData);
        
        
//        int readBytes = is.read(data);      
//        String strData = new String(data);
//        System.out.println("읽은 바이트 수: " + readBytes);
//        System.out.println("읽은 바이트 값: " + Arrays.toString(data));
//        System.out.println("읽은 문자열: " + strData);
//        
//        readBytes = is.read(data);  
//        strData = new String(data, 0, readBytes);
//        System.out.println("읽은 바이트 수: " + readBytes);
//        System.out.println("읽은 바이트 값: " + Arrays.toString(data));
//        System.out.println("읽은 문자열: " + strData);
        

    }
}
