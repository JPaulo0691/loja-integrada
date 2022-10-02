package com.backend.lojaintegrada.customAnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.backend.lojaintegrada.customValidation.NotEmptyListValidator;

@Retention(RetentionPolicy.RUNTIME) //Verify in Runtime Execution
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {
	
	String message() default "A lista não pode ser vazia";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	/*
	  -> Quando criar uma anotation customizada, sempre lembrar de colocar esses dois últimos
	métodos. Eles estão presentes em toda anotation de validação.
	*/
}