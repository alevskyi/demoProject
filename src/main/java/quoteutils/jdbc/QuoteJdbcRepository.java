package quoteutils.jdbc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import quoteutils.Lang;
import quoteutils.Quote;
import web.quote.QuoteResource;
import quoteutils.templateutils.XmlQuote;

public class QuoteJdbcRepository implements QuoteResource{
	
	private NamedParameterJdbcTemplate repository;
	
	private BeanPropertyRowMapper<Quote> mapper;
	
	private static final String randomQuotesSql = "SELECT * FROM QUOTE ORDER BY RAND() LIMIT :amount";
	private static final String quoteSql = "SELECT * FROM QUOTE WHERE id=:id";
	private static final String quotesInLangSql = "SELECT * FROM Quote WHERE lang=:lang ORDER BY RAND() LIMIT :amount";
	private static final String quotesByUsernameSql = "SELECT * FROM Quote WHERE user=:username";
	private static final String addQuoteSql = "INSERT INTO QUOTE (lang, person, text, user) VALUES (:lang, :person, :text, :user)";
	
	private static final String checkSql = "SELECT * FROM QUOTE WHERE text=:text";

	public QuoteJdbcRepository(DataSource data){
		this.repository = new NamedParameterJdbcTemplate(data);
		mapper = new BeanPropertyRowMapper<>(Quote.class);
		mapper.setConversionService(new LangConverter());
	}
			
	private HashSet<Quote> listToMap(List<Quote> quoteList){
		
		HashSet<Quote> result = new HashSet<>();
		Iterator<Quote> i = quoteList.iterator();
		while(i.hasNext()){
			result.add(i.next());
		}
		return result;
	}
	
	@Override
	public HashSet<Quote> getRandomQuotes(int amount) {
				
		Map<String, Object> params = new HashMap<>();
		params.put("amount", amount);
		List<Quote> quoteList = repository.query(randomQuotesSql, params, mapper);	
		return listToMap(quoteList);
	}

	@Override
	public Quote getQuote(int id) {
			
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		try{
			return repository.queryForObject(quoteSql, params, mapper);
		}
		catch(EmptyResultDataAccessException e){
			throw new IllegalArgumentException("No quote with id " + id + " found"); 
		}
	}

	@Override
	public HashSet<Quote> getQuotesInLang(Lang lang, int amount) {
				
		Map<String, Object> params = new HashMap<>();
		params.put("lang", lang.value());
		params.put("amount", amount);
		List<Quote> quoteList = repository.query(quotesInLangSql, params, mapper);
		return listToMap(quoteList);
	}

	@Override
	public HashSet<Quote> getUserQuotes(String username) {		
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		List<Quote> quoteList = repository.query(quotesByUsernameSql, params, mapper);
		return listToMap(quoteList);
	}

	@Override
	public void addQuote(String text, String person, Lang lang, String username) {
		Map<String, Object> params = new HashMap<>();
		params.put("text", text);
		try{
			repository.queryForObject(checkSql, params, mapper);
			throw new IllegalArgumentException("Quote already exists");
		}
		catch(EmptyResultDataAccessException e){
			 
		}		
		params.put("lang", lang.value());
		params.put("person", person);
		params.put("user", username);
		
		repository.update(addQuoteSql, params);
	}

	@Override
	public void addQuotes(HashSet<XmlQuote> quotes, String username) {
		Iterator<XmlQuote> i = quotes.iterator();
		
		while(i.hasNext()){
			XmlQuote quote = i.next();
			addQuote(quote.getText(), quote.getPerson(), quote.getLang(), username);
		}
		
	}

}
