package ch07.homework02;

public class AnimalExample {

	public static void main(String[] args) {
		Dog dog = new Dog();
		Cat cat = new Cat();
		
		dog.sound();
		cat.sound();
		
		System.out.println("---------------------");
		
		Animal animal;
		animal = new Dog();		
		animal.sound();
		
		animal = new Cat();
		animal.sound();
		System.out.println("---------------------");

		animalSound(new Dog());
		animalSound(new Cat());
	}
	
	public static void animalSound(Animal animal) {
		animal.sound();
	}

}
