package com.okavaa.kotlin_spring_starter.utils.security

import com.google.common.base.Joiner
import org.passay.*
import java.util.*
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class PasswordConstraintValidator : ConstraintValidator<ValidPassword?, String?> {

    override fun isValid(password: String?, context: ConstraintValidatorContext): Boolean {
        val validator = PasswordValidator(
            listOf(
                LengthRule(8, 30),
                AlphabeticalSequenceRule(3, false),
                NumericalSequenceRule(3, false),
                UppercaseCharacterRule(1),
                SpecialCharacterRule(1),
                DigitCharacterRule(1),
                QwertySequenceRule(3, false),
                WhitespaceRule()
            )
        )
        val result: RuleResult = validator.validate(PasswordData(password))

        if (result.isValid) {
            return true
        }
        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(Joiner.on(",").join(validator.getMessages(result)))
            .addConstraintViolation()
        return false
    }
}