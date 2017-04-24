package ch18.exam21;

import ch18.exam20.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream("src/ch18/exam20/Object.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        Member member =  new Member("감자바", 30);
        member.nation = "미국";
        
        oos.writeObject(member);
        
        oos.flush();
        fos.flush();
        oos.close();
        fos.close();
        
        
    }
}
