package com.example.invoice.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ProductValidator.class)
@Documented
public @interface IProductValidatior {
    String message() default "Tên phải chứa abc";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
