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
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Data
class UpdateProfileDTO {
    val fullNames: @NotEmpty String? = null
    val email: @Email String? = null
    val phoneNumber: @NotEmpty String? = null
    val nationalId: @NotEmpty @Size(min = 16, max = 16) String? = null
    val location: String? = null
}