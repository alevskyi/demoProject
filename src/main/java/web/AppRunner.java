package web;

import javax.persistence.AttributeConverter;
import javax.sql.DataSource;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import quoteutils.Lang;
import quoteutils.XmlQuoteResource;
import quoteutils.jdbc.QuoteJdbcRepository;
import quoteutils.orm.LangConverter;
import quoteutils.orm.QuoteRepository;
import quoteutils.orm.QuoteResourceAdapter;
import userutils.UserData;
import userutils.jdbc.UsersJdbcRepository;
import userutils.orm.UsersAdapter;
import userutils.orm.UsersRepository;
import web.quote.QuoteResource;
import web.user.Users;

@SpringBootApplication
@Configuration
@EntityScan(basePackageClasses={quoteutils.Quote.class, userutils.User.class})
@EnableJpaRepositories(basePackageClasses={userutils.orm.UsersRepository.class, quoteutils.orm.QuoteRepository.class})
public class AppRunner{
	
	@Autowired
	private DataSource data;
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private QuoteRepository quoteRepository; 
	
	@Profile("xmlQuotes")
	@Bean
	public QuoteResource quoteResource(){
		return new XmlQuoteResource();
	}
	
	@Profile("ormQuotes")
	@Bean
	public QuoteResource ormQuoteResource(){
		return new QuoteResourceAdapter(quoteRepository);
	}
	
	@Profile("ormQuotes")
	@Bean
	public AttributeConverter<Lang, String> langConverter(){
		return new LangConverter();
	}
	
	@Profile("jdbcQuotes")
	@Bean
	public QuoteResource jdbcQuoteResource(){
		return new QuoteJdbcRepository(data);
	}
	
	@Profile("xmlUsers")
	@Bean
	public Users users(){
		return new UserData();
	}
	
	@Profile("ormUsers")
	@Bean
	public Users ormUsers(){
		return new UsersAdapter(userRepository);
	}
	
	@Profile("jdbcUsers")
	@Bean
	public Users jdbcUsers(){
		return new UsersJdbcRepository(data);
	}
	
	@Profile("test")
	@Bean
	public QuoteResource mockResource(){
		return Mockito.mock(QuoteResource.class);	
	}
	
	@Profile("test")
	@Bean
	public Users mockUsers(){
		return Mockito.mock(Users.class);
	}
	
	 public static void main(String[] args){
		SpringApplication.run(AppRunner.class, args);
	}
	 
}
