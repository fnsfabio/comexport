package com.br.fns.comexport.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class DateValidator implements ConstraintValidator<NotDateNull, Date> {
    @Override
    public void initialize(NotDateNull constraintAnnotation) {}

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        return (date != null);
    }
}
