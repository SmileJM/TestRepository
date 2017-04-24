package ch11.homework2.p13;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePrintExample {

	public static void main(String[] args) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 EEEE hh시 mm분");
		String str = sdf.format(now);
		System.out.println(str);
	}

}
