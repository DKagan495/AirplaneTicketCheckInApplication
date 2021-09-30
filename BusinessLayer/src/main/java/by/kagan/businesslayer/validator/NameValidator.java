package by.kagan.businesslayer.validator;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.AuthRequest;
import by.kagan.businesslayer.dto.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class NameValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequest userRequest = (UserRequest) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName is empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName is empty");
        String firstName = userRequest.getFirstName();
        String lastName = userRequest.getLastName();
        log.info(firstName + "fuckup");
        if(firstName.charAt(0) != firstName.toUpperCase().charAt(0)){
            errors.rejectValue("firstName", "The first letter of the firstName should be in upper case");
        }
        if(!firstName.substring(1).equals(firstName.substring(1).toLowerCase())){
            errors.rejectValue("firstName", "All letters in name except the first should be in lower case");
        }

        if(lastName.charAt(0) != lastName.toUpperCase().charAt(0)){
            errors.rejectValue("lastName", "The first letter of the firstName should be in upper case");
        }
        if(!lastName.substring(1).equals(lastName.substring(1).toLowerCase())){
            errors.rejectValue("lastName", "All letters in name except the first should be in lower case");
        }

    }
}