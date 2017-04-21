package web.user;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import userutils.User;

//Authenticate users from Users object
@Component
public class UserAuthenticationService implements UserDetailsService{
	
	private Users users;
	
	public UserAuthenticationService(Users users){
		this.users = users;
	}
	
	private static Collection<? extends GrantedAuthority> authoritiesFromString(String s){
		String[] auth = s.split(" ");
		List<GrantedAuthority> autorities = new LinkedList<>();
		for(String a : auth){
			autorities.add(new SimpleGrantedAuthority(a));
		}
		return autorities;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user;
		try {
			 User u = users.getUser(username);
			 user = new org.springframework.security.core.userdetails.User(u.getUsername(),
					 u.getPasswd(), 
					 //account always enabled
					 true, 
					//account always not expired
					 true, 
					 //password may expire
					 u.passwordNonExpired(), 
					//account may be locked
					 u.nonLocked(),
					 //read authorities: ROLE_USER or ROLE_ADMIN
					 authoritiesFromString(u.getAuthorities()));
		}
		catch (NoSuchElementException e) {
			throw new UsernameNotFoundException("User with username '" + username + "' not found.", e);
		}
		return user;
	}

}
