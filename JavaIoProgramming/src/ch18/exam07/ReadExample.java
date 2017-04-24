package ch18.exam07;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String path = ReadExample.class.getResource("test.txt").getPath();  
        Reader reader =new FileReader(path);
        
        char[] data = new char[3];
        int readBytes = reader.read(data);        
        String str = new String(data);
        System.out.println(readBytes);
        System.out.println(str);
        
        readBytes = reader.read(data);        
        str = new String(data);
        System.out.println(readBytes);
        System.out.println(str);
        
        readBytes = reader.read(data);        
        str = new String(data,0, readBytes);
        System.out.println(readBytes);
        System.out.println(str);
       reader.close();
    }
}
