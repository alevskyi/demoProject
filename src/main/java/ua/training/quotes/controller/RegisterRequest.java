package ua.training.quotes.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterRequest extends LoginRequest {
    private String confirmPassword;
}
