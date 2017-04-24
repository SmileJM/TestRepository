package ch15.exam05;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertiesExample {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
//		prop.load(new FileReader("C:/00 IoTCourse/EclipseWorkspace/JavaProgramming/src/ch15/exam05/database.properties"));
		String path = URLDecoder.decode(PropertiesExample.class.getResource("database.properties").getPath(), "UTF-8");
		System.out.println(path);
		prop.load(new FileReader(path));
	
		String driver = prop.getProperty("driver");		
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		String manager = prop.getProperty("manager");
		
		System.out.println(driver);
		System.out.println(url);
		System.out.println(user);
		System.out.println(password);
		System.out.println(manager);
	}

}
