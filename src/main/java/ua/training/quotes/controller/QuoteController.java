package ua.training.quotes.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import ua.training.quotes.model.LangShourtcut;
import ua.training.quotes.model.Quote;
import ua.training.quotes.persistence.quote.QuoteResource;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuoteController {
				
	private final QuoteResource quoteResource;
	
	@Value("#{'${app.quotes.random.maxAmount}'}")
	private int randomMax;
	
	@Value("#{'${app.quotes.random.minAmount}'}")
	private int randomMin;
	
	@Value("#{'${app.quotes.quotesInLangMax}'}")
	private int quotesInLangMax;
	
	@Value("#{'${app.quotes.quotesInLangMin}'}")
	private int quotesInLangMin;

	
	@RequestMapping(value="/random", method=RequestMethod.GET)
	public String random(@RequestParam(value="amount", defaultValue="3") int amount,
			Model model){
		if(amount > randomMax || amount < randomMin) 
			return "redirect:random?amount=3";
		else{
			model.addAttribute("randomQuotes", quoteResource.getRandomQuotes(amount));
			return "random";
		}
	}
	
	@RequestMapping(value="/id/{id:[0-9]+}", method=RequestMethod.GET)
	public String byId(@PathVariable int id, Model model){
		try{
			model.addAttribute("quote", quoteResource.getQuoteById(id));
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return "specific";
	}
	
	@RequestMapping(value="/{ls:en|ru}", method=RequestMethod.GET)
	public String quoteInLang(@PathVariable LangShourtcut ls,
		@RequestParam(value="amount", defaultValue="2") int amount, Model model){
		
		if(amount > quotesInLangMax || amount < quotesInLangMin) 
			return "redirect:" + ls + "?amount=2";
		else{
			model.addAttribute("randomQuotes", quoteResource.getQuotesInLang(ls.value()));
			return "random";
		}
	}
	
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String newQuote(@Valid Quote xmlQuote, Errors errors, Model model,
						   @AuthenticationPrincipal User authenticatedUser){
		
		String username = authenticatedUser.getUsername();
		model.addAttribute("quotes", quoteResource.getUserQuotes(username));
		if(errors.hasErrors()) 
			return "profile";
		else{
			try{
				quoteResource.addQuote(xmlQuote.getText(), xmlQuote.getPerson(), 
						xmlQuote.getLang(), username);
			}
			catch (IllegalArgumentException e) {
				errors.rejectValue("text", null, e.getMessage());
				return "profile";
			}
			System.out.println("Quote added, Username " + username);
			return "redirect:/users/" + username;
		}
	}
	
	@RequestMapping(value="/xml", method=RequestMethod.POST)
	public String uploadXml(@RequestPart MultipartFile file, 
			@AuthenticationPrincipal User authenticatedUser) {
				
		String username = authenticatedUser.getUsername();
//		Set<Quote> quotes = Unmarshall.read(file.getInputStream());
		Set<Quote> quotes = new HashSet<>();
		quoteResource.addQuotes(quotes, username);		
		return "redirect:/users/" + username;
	}	
}
