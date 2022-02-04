package com.okavaa.kotlin_spring_starter.utils

import com.okavaa.kotlin_spring_starter.utils.payload.ApiResponse
import org.springframework.http.ResponseEntity

object Formatter {
    fun done(): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok(ApiResponse.success("done"))
    }

    fun ok(data: Any): ResponseEntity<ApiResponse> {
        return ResponseEntity.ok(ApiResponse.success(data))
    }
}