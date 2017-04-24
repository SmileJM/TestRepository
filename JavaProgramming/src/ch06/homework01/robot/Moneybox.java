package ch06.homework01.robot;

public class Moneybox {
	int balance;
	
	int getBalance() {
		return balance;
	}
	void setBalance(int balance) {
		this.balance = balance;
	}
	int charge(int cash, int price) {		
		int charge = cash-price;
		balance += price;
		return charge;
	}
}
