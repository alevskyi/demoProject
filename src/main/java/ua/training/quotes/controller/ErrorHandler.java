package ua.training.quotes.controller;

import jakarta.validation.ConstraintViolationException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> validation(ConstraintViolationException e) {
		Map<String, String> errors = new HashMap<>();
		//One error per field
		e.getConstraintViolations().forEach(err -> errors.put(((PathImpl) err.getPropertyPath()).getLeafNode().getName(), err.getMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> loginError(AuthenticationException e) {
		return ResponseEntity.badRequest().body("Incorrect username or password");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> uncaughtException(Exception e) {
		e.printStackTrace();
		return ResponseEntity.badRequest().body("Unknown error, check logs");
	}

}
