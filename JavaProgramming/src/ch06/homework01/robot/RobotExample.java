package ch06.homework01.robot;

public class RobotExample {

	public static void main(String[] args) {
		Robot robot = new Robot("초록", "200kg", "17-0001");
		robot.powerOn();
		robot.run();
	}
}
