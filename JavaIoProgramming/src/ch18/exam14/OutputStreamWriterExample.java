package ch18.exam14;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class OutputStreamWriterExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("src/ch18/exam14/test.txt");        
        OutputStreamWriter osw = new OutputStreamWriter(os);
        
//        byte[] data = "가ss".getBytes();
//        os.write(data);
//        os.flush();
//        os.close();
        osw.write("가나a");
        osw.flush();
        osw.close();        
        os.close();
        
    }
}
