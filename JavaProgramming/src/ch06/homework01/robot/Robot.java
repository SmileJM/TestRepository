package ch06.homework01.robot;

import java.util.Scanner;

public class Robot {
	/*
	자판기
		콜라 Coke 클래스
			수량 int quantity
			가격 int price 
			가격 세팅
			판매 가능 확인 메소드 boolean isCheck()
			수량 확인 메소드 int getQauntity()
			수량 채우기 메소드 int setQauntity(int quantity)
		주스
		물
		잔고
			현금 입금 Scanner / int cash
			거스름돈 메소드 int charge()
		버튼
			음료 뽑기 Scanner / int select
			거스름돈 받기 
		색깔 color
		무게 weight
		제품번호  itemNumber
		잔액 balance
		전원 연결 powerOn
		----- 기계측 -----
		전원 확인
		잔고 확인
		재고 확인
		입력값 확인
		
		----- 사용자 -----
		현금 입금
		물품 선택
		거스름돈 		
		==========
		(시스템-잔액: xxxx원)
		1 콜라 [ x병 ]
		2 주스 [ x병 ]
		3 워터 [ x병 ]
		==========
		현금을 넣어주세요: 1000
		음료를 선택하세요: 1
		콜라 1병 출력
		거스름돈: xxx원
		
	*/
	// Field
	String color;
	String weight;
	String itemNumber;
	int cash;
	
	Drink[] drink = {
			new Drink("Coke", 700, 2),
			new Drink("Juice", 1000, 5),
			new Drink("Water", 500, 10),
	};
//	Coke coke = new Drink("Coke", 700, 2);
//	Juice juice = new Juice(1000, 5);
//	Water water = new Water(500, 10);
	Moneybox moneybox = new Moneybox();
	// Construtcor
	Robot(String color, String weight, String itemNumber) {
		this.color = color;
		this.weight = weight;
		this.itemNumber = itemNumber;
	}
	// Method
	void powerOn() {
		System.out.println("자판기 전원을 켭니다.");
	}
	
	Scanner scanner = new Scanner(System.in);
	
	void run() {
		while(true) {
			System.out.println("======= 사용자 메뉴 =======");
			System.out.println("1 "+ drink[0].getDrink() + "\t" + drink[0].getPrice() + "원  \t[ " + drink[0].getQuantity() + " 병 ]");
			System.out.println("2 "+ drink[1].getDrink() + "\t" + drink[1].getPrice() + "원\t[ " + drink[1].getQuantity() + " 병 ]");
			System.out.println("3 "+ drink[2].getDrink() + "\t" + drink[2].getPrice() + "원  \t[ " + drink[2].getQuantity() + " 병 ]");
			System.out.println("4 종료");
			System.out.println("======= 관리자 메뉴 =======");
			System.out.println("5 잔고 확인");
			System.out.println("6 Coke 수량 채우기");
			System.out.println("7 Juice 수량 채우기");
			System.out.println("8 Water 수량 채우기");
			System.out.println("9 기기 정보");
			System.out.println("0 시스템 종료");			
			System.out.println("=======================");			
			System.out.print("메뉴를 선택하세요: ");
			int select = scanner.nextInt();
			if(select >=1 && select <=3) {
				System.out.print("현금을 넣어주세요: ");
				cash += scanner.nextInt();
			}
			switch(select) {			
			case 1:
				if(drink[0].isSale(cash) && drink[0].getQuantity()>0){
					System.out.println("Coke 1병이 나왔습니다");
					drink[0].setQuantity();					
					int charge = moneybox.charge(cash, drink[0].getPrice());
					cash = 0;
					System.out.println("거스름돈: " + charge);
				} else if(drink[0].isSale(cash)) {
					System.out.println("Coke가 매진되었습니다. 다른 음료를 선택하세요.");	
				} else {
					System.out.println("현금이 부족합니다. 현금을 더 넣어주세요.");
					System.out.println("남은 금액: " + cash);
				}
				break;
			case 2:
				if(drink[1].isSale(cash) && drink[1].getQuantity()>0){
					System.out.println("Juice 1병이 나왔습니다");
					drink[1].setQuantity();
					int charge = moneybox.charge(cash, drink[1].getPrice());
					cash = 0;
					System.out.println("거스름돈: " + charge);
				} else if(drink[1].isSale(cash)) {
					System.out.println("Juice가 매진되었습니다. 다른 음료를 선택하세요.");
					break;
				} else {
					System.out.println("현금이 부족합니다. 현금을 더 넣어주세요.");
					System.out.println("남은 금액: " + cash);
				}
				break;
			case 3:
				if(drink[2].isSale(cash) && drink[2].getQuantity()>0){
					System.out.println("Water 1병이 나왔습니다");
					drink[2].setQuantity();
					int charge = moneybox.charge(cash, drink[2].getPrice());
					cash = 0;
					System.out.println("거스름돈: " + charge);
				} else if(drink[2].isSale(cash)) {
					System.out.println("Water가 매진되었습니다. 다른 음료를 선택하세요.");
					break;
				} else {
					System.out.println("현금이 부족합니다. 현금을 더 넣어주세요.");
					System.out.println("남은 금액: " + cash);
				}
				break;
			case 4:
				int charge = moneybox.charge(cash, 0);
				System.out.println("거스름돈: " + charge);
				cash =0;
				break;
			case 5:
				int balance = moneybox.getBalance();
				System.out.println("시스템 잔고: " + balance + "\\");
				break;
			case 6:
				System.out.println("Coke 수량을 20개로 채웁니다.");
				drink[0].setQuantity(20);
				break;			
			case 7:
				System.out.println("Juice 수량을 20개로 채웁니다.");
				drink[1].setQuantity(20);
				break;			
			case 8:
				System.out.println("Water 수량을 20개로 채웁니다.");
				drink[2].setQuantity(20);
				break;			
			case 9:
				System.out.println("[기기 정보]");
				System.out.println("\t색깔: " + color);
				System.out.println("\t무게: " + weight);
				System.out.println("\t제품번호: " + itemNumber);			
				break;			
			case 0:
				powerOff();
				return;
			default:				
				break;
			}	
			System.out.println();
		}		
	}
	void powerOff() {
		System.out.println("자판기 전원을 끕니다.");
	}
}