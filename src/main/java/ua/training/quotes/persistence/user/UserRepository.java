//package ua.training.quotes.persistence.user;
//
//import java.util.HashSet;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import ua.training.quotes.model.User;
//
//@Repository
//public interface UserRepository extends CrudRepository<User, Integer>{
//
//	public User findByUsername(String username);
//
//	@Query(value = "SELECT * FROM User", nativeQuery=true)
//	public HashSet<User> getAllUsers();
//}
