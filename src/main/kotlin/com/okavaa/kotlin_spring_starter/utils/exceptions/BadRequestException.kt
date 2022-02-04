package com.okavaa.kotlin_spring_starter.utils.exceptions

import lombok.AllArgsConstructor
import org.springframework.web.bind.annotation.ControllerAdvice
import java.lang.RuntimeException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.validation.FieldError
import org.springframework.http.HttpStatus
import lombok.NoArgsConstructor
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException : RuntimeException {
    constructor(message: String?) : super(message) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
}