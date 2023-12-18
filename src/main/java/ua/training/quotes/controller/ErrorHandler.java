package ua.training.quotes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<Map<String, String>> validationErrors(BindException e) {
		Map<String, String> errors = new HashMap<>();
		//One error per field
		e.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> loginError(AuthenticationException e) {
		return ResponseEntity.badRequest().body("Incorrect username or password");
	}
}
