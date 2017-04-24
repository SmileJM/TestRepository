package ch18.exam19;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class LineDataExample {
    public static void main(String[] args) throws IOException {
//        FileWriter fw = new FileWriter("src/ch18/exam19/test.txt");
//        FileOutputStream fos = new FileOutputStream("src/ch18/exam19/test.txt");        
//        OutputStreamWriter osr = new OutputStreamWriter(fos);
//        osr.write("abcdef\r\n");
//        osr.write("12345\r\n");
//        osr.write("가나다라마");
//        osr.flush();
//        osr.close();
        
        FileOutputStream fos = new FileOutputStream("src/ch18/exam19/test.txt");
        PrintStream out = new PrintStream(fos);
        out.println("12345");
        out.println("abcde");
        out.print("가나다라마");
        
        out.flush();
        fos.flush();
        out.close();
        fos.close();
        
        FileInputStream fis = new FileInputStream("src/ch18/exam19/test.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        
        while(true) {            
            String line = br.readLine();
            if(line == null) break;
            System.out.println(line);
        }
        
        br.close();
        isr.close();
        fis.close();
        
    }
}
