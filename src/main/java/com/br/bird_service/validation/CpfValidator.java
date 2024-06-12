package com.br.bird_service.validation;

import com.br.bird_service.utils.SocialNumberUtils;
import com.br.bird_service.validation.annotation.Cpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

import java.util.Objects;

@SupportedValidationTarget({ValidationTarget.ANNOTATED_ELEMENT})
public class CpfValidator implements ConstraintValidator<Cpf, String> {

    @Override
    public boolean isValid(String cpfValue, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(cpfValue))
            return true;

        return SocialNumberUtils.cpfValidate(cpfValue);
    }



}
