package com.okavaa.kotlin_spring_starter.utils.dtos

import com.okavaa.kotlin_spring_starter.utils.security.ValidPassword
import javax.validation.constraints.NotEmpty

class ChangePasswordDTO {
    val currentPassword: @NotEmpty String? = null

    @ValidPassword
    val newPassword: String? = null
}