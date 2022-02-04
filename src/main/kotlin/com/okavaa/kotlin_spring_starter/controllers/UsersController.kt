package com.okavaa.kotlin_spring_starter.controllers

import com.okavaa.kotlin_spring_starter.models.User
import com.okavaa.kotlin_spring_starter.models.enums.ERole
import com.okavaa.kotlin_spring_starter.services.IUserService
import com.okavaa.kotlin_spring_starter.utils.dtos.ChangePasswordDTO
import com.okavaa.kotlin_spring_starter.utils.dtos.RegisterDTO
import com.okavaa.kotlin_spring_starter.utils.dtos.UpdateProfileDTO
import com.okavaa.kotlin_spring_starter.utils.payload.ApiResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class UsersController(userService: IUserService) {
    private val userService: IUserService

    init {
        this.userService = userService
    }

    @GetMapping
    fun all(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "limit", defaultValue = "1") limit: Int
    ): ResponseEntity<Page<User>> {
        val pageable: Pageable = Pageable.ofSize(10)
        return ResponseEntity.ok(userService.all(pageable))
    }

    @PostMapping
    fun create(@RequestBody dto: @Valid RegisterDTO): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok(ApiResponse.success(userService.create(dto)))
    }

    @GetMapping("/{id}")
    fun byId(@PathVariable id: Long): ResponseEntity<User> {
        return ResponseEntity.ok(userService.findById(id))
    }

    @GetMapping("/by-role/{role}")
    fun byRole(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "limit", defaultValue = "1") limit: Int,
        @PathVariable role: ERole
    ): ResponseEntity<ApiResponse> {
        val pageable: Pageable = Pageable.ofSize(10)
        return ResponseEntity.ok(ApiResponse.success(userService.byRole(role, pageable)))
    }

    @PutMapping("/{id}/change-profile-pic")
    fun changeProfilePic(
        @PathVariable id: Long, @RequestParam("file") document: MultipartFile
    ): ResponseEntity<ApiResponse> {
        val user: User = userService.findById(id)
        return ResponseEntity.ok(ApiResponse.success(user))
    }

    @PutMapping("/{id}/update-profile")
    fun updateProfile(
        @PathVariable id: Long, @RequestBody dto: @Valid UpdateProfileDTO
    ): ResponseEntity<ApiResponse> {
        val user: User = userService.findById(id)
        return ResponseEntity.ok(ApiResponse.success(user))
    }

    @PutMapping("/change-password")
    fun changePassword(@RequestBody dto: @Valid ChangePasswordDTO): ResponseEntity<ApiResponse> {
        userService.changePassword(userService.loggedInUser, dto)
        return ResponseEntity.ok(ApiResponse.success("done"))
    }
}