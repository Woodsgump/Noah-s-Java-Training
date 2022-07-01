package dev.cavazos.data;

import dev.cavazos.ds.List;
import dev.cavazos.models.Pet;
import dev.cavazos.models.Status;

public interface PetDAO extends DataAccessObject<Pet> {
	public List<Pet> findByStatus(Status status);
}
