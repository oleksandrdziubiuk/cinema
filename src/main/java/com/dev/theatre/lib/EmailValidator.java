package com.dev.theatre.lib;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private static final String VALID_EMAIL = "(\\w+@[a-z]+\\.[a-z]+)";

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && (email.length() > 5) && email.matches(VALID_EMAIL);
    }
}
