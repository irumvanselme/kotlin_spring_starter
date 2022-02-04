package com.okavaa.kotlin_spring_starter.utils.dtos

import lombok.AllArgsConstructor
import lombok.Data
import org.springframework.web.bind.annotation.ControllerAdvice
import java.lang.RuntimeException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.validation.FieldError
import org.springframework.http.HttpStatus
import lombok.NoArgsConstructor
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

class SendMoneyDTO {
    val accountNumber: @NotEmpty String? = null
    val amount: @NotNull @Positive Double? = null
}