package ch06.homework01.robot;

public class Drink {
	String drink;
	int price;
	int quantity;
	
	Drink(String drink, int price, int quantity) {
		this.drink = drink;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String getDrink() {
		return drink;
	}

	void setPrice(int price) {
		this.price = price;
	}
	void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	void setQuantity() {
		this.quantity -= 1;
	}
	
	int getQuantity() {
		return quantity;
	}
	int getPrice() {
		return price;
	}
	boolean isSale(int cash){
		
		if(cash < price){
			return false;
		} else {
			return true;
		}
		
	}
	
}
