package com.okavaa.kotlin_spring_starter.utils.payload

import org.springframework.http.HttpStatus

data class ApiResponse(var status: HttpStatus, var success: Boolean, var message: String?, var payload: Any?) {

    companion object {
        fun success(message: String): ApiResponse {
            return ApiResponse(HttpStatus.OK, true, message, null)
        }

        fun success(data: Any): ApiResponse {
            return ApiResponse(HttpStatus.OK, true, null, data)
        }

        fun badRequest(message: String?, data: Any): ApiResponse {
            return ApiResponse(HttpStatus.BAD_REQUEST, false, message, data)
        }
    }
}