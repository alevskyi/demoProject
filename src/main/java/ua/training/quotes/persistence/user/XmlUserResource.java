package ua.training.quotes.persistence.user;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.training.quotes.model.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class XmlUserResource implements UserResource {

    private final ObjectMapper objectMapper;
    private Set<User> users;

    @PostConstruct
    public void init() {
        try {
            this.users = objectMapper.readValue(this.getClass().getResourceAsStream("user.json"), new TypeReference<HashSet<User>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUser(String username) {
        return getAllUsers().stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("User with username " + username + " not found."));
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
