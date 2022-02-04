package com.okavaa.kotlin_spring_starter.controllers

import com.okavaa.kotlin_spring_starter.models.Todo
import com.okavaa.kotlin_spring_starter.services.ITodoService
import com.okavaa.kotlin_spring_starter.utils.payload.ApiResponse
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/todos")
class TodoController( @Autowired var iTodoService: ITodoService) {

    @GetMapping
    fun all(): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok(ApiResponse.success(iTodoService.all()))
    }

    @PostMapping
    fun create(@Valid @RequestBody todo: Todo): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok(ApiResponse.success(iTodoService.create(todo)));
    }
}