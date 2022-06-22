package dev.cavazos.petapp.main;

import java.util.Random;

public class Pet {
	// class members: states/behaviors or characteristics/actions in a class
	// (fields and methods)
	public int id;
	public String name;
	public int age;
	public String species;
	private String secret;
	
	// static: belongs to the class itself rather than individual objects
	// all of the instances (objects) share it
	public static int count = 0;
	
	public static boolean randomBoolean;
	
	// instance block: called every time you instantiate an object no matter
	// which constructor you use
	{
		this.id = ++count;
		// this.id = count++;
	}
	
	// static block: called once when the class is Loaded (beginning of application)
	static {
			// you might use this if you have static fields with more complex
			// setup than just basic assignment
		
			Random random = new Random();
			int num = random.nextInt(10);
			
			if (num < 5) {
				randomBoolean = true;
			} else {
				randomBoolean = false;
			}
	}
	
	//constructors: special type of method for
	// instantiating objects
	// default, no-args, overLoaded/parameterized
	
	// no-args constructor: a constructor with no parameters
	public Pet() {
		this.name = "Name";
		this.age = 0;
		this.species = "animal";
		this.secret = "";
	}
	
	// overLoaded constructor: a constructor with parameters
	public Pet(String name, int age, String species, String secret) {
		if(name != null) {
			this.name = name;
		} else {
			this.name = "Name";
		}
		this.age = age;
		this.species = species;
		this.secret = secret;
	}
	
	public Pet(String name, String species) {
		this.age = 0;
		this.secret = "";
		this.name = name;
		this.species = species;
	}
	
	// methods (functions in classes)
	// modifiers, return type, name, parameters
	void eat (String food) {
		System.out.println(this.name + " ate the " + food);
	}
	
	// static method: you can call it without having and object/instance
	// of this class
	static void printPetCount() {
		System.out.println(count);
	}
}
