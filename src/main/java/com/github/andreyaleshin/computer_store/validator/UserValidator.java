package com.github.andreyaleshin.computer_store.validator;

import com.github.andreyaleshin.computer_store.entity.User;
import com.github.andreyaleshin.computer_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    public static final int MINIMUM_USERNAME_LENGTH = 3;
    public static final int MINIMUM_PASSWORD_LENGTH = 3;
    public static final int MAXIMUM_USERNAME_LENGTH = 20;
    public static final int MAXIMUM_PASSWORD_LENGTH = 32;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "notEmpty.userForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "notEmpty.userForm.username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "notEmpty.userForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "notEmpty.userForm.passwordConfirm");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "notEmpty.userForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "notEmpty.userForm.lastName");

        if (user.getUsername().length() < MINIMUM_USERNAME_LENGTH ||
                user.getUsername().length() > MAXIMUM_USERNAME_LENGTH) {
            errors.rejectValue("username", "size.userForm.username");
        }

        if (userService.findUserByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "duplicate.userForm.username");
        }

        if (userService.findUserByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "duplicate.userForm.email");
        }

        if (user.getPassword().length() < MINIMUM_PASSWORD_LENGTH ||
                user.getPassword().length() > MAXIMUM_PASSWORD_LENGTH) {
            errors.rejectValue("password", "size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "match.userForm.passwordConfirm");
        }

    }

}
