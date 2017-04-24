package ch06.homework03.p20;

import java.util.Scanner;

public class BackApplication {
	private static Account[] accountArray = new Account[100];
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean run = true;
		while(run) {
			System.out.println("---------------------------------------------------------");
			System.out.println("1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4. 출금 | 5. 종료");			
			System.out.println("---------------------------------------------------------");
			System.out.print("선택> ");
			
			int selectNo = Integer.parseInt(scanner.nextLine());
//			int selectNo = scanner.nextInt();			
//			scanner.nextLine();
		
			if(selectNo ==1) {
				createAccount();
			} else if(selectNo ==2) {
				accountList();
			} else if(selectNo ==3) {
				deposit();
			} else if(selectNo ==4) {
				withdraw();
			} else if(selectNo ==5) {
				run = false;
			}
		}
		System.out.println("프로그램 종료");
	}
	
	private static void createAccount() {
		System.out.println("-------------");
		System.out.println("계좌생성");
		System.out.println("-------------");
		System.out.print("계좌번호: ");
		String ano = scanner.nextLine();
		System.out.print("계좌주: ");
		String owner  = scanner.nextLine();
		System.out.print("초기입금액: ");
		int balance  = Integer.parseInt(scanner.nextLine());
		
		for(int i=0; i<accountArray.length; i++) {
			if(accountArray[i] != null &&accountArray[i].getAno().equals(ano)) {
				System.out.println("이미 동일한 계좌가 존재합니다.");
				return;	
			} else if(accountArray[i] == null) {				
				accountArray[i] = new Account(ano, owner, balance);
				System.out.println("결과: 계좌가 생성되었습니다.");
				break;
			}
		}	
	}
	private static void accountList() {
		System.out.println("-------------");
		System.out.println("계좌목록");
		System.out.println("-------------");
		for(int i=0; i<accountArray.length; i++) {
			if(accountArray[i] != null) {
				System.out.print(accountArray[i].getAno() + "\t");
				System.out.print(accountArray[i].getOwner() + "\t");
				System.out.println(accountArray[i].getBalance());
			}// else {
//				break;
//			}
		}
	}
	private static void deposit() {
		System.out.println("-------------");
		System.out.println("예금");
		System.out.println("-------------");
		System.out.print("계좌번호: ");
		String ano = scanner.nextLine();
		
		System.out.print("예금액: ");
		int balance = Integer.parseInt(scanner.nextLine());
		
		Account account = findAccount(ano);
		
		if(account != null) {
			balance += account.getBalance();
			account.setBalance(balance);
			System.out.println("결과: 예금이 성공되었습니다.");
		} else {
			System.out.println("결과: 일치하는 계좌가 없습니다.");
		}
		
	}
	private static void withdraw() {
		System.out.println("------------");
		System.out.println("출금");
		System.out.println("------------");
		
		System.out.print("계좌번호: ");
		String ano = scanner.nextLine();
		
		System.out.print("출금: ");
		int balance = Integer.parseInt(scanner.nextLine());
		
		Account account = findAccount(ano);
		
		if(account != null && account.getBalance() >= balance) {
			balance = account.getBalance() - balance;
			account.setBalance(balance);
			System.out.println("결과: 출금이 성공되었습니다.");
		} else if(account != null){
			System.out.println("결과: 잔액이 부족합니다.");
		} else {
			System.out.println("결과: 일치하는 계좌가 없습니다.");
		}
	}
	
	private static Account findAccount(String ano) {
		for(int i=0; i<accountArray.length; i++) {
			if(accountArray[i] != null && accountArray[i].getAno().equals(ano)) {
				return accountArray[i];
			}// else if(accountArray[i] == null) {
//				return null;
//			}
		}
		return null;
	}
}
