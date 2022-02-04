package com.okavaa.kotlin_spring_starter.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.okavaa.kotlin_spring_starter.audits.Model
import com.okavaa.kotlin_spring_starter.models.enums.ERole
import javax.persistence.*

@Entity
@Table(name = "users")
class User : Model() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(name = "full_names")
    var fullNames: String? = null

    @Column(name = "email", unique = true)
    var email: String? = null

    @Column(name = "phone_number", unique = true)
    var phoneNumber: String? = null

    @Column(name = "national_id", unique = true)
    val nationalId: String? = null

    @Column(name = "profile_image")
    val profileImage: String? = null

    @Column(name = "location")
    val location: String? = null

    @JsonIgnore
    @Column(name = "password")
    var password: String? = null

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: ERole? = null
}