package com.okavaa.kotlin_spring_starter.repositories

import com.okavaa.kotlin_spring_starter.models.User
import com.okavaa.kotlin_spring_starter.models.enums.ERole
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IUserRepository : JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean

    fun existsByNationalId(email: String): Boolean

    fun existsByPhoneNumber(email: String): Boolean

    fun findByRole(role: ERole, pageable: Pageable): Page<User>

    fun findByEmailOrPhoneNumberOrNationalId(email: String, phoneNumber: String, nationalId: String): Optional<User>;

    fun findByEmailOrPhoneNumber(email: String, phoneNumber: String): Optional<User>

    fun findByEmail(email: String): Optional<User>

    fun findByPhoneNumber(phoneNumber: String): Optional<User>
}