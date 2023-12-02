package ua.training.quotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Configuration
//@EntityScan(basePackageClasses={QuoteList.class, User.class})
//@EnableJpaRepositories(basePackageClasses={UsersRepository.class, QuoteRepository.class})
public class Application {
	
//	@Autowired
//	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//	@Autowired
//	private UsersRepository userRepository;
//
//	@Autowired
//	private QuoteRepository quoteRepository;
//
//	@Profile("xmlQuotes")
//	@Bean
//	public QuoteResource quoteResource(){
//		return new XmlQuoteResource();
//	}
//
//	@Profile("ormQuotes")
//	@Bean
//	public QuoteResource ormQuoteResource(){
//		return new QuoteResourceAdapter(quoteRepository);
//	}
//
//	@Profile("ormQuotes")
//	@Bean
//	public AttributeConverter<Lang, String> langConverter(){
//		return new LangConverter();
//	}
//
//	@Profile("jdbcQuotes")
//	@Bean
//	public QuoteResource jdbcQuoteResource(){
//		return new QuoteJdbcRepository(namedParameterJdbcTemplate);
//	}
//
//	@Profile("xmlUsers")
//	@Bean
//	public Users users(){
//		return new UserData();
//	}
//
//	@Profile("ormUsers")
//	@Bean
//	public Users ormUsers(){
//		return new UsersAdapter(userRepository);
//	}
//
//	@Profile("jdbcUsers")
//	@Bean
//	public Users jdbcUsers(){
//		return new UsersJdbcRepository(namedParameterJdbcTemplate);
//	}
	
//	@Profile("test")
//	@Bean
//	public QuoteResource mockResource(){
//		return Mockito.mock(QuoteResource.class);
//	}
	
//	@Profile("test")
//	@Bean
//	public Users mockUsers(){
//		return Mockito.mock(Users.class);
//	}
	
	 public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	 
}
