package dev.cavazos.petapp.main;

import java.util.Random;

// classes are blueprints for objects
public class PetAppMain {
		// public: accessible anywhere
		// protected: same package & subclasses
		// "default" (no modifier): same package only
		// private: only within the same class
	
	public static void main(String[] args) {
			// petClassObjectExample();
			controlFlow();
	}
	
	private static void controlFlow() {
			// conditional statements
			// if-else
			// switch
			// ternary
		
			// if-else
			int x = -20;
			if (x < 15 && x > 5 || x < 0) {
				System.out.println("x is between 5 and 15");
			} else if (x == 15 || x == 100) {
				System.out.println("x is 15 or 100");
			} else if (x == 5) {
				System.out.println("x is 5");
			} else {
				System.out.println("x is not between 5 and 15");
			}
			
			// switch
			String hairColor = "brown";
			switch(hairColor) {
			case "black":
					System.out.println("black hair");
					break;
			case "brown":
					System.out.println("brown hair");
					break;
			case "gray":
				System.out.println("gray hair");
				break;
			case "red":
			case "orange":
			case "auburn":
				System.out.println("red hair");
				break;
			default:
				System.out.println("another color of hair");
				break;
			}
			
			// ternary
			System.out.println((x < 5)?"hello":"goodbye");
	
			// Loops
			// while/do-while
			// for/enhanced-for (for each)
			
			int n = 5;
			// while/do-while: can't determine how many times you need to iterate
			while (n != 5) {
				System.out.println("n is not 5");
				Random random = new Random();
				n = random.nextInt(10);
			}
			
			do {
				System.out.println("n might not be 5");
			} while(n != 5);
			
			// for/enhanced-for: can determine number of iterations
			int[] numberArray = new int[5]; // = {1,2,3,4,5}; (array literal)
			numberArray[0] = 500;
			numberArray[1] = 250;
			
			// regular for loops are good for when you are not dealing with
			// arrays or collections, or when you do need to know the index
			//  (initialization; condition; incrementation)
			for (int i = 0; i < numberArray.length; i++) {
				System.out.println(i + ": " + numberArray[i]);
			}
			
			// enhanced for loops (for-each loops) are good for when you are
			// iterating through an array or collection and don't need to know
			// the index as you go through
			for (int number : numberArray) {
				System.out.println(number);
			}
	}
	
	private static void petClassObjectExample() {
		// create an object using the class as a blueprint
		
		Pet.printPetCount();
		System.out.println(Pet.randomBoolean);
		
		// declare a variable, assigning it a value
		// instantiating (creating) an object by calling a constructor
		Pet myPet = new Pet();
		myPet.name = "Guap";
		System.out.println("pet id: " + myPet.id);
		// myPet.secret = "";
		// type of variable: the class
		
		Pet myLizard = new Pet("Spyro", 4, "lizard", "breathes flames");
		myLizard.eat("bug");
		System.out.println("pet id: " + myLizard.id);
		System.out.println(myLizard.randomBoolean);
		
		Pet.printPetCount();
	}
	
	private static void hello() {
		System.out.println("hello");
	}

}
