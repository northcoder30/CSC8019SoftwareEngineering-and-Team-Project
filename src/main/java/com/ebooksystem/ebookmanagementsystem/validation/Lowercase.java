package com.ebooksystem.ebookmanagementsystem.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Lowercase validation annotation. Used to ensure the emails passed to he backend are in lowercase.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LowercaseValidator.class)
public @interface Lowercase {
    String message() default "Must lower case letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
