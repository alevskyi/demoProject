package ua.training.quotes.persistence.quote;

import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;

import java.util.Set;

public interface QuoteResource {

    Set<Quote> getRandomQuotes(int amount);

    Quote getQuoteById(int id) throws IllegalArgumentException;

    Set<Quote> getQuotesInLang(Lang lang);

    Set<Quote> getUserQuotes(String username);

    void addQuote(String text, String person, Lang lang, String username) throws IllegalArgumentException;

    void addQuotes(Set<Quote> quotes, String username);

}
