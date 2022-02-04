package com.okavaa.kotlin_spring_starter.utils.dtos

import com.okavaa.kotlin_spring_starter.utils.security.ValidPassword
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

class RegisterDTO {
    val fullNames: @NotEmpty String? = null
    val emailOrPhone: @Pattern(
        regexp = "^(?:\\d{10}|\\w+@\\w+\\.\\w{2,3})$",
        message = "It must be a valid email or a valid phone number"
    ) String? = null

    @ValidPassword
    val password: @NotEmpty String? = null
}