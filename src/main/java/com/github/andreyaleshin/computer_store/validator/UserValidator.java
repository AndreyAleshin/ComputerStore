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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "userForm.notEmpty.username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "userForm.notEmpty.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "userForm.notEmpty.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "userForm.notEmpty.passwordConfirm");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "userForm.notEmpty.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "userForm.notEmpty.lastName");

        if (user.getUsername().length() < MINIMUM_USERNAME_LENGTH ||
                user.getUsername().length() > MAXIMUM_USERNAME_LENGTH) {
            errors.rejectValue("username", "userForm.size.username");
        }

        if (userService.findUserByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "userForm.duplicate.username");
        }

        if (userService.findUserByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "userForm.duplicate.email");
        }

        if (user.getPassword().length() < MINIMUM_PASSWORD_LENGTH ||
                user.getPassword().length() > MAXIMUM_PASSWORD_LENGTH) {
            errors.rejectValue("password", "userForm.size.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "userForm.match.passwordConfirm");
        }

    }

}
