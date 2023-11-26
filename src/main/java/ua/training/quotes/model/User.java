package ua.training.quotes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
    @GeneratedValue
    private Integer id;
	@Pattern(regexp="[a-zA-Z0-9_]{5,20}", message="Username must be 5 to 20 characters long, " + 
			"only digits, letters(Latin only) and underscore permitted.")
    private String username;
    @Pattern(regexp="[0-9a-zA-Z]{5,20}", message="Password must be 5 to 20 characters(Latin only) long, " + 
			"only digits and letters permitted.")
    private String passwd;
    private Boolean nonLocked;
    private Boolean passwordNonExpired;
    private String authorities;
}
