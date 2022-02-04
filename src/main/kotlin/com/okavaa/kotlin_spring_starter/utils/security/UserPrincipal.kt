package com.okavaa.kotlin_spring_starter.utils.security

import com.okavaa.kotlin_spring_starter.models.User
import com.okavaa.kotlin_spring_starter.models.enums.ERole

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(
    var id: Long?,
    var fullNames: String?,
    private var email: String?,
    var nationalId: String?,
    var phoneNumber: String?,
    var location: String?,
    var profileImage: String?,
    private var _password: String?,
    var role: ERole?,
    private var _authorities: List<GrantedAuthority>
) : UserDetails {


    companion object {
        fun create(user: User): UserPrincipal {
            val authorities: List<GrantedAuthority> = listOf<GrantedAuthority>(SimpleGrantedAuthority(user.role.toString()))
            return UserPrincipal(
                user.id,
                user.fullNames,
                user.email,
                user.nationalId,
                user.phoneNumber,
                user.location,
                user.profileImage,
                user.password,
                user.role,
                authorities
            )
        }
    }

    override fun getAuthorities(): List<GrantedAuthority> {
        return _authorities
    }

    override fun getPassword(): String {
        return _password.toString()
    }

    override fun getUsername(): String? {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}