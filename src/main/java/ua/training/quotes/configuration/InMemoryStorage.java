package ua.training.quotes.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import ua.training.quotes.persistence.InMemoryQuoteResource;
import ua.training.quotes.persistence.QuoteResource;

@Profile("inmemory")
@Configuration
public class InMemoryStorage {

    @Bean
    public QuoteResource quoteResource(ObjectMapper objectMapper) {
        return new InMemoryQuoteResource(objectMapper);
    }

    @Bean
    public UserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(User.withUsername("testUser")
                .password("1234")
                .build());
    }
}
