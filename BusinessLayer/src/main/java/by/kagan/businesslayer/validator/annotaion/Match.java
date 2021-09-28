package by.kagan.businesslayer.validator.annotaion;

import by.kagan.businesslayer.validator.MatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MatchValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Match {
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String message() default "Fields doesn`t matches";

    String field();

    String fieldMatch();
}
