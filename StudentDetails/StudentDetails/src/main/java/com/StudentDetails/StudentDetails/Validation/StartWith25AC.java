package com.StudentDetails.StudentDetails.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented   // Let Spring Know that we are creating annotation
@Constraint(validatedBy = StartWith25ACValidator.class)  // By which class is it validated
@Target({ElementType.FIELD}) // for which the annotation is eligible
@Retention(RetentionPolicy.RUNTIME) // when should annotation run
public @interface StartWith25AC {
    String message () default "student id must start with 25Ac";
    Class<?> [] groups() default {};// Array that stores different type of classs object
    Class<? extends Payload>[] payload() default {};
}
