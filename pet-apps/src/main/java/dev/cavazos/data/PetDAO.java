package dev.cavazos.data;

import dev.cavazos.ds.List;
import dev.cavazos.models.Pet;

public interface PetDAO extends DataAccessObject<Pet> {
	public List<Pet> findByStatus(String status);
}
