package ua.training.quotes.controller;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest {
    @Pattern(regexp = "[a-z0-9]{5,10}")
    private String username;
    private String password;
}
