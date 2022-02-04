package com.okavaa.kotlin_spring_starter.utils.dtos

import com.okavaa.kotlin_spring_starter.utils.security.ValidPassword
import lombok.Data
import javax.validation.constraints.NotEmpty

@Data
class ChangePasswordDTO {
    val currentPassword: @NotEmpty String? = null

    @ValidPassword
    val newPassword: String? = null
}