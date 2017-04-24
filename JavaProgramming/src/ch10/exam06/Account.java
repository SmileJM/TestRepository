package ch10.exam06;

public class Account {
	private long balance;
	
	public long getBalance() {
		return balance;
	}
	public void deposit(int money) {
		balance += money;
	}
	public void withdraw(int money) throws BalanceInsufficientException {
		if(balance < money) {
//			throw new BalanceInsufficientException();
			throw new BalanceInsufficientException("잔고 부족");
		
		}
		balance -= money;
	}
}
