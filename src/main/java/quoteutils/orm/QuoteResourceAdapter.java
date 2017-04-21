package quoteutils.orm;

import java.util.HashSet;
import java.util.Iterator;

import quoteutils.Lang;
import quoteutils.Quote;
import quoteutils.templateutils.XmlQuote;
import web.quote.QuoteResource;


public class QuoteResourceAdapter implements QuoteResource{
	
	private QuoteRepository repository;
		
	public QuoteResourceAdapter(QuoteRepository repository){
		this.repository = repository;
	}
		
	public Quote getQuote(int id){
		Quote quote = repository.findOne(id);
		if(quote == null)
			throw new IllegalArgumentException("No quote with id " + id + " found");
		else
			return repository.findOne(id);
	}
		
		
	public HashSet<Quote> getRandomQuotes(int amount){
		return repository.getRandomQuotes(amount);
	}
				

	public HashSet<Quote> getQuotesInLang(Lang lang, int amount){
		return repository.getQuotesInLang(lang.value(), amount);
	}
		
	
	public HashSet<Quote> getUserQuotes(String username){
		return repository.findByUser(username);
	}
	
	
	public void addQuote(String text, String person, Lang lang, String username){
		Quote q = new Quote(text, person, lang, username);
		if(repository.findByText(text) != null) 
			throw new IllegalArgumentException("Quote already exists");
		repository.save(q);
	}
	
	public void addQuotes(HashSet<XmlQuote> quotes, String username){
		Iterator<XmlQuote> i = quotes.iterator();
				
		XmlQuote q;
		while(i.hasNext()){
			Quote quote = new Quote();
			quote.setUser(username);
			
			q = i.next();
			quote.setLang(q.getLang());
			quote.setPerson(q.getPerson());
			quote.setText(q.getText());
			repository.save(quote);
			System.out.println("Saved: " + quote.getText());
		}
	}
		
}
