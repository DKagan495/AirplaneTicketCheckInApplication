package by.kagan.businesslayer.validator;

import by.kagan.businesslayer.dto.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequest request = (UserRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Password cannot be empty or blank");

        String password = request.getPassword();

        log.info(password + " -- this is your incorrect password!");

        if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            errors.rejectValue("password", "Password must contains at least: one upper-case symbol, one lower-case symbol, one number and one special symbol. Password length cannot be less than 8 symbols");
        }
    }
}
