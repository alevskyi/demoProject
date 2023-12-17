package ua.training.quotes.controller;

import lombok.Data;

@Data
public class LoginRequest {
    //    Lowercase only
    private String username;
    private String password;

}
