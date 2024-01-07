package ua.training.quotes.persistence;

import jakarta.validation.Valid;
import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;

import java.util.Set;

public interface QuoteResource {

    Set<Quote> getRandomQuotes(Integer amount);

    Quote getQuoteById(Integer id);

    Set<Quote> getQuotesInLang(Lang lang);

    Set<Quote> getUserQuotes(String username);

    void addQuote(@Valid Quote quote);

    void addQuotes(@Valid Set<Quote> quotes);

}
