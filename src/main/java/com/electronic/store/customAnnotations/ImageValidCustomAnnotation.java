package com.electronic.store.customAnnotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageValidationLogic.class)
public @interface ImageValidCustomAnnotation {
//    Message that will get displayed when validations fails
    String message() default "Image name is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
