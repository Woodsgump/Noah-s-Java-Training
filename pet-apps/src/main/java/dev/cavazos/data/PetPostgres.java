package dev.cavazos.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dev.cavazos.ds.ArrayList;
import dev.cavazos.ds.List;
import dev.cavazos.models.Pet;
import dev.cavazos.models.Species;
import dev.cavazos.models.Status;
import dev.cavazos.utils.ConnectionUtil;

public class PetPostgres implements PetDAO {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Pet create(Pet pet) {
		try (Connection conn = connUtil.getConnection()) {
			// because this is a transaction (DML), we'll start by
			// setting autocommit to false
			conn.setAutoCommit(false);
			
			String sql = "insert into pet "
					+ "(id, name, age, species_id, description, status_id) "
					+ "values (default, ?, ?, ?, ?, ?)";
			// when inserting, we want to retrieve the ID that was generated
			// so we need to specify which column(s) are autogenerated
			String[] keys = {"id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, pet.getName());
			stmt.setInt(2, pet.getAge());
			stmt.setInt(3, pet.getSpecies().getId());
			stmt.setString(4, pet.getDescription());
			stmt.setInt(5, 1); // set as Available
			
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected==1) {
				pet.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pet;
	}

	// DQL Demonstration
	@Override
	public Pet findByID(int id) {
			Pet pet = null;
		
		// try-with-resources: sets up closing for closeable resources
		try (Connection conn = connUtil.getConnection()) {
			// set up the SQL statement that we want to execute
			String sql = "select pet.id, " 
					+ "pet.name, " 
					+ "age, " 
					+ "pet.description, "
					+ "status.name as status_name, " 
					+ "species.name as species_name " 
					+ "from pet "
					+ "join species on pet.species_id = species.id " 
					+ "join status on pet.status_id = status.id "
					+ "where pet.id = ?";

			// set up that statement with the database
			// preparedstatement is pre-processed to prevent sql injection
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id); // parameter indexes start at 1 (the first ?)

			// execute the statement
			ResultSet resultSet = stmt.executeQuery();

			// process the result set
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String description = resultSet.getString("description");

				Status status = new Status(resultSet.getInt("status_id"),
						resultSet.getString("status_name"));
				
				Species species = new Species(
								resultSet.getInt("species_id"),
								resultSet.getString("species_name"),
								resultSet.getString("species_description"));

				pet = new Pet(name, age, species, description);
				pet.setId(id);
				pet.setStatus(status);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pet;
	}

	//DQL
	@Override
	public List<Pet> findAll() {
		List<Pet> allPets = new ArrayList<>();

		// try-with-resources: sets up closing for closeable resources
		try (Connection conn = connUtil.getConnection()) {
			// set up the SQL statement that we want to execute
			String sql = "select pet.id, " 
					+ "pet.name, " 
					+ "age, " 
					+ "pet.description, "
					+ "status.id as status_id, "
					+ "status.name as status_name, "
					+ "species.id as species_id, " 
					+ "species.name as species_name, "
					+ "species.description as species_description " 
					+ "from pet "
					+ "join species on pet.species_id = species.id " + "join status on pet.status_id = status.id";

			// set up that statement with the database
			Statement stmt = conn.createStatement();

			// execute the statement
			ResultSet resultSet = stmt.executeQuery(sql);

			// process the result set
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String description = resultSet.getString("description");
				
				Status status = new Status(resultSet.getInt("status_id"),
						resultSet.getString("status_name"));
				
				Species species = new Species(
						resultSet.getInt("species_id"),
						resultSet.getString("species_name"),
						resultSet.getString("species_description"));

				Pet pet = new Pet(name, age, species, description);
				pet.setId(id);
				pet.setStatus(status);

				allPets.add(pet);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allPets;
	}


	//DML
	@Override
	public void update(Pet t) {
		// TODO Auto-generated method stub
		
	}

	//DML
	@Override
	public void delete(Pet t) {
		// TODO Auto-generated method stub
		
	}

	//DML
	@Override
	public List<Pet> findByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
