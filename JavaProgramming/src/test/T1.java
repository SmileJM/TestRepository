package test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class T1 {
	public static void main(String[] args) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		sdf.format(now);
		System.out.println(sdf.format(now));
		LocalDateTime currentDateTIme = LocalDateTime.now();
		System.out.println(currentDateTIme);
		System.out.println(new Date().getTime());

	}
}
