package ua.training.quotes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<Map<String, String>> process(BindException e){
		Map<String, String> errors = e.getFieldErrors().stream().collect(Collectors.toMap(err -> err.getField(), err -> err.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}
}
