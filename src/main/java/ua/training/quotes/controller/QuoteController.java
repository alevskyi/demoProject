package ua.training.quotes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;
import ua.training.quotes.persistence.quote.QuoteResource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("quote")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteResource quoteResource;

    @Value("${quotes.random.amount}")
    private int randomAmount;
    @Value("${quotes.lang.amount}")
    private int langAmount;

    @GetMapping("random")
    public Set<Quote> random() {
        return quoteResource.getRandomQuotes(randomAmount);
    }

    @GetMapping("{id}")
    public Set<Quote> byId(@PathVariable Integer id) {
       return Collections.singleton(quoteResource.getQuoteById(id));
    }

    @GetMapping("user/{username}")
    public Set<Quote> byUsername(@PathVariable String username) {
       return quoteResource.getUserQuotes(username);
    }

    @GetMapping("lang/{lang}")
    public Set<Quote> byLanguage(@PathVariable Lang lang) {
        return quoteResource.getQuotesInLang(lang);
    }

    @PostMapping
    public void newQuote(@Valid Quote quote) {
        String username = "testUser";
        quoteResource.addQuote(quote.getText(), quote.getPerson(),
                quote.getLang(), username);
    }

    @PostMapping("upload")
    public void upload(@RequestPart MultipartFile file) {
        String username = "testUser";
        Set<Quote> quotes = new HashSet<>();
        quoteResource.addQuotes(quotes, username);
    }
}
