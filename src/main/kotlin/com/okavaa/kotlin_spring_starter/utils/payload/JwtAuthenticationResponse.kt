package com.okavaa.kotlin_spring_starter.utils.payload

class JwtAuthenticationResponse(private val accessToken: String?) {
    private val tokenType = "Bearer"
}