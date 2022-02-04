package com.okavaa.kotlin_spring_starter.services

import com.okavaa.kotlin_spring_starter.models.User
import com.okavaa.kotlin_spring_starter.models.enums.ERole
import com.okavaa.kotlin_spring_starter.repositories.IUserRepository
import com.okavaa.kotlin_spring_starter.utils.Utility
import com.okavaa.kotlin_spring_starter.utils.dtos.ChangePasswordDTO
import com.okavaa.kotlin_spring_starter.utils.dtos.RegisterDTO
import com.okavaa.kotlin_spring_starter.utils.exceptions.BadRequestException
import com.okavaa.kotlin_spring_starter.utils.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress

@Service
class UserServiceImpl @Autowired constructor(userRepository: IUserRepository) : IUserService {
    private val userRepository: IUserRepository

    @Value("\${uploads.storage.profile_pics}")
    private val profilePicsDirectory: String? = null

    @Value("\${uploads.extensions}")
    private val extensions: String? = null

    init {
        this.userRepository = userRepository
    }

    override fun all(pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }

    override fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow { ResourceNotFoundException("User", "id", id.toString()) }
    }

    override fun byRole(role: ERole, pageable: Pageable): Page<User> {
        return userRepository.findByRole(role, pageable)
    }

    @Transactional
    override fun create(dto: RegisterDTO): User {
        var user = User()
        val password: String = Utility.encode(dto.password)
        if (dto.emailOrPhone?.let { isValidEmailAddress(it) } == true) user.email = dto.emailOrPhone
        else user.phoneNumber = dto.emailOrPhone

        user.role = ERole.USER
        user.fullNames = dto.fullNames
        user.password = password

        println(Utility.matches(dto.password, password))

        if (!isUnique(user)) throw BadRequestException("The provided emailOrPhone is already used in the app")
        user = userRepository.save(user)
        return user
    }

    override fun isUnique(user: User): Boolean {
        val userOptional: Optional<User>? = if (user.email != null) userRepository.findByEmail(user.email!!)
        else user.phoneNumber?.let { userRepository.findByPhoneNumber(it) }

        return if (userOptional != null) {
            !userOptional.isPresent
        } else {
            true
        }
    }

    override val loggedInUser: User
        get() {
            if (SecurityContextHolder.getContext().authentication.principal === "anonymousUser") throw BadRequestException(
                "You are not logged in, try to log in"
            )
            val email: String
            val principal: Any = SecurityContextHolder.getContext().authentication.principal
            email = if (principal is UserDetails) {
                principal.username
            } else {
                principal.toString()
            }
            return userRepository.findByEmail(email).orElseThrow { ResourceNotFoundException("User", "email", email) }
        }

    override fun changePassword(user: User, dto: ChangePasswordDTO) {
//        if (!Utility.matches(
//                dto.getCurrentPassword(),
//                user.getPassword()
//            )
//        ) throw BadRequestException("Invalid current password")
        user.password = dto.newPassword
        userRepository.save(user)
    }

    override fun save(user: User): User {
        return userRepository.save(user)
    }

    private fun isValidEmailAddress(email: String): Boolean {
        var result = true
        try {
            val emailAddress = InternetAddress(email)
            emailAddress.validate()
        } catch (ex: AddressException) {
            result = false
        }
        return result
    }
}