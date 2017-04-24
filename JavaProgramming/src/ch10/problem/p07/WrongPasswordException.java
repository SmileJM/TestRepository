package ch10.problem.p07;

public class WrongPasswordException extends Exception{
	public WrongPasswordException() {}
	public WrongPasswordException(String message) {
		super(message);
	}

}
