package com.okavaa.kotlin_spring_starter.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class AppController {

    @GetMapping
    fun app(): String {
        return "Hello"
    }
}