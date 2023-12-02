package quoteutils;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;
import ua.training.quotes.model.XmlQuote;
import ua.training.quotes.persistence.quote.XmlQuoteResource;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class XmlQuoteResourceTest {

	@Autowired
    private XmlQuoteResource rep;
        
    @BeforeEach
    public void init(){
    	
    	HashSet<Quote> quotes = new HashSet<>();
    	
    	Quote q = new Quote();
    	q.setId(1);
    	q.setLang(Lang.ENGLISH);
    	q.setPerson("SomePerson");
    	q.setText("This is quote text");
    	q.setUser("TestUser");
    	quotes.add(q);
    	
    	Quote q2 = new Quote();
    	q2.setId(2);
    	q2.setLang(Lang.RUSSIAN);
    	q2.setPerson("Автор текста");
    	q2.setText("Тест Тест Тест");
    	q2.setUser("TestUser");
    	quotes.add(q2);
    	
    	Quote q3 = new Quote();
    	q3.setId(3);
    	q3.setLang(Lang.RUSSIAN);
    	q3.setPerson("Автор текста2");
    	q3.setText("Тест Тест Тест совсем другой текст");
    	q3.setUser("testUserWithTestUsername");
    	quotes.add(q3);
    	
    	Quote q4 = new Quote();
    	q4.setId(4);
    	q4.setLang(Lang.ENGLISH);
    	q4.setPerson("SomePerson");
    	q4.setText("This is quote text in English posted by testUserWithTestUsername");
    	q4.setUser("testUserWithTestUsername");
    	quotes.add(q4);
    	
    	rep = new XmlQuoteResource(quotes); 	
    	
    	/*
    	 * 
    	 * Quotes update commented out for testing in XmlQuoteResource
    	 * 
    	 */
    }
        
    @Test
    public void testGetQuote() {
    	Quote q = rep.getQuoteById(1);
        assertThat(q).hasFieldOrPropertyWithValue("text", "This is quote text");
        
        assertThatThrownBy(() -> rep.getQuoteById(50))
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
		rep.addQuote("Most fresh quote", "QuoteList Autor", Lang.ENGLISH, "uniqueUser");
		
		HashSet<Quote> q = rep.getUserQuotes("uniqueUser");
		assertThat(q).hasSize(1);
		assertThat(q).extracting("text", "person", "lang", "user")
		.containsExactly(new Tuple("Most fresh quote", "QuoteList Autor", Lang.ENGLISH, "uniqueUser"));
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
