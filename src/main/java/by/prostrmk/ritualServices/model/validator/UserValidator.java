package by.prostrmk.ritualServices.model.validator;

import by.prostrmk.ritualServices.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "label.validate.usernameEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"mobilePhone", "label.validate.mobileEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"message", "label.validate.messageEmpty");

    }
}
