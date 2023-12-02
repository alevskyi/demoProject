//package ua.training.quotes.persistence.quote;
//
//import java.util.HashSet;
//import java.util.Iterator;
//
//import ua.training.quotes.model.Lang;
//import ua.training.quotes.model.QuoteList;
//import ua.training.quotes.model.XmlQuote;
//
//
//public class QuoteResourceAdapter implements QuoteResource{
//
//	private QuoteRepository repository;
//
//	public QuoteResourceAdapter(QuoteRepository repository){
//		this.repository = repository;
//	}
//
//	public QuoteList getQuote(int id){
//		QuoteList quote = repository.findById(id).get();
//		if(quote == null)
//			throw new IllegalArgumentException("No quote with id " + id + " found");
//		else
//			return repository.findById(id).get();
//	}
//
//
//	public HashSet<QuoteList> getRandomQuotes(int amount){
//		return repository.getRandomQuotes(amount);
//	}
//
//
//	public HashSet<QuoteList> getQuotesInLang(Lang lang, int amount){
//		return repository.getQuotesInLang(lang.value(), amount);
//	}
//
//
//	public HashSet<QuoteList> getUserQuotes(String username){
//		return repository.findByUser(username);
//	}
//
//
//	public void addQuote(String text, String person, Lang lang, String username){
//		QuoteList q = new QuoteList(text, person, lang, username);
//		if(repository.findByText(text) != null)
//			throw new IllegalArgumentException("QuoteList already exists");
//		repository.save(q);
//	}
//
//	public void addQuotes(HashSet<XmlQuote> quotes, String username){
//		Iterator<XmlQuote> i = quotes.iterator();
//
//		XmlQuote q;
//		while(i.hasNext()){
//			QuoteList quote = new QuoteList();
//			quote.setUser(username);
//
//			q = i.next();
//			quote.setLang(q.getLang());
//			quote.setPerson(q.getPerson());
//			quote.setText(q.getText());
//			repository.save(quote);
//			System.out.println("Saved: " + quote.getText());
//		}
//	}
//
//}
