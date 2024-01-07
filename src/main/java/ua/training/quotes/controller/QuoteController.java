package ua.training.quotes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.training.quotes.model.Lang;
import ua.training.quotes.model.Quote;
import ua.training.quotes.persistence.QuoteResource;
import ua.training.quotes.service.XmlQuoteParserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

@RestController
@RequestMapping("quote")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteResource quoteResource;
    private final XmlQuoteParserService parserService;

    @Value("${quotes.random.amount}")
    private int randomAmount;

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
    public void newQuote(@RequestBody Quote quote) {
        quoteResource.addQuote(quote);
    }

    @PostMapping("upload")
    public void upload(@RequestPart MultipartFile file) {
        Set<Quote> quotes = parserService.parse(file);
        quoteResource.addQuotes(quotes);
    }

    @GetMapping(value = "template.xml", produces = "application/octet-stream")
    public byte[] getTemplate() {
        try(InputStream is = new ClassPathResource("quotes_template.xml").getInputStream()) {
            return is.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
