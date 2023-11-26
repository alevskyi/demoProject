//package web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import org.springframework.security.web.SecurityFilterChain;
//import ua.training.quotes.security.UserAuthenticationService;
//import ua.training.quotes.persistence.Users;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	public Users users;
//
//	@Value("#{'${app.security.tokenValidity}'}")
//	private int tokenValidity;
//
//	@Value("#{'${app.security.cookieName}'}")
//	private String cookieName;
//
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
////        http
//////        .csrf()
//////        .ignoringAntMatchers("/api/**")
//////        	.and()
////        .authorizeRequests()
////        	.regexMatchers("/errors/.*").denyAll()
////        	.regexMatchers("/userlist").hasAuthority("ROLE_ADMIN")
////        	.regexMatchers("/users/.*", "/quotes/xml", "/quotes/new").authenticated()
////        	.anyRequest().permitAll()
////        	.and()
////        .formLogin()
////        	.loginPage("/login")
////        	.permitAll()
////        	.and()
////        .logout()
////        	.logoutUrl("/logout")
////        	.permitAll()
////        	.and()
////        .rememberMe()
////        	.alwaysRemember(false)
////        	.rememberMeCookieName(cookieName)
////        	.rememberMeParameter("remember")
////        	.tokenValiditySeconds(tokenValidity)
////         	.and()
////         .exceptionHandling()
////         	.accessDeniedPage("/errors/403");
////	}
////
////	@Override
////	protected void 	configure(AuthenticationManagerBuilder auth) throws Exception{
////		auth.userDetailsService(new UserAuthenticationService(users));
////	}
//
//}
