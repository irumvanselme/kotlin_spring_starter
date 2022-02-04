package com.okavaa.kotlin_spring_starter.utils.dtos

import javax.validation.constraints.NotEmpty

class LoginDTO {
    val login: @NotEmpty String? = null
    val password: @NotEmpty String? = null

    override fun toString(): String {
        return "LoginDTO(login=$login, password=$password)"
    }
}