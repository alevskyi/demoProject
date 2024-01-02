package ua.training.quotes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Quote {
    @Id
    @GeneratedValue
    private Integer id;
    @Pattern(regexp="[а-яіїєА-ЯІЇЄa-zA-Z0-9 :;,\\.\\-\"'\\!\\?\\s]{10,}", message="{ua.training.quotes.validation.Pattern.quote.text.notMatch.message}")
    private String text;
    @Enumerated(EnumType.STRING)
    @NotNull(message="{ua.training.quotes.validation.NotNull.quote.lang.message}")
    private Lang lang;
    @Pattern(regexp="[а-яіїєА-ЯІЇЄa-zA-Z 0-9\\-']{3,}", message="{ua.training.quotes.validation.Pattern.quote.person.notMatch.message}")
    private String person;
    private String user;
}
