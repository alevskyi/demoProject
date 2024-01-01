package ua.training.quotes.persistence;

import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;

import java.util.Set;

public interface QuoteResource {

    Set<Quote> getRandomQuotes(Integer amount);

    Quote getQuoteById(int id);

    Set<Quote> getQuotesInLang(Lang lang);

    Set<Quote> getUserQuotes(String username);

    void addQuote(Quote quote);

    void addQuotes(Set<Quote> quotes);

}
