package dev.cavazos.petapp.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;

// object designed to load and save objects to a file
public class FileAccessObject {
	
	public void saveObjects(File file, Object objects) throws FileNotFoundException {
		Serializable serializableObj = null;
		try {
			serializableObj = (Serializable) objects;
		} catch(Exception e) {
			throw new ClassCastException("Object is not Serializable.");
		}
		try (ObjectOutputStream objWriter = 
				new ObjectOutputStream(new FileOutputStream(file));) {
			objWriter.writeObject(serializableObj);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Object readObjects(File file) throws FileNotFoundException {
		Object objects = null;
		try (ObjectInputStream objReader = 
				new ObjectInputStream(new FileInputStream(file))) {
			objects = (Object) objReader.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return objects;

	}
}

