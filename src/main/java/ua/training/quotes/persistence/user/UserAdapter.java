//package ua.training.quotes.persistence.user;
//
//import java.util.HashSet;
//import java.util.NoSuchElementException;
//
//import ua.training.quotes.model.User;
//
//public class UserAdapter implements UserResource {
//
//	private UserRepository repository;
//
//	public UserAdapter(UserRepository repository){
//		this.repository = repository;
//	}
//
//	public void addUser(User user){
//		if(repository.findByUsername(user.getUsername()) != null)
//			throw new IllegalArgumentException("User with username '" + user.getUsername() +
//					"' already exists");
//		else
//			repository.save(user);
//	}
//
//	public User getUser(String username){
//		User user = repository.findByUsername(username);
//		if(user == null)
//			throw new NoSuchElementException("User with username " + username + " not found.");
//		else
//			return user;
//	}
//
//	public HashSet<User> getAllUsers(){
//		return repository.getAllUsers();
//	}
//}
