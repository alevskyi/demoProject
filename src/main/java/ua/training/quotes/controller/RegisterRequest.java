package ua.training.quotes.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.training.quotes.validation.RegisterFormConstraint;

@EqualsAndHashCode(callSuper = true)
@Data
@RegisterFormConstraint
public class RegisterRequest extends LoginRequest {
    private String confirmPassword;
}
