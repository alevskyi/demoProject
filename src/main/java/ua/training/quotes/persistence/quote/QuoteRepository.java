//package ua.training.quotes.persistence.quote;
//
//import java.util.HashSet;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//
//import ua.training.quotes.model.QuoteList;
//
//@Repository
//public interface QuoteRepository extends CrudRepository<QuoteList, Integer>{
//
//	public HashSet<QuoteList> findByUser(String user);
//
//	public QuoteList findByText(String text);
//
//	@Query(value = "SELECT * FROM QuoteList WHERE lang=?1 ORDER BY RAND() LIMIT ?2", nativeQuery=true)
//	public HashSet<QuoteList> getQuotesInLang(String lang, int amount);
//
//	@Query(value = "SELECT * FROM QuoteList ORDER BY RAND() LIMIT ?1", nativeQuery=true)
//	public HashSet<QuoteList> getRandomQuotes(int amount);
//}
