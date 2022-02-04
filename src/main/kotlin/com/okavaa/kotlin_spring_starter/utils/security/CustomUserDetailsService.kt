package com.okavaa.kotlin_spring_starter.utils.security

import com.okavaa.kotlin_spring_starter.models.User
import com.okavaa.kotlin_spring_starter.repositories.IUserRepository
import com.okavaa.kotlin_spring_starter.utils.exceptions.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CustomUserDetailsService @Autowired constructor(userRepository: IUserRepository) : UserDetailsService {
    private val userRepository: IUserRepository

    init {
        this.userRepository = userRepository
    }

    @Transactional
    fun loadByUserId(id: Long): UserDetails {
        val user: User =
            userRepository.findById(id).orElseThrow { UsernameNotFoundException("User not found with id: $id") }
        return UserPrincipal.create(user)
    }

    @Transactional
    @Throws(BadRequestException::class)
    override fun loadUserByUsername(s: String): UserDetails {
        val user: User = userRepository.findByEmailOrPhoneNumber(s, s)
            .orElseThrow { UsernameNotFoundException("user not found with email or mobile of $s") }
        return UserPrincipal.create(user)
    }
}