package dev.cavazos.data;

import dev.cavazos.models.User;

public interface UserDAO extends DataAccessObject<User> {
	public User findByUsername(String username);
}
