package userutils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import web.user.Users;

public class UserData implements Users{
		
	protected static HashSet<User> users;
	
	public UserData(){
		users = Unmarshall.read();
	}
	//For testing
	public UserData(HashSet<User> users){
		UserData.users = users;
	}
	
	private void updateUsers(){
		Marshall.write();
		users = Unmarshall.read();
	}
	
	@Override
	public HashSet<User> getAllUsers(){
		return users;
	}

	@Override
	public User getUser(String username) {
		Iterator<User> i = UserData.users.iterator();
		User u;
		while(i.hasNext()){
			u = i.next();
			if(u.getUsername().equals(username)) return u;
		}
		throw new NoSuchElementException("User with username " + username + " not found.");
	}

	@Override
	public void addUser(User user) {
		boolean result = UserData.users.add(user);
		if(! result) throw new IllegalArgumentException("User with username '" + user.getUsername() + 
				"' already exists");
		else updateUsers();
	}

}
