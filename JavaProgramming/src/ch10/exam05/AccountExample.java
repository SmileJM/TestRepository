package ch10.exam05;

public class AccountExample {

	public static void main(String[] args) {
		Account account = new Account();
		System.out.println("잔고: " + account.getBalance());
		
		account.deposit(100000);
		System.out.println("잔고: " + account.getBalance());
		try {
			account.withdraw(1000000);
		} catch (BalanceInsufficientException e) {
			// 에외 처리 코드
			System.out.println(e.getMessage());			
		}
		System.out.println("잔고: " + account.getBalance());
	}
}