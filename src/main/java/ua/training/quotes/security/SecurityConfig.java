package ua.training.quotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedHeaders("*");
            }
        };
    }
}
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