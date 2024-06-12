package com.br.bird_service.validation.annotation;

import com.br.bird_service.validation.CpfValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CpfValidator.class)
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface Cpf {

    String message() default
            "Cpf inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
