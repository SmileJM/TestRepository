package ch18.homework2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerialVersionUIDExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("d:/temp/object.dat");
        ObjectOutputStream oos =  new ObjectOutputStream(fos);
        ClassC classC = new ClassC();
        classC.field1 = 1;
        oos.writeObject(classC);
        oos.flush();
        oos.close();
        fos.close();                
    }
}
