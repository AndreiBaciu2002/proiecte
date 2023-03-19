package com.example.tematrei.domain.validators;

import com.example.tematrei.domain.User;

public class UserValidator implements Validator<User> {

    public UserValidator() {
    }

    @Override
    public void validate(User entity) throws ValidationException {
        String message = "";
        if (entity.getFirstName().length() == 0) {
            message += "First name can't be an empty string!\n";
        }
        if (entity.getLastName().length() == 0) {
            message += "Last name can't be an empty string!\n";
        }
        if (entity.getPasswordHash().length() == 0) {
            message += "Password can't be an empty string!\n";
        }
        if (message.length() > 0) {
            throw new ValidationException(message);
        }
    }
}
