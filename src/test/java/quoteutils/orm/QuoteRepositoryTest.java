package quoteutils.orm;

import jakarta.persistence.EntityManager;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import quoteutils.Lang;
import quoteutils.Quote;
import quoteutils.templateutils.XmlQuote;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@SpringBootTest(classes={web.AppRunner.class})

public class QuoteRepositoryTest {
	
	@Autowired
	private EntityManager em;
	
    @Autowired
    private QuoteRepository quotes;
    
    private QuoteResourceAdapter rep;
        
    @BeforeEach
    public void init(){
    	
    	rep = new QuoteResourceAdapter(quotes);
    	
    	Quote q = new Quote();
    	q.setLang(Lang.ENGLISH);
    	q.setPerson("SomePerson");
    	q.setText("This is quote text");
    	q.setUser("TestUser");
    	em.persist(q);
    	
    	Quote q2 = new Quote();
    	q2.setLang(Lang.RUSSIAN);
    	q2.setPerson("Автор текста");
    	q2.setText("Тест Тест Тест");
    	q2.setUser("TestUser");
    	em.persist(q2);
    	
    	Quote q3 = new Quote();
    	q3.setLang(Lang.RUSSIAN);
    	q3.setPerson("Автор текста2");
    	q3.setText("Тест Тест Тест совсем другой текст");
    	q3.setUser("testUserWithTestUsername");
    	em.persist(q3);
    	
    	Quote q4 = new Quote();
    	q4.setLang(Lang.ENGLISH);
    	q4.setPerson("SomePerson");
    	q4.setText("This is quote text in English posted by testUserWithTestUsername");
    	q4.setUser("testUserWithTestUsername");
    	em.persist(q4);
    }
   
    @Test
    public void testGetQuote() { 	
    	Quote q = rep.getQuote(1);
        assertThat(q).hasFieldOrPropertyWithValue("text", "This is quote text");
        
        assertThatThrownBy(() -> rep.getQuote(50))
    	.isInstanceOf(IllegalArgumentException.class)
    	.hasNoCause();
    }
    
    @Test
    public void testGetRandomQuotes(){
    	HashSet<Quote> quotes = rep.getRandomQuotes(3);
    	assertThat(quotes).hasSize(3);
    }
	
	@Test
	public void getQuotesInLang(){
		HashSet<Quote> quotes = rep.getQuotesInLang(Lang.ENGLISH, 2);
    	assertThat(quotes).hasSize(2);
    	assertThat(quotes).extracting("lang").containsOnly(Lang.ENGLISH);
	}
	
	@Test
	public void getUserQuotes(){
		HashSet<Quote> quotes = rep.getUserQuotes("testUserWithTestUsername");
		assertThat(quotes).hasSize(2);
		assertThat(quotes).extracting("user").containsOnly("testUserWithTestUsername");
	}
	
	@Test
	public void addQuote(){
		rep.addQuote("Most fresh quote", "Quote Autor", Lang.ENGLISH, "uniqueUser");
		
		HashSet<Quote> q = rep.getUserQuotes("uniqueUser");
		assertThat(q).hasSize(1);
		assertThat(q).extracting("text", "person", "lang", "user")
		.containsExactly(new Tuple("Most fresh quote", "Quote Autor", Lang.ENGLISH, "uniqueUser"));	
	}
	
	@Test
	public void addQuoteFail(){
		rep.addQuote("Test text for same quote text", "AutorOne", Lang.ENGLISH, "someUser");
		//Add quote with same text
		assertThatThrownBy(() -> rep.addQuote("Test text for same quote text", "AutorTwo", Lang.RUSSIAN, "anotherUser"))
    	.isInstanceOf(IllegalArgumentException.class)
    	.hasNoCause();
	}
	
	@Test
	public void addQuotes(){
	HashSet<XmlQuote> quotes = new HashSet<>();
	String user = "anotherUniqueUser";
	
	XmlQuote q = new XmlQuote();
	q.setText("Тест текст ХМЛ1");
	q.setPerson("Автор текста1");
	q.setLang(Lang.RUSSIAN);
	
	XmlQuote q2 = new XmlQuote();
	q2.setText("Xml test 2");
	q2.setPerson("Xml person2");
	q2.setLang(Lang.ENGLISH);
	
	quotes.add(q);
	quotes.add(q2);
	
	rep.addQuotes(quotes, user);
	HashSet<Quote> result = rep.getUserQuotes(user);
	assertThat(result).hasSize(2);
	}
	
}
