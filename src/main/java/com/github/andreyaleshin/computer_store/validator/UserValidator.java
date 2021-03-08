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

//    private final EmailValidator emailValidator;
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User userForm = (User) o;

        String messageEmpty = "This field is required";

        // todo email and name regex validation
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", null, messageEmpty);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", null, messageEmpty);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null, messageEmpty);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", null, messageEmpty);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", null, messageEmpty);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", null, messageEmpty);

        if (userForm.getUsername().length() < 3 || userForm.getUsername().length() > 20) {
            errors.rejectValue(
                    "username",
                    null,
                    "The username must contain from 3 to 20 characters"
            );
        }

        // todo не должен пропускать User, если в БД уже есть ник userForm
        if (userService.findByUsername(userForm.getUsername()).isPresent()) {
            errors.rejectValue(
                    "username",
                    null,
                    "This username is already taken"
            );
        }

        if (userForm.getPassword().length() < 3 || userForm.getPassword().length() > 32) {
            errors.rejectValue(
                    "password",
                    null,
                    "The password must be at least 3 and not greater then 32 characters"
            );
        }

        if (!userForm.getPasswordConfirm().equals(userForm.getPassword())) {
            errors.rejectValue(
                    "passwordConfirm",
                    null,
                    "The passwords don't match"
            );
        }

    }

}
