package com.okavaa.kotlin_spring_starter.utils.dtos

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

class UpdateProfileDTO {
    val fullNames: @NotEmpty String? = null
    val email: @Email String? = null
    val phoneNumber: @NotEmpty String? = null
    val nationalId: @NotEmpty @Size(min = 16, max = 16) String? = null
    val location: String? = null
}