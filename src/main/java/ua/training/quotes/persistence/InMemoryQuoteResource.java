package ua.training.quotes.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;
import ua.training.quotes.security.SecurityUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Validated
@RequiredArgsConstructor
public class InMemoryQuoteResource implements QuoteResource {

    private final ObjectMapper objectMapper;
    private Set<Quote> quotes;

    @PostConstruct
    public void init() {
        try {
            this.quotes = objectMapper.readValue(this.getClass().getClassLoader().getResourceAsStream("data.json"), new TypeReference<HashSet<Quote>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Quote> getRandomQuotes(Integer amount) {
        List<Quote> list = new ArrayList<>(quotes);
        Collections.shuffle(list);
        return list.stream().limit(amount).collect(Collectors.toSet());
    }

    @Override
    public Quote getQuoteById(Integer id) {
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
    public Set<Quote> getUserQuotes(String username) {
        return quotes.stream()
                .filter(q -> q.getUser().equals(username))
                .collect(Collectors.toSet());
    }

    @Override
    public void addQuote(Quote quote) {
        addIdAndUserName(quote);
        quotes.add(quote);
    }

    @Override
    public void addQuotes(Set<Quote> quotes) {
        this.quotes.forEach(this::addQuote);
    }

    private void addIdAndUserName(Quote q) {
        int maxId = quotes.stream().map(Quote::getId).flatMapToInt(id -> IntStream.of(id)).max().orElse(0);
        q.setId(maxId + 1);
        q.setUser(SecurityUtil.getCurrentUserName());
    }
}
