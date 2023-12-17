package ua.training.quotes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.training.quotes.controller.RegisterRequest;

import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordConstraint, RegisterRequest> {
    private static final String PATTERN = "[a-z0-9]{6,}";

    @Override
    public boolean isValid(RegisterRequest value, ConstraintValidatorContext context) {
        boolean valid = true;
        boolean matchesPattern = Pattern.compile(PATTERN).matcher(value.getPassword()).find();
        if (!matchesPattern) {
            context.buildConstraintViolationWithTemplate("{ua.training.quotes.validation.PasswordConstraint.message.notMatch}")
                    .addPropertyNode("password").addConstraintViolation();
            valid = false;
        }
        if (!value.getPassword().equals(value.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("{ua.training.quotes.validation.PasswordConstraint.message.notEqual}")
                    .addPropertyNode("confirmPassword").addConstraintViolation();
            valid = false;
        }
        return valid;
    }
}
