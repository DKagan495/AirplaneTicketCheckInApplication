package by.kagan.businesslayer.validator;

import by.kagan.businesslayer.validator.annotaion.Match;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//Spring interface Validator
public class MatchValidator implements ConstraintValidator<Match, Object> {

    String field;
    String fieldMatch;

    @Override
    public void initialize(Match constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

        if(fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        }
            return fieldMatchValue == null;
    }




}
