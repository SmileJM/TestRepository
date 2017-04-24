package ch06.homework03.p18;

public class ShopService {
	private static ShopService shopService = new ShopService();
	
	private ShopService(){}
	
	static ShopService getInstance() {
		return shopService;
	}
}
