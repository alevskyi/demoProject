package userutils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserDataTest {
	
	    
    private UserData rep;
       
    @BeforeEach
    public void init(){
    	  	
    	HashSet<User> users = new HashSet<>();
    	
    	User u = new User();
    	u.setUsername("testUser");
    	u.setPasswd("userPasswd");
    	u.setNonLocked(true);
    	u.setPasswordNonExpired(true);
    	u.setAuthorities("ROLE_USER");
    	users.add(u);
    	
    	User u2 = new User();
    	u2.setUsername("testAdmin");
    	u2.setPasswd("adminPasswd");
    	u2.setNonLocked(false);
    	u2.setPasswordNonExpired(false);
    	u2.setAuthorities("ROLE_ADMIN");
    	users.add(u2);    	
    	
    	rep = new UserData(users);
    }
    /*
     * 
     * updateUsers commented out in UserData for testing
     * 
     */
    
    @Test
    public void addUser(){
    	
    	User u = new User();
    	u.setUsername("AddedUser");
    	u.setPasswd("userPasswd");
    	u.setNonLocked(true);
    	u.setPasswordNonExpired(true);
    	u.setAuthorities("ROLE_USER");
    	
    	rep.addUser(u);
    	
    	User user = rep.getUser(u.getUsername());
    	assertThat(user).extracting("username", "passwd", "nonLocked", "passwordNonExpired", "authorities")
    	.containsExactly(u.getUsername(), u.getPasswd(), u.nonLocked(), u.passwordNonExpired(), u.getAuthorities());
    	
    	//Add same user
    	assertThatThrownBy(() -> rep.addUser(u))
    	.isInstanceOf(IllegalArgumentException.class)
    	.hasNoCause();
	}
    
    @Test
	public void getUser(){
    	User user = rep.getUser("testAdmin");
    	assertThat(user).extracting("username", "passwd", "nonLocked", "passwordNonExpired", "authorities")
    	.containsExactly("testAdmin", "adminPasswd", false, false, "ROLE_ADMIN");
    	
    	assertThatThrownBy(() -> rep.getUser("NonExistingUsername"))
    	.isInstanceOf(NoSuchElementException.class)
    	.hasNoCause();
	}
    
    
    @Test
	public void getAllUsers(){
    	 HashSet<User> result = rep.getAllUsers();
    	 assertThat(result).hasSize(2);
    	 assertThat(result).extracting("username")
    	 .containsExactlyInAnyOrder("testUser", "testAdmin");
	}
	
}