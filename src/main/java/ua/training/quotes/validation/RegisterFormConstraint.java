package ua.training.quotes.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = RegisterFormConstraintValidator.class)
public @interface RegisterFormConstraint {
    String message() default "{ua.training.quotes.validation.RegisterFormConstraint.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
