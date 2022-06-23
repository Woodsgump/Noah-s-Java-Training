package dev.cavazos.petapp.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import dev.cavazos.petapp.data.FileAccessObject;

// classes are blueprints for objects
public class PetAppMain {
		// public: accessible anywhere
		// protected: same package & subclasses
		// "default" (no modifier): same package only
		// private: only within the same class
		static Scanner scanner = new Scanner(System.in);
		
		public static void main(String[] args) {
			// petClassObjectExample();
			//controlFlow();
			objectClass();
			
			//Object obj = null;
			//if (obj != null)
			//	obj.hashCode();
			
			petApp();
			
			scanner.close();
		}
		
		private static void petApp(){
			Pet[] pets = new Pet[3];
			FileAccessObject fileAccessObject = new FileAccessObject();
			
			// handling exceptions right now
			try {
					File file = new File("pets.dat");
					fileAccessObject.readObjects(file);
			} catch (FileNotFoundException e) {
				// this is whatever we want to happen if the exception does not get thrown
				e.printStackTrace();
			} catch (Exception e) {
				// this will handle any exceptions that aren't caught by the above
				// catch block(s)
				e.printStackTrace();
			} finally {
				// finally blocks are optional (when you have a try you either have to
				// have at least one catch or finally)
				System.out.println("This always happens either way");
			}
			
			boolean userContinue = true;
			
			// condition must be true
			while (userContinue) {
				System.out.println("Type 1 to add a pet,"
								+ "type 2 to view pets, type anything to exit");
				String input = scanner.nextLine();
				
				switch (input) {
				case "1":
						// add a pet
						System.out.println("Enter a pet name: ");
						String name = scanner.nextLine();
						System.out.println("Enter the pet's age: ");
						String ageInput = scanner.nextLine(); // why is the variable a string instead of an input???
						
						int age = Integer.parseInt(scanner.nextLine());
						System.out.println("Enter the pet's species: ");
						String species = scanner.nextLine();
						System.out.println("Enter the pet's secret: ");
						String secret = scanner.nextLine();
						
						Pet newPet = new Pet(name, age, species, secret);
						int index = newPet.getId() - 1;
						
						pets[index] = newPet;
						break;
				case "2":
						// view pets
						System.out.println(Arrays.toString(pets));
						break;
				default:
						userContinue = false;
						break;
				}
			}
		}
		
		private static void objectClass() {
			Pet myPet = new Pet();
			myPet.setId(1);
			Pet otherPet = new Pet();
			otherPet.setId(1);
			
			System.out.println(myPet.hashCode());
			System.out.println(myPet.equals(otherPet));
			System.out.println(myPet); //(myPet.toString()) implicitly
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
	
	public static void petClassObjectExample() {
		// create an object using the class as a blueprint
		
		Pet.printPetCount();
		System.out.println(Pet.randomBoolean);
		
		// declare a variable, assigning it a value
		// instantiating (creating) an object by calling a constructor
		Pet myPet = new Pet();
		//myPet.name = "Guap";
		myPet.setName("Guap");
		System.out.println("pet id: " + myPet.getId());
		// myPet.secret = "";
		// type of variable: the class
		
		Pet myLizard = new Pet("Spyro", 4, "lizard", "breathes flames");
		myLizard.eat("bug");
		System.out.println("pet id: " + myLizard.getId());
		System.out.println(myLizard.randomBoolean);
		
		Pet.printPetCount();
	}
	
	private static void hello() {
		System.out.println("hello");
	}

}
