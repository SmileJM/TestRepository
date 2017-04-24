package ch06.homework03.p19;

public class Account {
	private int balance;
	final static int MIN_BALANCE = 0; 
	final static int MAX_BALANCE = 1000000; 

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		if(balance<MIN_BALANCE || balance > MAX_BALANCE) {
			
		} else {
			this.balance = balance;
		}
	}
	
	
}
