package ch07.homework02;

public class PhoneExample {

	public static void main(String[] args) {
//		Phone phone = new Phone("나");
		SmartPhone smartPhone = new SmartPhone("나");
		
		smartPhone.turnOn();
		smartPhone.internetSearch();
		smartPhone.turnOff();

	}

}
