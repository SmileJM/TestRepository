package ch06.exam07;

public class Calculator {
	// Field
	static String model = "MI-84";
	static 	String makeDay = "2017-03-29";
	static String info;
	
	static {
		info = model;
		info +="(";
		info +=makeDay;
		info +=")";
	}
}
