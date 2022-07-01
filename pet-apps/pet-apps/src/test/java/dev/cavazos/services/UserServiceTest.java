package dev.cavazos.services;

// importing all of the static methods of the Assertion class
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.cavazos.data.PetDAO;
import dev.cavazos.data.UserDAO;
import dev.cavazos.models.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	// object that we're testing
	@InjectMocks
	private UserService userServ = new UserServiceImpl();
	
	// UserService dependencies that we need to mock
	@Mock
	private UserDAO userDao;
	@Mock
	private PetDAO petDao;

	@BeforeEach
	public static void setUp() {
		// set up some mock values, etc.
	}
	
	// @BeforeEach, @AfterEach, @AfterAll
	
	@Test
	public void logInSuccessfully() {
		// setup (inputs, mocks, etc.)
		String username = "test";
		String password = "test";
		
		User mockUser = new User(username, password);
		Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);
		
		// call the method that we're testing
		User returnedUser = userServ.logIn(username, password);
		
		// assertion (checking for expected behavior)
		assertEquals(username, returnedUser.getUsername());
	}
	
	@Test
	public void logInUsernameDoesntExist() {
		// setup (inputs, mocks, etc.)
		String username = "wrong";
		String password = "test";
				
		Mockito.when(userDao.findByUsername(username)).thenReturn(null);
				
		// call the method that we're testing
		User returnedUser = userServ.logIn(username, password);
				
		// assertion (checking for expected behavior)
		assertNull(returnedUser);
	}
	
	@Test
	public void logInWrongPassword() {
		// setup (inputs, mocks, etc.)
		String username = "test";
		String password = "wrong";
		
		User mockUser = new User(username, "test");
		Mockito.when(userDao.findByUsername(username)).thenReturn(mockUser);
						
		// call the method that we're testing
		User returnedUser = userServ.logIn(username, password);
						
		// assertion (checking for expected behavior)
		assertNull(returnedUser);	
	}
	
}