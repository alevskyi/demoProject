package web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

import quoteutils.templateutils.InvalidFileContentException;

@ControllerAdvice
public class ErrorHandler {
	
	public static String makeReadable(String s){
		
		StringBuilder msg = new StringBuilder();
		
		for(String word : s.toLowerCase().replace("_", " ").split(" ")){
			msg.append(word.replace(word.charAt(0), Character.toUpperCase(word.charAt(0))));
			msg.append(" ");
		}
		return msg.toString().trim();
	}

	
	@ExceptionHandler(HttpClientErrorException.class)
	public ModelAndView process(HttpClientErrorException e){
			
		ModelAndView mview = new ModelAndView();
	    mview.addObject("status", e.getStatusCode());
	    mview.addObject("error", makeReadable(e.getStatusText()));
	    mview.setViewName("error");
	    return mview; 
	}
	
	@ExceptionHandler(InvalidFileContentException.class)
	public ModelAndView handleUploadedFile(InvalidFileContentException e){
		
		ModelAndView mview = new ModelAndView();
	    //using HTTP error template, status code is empty string 
		mview.addObject("status", "");
	    mview.addObject("error", e.getMessage());
	    mview.setViewName("error");
	    return mview; 
	}
		
}
