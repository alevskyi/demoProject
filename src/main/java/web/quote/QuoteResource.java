package web.quote;

import java.util.HashSet;
import quoteutils.Lang;
import quoteutils.Quote;
import quoteutils.templateutils.XmlQuote;

public interface QuoteResource {
	
	public HashSet<Quote> getRandomQuotes(int amount);
			
	public Quote getQuote(int id) throws IllegalArgumentException;
	
	public HashSet<Quote> getQuotesInLang(Lang lang, int amount);
	
	public HashSet<Quote> getUserQuotes(String username);
	
	public void addQuote(String text, String person, Lang lang, String username) throws IllegalArgumentException;
	
	public void addQuotes(HashSet<XmlQuote> quotes, String username);
		
}
