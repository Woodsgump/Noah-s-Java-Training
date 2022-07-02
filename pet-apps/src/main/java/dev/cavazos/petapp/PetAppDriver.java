package dev.cavazos.petapp;

import java.util.Scanner;

import dev.cavazos.ds.List;
import dev.cavazos.exceptions.AlreadyAdoptedException;
import dev.cavazos.exceptions.UsernameAlreadyExistsException;
import dev.cavazos.models.Pet;
import dev.cavazos.models.User;
import dev.cavazos.services.UserService;

public class PetAppDriver {
		private static Scanner scanner = new Scanner(System.in);
		private static UserService userService;
		
		public static void main (String[] args) {
			boolean usingPetApp = true;
			System.out.println("Welcome to PetApp!");
			
			User user = null;
			while (usingPetApp) {
				if (user == null) {
					System.out.println("What would you like to do?\n" +
							"1. Log in\n" +
							"2. Register\n" +
							"x. Quit");
					
					String input = scanner.nextLine();
					
					switch (input) {
					case "1":
						user = logIn();
						break;
					case "2":
						register();
						break;
					default:
						usingPetApp = false;
						System.out.println("Thanks for visiting! See you next time.");
					}
				}
				
				// if they logged in or were already logged in
				if (user != null) {
					System.out.println("Welcome, what would you like to do next?");
					// TODO show different menu for admin role?
					System.out.println("1. View available pets\n"
							+ "2. View my pets\n"
							+ "x. Log out");
					String input = scanner.nextLine();
					
					switch (input) {
					case "1":
						viewAllPets(user);
						break;
					case "2":
						viewMyPets(user);
						break;
					default:
						System.out.println("Logging out.");
						user = null;
					}
				}
			}
			scanner.close();
		}
		
		private static User logIn() {
			boolean loggingIn = true;
			
			while (loggingIn) {
				System.out.println("Enter your username: ");
				String username = scanner.nextLine();
				System.out.println("Enter your password: ");
				String password = scanner.nextLine();
				
				User user = userService.logIn(username, password);
				
				if (user==null) {
					System.out.println("Hmm, we couldn't find a user matching those credentials.");
					System.out.println("Do you want to try again? y/n");
					String input = scanner.nextLine().toLowerCase();
					// if they did not say "yes" to trying again
					if (!("y".equals(input))) {
						loggingIn = false;
					}
				} else {
					return user;
				}
			}
			return null;
		}

		private static void register() {
			boolean registering = true;
			
			while (registering) {
				System.out.println("Enter a username: ");
				String username = scanner.nextLine();
				System.out.println("Enter a password: ");
				String password = scanner.nextLine();
				
				System.out.println("Type \"y\" to confirm, \"n\" to try again, or something "
						+ "else to go back.");
				String input = scanner.nextLine().toLowerCase();
				
				switch (input) {
				case "y":
					User user = new User(username, password);
					try {
						userService.registerUser(user);
						registering = false;
						System.out.println("Success!");
					} catch (UsernameAlreadyExistsException e) {
						System.out.println("Oh no, a user with that username already exists. "
								+ "Let's try again.");
					}
					break;
				case "n":
					System.out.println("Okay, let's try again.");
					break;
				default:
					System.out.println("Okay, let's go back.");
					registering = false;
				}
			}
		}
		
		private static User viewAllPets(User user) {
			List<Pet> pets = userService.viewAllPets();
			
			System.out.println("Available pets:");
			System.out.println(pets);
			
			System.out.println("If you want to adopt a pet, enter its ID. "
					+ "Enter anything else to go back.");
			String input = scanner.nextLine();
			Integer id = -1;
			try {
				id = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Okay, let's go back, then.");
				return user;
			}
			
			for (int i = 0; i < pets.size(); i++) {
				Pet pet = pets.get(i);
				if (pet.getId() == id.intValue()) {
					System.out.println("Adopt " + pet.getName() + "? y/n");
					input = scanner.nextLine().toLowerCase();
					if ("y".equals(input)) {
						try {
							user = userService.adoptPet(pet, user);
						} catch (AlreadyAdoptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("Thanks for adopting " + pet.getName() + "! "
								+ "They're so excited!");
					} else {
						System.out.println("Okay, not this time.");
					}
					break;
				}
			}
			return user;
		}
		
		private static void viewMyPets(User user) {
			List<Pet> pets = user.getPets();
			
			System.out.println("Your pets:");
			System.out.println(pets);
		}
}
