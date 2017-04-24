package ch18.homework2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerialVersionUIDExample2 {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("d:/temp/object.dat");
        ObjectInputStream ois =  new ObjectInputStream(fis);
        ClassC classC = (ClassC) ois.readObject();
        System.out.println("field1: " + classC.field1);        
        
        ois.close();
        fis.close();                
    }
}
