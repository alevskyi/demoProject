package ua.training.quotes.persistence.quote;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
@RequiredArgsConstructor
public class XmlQuoteResource implements QuoteResource {

    private final ObjectMapper objectMapper;
    private Set<Quote> quotes;

    @PostConstruct
    public void init() {
        try {
            this.quotes = objectMapper.readValue(this.getClass().getResourceAsStream("quote.json"), new TypeReference<HashSet<Quote>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Quote> getRandomQuotes(int amount) {
        List<Quote> list = new ArrayList<>(quotes);
        Collections.shuffle(list);
        return list.stream().limit(amount).collect(Collectors.toSet());
    }

    @Override
    public Quote getQuoteById(int id) {
        return quotes.stream()
                .filter(q -> q.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No quote with id " + id + " found"));
    }

    @Override
    public Set<Quote> getQuotesInLang(Lang lang) {
        return quotes.stream()
                .filter(q -> q.getLang().equals(lang))
                .collect(Collectors.toSet());
    }

    @Override
    public void addQuote(String text, String person, Lang lang, String username) {
        int maxId = quotes.stream().map(Quote::getId).flatMapToInt(id -> IntStream.of(id)).max().orElse(0);
        Quote quote = new Quote();
        quote.setId(maxId + 1);
        quote.setText(text);
        quote.setPerson(person);
        quote.setLang(lang);
        quote.setUser(username);

        quotes.add(quote);
    }

    @Override
    public Set<Quote> getUserQuotes(String username) {
        return quotes.stream()
                .filter(q -> q.getUser().equals(username))
                .collect(Collectors.toSet());
    }

    @Override
    public void addQuotes(Set<Quote> quotes, String username) {
//TODO username included in quote
        this.quotes.addAll(quotes);
    }

}
