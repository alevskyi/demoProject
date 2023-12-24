package ua.training.quotes.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import ua.training.quotes.persistence.LangConverter;

@Entity
@Data
public class Quote {
    @Id
    @GeneratedValue
    private Integer id;
    @Pattern(regexp="[а-яА-Яa-zA-Z0-9 :;,\\.\\-\"'\\!\\?\\s]{10,}", message="{ua.training.quotes.validation.Pattern.quote.text.notMatch.message}")
    private String text;
    @Convert(converter=LangConverter.class)
    @NotNull(message="{ua.training.quotes.validation.NotNull.quote.lang.message}")
    private Lang lang;
    @Pattern(regexp="[а-яА-Яa-zA-Z 0-9\\-']{3,}", message="{ua.training.quotes.validation.Pattern.quote.person.notMatch.message}")
    private String person;
    private String user;
}
