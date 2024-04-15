package com.exam1.vallidation.validateUserLoginDTO;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = UserLoginValidator.class)
public @interface UserLoginValidate {
    String message() default "Wrong username/password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
