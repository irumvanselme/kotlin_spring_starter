package com.okavaa.kotlin_spring_starter.services

import com.okavaa.kotlin_spring_starter.models.User
import com.okavaa.kotlin_spring_starter.models.enums.ERole
import com.okavaa.kotlin_spring_starter.utils.dtos.ChangePasswordDTO
import com.okavaa.kotlin_spring_starter.utils.dtos.RegisterDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IUserService {
    fun all(pageable: Pageable): Page<User>
    fun findById(id: Long): User
    fun byRole(role: ERole, pageable: Pageable): Page<User>
    fun create(dto: RegisterDTO): User
    fun isUnique(user: User): Boolean
    val loggedInUser: User
    fun changePassword(user: User, dto: ChangePasswordDTO)
    fun save(user: User): User
}