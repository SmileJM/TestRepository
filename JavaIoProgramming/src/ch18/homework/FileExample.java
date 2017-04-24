package ch18.homework;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileExample {
    public static void main(String[] args) throws URISyntaxException, IOException {
        File dir = new File("d:/Temp/Dir");
        File file1 = new File("d:/Temp/file1.txt");
        File file2 = new File("d:/Temp/file2.txt");
        // 파일 경로를 URI 객체로 생성해서 매개값으로 제공
        File file3 = new File(new URI("file:///d:/Temp/file3.txt"));
        
        // dir이 존재하지 않으면 경로상의 모든 디렉토리 생성
        if(dir.exists() == false) {
            dir.mkdirs();
        }
        if(file1.exists() == false) {
            file1.createNewFile();
        }
        if(file2.exists() == false) {
            file2.createNewFile();
        }
        if(file3.exists() == false) {
            file3.createNewFile();
        }
        File temp = new File("d:/Temp");
        // 포맷 설정
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\ta HH:mm");
        // temp 폴더의 모든 파일, 폴더 리스트를 배열에 저장
        File[] contents = temp.listFiles();
        
        System.out.println("날짜\t\t시간\t\t형태\t크기\t이름");
        System.out.println("-----------------------------------------------------");
        for(File file : contents) {
        	System.out.print(sdf.format(new Date(file.lastModified())));
        	if(file.isDirectory()) {
        		System.out.print("\t<DIR>\t\t" + file.getName());        		
        	} else {
        		System.out.print("\t\t" + file.length() + "\t" + file.getName());
        	}
        	System.out.println();
        }
        
        
    }
}
