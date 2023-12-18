package ua.training.quotes.controller;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest {
    @Pattern(regexp = "[a-z0-9]{5,10}", message = "{ua.training.quotes.validation.RegisterFormConstraint.username.invalid.message}")
    private String username;
    @Pattern(regexp = "[a-z0-9]{6,}", message = "{ua.training.quotes.validation.RegisterFormConstraint.password.invalid.message}")
    private String password;
}
