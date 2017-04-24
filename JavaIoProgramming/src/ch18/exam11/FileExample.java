package ch18.exam11;

import java.io.File;
import java.io.IOException;

public class FileExample {
    public static void main(String[] args) throws IOException {
        // 파일(디렉토리)의 정보 얻기
        File file = new File("src/ch18/exam11/FileExample.java");
        System.out.println(file.exists());
        System.out.println(file.length());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println();
        
        File dir = new File("c:/windows");
        System.out.println(dir.exists());
        System.out.println(dir.length());
        System.out.println(dir.isFile());
        System.out.println(dir.isDirectory());
        System.out.println();
        String[] contents1 = dir.list();
        File[] contents2 = dir.listFiles();     // 디렉토리에서만 사용 가능        

        System.out.println();
        for(File c2 : contents2) {
            String info = "";
            info += c2.getName();
            info += "\t\t";
            info += (c2.isDirectory() ? "<DIR>" : "");
            info += "\t\t";
            info += c2.length();
            System.out.println(info);
        }
        
        // 파일(디렉토리)의 생성, 삭제        
        File dir2 = new File("d:/Temp/dir2");
        dir2.mkdirs();
        
        File file2 = new File("d:/Temp/w/w/w/w/w/test.txt");
        file2.mkdirs();
        file2.createNewFile();        
        File dir3 = new File("d:/Temp/dir3/dir4/dir5");
        dir3.mkdirs();
        dir3.delete();
    }
}
