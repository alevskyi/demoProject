package ua.training.quotes.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.extras.springsecurity6.util.SpringSecurityContextUtils;
import ua.training.quotes.model.Quote;
import ua.training.quotes.model.User;
import ua.training.quotes.persistence.quote.QuoteResource;
import ua.training.quotes.persistence.user.UserResource;
import ua.training.quotes.security.UserRegistration;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class UserController{

	private final QuoteResource quotes;
	private final UserResource users;

	@RequestMapping(value="/userlist", method=RequestMethod.GET)
	public String userList(Model model){
		model.addAttribute("users", users.getAllUsers());
		return "__userlist";
	}
	
//	@PreAuthorize("#login == authentication.name")
	@RequestMapping(value="/users/{login:[a-zA-Z0-9_]{5,20}}", method=RequestMethod.GET)
	public String profile(@PathVariable String login, Model model){
		
		model.addAttribute("xmlQuote", new Quote());
		model.addAttribute("quotes", quotes.getUserQuotes(login));
		return "profile";
	}

	@RequestMapping(value="/validation/user", method=RequestMethod.POST)
	public Map<String, String> userValidation(@Valid UserRegistration userRegistration, Errors errors){
		HashMap<String, String> map = new HashMap<>();

		if(errors.hasFieldErrors("passwd")) map.put("passwd", errors.getFieldError("passwd").getDefaultMessage());

		if(errors.hasFieldErrors("username")){
			map.put("username", errors.getFieldError("username").getDefaultMessage());
		}
		else{
			try{
//				userData.getUser(userRegistration.getUsername());

				map.put("username", "User with username '" + userRegistration.getUsername() +
						"' already exists");
			}
			catch (NoSuchElementException e) {

			}
		}
//		if(!userRegistration.isPasswordsMatch()) map.put("confirmPasswd", "Passwords don't match.");
		return map;
	}
	
//
//	@RequestMapping(value="/register", method=RequestMethod.GET)
//	public String register(@Valid UserRegistration userRegistration, Errors errors,
//		@AuthenticationPrincipal org.springframework.security.core.userdetails.User authenticatedUser){
//
//		//Don't show register form to logged in users
//		if(authenticatedUser != null) return "redirect:/users/" + authenticatedUser.getUsername();
//		return "register";
//	}
	
	
//	@RequestMapping(value="/register", method=RequestMethod.POST)
//	public String processRegistration(@ModelAttribute @Valid UserRegistration userRegistration, Errors errors){
		
//		if(!userRegistration.isPasswordsMatch()) errors.rejectValue("passwordsMatch", null, "Passwords don't match");
//		if(errors.hasErrors()) return "register";
//
//		try{
//			User u = new User(userRegistration.getUsername(), userRegistration.getPasswd(),
//					userRegistration.nonLocked(), userRegistration.passwordNonExpired(), userRegistration.getAuthorities());
//			users.addUser(u);
//			return "redirect:/users/" + userRegistration.getUsername();
//		}
//		catch(IllegalArgumentException e){
//			errors.rejectValue("username", null, e.getMessage());
//			return "register";
//		}
//	}
//
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public String login(
//			@RequestParam(value = "error", required = false) String error, @AuthenticationPrincipal User user){
//
//		//don't show form to logged in users
//		if(user != null) return "redirect:/users/" + user.getUsername();
//		if(error != null){
//			model.addAttribute("error", "Incorrect username or password. Please, try again.");
//		}
//		return "login";
//	}
//

//	@RequestMapping(value="/logout", method=RequestMethod.GET)
//	public String logout(HttpServletRequest request) throws ServletException {
//		request.logout();
//		return "redirect:/";
//	}

}
