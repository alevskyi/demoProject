package userutils.orm;

import java.util.HashSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import userutils.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer>{
	
	public User findByUsername(String username);
	
	@Query(value = "SELECT * FROM User", nativeQuery=true)
	public HashSet<User> getAllUsers();
}
