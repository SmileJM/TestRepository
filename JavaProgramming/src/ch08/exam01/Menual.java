package ch08.exam01;

public interface Menual {
	// Field
	// public static final 기본적으로 생략함(무조건 그렇기 때문에)
	 String COMPANY = "삼성전자";
	 int MAX_SPEED = 10;
	 int MIN_SPEED = 0;	
	// Method
	//public abstract 생략함 
	 void turnOn();
	 void turnOff();
	 void setSpeed(int speed);
	 int getSpeed();
	 void run();
}
