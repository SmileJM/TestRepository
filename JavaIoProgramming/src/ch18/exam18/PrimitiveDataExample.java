package ch18.exam18;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PrimitiveDataExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int value1 = 10;
        double value2 = 10.5;
        boolean value3 = true;
        String value4 = "자바";
        
        // int 문자가 아니기 때문에 .txt 파일에 저장해도 의미가 없음
        // .dat 형식: binary 데이터
        OutputStream os = new FileOutputStream("src/ch18/exam18/test.dat");
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(value1);
        dos.writeDouble(value2);
        dos.writeBoolean(value3);
        dos.writeUTF(value4);
        // 저장된 순서로 읽어야 함
        
        dos.flush();
        os.flush();
        
        dos.close();
        os.close();
        
        InputStream is = new FileInputStream("src/ch18/exam18/test.dat");
        DataInputStream dis = new DataInputStream(is);
        int readValue = dis.readInt();
        double readDouble = dis.readDouble();
        boolean readboolean = dis.readBoolean();
        String readString = dis.readUTF();
        
        System.out.println(readValue);
        System.out.println(readDouble);
        System.out.println(readboolean);
        System.out.println(readString);
        
        dis.close();
        is.close();
        
    }
}
