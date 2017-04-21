package userutils.orm;

import java.util.HashSet;
import java.util.NoSuchElementException;

import userutils.User;
import web.user.Users;

public class UsersAdapter implements Users{
	
	private UsersRepository repository;
		
	public UsersAdapter(UsersRepository repository){
		this.repository = repository;
	}
		
	public void addUser(User user){
		if(repository.findByUsername(user.getUsername()) != null)
			throw new IllegalArgumentException("User with username '" + user.getUsername() + 
					"' already exists");
		else
			repository.save(user);		
	}
		
	public User getUser(String username){
		User user = repository.findByUsername(username); 
		if(user == null)
			throw new NoSuchElementException("User with username " + username + " not found.");
		else
			return user; 	
	}
	
	public HashSet<User> getAllUsers(){
		return repository.getAllUsers();
	}
}
