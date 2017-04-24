package ch18.homework2;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerializableReader {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("d:/temp/object.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
       
        ClassA v =(ClassA) ois.readObject();
        System.out.println("field1: " + v.field1);
        System.out.println("field2.field1: " + v.field2.field1);
        System.out.println("field3: " + v.field3);
        System.out.println("field2: " + v.field4);
        
        ois.close();
        fis.close();
        
    }
}
