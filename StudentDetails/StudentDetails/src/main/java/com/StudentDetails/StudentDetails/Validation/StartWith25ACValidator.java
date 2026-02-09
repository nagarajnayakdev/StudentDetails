package com.StudentDetails.StudentDetails.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartWith25ACValidator implements ConstraintValidator<StartWith25AC,String> {
    @Override
    public boolean isValid(String value,ConstraintValidatorContext context){
        if(value == null || value.isEmpty()){
        return true;
    }
        return value.startsWith("25AC");
    }
}
