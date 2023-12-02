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
    public Quote byId(@PathVariable Integer id) {
       return quoteResource.getQuoteById(id);
    }

    @GetMapping("{lang}")
    public Set<Quote> byLanguage(@PathVariable Lang lang) {
        return quoteResource.getQuotesInLang(lang);
    }

    @PostMapping
    public void newQuote(@Valid Quote quote) {
        String username = "1231231";
        quoteResource.addQuote(quote.getText(), quote.getPerson(),
                quote.getLang(), username);
    }

    @PostMapping("upload")
    public void upload(@RequestPart MultipartFile file) {
        String username = "1231231";
        Set<Quote> quotes = new HashSet<>();
        quoteResource.addQuotes(quotes, username);
    }
}
