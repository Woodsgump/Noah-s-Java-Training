package dev.cavazos.data;

import dev.cavazos.models.Status;

public interface StatusDAO extends DataAccessObject<Status> {
	public Status findByName(String name);
}