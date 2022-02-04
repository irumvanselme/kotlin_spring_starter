package com.okavaa.kotlin_spring_starter.controllers

import com.okavaa.kotlin_spring_starter.models.User
import com.okavaa.kotlin_spring_starter.repositories.IUserRepository
import com.okavaa.kotlin_spring_starter.services.IUserService
import com.okavaa.kotlin_spring_starter.utils.dtos.LoginDTO
import com.okavaa.kotlin_spring_starter.utils.payload.ApiResponse
import com.okavaa.kotlin_spring_starter.utils.payload.JwtAuthenticationResponse
import com.okavaa.kotlin_spring_starter.utils.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AuthenticationFilter
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController @Autowired constructor(
    userService: IUserService, authenticationManager: AuthenticationManager, jwtTokenProvider: JwtTokenProvider
) {
    private val userService: IUserService
    private val authenticationManager: AuthenticationManager
    private val jwtTokenProvider: JwtTokenProvider

    init {
        this.userService = userService
        this.authenticationManager = authenticationManager
        this.jwtTokenProvider = jwtTokenProvider
    }

    @PostMapping("/login")
    fun login(@RequestBody dto: @Valid LoginDTO): ResponseEntity<String> {
        println(dto.login)
        var jwt: String? = null
        val authentication: Authentication =
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(dto.login, dto.password))
        try {
            SecurityContextHolder.getContext().authentication = authentication
            jwt = jwtTokenProvider.generateToken(authentication)
        } catch (e: Exception) {
            println(e.message)
        }
        return ResponseEntity.accepted().body(jwt)
    }

    @GetMapping("/profile")
    fun profile(): ResponseEntity<ApiResponse> {
        val profile: User = userService.loggedInUser
        return ResponseEntity.ok(ApiResponse.success(profile))
    }
}