package com.okavaa.kotlin_spring_starter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2


@EnableSwagger2
@SpringBootApplication
class KotlinSpringStarterApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringStarterApplication>(*args)
}
