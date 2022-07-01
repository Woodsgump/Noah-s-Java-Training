package dev.cavazos.services;

import dev.cavazos.data.PetDAO;
import dev.cavazos.data.PetPostgres;
import dev.cavazos.data.StatusDAO;
import dev.cavazos.data.UserDAO;
import dev.cavazos.data.UserPostgres;
import dev.cavazos.ds.List;
import dev.cavazos.exceptions.AlreadyAdoptedException;
import dev.cavazos.exceptions.UsernameAlreadyExistsException;
import dev.cavazos.models.Pet;
import dev.cavazos.models.Status;
import dev.cavazos.models.User;

public class UserServiceImpl implements UserService {
	private UserDAO userDao = new UserPostgres();
	private PetDAO petDao = new PetPostgres();
	private StatusDAO statusDao;

	@Override
	public User registerUser(User user) throws UsernameAlreadyExistsException {
		user = userDao.create(user);
		if (user == null) {
			throw new UsernameAlreadyExistsException();
		}
		return user;
	}

	@Override
	public User logIn(String username, String password) {
		User user = userDao.findByUsername(username);
		if(user != null && password.equals(user.getPassword())) {
			return user;
		} else {
			return null;
		}
	}

	@Override
	public List<Pet> viewAllPets() {
		Status availableStatus = statusDao.findByName("Available");
		return petDao.findByStatus(availableStatus);
	}

	@Override
	public User adoptPet(Pet pet, User user) throws AlreadyAdoptedException {
		if( user == null || pet == null) {
			return null;
		}
		if("Adopted".equals(pet.getStatus().getName())) {
			throw new AlreadyAdoptedException();
		}
		
		Status adoptedStatus = statusDao.findByName("Adopted");
		return null;
	}

}
