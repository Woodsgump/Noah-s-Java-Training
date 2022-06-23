package dev.cavazos.petapp.main;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

// fully encapsulated class: private fields + public getter/setter methods

// "java bean" fully encapsulated class w/ a no-args constructor
// & overridden Object methods (hashCode, equals, toString), implements Serializable

public class Pet implements Serializable {
	// class members: states/behaviors or characteristics/actions in a class
	// (fields and methods)
	private int id;
	private String name;
	private int age;
	private String species;
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
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if (this.age == age) {} // primitives can use == 
		if (this.species.equals("")) {} // objects use .equals()
		//this.age.equals(8) // primitives aren't objects so you can't call methods
		
		if(age >= 0) {
			switch(this.species) {
			case "dog":
					age = age * 7;
					break;
			case "cat":
					age = age * 3;
					break;
			}
			this.age = age;
		} else {
			this.age = 0;
		}
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	// static method: you can call it without having and object/instance
	// of this class
	static void printPetCount() {
		System.out.println(count);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(age, id, name, secret, species);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		return age == other.age && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(secret, other.secret) && Objects.equals(species, other.species);
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", age=" + age + ", species=" + species + ", secret=" + secret
				+ "]";
	}
}
