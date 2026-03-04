package com.electronic.store.customAnnotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageValidationLogic implements ConstraintValidator<ImageValidCustomAnnotation, String> {

    private Logger logger = LoggerFactory.getLogger(ImageValidationLogic.class);

    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        logger.info("Image name : {}",s);
       if(s.isBlank())
       {
           return false;
       }
       else {
           return true;
       }
    }
}
