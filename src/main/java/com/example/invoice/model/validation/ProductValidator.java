package com.example.invoice.model.validation;

import com.example.invoice.model.Product;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductValidator implements ConstraintValidator<IProductValidatior, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.contains("abc");
    }
}
