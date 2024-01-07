package ua.training.quotes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import ua.training.quotes.persistence.DatabaseQuoteResource;
import ua.training.quotes.persistence.QuoteRepository;
import ua.training.quotes.persistence.QuoteResource;

import javax.sql.DataSource;

@Profile("!inmemory")
@Configuration
@EnableJpaRepositories(basePackages = "ua.training.quotes.persistence")
@ImportResource("classpath:populator.xml")
public class DatabaseStorage {

    @Bean
    public QuoteResource databaseQuoteResource(QuoteRepository repository) {
        return new DatabaseQuoteResource(repository);
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer(){
            @Override
            public void afterPropertiesSet() {
                try {
                    super.afterPropertiesSet();
                } catch (ScriptException e) {
                    e.printStackTrace(System.err);
                }
            }
        };
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("users.ddl")));
        return initializer;
    }

    @Bean
    public UserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return  new JdbcUserDetailsManager(dataSource);
    }
}
