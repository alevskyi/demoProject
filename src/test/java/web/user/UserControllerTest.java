package web.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import userutils.User;
import web.AppRunner;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.NoSuchElementException;

class UserMatcher implements ArgumentMatcher<UserRegistration> {
	
	private String username;
	
	public UserMatcher(String username){
		this.username = username;
	}

	@Override
	public boolean matches(UserRegistration argument) {
		if(argument.getUsername().equals(username))
			return true;
		else
			return false;
	}
	
}


@ActiveProfiles("test")

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AppRunner.class)
@AutoConfigureMockMvc

public class UserControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private Users users;
	
	@Before
	public void init(){
		UserMatcher existing = new UserMatcher("existingUser");
		UserMatcher invalid = new UserMatcher("invalidUser");
		
		User u = new User("testUser", "testPass", true, true, "ROLE_USER");
				
		doThrow(new IllegalArgumentException("User exist already")).when(users).addUser(argThat(existing));
		when(users.getUser("testUser")).thenReturn(u);
		doThrow(new NoSuchElementException("User not found")).when(users).addUser(argThat(invalid));
		
	}
	
	@Test
	public void userListFail() throws Exception{
		mvc.perform(get("/userlist"))
		.andExpect(status().is(302));			
	}
	
	@WithMockUser
	@Test
	public void userListAuthenticatedFail() throws Exception{
		mvc.perform(get("/userlist"))
		.andExpect(status().is(403));				
	}
	
	@WithMockUser(authorities={"ROLE_ADMIN"})
	@Test
	public void userList() throws Exception{
		mvc.perform(get("/userlist"))
		.andExpect(status().is(200));
	}
	
	
	@Test
	public void profileAnuathorizedFail() throws Exception{
		mvc.perform(get("/users/testUser"))
		.andExpect(status().is(302));
	}
	
	@WithMockUser(username="someUser")
	@Test
	public void profileWrongUserFail() throws Exception{
		mvc.perform(get("/users/testUser"))
		.andExpect(status().is(403));
	}
	
	@WithMockUser(username="testUser")
	@Test
	public void profileSuccess() throws Exception{
		mvc.perform(get("/users/testUser"))
		.andExpect(status().is(200))
		.andExpect(model().attributeExists("xmlQuote", "quotes"));		
	}
		
	@Test
	public void register() throws Exception{
		mvc.perform(get("/register"))
		.andExpect(view().name("register"));
	}
	
	@WithMockUser(username="testUser")
	@Test
	public void registerRedirect() throws Exception{
		mvc.perform(get("/register"))
		.andExpect(redirectedUrl("/users/testUser"));
	}
	
	@Test
	public void processRegistrationInvalid() throws Exception{
		mvc.perform(post("/register")
				.param("username", "a")
				.param("passwd", "b")
				.param("confirmPasswd", "c")
				.with(csrf()))
		.andExpect(status().is(200))
		.andExpect(model().attributeHasFieldErrors("userRegistration", "username", "passwd", "passwordsMatch"));
	}
	
		
	@Test
	public void processRegistrationExisting() throws Exception{
		mvc.perform(post("/register")
				.param("username", "existingUser")
				.param("passwd", "validpass")
				.param("confirmPasswd", "validpass")
				.with(csrf()))
		.andExpect(view().name("register"))
		.andExpect(model().attributeHasFieldErrors("userRegistration", "username"));
	}
	
	@Test
	public void processRegistrationSuccess() throws Exception{
		mvc.perform(post("/register")
				.param("username", "uniqueUser")
				.param("passwd", "validpass")
				.param("confirmPasswd", "validpass")
				.with(csrf()))
		.andExpect(redirectedUrl("/users/uniqueUser"));
	}
	
	@Test
	public void login() throws Exception{ 
		mvc.perform(post("/login")
				.param("username", "testUser")
				.param("password", "testPass")
				.param("remember", "true")
				.with(csrf()))
	    .andExpect(authenticated().withUsername("testUser"));
	}
	
	@Test
	public void loginError() throws Exception{ 
		mvc.perform(post("/login")
				.param("username", "invalidUser")
				.param("password", "testPass")
				.param("remember", "true")
				.with(csrf()))
	    .andExpect(redirectedUrl("/login?error"));
	}
	
	@Test
	public void loginErrorRedirect() throws Exception{
		mvc.perform(get("/login")
				//value doesn't matter
				.param("error", ""))
		.andExpect(view().name("login"))
		.andExpect(model().attribute("error", 
				"Incorrect username or password. Please, try again."));
	}
		
	@WithMockUser("testUser")
	@Test
	public void loginAsAuthenticated() throws Exception{
		mvc.perform(get("/login")
				.param("error", ""))
		.andExpect(redirectedUrl("/users/testUser"));
	}
	
	
	@WithMockUser("testUser")
	@Test
	public void logout() throws Exception{ 
		mvc.perform(get("/logout"))
		.andExpect(unauthenticated());
	}
	
}
