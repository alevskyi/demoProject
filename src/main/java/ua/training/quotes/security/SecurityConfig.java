package ua.training.quotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String REMEMBER_ME_KEY = "someString";

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                                          SecurityContextRepository securityContextRepository,
                                                          RememberMeServices rememberMeServices) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/auth/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/quote/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/quote/user/**").authenticated()
                .anyRequest().permitAll());
        http.securityContext(c -> c.securityContextRepository(securityContextRepository));
        http.csrf(AbstractHttpConfigurer::disable);
        http.requestCache(AbstractHttpConfigurer::disable);
        http.logout(c -> {
            AntPathRequestMatcher matcher = new AntPathRequestMatcher("/logout");
            c.logoutRequestMatcher(matcher)
                    .clearAuthentication(true)
                    .defaultLogoutSuccessHandlerFor(new HttpStatusReturningLogoutSuccessHandler(), matcher)
                    .deleteCookies("JSESSIONID");
        });
        http.rememberMe(c -> c.rememberMeServices(rememberMeServices));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {

        DaoAuthenticationProvider loginAndPasswordProvider = new DaoAuthenticationProvider();
        loginAndPasswordProvider.setUserDetailsService(userDetailsService);
        loginAndPasswordProvider.setPasswordEncoder(passwordEncoder);

        RememberMeAuthenticationProvider rememberMeProvider = new RememberMeAuthenticationProvider(REMEMBER_ME_KEY);
        return new ProviderManager(loginAndPasswordProvider, rememberMeProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(REMEMBER_ME_KEY, userDetailsService);
        tokenBasedRememberMeServices.setAlwaysRemember(true);
        return tokenBasedRememberMeServices;
    }
}
