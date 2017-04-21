package web.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import quoteutils.templateutils.XmlQuote;
import userutils.User;
import web.quote.QuoteResource;

@Controller
public class UserController{
	
	@Autowired
	private QuoteResource quotes;
	
	private Users users;
	
	@Autowired
	public UserController(Users users){
		this.users = users;
	}
	
	
	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	public String userList(Model model){
		model.addAttribute("users", users.getAllUsers());
		return "__userlist";
	}
	
	@PreAuthorize("#login == authentication.name")
	@RequestMapping(value="/users/{login:[a-zA-Z0-9_]{5,20}}", method=RequestMethod.GET)
	public String profile(@PathVariable String login, Model model){
		
		model.addAttribute("xmlQuote", new XmlQuote());
		model.addAttribute("quotes", quotes.getUserQuotes(login));
		return "profile";
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(@Valid UserRegistration userRegistration, Errors errors,
		@AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticatedUser){
		
		//Don't show register form to logged in users 
		if(authenticatedUser != null) return "redirect:/users/" + authenticatedUser.getUsername(); 
		return "register";
	}
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistration(@ModelAttribute @Valid UserRegistration userRegistration, Errors errors){
		
		if(!userRegistration.isPasswordsMatch()) errors.rejectValue("passwordsMatch", null, "Passwords don't match");		
		if(errors.hasErrors()) return "register";
		
		try{
			User u = new User(userRegistration.getUsername(), userRegistration.getPasswd(),
					userRegistration.nonLocked(), userRegistration.passwordNonExpired(), userRegistration.getAuthorities());
			users.addUser(u);
			return "redirect:/users/" + userRegistration.getUsername(); 
		}
		catch(IllegalArgumentException e){
			errors.rejectValue("username", null, e.getMessage());
			return "register";
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(
		@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
		@RequestParam(value = "error", required = false) String error, Model model){
		
		//don't show form to logged in users
		if(user != null) return "redirect:/users/" + user.getUsername();
		if(error != null){
			model.addAttribute("error", "Incorrect username or password. Please, try again.");
		}
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:/";
	}

}
