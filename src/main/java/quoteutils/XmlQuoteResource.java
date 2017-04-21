package quoteutils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import quoteutils.templateutils.XmlQuote;
import web.quote.QuoteResource;

public class XmlQuoteResource implements QuoteResource{
	
	protected static HashSet<Quote> quotes = null;
	
	public XmlQuoteResource(){
		quotes = Unmarshall.read();
	}
	
	//For testing
	public XmlQuoteResource(HashSet<Quote> quotes){
		XmlQuoteResource.quotes = quotes;
	}
	
	//Index must be in range
	private Quote getQuoteByIndex(HashSet<Quote> set, int index){
		Iterator<Quote> iter = set.iterator();
		int i = 0;
		//loop until reach index
		while(i < index){
			iter.next();
			i += 1;
		}
		return iter.next();
	}

	private void updateQuotes(){
		Marshall.write();
		quotes = Unmarshall.read();
	}
	
	private HashSet<Quote> getRandomFromSet(HashSet<Quote> set, int amount){
		Random rnd = new Random();
		HashSet<Quote> quotes = new HashSet<Quote>();
		
		for(int i = 0; i < amount; i++){
			boolean result = false;
			do{
				int index = rnd.nextInt(set.size());
				
				result = quotes.add(getQuoteByIndex(set, index));
			}while(!result);
		}
		
		return quotes;
	}
	
	@Override
	public HashSet<Quote> getRandomQuotes(int amount) {
		
		return getRandomFromSet(quotes, amount);
	}
			
	@Override
	public Quote getQuote(int id) {
	
		Iterator<Quote> i = quotes.iterator();
		Quote quote = null;
		while(i.hasNext()){
			quote = i.next();
			if(quote.getId() == id) return quote;
		}
		throw new IllegalArgumentException("No quote with id " + id + " found");
	}

	@Override
	public HashSet<Quote> getQuotesInLang(Lang lang, int amount) {
		
		HashSet<Quote> quotesInLang = new HashSet<Quote>();
		Iterator<Quote> i = quotes.iterator();
		while(i.hasNext()){
			Quote q = i.next();
			if(q.getLang() == lang) quotesInLang.add(q);
		}
		
		return getRandomFromSet(quotesInLang, amount);
	}
	
	@Override
	public void addQuote(String text, String person, Lang lang, String username) {
		
		Quote newQuote = new Quote();
		newQuote.setText(text);
		newQuote.setPerson(person);
		newQuote.setLang(lang);
		newQuote.setUser(username);
		
		//Get max Id from quotes
		int maxId = 1;
		Iterator<Quote> i = quotes.iterator();
		Quote q;
		while(i.hasNext()){
			q = i.next();
			
			if(q.equals(newQuote)) 
				throw new IllegalArgumentException("Quote already exists");
			int currentId = q.getId();
			if(currentId > maxId) maxId = currentId;
		}
		newQuote.setId(maxId+1);
		quotes.add(newQuote);
		updateQuotes();
	}

	@Override
	public HashSet<Quote> getUserQuotes(String username) {
		HashSet<Quote> quotes = new HashSet<Quote>();
		Iterator<Quote> i = XmlQuoteResource.quotes.iterator();
		Quote q;
		while(i.hasNext()){
			q = i.next();
			if(q.getUser().equals(username)) quotes.add(q);
		}
		return quotes;
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
