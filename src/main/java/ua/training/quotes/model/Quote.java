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

    @Pattern(regexp="[а-яА-Яa-zA-Z0-9 :;,\\.\\-\"'\\!\\?\\s]{10,}", message="QuoteList must be 10 and more characters long. Only letters(Latin and Cyrillic), digits, period, colon, semicolon, comma, "
			+ "double quote, question mark, exclamation mark and dash allowed in quote text.")
    private String text = "";


    @Convert(converter=LangConverter.class)
    @NotNull(message="Language is required.")
    private Lang lang;
    
    @Pattern(regexp="[а-яА-Яa-zA-Z 0-9\\-']{3,}", message="Person name must be 3 and more characters long. Only letters(Latin and Cyrillic), digits, "
    			+ "dash and single quote allowed in person field.")
    private String person;
    
    @NotNull(message="User is required.")
    private String user;

}
