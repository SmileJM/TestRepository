package ch18.exam16;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class BufferdExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        OutputStream os = new FileOutputStream("src/ch18/exam15/test.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);            
        
        long startTime = System.nanoTime();
        
        for(int i=0; i<1000; i++) {
            bw.write("가나다");                    
        }
        
        
        long endTime = System.nanoTime();
        System.out.println("bw: " + (endTime-startTime) + " ns");
        System.out.println();
        
        startTime = System.nanoTime();
        for(int i=0; i<1000; i++) {
            osw.write("가나다");                 
        }        
        
        endTime = System.nanoTime();        
        System.out.println("osw: " + (endTime-startTime) + " ns");       
 
        bw.flush();        
        osw.flush();
        os.flush();
        
        bw.close();
        osw.close();
        os.close();        
    }
}
