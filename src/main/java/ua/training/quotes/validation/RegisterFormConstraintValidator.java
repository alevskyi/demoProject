package ua.training.quotes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.provisioning.UserDetailsManager;
import ua.training.quotes.controller.RegisterRequest;

@RequiredArgsConstructor
public class RegisterFormConstraintValidator implements ConstraintValidator<RegisterFormConstraint, RegisterRequest> {
    private final UserDetailsManager userDetailsManager;

    @Override
    public boolean isValid(RegisterRequest value, ConstraintValidatorContext context) {
        boolean valid = true;
        if (userDetailsManager.userExists(value.getUsername())) {
            rejectField("username", "ua.training.quotes.validation.RegisterFormConstraint.username.exists.message", context);
            valid = false;
        }
        if (! value.getPassword().equals(value.getConfirmPassword())) {
            rejectField("confirmPassword", "ua.training.quotes.validation.RegisterFormConstraint.confirmPassword.notEqual.message", context);
            valid = false;
        }
        return valid;
    }

    private void rejectField(String fieldName, String message, ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate("{" + message + "}")
                .addPropertyNode(fieldName).addConstraintViolation();
    }
}
