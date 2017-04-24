package ch18.homework3.p07;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class AddLineNumberExample {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String filePath = "src/ch18/homework3/p07/AddLineNumberExample.java";
        
        Reader reader = new FileReader(filePath);    
        BufferedReader br = new BufferedReader(reader);
        
        int count = 1;
        while(true) {
            // 한 줄씩 출력하기 위해서는 BufferedReader의 readLine() 메소드를 이용
            String strData = br.readLine();
            if(strData == null) break;             
            System.out.println((count++) + ": " + strData);            
        }
        br.close();
        reader.close();
    }
}
