//package ua.training.quotes.persistence.quote;
//
//import java.util.HashSet;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import ua.training.quotes.model.Quote;
//
//@Repository
//public interface QuoteRepository extends CrudRepository<Quote, Integer>{
//
//	public HashSet<Quote> findByUser(String user);
//
//	public Quote findByText(String text);
//
//	@Query(value = "SELECT * FROM Quote WHERE lang=?1 ORDER BY RAND() LIMIT ?2", nativeQuery=true)
//	public HashSet<Quote> getQuotesInLang(String lang, int amount);
//
//	@Query(value = "SELECT * FROM Quote ORDER BY RAND() LIMIT ?1", nativeQuery=true)
//	public HashSet<Quote> getRandomQuotes(int amount);
//}
