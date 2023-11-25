package userutils.jdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.NoSuchElementException;

import jakarta.persistence.EntityManager;
import jakarta.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import userutils.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes={web.AppRunner.class})

public class UserJdbcRepositoryTest {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private DataSource data;
	       
    private UsersJdbcRepository rep;
             
    @Before
    public void init(){
    	    	    	
    	User u = new User();
    	u.setUsername("testUser");
    	u.setPasswd("userPasswd");
    	u.setNonLocked(true);
    	u.setPasswordNonExpired(true);
    	u.setAuthorities("ROLE_USER");
    	em.persist(u);
    	em.flush();
    	
    	User u2 = new User();
    	u2.setUsername("testAdmin");
    	u2.setPasswd("adminPasswd");
    	u2.setNonLocked(false);
    	u2.setPasswordNonExpired(false);
    	u2.setAuthorities("ROLE_ADMIN");
    	em.persist(u2);
    	em.flush();
    	
    	rep = new UsersJdbcRepository(data);   	
    }
    
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