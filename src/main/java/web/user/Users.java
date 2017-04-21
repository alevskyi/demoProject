package web.user;

import java.util.HashSet;
import java.util.NoSuchElementException;

import userutils.User;

public interface Users {
	
	public void addUser(User user) throws IllegalArgumentException;
		
	public User getUser(String username) throws NoSuchElementException;

	public HashSet<User> getAllUsers();
			
}
