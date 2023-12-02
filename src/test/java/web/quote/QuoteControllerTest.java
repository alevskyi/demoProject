package web.quote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import ua.training.quotes.Application;
import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;
import ua.training.quotes.model.XmlQuote;
import ua.training.quotes.persistence.quote.QuoteResource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")

@SpringBootTest(classes= Application.class)
@AutoConfigureMockMvc

public class QuoteControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private QuoteResource quotes;
		
	@BeforeEach
	public void init(){
		
	Quote q = new Quote("QuoteList text","quote person", Lang.RUSSIAN, "testUser");
	when(quotes.getQuoteById(50)).thenThrow(new IllegalArgumentException());
	when(quotes.getQuoteById(5)).thenReturn(q);
	doThrow(new IllegalArgumentException("QuoteList already exists")).when(quotes).addQuote("Same quote test text",
			"TestPerson", Lang.ENGLISH, "sameTestUser");
	}
		
	@Test
	public void randomTest() throws Exception{
		mvc.perform(get("/quotes/random?amount=0"))
		.andExpect(redirectedUrl("random?amount=3"));
		
		mvc.perform(get("/quotes/random?amount=10"))
		.andExpect(redirectedUrl("random?amount=3"));
		
		mvc.perform(get("/quotes/random"))
				.andExpect(view().name("random"));
		
		verify(quotes, times(1)).getRandomQuotes(3);
	}
	
	@Test
	public void byIdTest() throws Exception{
		Exception e = mvc.perform(get("/quotes/id/50")).andReturn().getResolvedException();
		assertThat(e).isInstanceOf(HttpClientErrorException.class);
		
		mvc.perform(get("/quotes/id/5"))
		.andExpect(view().name("specific"));
		
		verify(quotes, times(1)).getQuoteById(5);
	}
	
	@Test
	public void randomInLangTest() throws Exception{
		
		mvc.perform(get("/quotes/ru?amount=0"))
		.andExpect(redirectedUrl("ru?amount=2"));
		
		mvc.perform(get("/quotes/ru?amount=10"))
		.andExpect(redirectedUrl("ru?amount=2"));
		
		mvc.perform(get("/quotes/en"))
				.andExpect(view().name("random"));
		
		verify(quotes, times(1)).getQuotesInLang(Lang.ENGLISH, 2);
	}
	
	@Test
	public void newTestFail() throws Exception{
		
		XmlQuote xmlQuote = new XmlQuote();
		xmlQuote.setLang(Lang.ENGLISH);
		xmlQuote.setPerson("TestPerson");
		xmlQuote.setText("This is test quote text");
		
		mvc.perform(post("/quotes/new")
				.param("person", xmlQuote.getPerson())
				.param("text", xmlQuote.getText())
				.param("lang", "ENGLISH")
				.with(csrf())).andExpect(status().is(302));	
		
		mvc.perform(post("/quotes/new")
				.param("person", xmlQuote.getPerson())
				.param("text", xmlQuote.getText())
				.param("lang", "ENGLISH")).andExpect(status().is(403));	
	}
	
	
	@Test
	@WithMockUser("testUser")
	public void newTestError() throws Exception{
		
		XmlQuote invalidQuote = new XmlQuote();
		invalidQuote.setLang(Lang.ENGLISH);
		invalidQuote.setPerson("");
		invalidQuote.setText("");
		
		mvc.perform(post("/quotes/new")
				.param("person", invalidQuote.getPerson())
				.param("text", invalidQuote.getText())
				.param("lang", "ENGLISH")
				.with(csrf()))
		.andExpect(view().name("profile"));	
		
	}
	
	@Test
	@WithMockUser("testUser")
	public void newTest() throws Exception{
		
		XmlQuote xmlQuote = new XmlQuote();
		xmlQuote.setLang(Lang.ENGLISH);
		xmlQuote.setPerson("TestPerson");
		xmlQuote.setText("This is test quote text");
		
		mvc.perform(post("/quotes/new")
				.param("person", xmlQuote.getPerson())
				.param("text", xmlQuote.getText())
				.param("lang", "ENGLISH")
				.with(csrf()))
		.andExpect(redirectedUrl("/users/testUser"));
		
		verify(quotes, times(1)).addQuote(xmlQuote.getText(), xmlQuote.getPerson(),
				xmlQuote.getLang(), "testUser");
	}
	
	@Test
	@WithMockUser("sameTestUser")
	public void newSameQuoteTest() throws Exception{
		
		XmlQuote quote = new XmlQuote();
		quote.setLang(Lang.ENGLISH);
		quote.setPerson("TestPerson");
		quote.setText("Same quote test text");
				
		mvc.perform(post("/quotes/new")
				.param("person", quote.getPerson())
				.param("text", quote.getText())
				.param("lang", "ENGLISH")
				.with(csrf()))
		.andExpect(model().attributeHasFieldErrors("xmlQuote", "text"))
		.andExpect(view().name("profile"));
		
	}

}
