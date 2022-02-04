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

@Data
class SendMessageDTO {
    private val senderId: @NotNull Long? = null
    private val receiverId: @NotNull Long? = null
    private val body: @NotEmpty String? = null
}