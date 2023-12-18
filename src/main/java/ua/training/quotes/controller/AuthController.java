package ua.training.quotes.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private final RememberMeServices rememberMeServices;
    private final UserDetailsManager userDetailsManager;

    @GetMapping
    public String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        securityContextRepository.saveContext(new SecurityContextImpl(authenticationResponse), request, response);
        rememberMeServices.loginSuccess(request, response, authenticationResponse);
        return loginRequest.getUsername();
    }

    @PostMapping("register")
    public void register(@Valid @RequestBody RegisterRequest registerRequest) {
        userDetailsManager.createUser(new User(registerRequest.getUsername(), registerRequest.getPassword(), Collections.emptyList()));
    }
}
