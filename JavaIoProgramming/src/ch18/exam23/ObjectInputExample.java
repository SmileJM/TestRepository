package ch18.exam23;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputExample {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
	FileInputStream fis = new FileInputStream("src/ch18/exam23/Object.dat");
	ObjectInputStream ois = new ObjectInputStream(fis);

	VVIP vvip = (VVIP) ois.readObject();        
	System.out.println(vvip.getMemberShipNo());
	System.out.println(vvip.getName());
	System.out.println(vvip.getGrade());
	System.out.println(vvip.getAge());

	ois.close();
	fis.close();
    }
}
