package com.okavaa.kotlin_spring_starter.utils.payload

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class JwtAuthenticationResponse(private val accessToken: String?) {
    private val tokenType = "Bearer"
}