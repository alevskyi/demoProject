package ua.training.quotes.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;
import ua.training.quotes.security.SecurityUtil;

import java.util.Set;

@Validated
@RequiredArgsConstructor
public class DatabaseQuoteResource implements QuoteResource {

    private final QuoteRepository repository;
    @Override
    public Set<Quote> getRandomQuotes(Integer amount) {
        return repository.getRandomQuotes(amount);
    }

    @Override
    public Quote getQuoteById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No quote with id " + id + " found"));
    }

    @Override
    public Set<Quote> getQuotesInLang(Lang lang) {
        return repository.findAllByLang(lang);
    }

    @Override
    public Set<Quote> getUserQuotes(String username) {
        return repository.findAllByUser(username);
    }

    @Override
    public void addQuote(Quote quote) {
        addUsername(quote);
        repository.save(quote);
    }

    @Override
    public void addQuotes(Set<Quote> quotes) {
        quotes.forEach(this::addUsername);
        repository.saveAll(quotes);
    }

    private void addUsername(Quote q) {
        q.setUser(SecurityUtil.getCurrentUserName());
    }
}
