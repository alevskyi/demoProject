package ua.training.quotes.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;

import java.util.Set;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {

    @Query(value = "SELECT * FROM Quote ORDER BY RAND() LIMIT :amount", nativeQuery = true)
    Set<Quote> getRandomQuotes(Integer amount);

    Set<Quote> findAllByLang(Lang lang);

    Set<Quote> findAllByUser(String user);
}
