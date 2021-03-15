package com.github.andreyaleshin.computer_store.validator;

import com.github.andreyaleshin.computer_store.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    public static final int MINIMUM_PRODUCT_NAME_LENGTH = 3;
    public static final int MAXIMUM_PRODUCT_NAME_LENGTH = 24;

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "notEmpty.productForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "notEmpty.productForm.description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "image", "notEmpty.productForm.image");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "notEmpty.productForm.price");

        if (product.getName().length() < MINIMUM_PRODUCT_NAME_LENGTH ||
                product.getName().length() > MAXIMUM_PRODUCT_NAME_LENGTH) {
            errors.rejectValue("name", "length.productForm.name");
        }
    }

}
