package com.okavaa.kotlin_spring_starter.utils.dtos

import lombok.Data
import javax.validation.constraints.NotEmpty

@Data
class LoginDTO {
    val login: @NotEmpty String? = null
    val password: @NotEmpty String? = null

    override fun toString(): String {
        return "LoginDTO(login=$login, password=$password)"
    }
}