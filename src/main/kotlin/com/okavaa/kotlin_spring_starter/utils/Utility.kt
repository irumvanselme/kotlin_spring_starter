package com.okavaa.kotlin_spring_starter.utils

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

object Utility {
    private val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
    fun from(page: Int, limit: Int): Pageable {
        return PageRequest.of(page, limit, Sort.Direction.ASC, "id")
    }

    fun encode(password: String?): String {
        return passwordEncoder.encode(password)
    }

    fun matches(raw: String?, encoded: String?): Boolean {
        return passwordEncoder.matches(raw, encoded)
    }
}