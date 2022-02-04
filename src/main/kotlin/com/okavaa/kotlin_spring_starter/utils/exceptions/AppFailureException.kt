package com.okavaa.kotlin_spring_starter.utils.exceptions

import com.okavaa.kotlin_spring_starter.utils.payload.ApiResponse
import org.hibernate.exception.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*

@ControllerAdvice
class AppFailureException {
    @ExceptionHandler(RuntimeException::class)
    fun handleAnyError(exception: RuntimeException): ResponseEntity<ApiResponse> {
        return ResponseEntity.badRequest().body(ApiResponse.badRequest(exception.message, exception.stackTrace));
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidations(exception: MethodArgumentNotValidException): ResponseEntity<ApiResponse> {
        val error: FieldError = Objects.requireNonNull<FieldError>(exception.fieldError)
        val message: String = error.field + ": " + error.defaultMessage
        return ResponseEntity.badRequest().body(ApiResponse(HttpStatus.BAD_REQUEST, false, message, null))
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleSqlExceptions(exception: ConstraintViolationException): ResponseEntity<ApiResponse> {
        return ResponseEntity.badRequest().body(
            ApiResponse.badRequest(
                exception.message + " - " + exception.sql + " - " + exception.sqlState,
                exception.sqlException
            )
        )
    }
}