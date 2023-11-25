package web.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import quoteutils.Quote;
import web.quote.LangShourtcut;
import web.quote.QuoteResource;
import web.user.UserRegistration;
import web.user.Users;

@RestController
@RequestMapping("/api")
public class ServiceController {
	
	@Autowired
	private Users userData;
	
	@Autowired
	private QuoteResource quoteResource;
			
	@RequestMapping(value="/validation/user", method=RequestMethod.POST)
	public Map<String, String> userValidation(@Valid UserRegistration userRegistration, Errors errors){
		HashMap<String, String> map = new HashMap<>();
		
		if(errors.hasFieldErrors("passwd")) map.put("passwd", errors.getFieldError("passwd").getDefaultMessage());
		
		if(errors.hasFieldErrors("username")){
			map.put("username", errors.getFieldError("username").getDefaultMessage());
		}
		else{
			try{
				userData.getUser(userRegistration.getUsername());
				
				map.put("username", "User with username '" + userRegistration.getUsername() + 
						"' already exists");
			}
			catch (NoSuchElementException e) {
				
			}
		}
		if(!userRegistration.isPasswordsMatch()) map.put("confirmPasswd", "Passwords don't match.");			
		return map;
	}
	
	@RequestMapping(value="/quote/random", method=RequestMethod.GET)
	public Map<String, String> random(){
		HashMap<String, String> map = new HashMap<>();
		Iterator<Quote> i = quoteResource.getRandomQuotes(1).iterator();
		while(i.hasNext()){
			Quote quote = i.next();
			map.put("text", quote.getText());
			map.put("person", quote.getPerson());
		}
		return map;
	}
	
	@RequestMapping(value="/quote/{ls:en|ru}", method=RequestMethod.GET)
	public Map<String, String> randomInLang(@PathVariable LangShourtcut ls){
		HashMap<String, String> map = new HashMap<>();
		Iterator<Quote> i = quoteResource.getQuotesInLang(ls.value(), 1).iterator();
		while(i.hasNext()){
			Quote quote = i.next();
			map.put("text", quote.getText());
			map.put("person", quote.getPerson());
		}
		return map;
	}
}
