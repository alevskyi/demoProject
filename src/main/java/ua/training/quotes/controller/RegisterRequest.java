package ua.training.quotes.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.training.quotes.validation.PasswordConstraint;

@EqualsAndHashCode(callSuper = true)
@Data
@PasswordConstraint
public class RegisterRequest extends LoginRequest {
    private String confirmPassword;
}
