package com.bank.account.custom.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target( { ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AccountTypeValidation.class)
public @interface AccountTypeConstraintValidation {

	 String message() default "Account type should be saving or current type only.";

	  Class<?>[] groups() default {};

	  Class<? extends Payload>[] payload() default {};
}
